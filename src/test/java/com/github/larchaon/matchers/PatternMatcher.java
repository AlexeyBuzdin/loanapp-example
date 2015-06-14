package com.github.larchaon.matchers;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.regex.Pattern;

public class PatternMatcher {

    public static Matcher<CharSequence> matchesPattern(String regex) {
        return new TypeSafeMatcher<CharSequence>() {

            @Override
            protected boolean matchesSafely(CharSequence item) {
                Pattern pattern = Pattern.compile(regex);
                return pattern.matcher(item).matches();
            }

            @Override
            public void describeTo(org.hamcrest.Description description) {
                description.appendText("a string with pattern \"").appendText(regex).appendText("\"");
            }

            @Override
            public void describeMismatchSafely(CharSequence item, org.hamcrest.Description mismatchDescription) {
                mismatchDescription.appendText("was \"").appendText(String.valueOf(item)).appendText("\"");
            }
        };
    }
}
