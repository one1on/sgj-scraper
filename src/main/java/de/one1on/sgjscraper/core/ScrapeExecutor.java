package de.one1on.sgjscraper.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ScrapeExecutor {

    private List<ScrapingTaskLifecycle> tasks = new ArrayList<>();

    public void addTask(ScrapingTaskLifecycle task) {
        tasks.add(task);
    }

    public void execute() {
        final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(this::doExecute, ThreadLocalRandom.current().nextLong(100, 2500), TimeUnit.MINUTES.toMillis(5), TimeUnit.MILLISECONDS);
    }

    private void doExecute() {
        tasks.forEach(ScrapingTaskLifecycle::pre);
        tasks.forEach(ScrapingTaskLifecycle::scrape);
        tasks.forEach(ScrapingTaskLifecycle::process);
        tasks.forEach(ScrapingTaskLifecycle::post);
    }
}
