package de.one1on.sgjscraper.core;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HashTagExtractorTest {

    @Test
    void extractEmpty() {
        assertThat(extractFrom("")).isEqualTo(HashTagExtractor.NONE);
    }

    @Test
    void extractNone() {
        assertThat(extractFrom("Hello World")).isEqualTo(HashTagExtractor.NONE);
    }

    @Test
    void extractSimple() {
        assertThat(extractFrom("<a>#wow</a>")).isEqualTo("wow");
    }

    @Test
    void extractMultiple() {
        assertThat(extractFrom("<a>#wow</a><a>#how</a>")).isEqualTo("wow");
    }

    private String extractFrom(String ... s) {
        List<String> lines = Lists.newArrayList(s);
        return HashTagExtractor.extract(lines);
    }
}