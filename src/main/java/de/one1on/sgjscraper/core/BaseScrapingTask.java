package de.one1on.sgjscraper.core;


import de.one1on.sgjscraper.Main;
import de.one1on.sgjscraper.api.SecretGermanJodelAPI;
import de.one1on.sgjscraper.model.Comment;
import de.one1on.sgjscraper.model.Jodel;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static de.one1on.sgjscraper.util.CompletableFutureCollector.collectResult;


public abstract class BaseScrapingTask implements Runnable, ScrapingTaskLifecycle {
    public static Logger logger = LoggerFactory.getLogger(Main.class);
    protected static Predicate<Jodel> isFemale = (jodel -> jodel.getAuthor().isFemale());
    protected static Predicate<Comment> isFemaleComment = (comment -> comment.getAuthor().isFemale());
    protected static Predicate<Jodel> hasImage = (jodel -> !jodel.getImage().isEmpty());
    protected static Predicate<Comment> hasImageComment = (comment -> !comment.getImage().isEmpty());
    private static Function<Jodel, String> extractHashTag = jodel -> HashTagExtractor.extract(jodel.getResolvedComments().stream().map(Comment::getText).collect(Collectors.toList()));
    @Getter
    private final List<CompletableFuture<File>> downloads = new ArrayList<>();

    protected SecretGermanJodelAPI api;

    public BaseScrapingTask(SecretGermanJodelAPI api) {
        this.api = api;
    }

    public void addDownloads(Collection<? extends CompletableFuture<File>> downloads) {
        this.downloads.addAll(downloads);
    }

    @SafeVarargs
    protected static List<Jodel> filter(List<Jodel> list, Predicate<Jodel>... predicates) {
        return list.stream()
                   .filter(Arrays.stream(predicates).reduce($ -> true, Predicate::and))
                   .collect(Collectors.toList());
    }

    @SafeVarargs
    protected static Map<String, List<Jodel>> filterAndGroup(List<Jodel> list, Predicate<Jodel>... predicates) {
        return filter(list, predicates).stream()
                                       .collect(Collectors.groupingBy(extractHashTag));
    }

    @SafeVarargs
    protected static List<Comment> filterComments(List<Comment> list, Predicate<Comment>... predicates) {
        return list.stream().filter(Arrays.stream(predicates).reduce($ -> true, Predicate::and))
                        .collect(Collectors.toList());
    }

    @Override
    public final void run() {
        scrape();
    }

    @Override
    public void pre() {
        logger.info("Pre scraping..");
    }

    @Override
    public void scrape() {
        logger.info("Scraping..");
    }

    @Override
    public void process() {
        final CompletableFuture<List<File>> downloadStage = downloads.stream().collect(collectResult());
        downloadStage.thenAccept((d) -> logger.info("Completed {} downloads.", d.size())).exceptionally(e -> {
            System.out.println(e.getCause()); // returns a throwable back
            return null;
        });

        System.out.println(downloadStage.join());
    }

    @Override
    public void post() {
        logger.info("Post scraping..");
    }
}
