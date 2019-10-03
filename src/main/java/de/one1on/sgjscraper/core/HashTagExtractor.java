package de.one1on.sgjscraper.core;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HashTagExtractor {

    public static final String NONE = "none";

    public static String extract(List<String> lines) {
        Pattern pattern = Pattern.compile("(?i)<a.*?>#(.+?)</a>");
        return lines.stream().map(pattern::matcher)
                    .filter(Matcher::find)
                    .map(m -> m.group(1))
                    .map(String::toLowerCase)
                    .map(HashTagExtractor::replaceUmlaute)
                    .findFirst()
                    .orElse(NONE);
    }

    private static String replaceUmlaute(String input) {

        //replace all lower Umlauts
        String o_strResult =
            input
                .replaceAll("ü", "ue")
                .replaceAll("ö", "oe")
                .replaceAll("ä", "ae")
                .replaceAll("ß", "ss");

        //first replace all capital umlaute in a non-capitalized context (e.g. Übung)
        o_strResult =
            o_strResult
                .replaceAll("Ü(?=[a-zäöüß ])", "Ue")
                .replaceAll("Ö(?=[a-zäöüß ])", "Oe")
                .replaceAll("Ä(?=[a-zäöüß ])", "Ae");

        //now replace all the other capital umlaute
        o_strResult =
            o_strResult
                .replaceAll("Ü", "UE")
                .replaceAll("Ö", "OE")
                .replaceAll("Ä", "AE");

        return o_strResult;
    }
}
