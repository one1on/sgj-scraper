package de.one1on.sgjscraper.core;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Downloader {

    public static Logger logger = LoggerFactory.getLogger(Downloader.class);
    private final List<String> hashtags;
    private List<String> urls;
    private Dotenv env = Dotenv.load();
    private File baseDir;

    public Downloader(List<String> hashtags, List<String> urls) {
        this.hashtags = hashtags;
        this.urls = urls;
        initBaseDir();
    }

    private void initBaseDir() {
        final String baseDir = env.get("IMG_LOCATION");
        if(null == baseDir || baseDir.isEmpty()) {
            this.baseDir = new File("build/images");
            logger.warn("Using default image location {}", this.baseDir.getPath());
        } else {
            this.baseDir = new File(baseDir);
            logger.info("Using image location {}", this.baseDir.getPath());
        }

        if(this.baseDir.mkdir()) {
            logger.info("Created directory image location {}", this.baseDir.getPath());
        }
        if(!this.baseDir.canWrite()) {
            throw new IllegalStateException("Can not write to directory " + this.baseDir.getAbsolutePath());
        }
        System.out.println();
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
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

     public File downloadFileA(String imageUrl) throws IOException, URISyntaxException {
        URL url = new URL(imageUrl);
        String fileName = FilenameUtils.getName(url.toURI().getPath());
        String destName = "figure-" + fileName.hashCode() + ".jpg";

         File file = null;
         if(hashtags.isEmpty()) {
             hashtags.add("#none");
         }

         for (String hashtag : hashtags) {
             final File hashTagDir = new File(baseDir, hashtag);
             hashTagDir.mkdir();
             file = new File(hashTagDir, destName);
             logger.info("Image for hashtag {} - {}", hashtag, fileName);
             if(!file.exists()) {
                 logger.info("New: {}", file.getName());
                 FileUtils.copyURLToFile(url, file);
             } else {
                 logger.debug("Already downloaded: {}", file.getName());
             }
         }

        return file;
    }
}
