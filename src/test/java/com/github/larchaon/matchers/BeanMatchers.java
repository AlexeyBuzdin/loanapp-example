package com.github.larchaon.matchers;

import com.github.larchaon.builder4j.GetProperty;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class BeanMatchers {

    public static <BEAN, GETTER_TYPE> Matcher<BEAN> hasProperty(GetProperty<BEAN,GETTER_TYPE> getter, Matcher<GETTER_TYPE> getterMatcher) {
        return new TypeSafeMatcher<BEAN>() {
            @Override
            public boolean matchesSafely(BEAN item) {
                return getterMatcher.matches(getter.accept(item));
            }

            @Override
            public void describeTo(Description description) {
                getterMatcher.describeTo(description);
            }

            @Override
            protected void describeMismatchSafely(BEAN item, Description mismatchDescription) {
                super.describeMismatchSafely(item, mismatchDescription);
                // TODO: Add Getter name to description
            }
        };
    }
}
