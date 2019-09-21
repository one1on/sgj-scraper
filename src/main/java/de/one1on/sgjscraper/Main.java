package de.one1on.sgjscraper;

import de.one1on.sgjscraper.api.SecretGermanJodelAPI;
import de.one1on.sgjscraper.api.SecretGermanJodelAPIImpl;
import de.one1on.sgjscraper.core.NotificationsScrapingTask;
import de.one1on.sgjscraper.core.ScrapeExecutor;
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
        //System.setProperty("org.apache.commons.logging.Log","org.apache.commons.logging.impl.SimpleLog");
        //System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        //System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.wire", "DEBUG");
        new Main().run();
    }

    private void run() {

        final String hashtag_subscribe = dotenv.get("HASHTAG_SUBSCRIBE", "");
        if(!hashtag_subscribe.isEmpty()) {
            api.subscribeHashtag(hashtag_subscribe);
        }

        final ScrapeExecutor scrapeExecutor = new ScrapeExecutor();
        scrapeExecutor.addTask(new ScrapingTask(api));
        scrapeExecutor.addTask(new NotificationsScrapingTask(api));
        scrapeExecutor.execute();
    }
}
