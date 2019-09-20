package de.one1on.sgjscraper.core;

import de.one1on.sgjscraper.api.SecretGermanJodelAPI;
import de.one1on.sgjscraper.model.Comment;
import de.one1on.sgjscraper.model.Jodel;
import de.one1on.sgjscraper.model.Notification;

import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NotificationsScrapingTask extends BaseScrapingTask {

    public NotificationsScrapingTask(SecretGermanJodelAPI api) {
        super(api);
    }

    @Override
    public void scrape() {
        System.out.println("Scraping notification");

        final List<Notification> notifications = api.getNotifications().stream().limit(25).collect(Collectors.toList());

        for (Notification notification : notifications) {
            final List<Jodel> jodel = api.getPost(notification.getJodel().getId());
            final List<Comment> comments = api.getPostComments(jodel.get(0).getId());
            notification.getJodel().setResolvedComments(comments);

            List<String> imageUrls = Stream.concat(Stream.of(jodel.get(0).getImage()), filterComments(notification.getJodel().getResolvedComments(), isFemaleComment, hasImageComment).stream().map(Comment::getImage)).collect(Collectors.toList());
            final List<CompletableFuture<File>> downloads = new Downloader(notification.getHashtags(), imageUrls).download();
            addDownloads(downloads);
        }
    }
}
