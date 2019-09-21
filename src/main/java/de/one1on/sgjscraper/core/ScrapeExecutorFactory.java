package de.one1on.sgjscraper.core;

import java.util.concurrent.TimeUnit;

public final class ScrapeExecutorFactory {

    public final static ScrapeExecutorFactory INSTANCE = new ScrapeExecutorFactory();
    private ScrapeExecutorFactory(){}

    public ScrapeExecutor scrapeExecutor() {
        return new ScrapeExecutor(TimeUnit.MINUTES.toMillis(5));
    }

    public ScrapeExecutor slowScrapeExecutor() {
        return new ScrapeExecutor(TimeUnit.MINUTES.toMillis(50));
    }
}
