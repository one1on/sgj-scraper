package de.one1on.sgjscraper.api;

import com.google.common.collect.Lists;
import com.google.common.collect.Streams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.one1on.sgjscraper.api.gson.BooleanTypeAdapter;
import de.one1on.sgjscraper.api.gson.DateTypeAdapter;
import de.one1on.sgjscraper.model.Comment;
import de.one1on.sgjscraper.model.Jodel;
import de.one1on.sgjscraper.model.Notification;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecretGermanJodelAPIImpl implements SecretGermanJodelAPI {

    private final API API = new API();
    private final HttpRequestInterceptor delayInterceptor;
    private HttpClient client;
    private final BasicCookieStore cookieStore;
    final Gson gson = new GsonBuilder().registerTypeAdapter(boolean.class, new BooleanTypeAdapter())
                                       .registerTypeAdapter(DateTime.class, new DateTypeAdapter())
                                       .disableHtmlEscaping()
                                       .create();

    public SecretGermanJodelAPIImpl(String token) {
        cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("SGJ_TOKEN", token);
        cookie.setDomain("secretgermanjodel.com");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        delayInterceptor = new DelayRequestInterceptor();
        client = HttpClientBuilder.create()
                                  .disableDefaultUserAgent()
                                  .setDefaultCookieStore(cookieStore)
                                  .addInterceptorFirst(delayInterceptor)
                                  .build();
    }

    @Override
    public List<Jodel> getPosts(long sort, long amout_loaded) {
        try {
            final HttpResponse response = client.execute(API.getPosts(sort, amout_loaded));
            String json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            List<Jodel> list = Stream.of(new JsonParser().parse(json).getAsJsonObject()
                                                              .getAsJsonObject("results")
                                                              .getAsJsonArray("jodels"))
                                          .flatMap(e -> Stream.of(gson.fromJson(e, Jodel[].class)))
                                          .collect(Collectors.toList());
            return list;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Jodel> getPost(long id) {
        try {
            final HttpResponse response = client.execute(API.getPost(id, 0));
            String json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            List<Jodel> list = Stream.of(new JsonParser().parse(json).getAsJsonObject()
                                                           .getAsJsonObject("results")
                                                           .getAsJsonObject("jodel"))
                                       .flatMap(e -> Stream.of(gson.fromJson(e, Jodel.class)))
                                       .collect(Collectors.toList());
            return list;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Comment> getPostComments(long id) {
        try {
            boolean allLoaded = false;
            int lastId = 0;
            List<Comment> list = Lists.newArrayList();
            // Number reloads r. resolved comments = r * 30 comments
            int leftReloads = 3;
            while(!allLoaded && leftReloads >= 0) {
                final HttpResponse response = client.execute(API.getPost(id, lastId));
                final String json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

                final JsonObject results = new JsonParser().parse(json)
                                                           .getAsJsonObject()
                                                           .getAsJsonObject("results");
                allLoaded = this.gson.fromJson(results.get("all_loaded"), boolean.class);
                Stream.of(results.getAsJsonArray("comments"))
                             .flatMap(e -> Stream.of(gson.fromJson(e, Comment[].class)))
                             .forEachOrdered(list::add);
                lastId = Streams.findLast(list.stream()).map(Comment::getId).orElse(0);
                leftReloads--;
            }

            return list;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Notification> getNotifications() {
        try {
            final HttpResponse response = client.execute(API.getNotifications());
            String json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            List<Notification> list = Stream.of(new JsonParser().parse(json).getAsJsonObject()
                                                           .getAsJsonObject("results")
                                                           .getAsJsonArray("notifications"))
                                       .flatMap(e -> Stream.of(gson.fromJson(e, Notification[].class)))
                                       .collect(Collectors.toList());
            return list;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void subscribeHashtag(String hashtag) {
        try {
            final HttpResponse response = client.execute(API.subscribeHashtag(hashtag));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
