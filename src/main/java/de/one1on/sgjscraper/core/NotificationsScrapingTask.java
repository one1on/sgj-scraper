package de.one1on.sgjscraper.core;

import de.one1on.sgjscraper.api.SecretGermanJodelAPI;
import de.one1on.sgjscraper.model.Comment;
import de.one1on.sgjscraper.model.Jodel;
import de.one1on.sgjscraper.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.tools.generic.DateTool;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class NotificationsScrapingTask extends BaseScrapingTask {

    public NotificationsScrapingTask(SecretGermanJodelAPI api) {
        super(api);
    }

    @Override
    public void scrape() {

        logger.info("Scraping notifications");

        final List<Notification> notifications = api.getNotifications();

        for (Notification notification : notifications) {
            final List<Jodel> jodelList = filter(api.getPost(notification.getJodel().getId()), isFemale);
            final Optional<Jodel> optionalJodel = jodelList.stream().findFirst();
            if(optionalJodel.isPresent()) {
                final Jodel jodel = optionalJodel.get();
                final List<Comment> comments = api.getPostComments(jodel.getId());
                notification.getJodel().setResolvedComments(comments);

                List<String> imageUrls = Stream.concat(Stream.of(jodel.getImage()), filterComments(notification.getJodel().getResolvedComments(), isFemaleComment, hasImageComment).stream().map(Comment::getImage)).collect(Collectors.toList());
                final List<CompletableFuture<File>> downloads = new Downloader(notification.getHashtags(), imageUrls).download();
                addDownloads(downloads);
            }
        }

        writeNotificationsPage(notifications);
    }

    private void writeNotificationsPage(List<Notification> notifications) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "class");
        ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        ve.init();

        Template t = ve.getTemplate("notifications.vm");
        VelocityContext context = new VelocityContext();
        context.put("date", new DateTool());
        context.put("notifications", notifications);
        /* now render the template into a StringWriter */
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        try {
            FileUtils.write(new File("build/images/notifications.md"), writer.toString(), Charset.defaultCharset());
        } catch (IOException e) {
            log.error("Failed to write log.md", e);
        }
    }
}
