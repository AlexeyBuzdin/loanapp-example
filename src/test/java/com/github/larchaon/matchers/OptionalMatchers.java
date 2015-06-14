package com.github.larchaon.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Optional;

public class OptionalMatchers {
    public static <VAL> Matcher<Optional<VAL>> exists() {
        return new TypeSafeMatcher<Optional<VAL>>() {

            @Override
            protected boolean matchesSafely(Optional<VAL> item) {
                return item.isPresent();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("value should exist in Optional");
            }
        };
    }

    public static <VAL> Matcher<Optional<VAL>> existsAnd(Matcher<VAL> valueMatcher) {
        return new TypeSafeMatcher<Optional<VAL>>() {

            @Override
            protected boolean matchesSafely(Optional<VAL> item) {
                boolean present = item.isPresent();
                return present && valueMatcher.matches(item.get());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("value should exist in Optional and");
            }
        };
    }
}
