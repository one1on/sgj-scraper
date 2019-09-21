package de.one1on.sgjscraper.core;

import java.text.Normalizer;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HashTagExtractor {

    public static final String NONE = "none";
    private static final Function<String, String> normalize = s -> Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

    public static String extract(List<String> lines) {
        Pattern pattern = Pattern.compile("(?i)<a.*?>#(.+?)</a>");
        return lines.stream().map(pattern::matcher)
                    .filter(Matcher::find)
                    .map(m -> m.group(1))
                    .map(String::toLowerCase)
                    .map(normalize)
                    .findFirst()
                    .orElse(NONE);
    }
}
