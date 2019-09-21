package de.one1on.sgjscraper;

import de.one1on.sgjscraper.api.SecretGermanJodelAPI;
import de.one1on.sgjscraper.api.SecretGermanJodelAPIImpl;
import de.one1on.sgjscraper.core.NotificationsScrapingTask;
import de.one1on.sgjscraper.core.ScrapeExecutor;
import de.one1on.sgjscraper.core.ScrapeExecutorFactory;
import de.one1on.sgjscraper.core.ScrapingTask;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {

    private final SecretGermanJodelAPI api;
    private final Dotenv dotenv;

    public Main() {
        dotenv = Dotenv.load();
        final String token = dotenv.get("SGJ_TOKEN", "");
        if(token.isEmpty())
            throw new IllegalStateException("Please set SGJ_TOKEN in .env file. By making a copy of " +
                    ".env.dist to .env and filling the field.");
        this.api = new SecretGermanJodelAPIImpl(token);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {

        final String hashtag_subscribe = dotenv.get("HASHTAG_SUBSCRIBE", "");
        if(!hashtag_subscribe.isEmpty()) {
            api.subscribeHashtag(hashtag_subscribe);
        }

        final ScrapeExecutor scrapeExecutor = ScrapeExecutorFactory.INSTANCE.scrapeExecutor();
        scrapeExecutor.addTask(new ScrapingTask(api));
        scrapeExecutor.execute();

        final ScrapeExecutor slowScrapeExecutor = ScrapeExecutorFactory.INSTANCE.slowScrapeExecutor();
        slowScrapeExecutor.addTask(new NotificationsScrapingTask(api));
        slowScrapeExecutor.execute();
    }
}
