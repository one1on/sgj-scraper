package de.one1on.sgjscraper.api;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;

public class API {
    static final String BASE_API_URI = "https://secretgermanjodel.com/api";

    public API() {
    }

    public HttpUriRequest getPosts(long sort, long amout_loaded) {
        return RequestBuilder.post()
                             .setUri(BASE_API_URI + "/jodels/get")
                             .addParameter("sort", String.valueOf(sort))
                             .addParameter("amount_loaded", String.valueOf(amout_loaded))
                             .setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.toString())
                             .build();
    }

    public HttpUriRequest getPost(long id, long lastId) {
        return RequestBuilder.post()
                             .setUri(BASE_API_URI + "/comments/get")
                             .addParameter("id", String.valueOf(id))
                             .addParameter("direction", String.valueOf(1))
                             .addParameter("last_id", String.valueOf(lastId))
                             .addParameter("comment_id", String.valueOf(0))
                             .addParameter("jodel_needed", String.valueOf(1))
                             .addParameter("notification_id", String.valueOf(0))
                             .addParameter("special", String.valueOf(0))
                             .setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.toString())
                             .build();
    }

    public HttpUriRequest getNotifications() {
        return RequestBuilder.post()
                             .setUri(BASE_API_URI + "/notifications/get")
                             .setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString())
                             .build();
    }

    public HttpUriRequest subscribeHashtag(String hashtag) {
        return RequestBuilder.post()
                             .setUri(BASE_API_URI + "/hashtag/subscribe")
                             .addParameter("hashtag", hashtag)
                             .setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.toString())
                             .build();
    }
}