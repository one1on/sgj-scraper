package de.one1on.sgjscraper.core;


import com.google.common.collect.Lists;
import de.one1on.sgjscraper.api.SecretGermanJodelAPI;
import de.one1on.sgjscraper.model.Comment;
import de.one1on.sgjscraper.model.Jodel;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScrapingTask extends BaseScrapingTask {

    public ScrapingTask(SecretGermanJodelAPI api) {
        super(api);
    }

    @Override
    public void scrape() {
        List<Jodel> jodelList = api.getPosts(0, 0);

        final List<Jodel> filteredJodels = filter(jodelList, isFemale, hasImage);

        for (Jodel jodel : filteredJodels) {
            final List<Comment> comments = api.getPostComments(jodel.getId());
            jodel.setResolvedComments(comments);
        }

        final Map<String, List<Jodel>> filterAndGrouped = filterAndGroup(filteredJodels);

        for (Map.Entry<String, List<Jodel>> entry : filterAndGrouped.entrySet()) {
            List<String> imageUrls = entry.getValue().stream()
                                          .flatMap(s -> Stream.concat(Stream.of(s.getImage()), filterComments(s.getResolvedComments()).stream().skip(1).map(Comment::getImage)))
                                          .collect(Collectors.toList());
            final List<CompletableFuture<File>> downloads = new Downloader(Lists.newArrayList(entry.getKey()), imageUrls).download();
            addDownloads(downloads);
        }
    }

}
