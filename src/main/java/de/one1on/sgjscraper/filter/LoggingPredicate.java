package de.one1on.sgjscraper.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;

@Data
@Slf4j
class LoggingPredicate<T> implements Predicate<T> {

    private final Predicate<T> predicate;
    private final String name;

    @Override
    public boolean test(T t) {
        final boolean result = predicate.test(t);
        if(!result) {
            log.debug("{}: Filtered out {}" , name, t);
        }
        return result;
    }

}
