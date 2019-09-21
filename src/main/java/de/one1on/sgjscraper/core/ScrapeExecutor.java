package de.one1on.sgjscraper.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ScrapeExecutor {

    public static Logger logger = LoggerFactory.getLogger(Downloader.class);
    private final long period;

    public ScrapeExecutor(long period) {
        this.period = period;
    }

    private List<ScrapingTaskLifecycle> tasks = new ArrayList<>();

    public void addTask(ScrapingTaskLifecycle task) {
        tasks.add(task);
    }

    public void execute() {
        final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(this::doExecute, ThreadLocalRandom.current().nextLong(100, 2500), period, TimeUnit.MILLISECONDS);
    }

    private void doExecute() {
        try {
            tasks.forEach(ScrapingTaskLifecycle::pre);
            tasks.forEach(ScrapingTaskLifecycle::scrape);
            tasks.forEach(ScrapingTaskLifecycle::process);
            tasks.forEach(ScrapingTaskLifecycle::post);
        } catch (RuntimeException e) {
            logger.error("Failure in execution", e);
        }
    }
}
