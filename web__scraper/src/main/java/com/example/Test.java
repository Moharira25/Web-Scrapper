package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {
        // Example strings
        String[] strings = {
                "5 hours",
                "10+ hours",
                "20+ hours",
                "10+ hours",
                "5 hours",
                "20+ hours",
                "10+ hours",
                "10+ hours",
                "10+ hours",
                "10+ hours",
                "5 hours",
                "10+ hours",
                "20+ hours",
                "7 hours",
                "5 hours",
                "10+ hours",
                "20+ hours",
                "10+ hours"
        };

        // Regular expression to match numbers
        Pattern pattern = Pattern.compile("\\d+");

        // Loop through the strings one by one
        for (String str : strings) {
            // Create a matcher for the current string
            Matcher matcher = pattern.matcher(str);

            // Find and print numbers in the current string
            while (matcher.find()) {
                int points = Integer.parseInt(matcher.group());
                System.out.println(points);
            }
        }
    }
}
