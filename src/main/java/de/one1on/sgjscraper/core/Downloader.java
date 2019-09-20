package de.one1on.sgjscraper.core;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Downloader {

    private final List<String> hashtags;
    private List<String> urls;

    public Downloader(List<String> hashtags, List<String> urls) {
        this.hashtags = hashtags;
        this.urls = urls;
    }

    public List<CompletableFuture<File>> download() {

        final List<CompletableFuture<File>> collect = urls.stream()
                                                          .filter(s -> !s.isEmpty())
                                                          .map(this::downloadFile)
                                                          .collect(Collectors.toList());
        return collect;
    }

    private CompletableFuture<File> downloadFile(String url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return downloadFileA(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

     public File downloadFileA(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        String fileName = url.getFile();
        String destName = "figure-" + fileName.hashCode() + ".jpg";
        final File imageDir = new File("build/images");
        imageDir.mkdir();

         File file = null;
         if(hashtags.isEmpty()) {
             hashtags.add("#none");
         }

         for (String hashtag : hashtags) {
             final File hashTagDir = new File(imageDir, hashtag);
             hashTagDir.mkdir();
             file = new File(hashTagDir, destName);
             System.out.println(file.getName());

             FileUtils.copyURLToFile(url, file);
         }

        return file;
    }
}
