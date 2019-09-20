package de.one1on.sgjscraper.api;

import de.one1on.sgjscraper.util.SleepUtils;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class DelayRequestInterceptor implements HttpRequestInterceptor {

    private final Supplier<Long> delaySupplier;

    public DelayRequestInterceptor() {
        delaySupplier = () -> ThreadLocalRandom.current().nextLong(60, 121);
    }

    @Override
    public void process(final HttpRequest request, final HttpContext context) {
        SleepUtils.safeSleep(delaySupplier.get());
    }

}

