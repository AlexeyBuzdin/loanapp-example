package com.github.larchaon.loanapp.util.orika;


import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import org.apache.commons.lang3.StringUtils;

import java.beans.IntrospectionException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Attributes {

    private static final ThreadLocal<Context> contextHolder = new ThreadLocal<Context>();

    static final Map<Class, Object> proxyCache = new ConcurrentHashMap<Class, Object>();

    private Attributes() {}

    private static final Map<Class, Object> defaultReturnValues;

    static {
        Map<Class, Object> values = new HashMap<Class, Object>();
        values.put(String.class, "");
        values.put(BigDecimal.class, BigDecimal.ZERO);
        values.put(Boolean.class, Boolean.FALSE);
        values.put(Boolean.TYPE, Boolean.FALSE);
        values.put(Integer.class, Integer.valueOf(0));
        values.put(Integer.TYPE, Integer.valueOf(0));
        values.put(Long.class, Long.valueOf(0L));
        values.put(Long.TYPE, Long.valueOf(0L));
        values.put(Double.class, Double.valueOf(.0));
        values.put(Double.TYPE, Double.valueOf(.0));
        defaultReturnValues = Collections.unmodifiableMap(values);
    }

    private static class Context {
        private final StringBuilder path = new StringBuilder();
        private String lastName;
        private Class lastClass;

        String path() {
            return path.toString();
        }

        String name() {
            return lastName;
        }

        void append(String name) {
            this.lastName = name;
            if (path.length() > 0) {
                path.append(".");
            }
            path.append(name);
        }

        void setLastClass(Class lastClass) {
            this.lastClass = lastClass;
        }

        Class getLastClass() {
            return lastClass;
        }
    }

    private static class InvocationInterceptor implements MethodHandler {
        private final boolean rootAttribute;

        InvocationInterceptor(boolean rootAttribute) {
            this.rootAttribute = rootAttribute;
        }

        public Object invoke(Object self, Method method, Method proceed, Object[] args) throws IntrospectionException {
            if (rootAttribute) {
                contextHolder.set(new Context());
            } else if (contextHolder.get() == null) {
                throw new IllegalArgumentException("Start attribute traversal from root descriptor!");
            }
            if (isToStringMethod(method)) {
                return contextHolder.get().path();
            }
            final String propertyname = getPropertyName(method);
            contextHolder.get().append(propertyname);
            final Class<?> returnType = method.getReturnType();
            contextHolder.get().setLastClass(returnType);
            if (defaultReturnValues.containsKey(returnType)) {
                return defaultReturnValues.get(returnType);
            }
            if (!returnType.isInterface() && !hasNoArgConstructor(returnType)) {
                return null;
            }
            if (isFinalClass(returnType)) {
                return null;
            }
            return generateProxy(returnType, false);
        }

        private boolean isToStringMethod(Method method) {
            return method.getName().equals("toString") &&
                    method.getReturnType() == String.class &&
                    method.getParameterTypes().length == 0;
        }

        boolean isFinalClass(Class<?> clasz) {
            return (Modifier.FINAL & clasz.getModifiers()) != 0;
        }

        boolean hasNoArgConstructor(Class<?> clasz) {
            for (Constructor constructor : clasz.getDeclaredConstructors()) {
                if (constructor.getParameterTypes().length == 0) {
                    return true;
                }
            }
            return false;
        }

        String getPropertyName(final Method method) throws IntrospectionException {
            String prefix = null;
            if (method.getName().startsWith("get")) {
                prefix = "get";
            } else if (method.getName().startsWith("is") && method.getReturnType() == Boolean.TYPE) {
                prefix = "is";
            }
            if (prefix == null ||
                    method.getParameterTypes().length != 0 ||
                    method.getReturnType() == Void.TYPE) {
                throw new IllegalArgumentException(method.getName() + " is not valid property read method");
            }
            final String methodName = method.getName();
            String name = StringUtils.right(methodName, methodName.length() - prefix.length());
            final String firstChar = StringUtils.left(name, 1).toLowerCase();
            name = firstChar + StringUtils.right(name, name.length() - 1);
            return name;
        }
    }

    /**
     * @param object
     * @return attribute traversal path
     */
    public static String path(Object object) {
        return popContext().path();
    }

    public static Class determineClass(Object object) {
        Context context = contextHolder.get();
        if (context == null) {
            return null;
        }
        return context.getLastClass();
    }

    /**
     * @param object
     * @return last attribute in traversal path
     */
    public static String name(Object object) {
        return popContext().name();
    }

    private static Context popContext() {
        if (contextHolder.get() == null) {
            throw new IllegalStateException("Context is null");
        }
        final Context context = contextHolder.get();
        contextHolder.remove();
        return context;
    }

    /**
     * @param targetClass
     * @return proxy which keeps track of attribute traversal path
     */
    public static <T> T rootAttribute(Class<T> targetClass) {
        return generateProxy(targetClass, true);
    }

    private static <T> T generateProxy(Class<T> targetClass, boolean rootAttribute) {
        if (!rootAttribute) {
            Object proxy = proxyCache.get(targetClass);
            if (proxy != null) {
                return (T) proxy;
            }
        }
        Class<T> proxyClass = generateProxyClass(targetClass);
        T proxy = instantiateProxy(proxyClass);
        ((ProxyObject) proxy).setHandler(new InvocationInterceptor(rootAttribute));

        if (!rootAttribute) {
            proxyCache.put(targetClass, proxy);
        }
        return proxy;
    }

    private static <T> Class<T> generateProxyClass(Class<T> targetClass) {
        ProxyFactory proxyFactory = new ProxyFactory();
        if (targetClass.isInterface()) {
            proxyFactory.setInterfaces(new Class[]{targetClass});
        } else {
            proxyFactory.setSuperclass(targetClass);
        }
        return (Class<T>) proxyFactory.createClass();
    }

    private static <T> T instantiateProxy(Class<T> proxyClass) {
        try {
            return proxyClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);   // RuntimeException used intentionally
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);   // RuntimeException used intentionally
        }
    }
}
