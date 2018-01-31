package org.kodejava.example.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IgnoreCaseDemo {
    public static void main(String[] args) {
        String sentence =
                "The quick brown fox and BROWN tiger jumps " +
                        "over the lazy dog";

        Pattern pattern = Pattern.compile("brown",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sentence);

        while (matcher.find()) {
            System.out.format("Text \"%s\" found at %d to %d.%n",
                    matcher.group(), matcher.start(), matcher.end());
        }
    }
}
