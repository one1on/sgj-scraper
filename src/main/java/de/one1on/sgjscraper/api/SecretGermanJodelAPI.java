package de.one1on.sgjscraper.api;

import de.one1on.sgjscraper.model.Comment;
import de.one1on.sgjscraper.model.Jodel;
import de.one1on.sgjscraper.model.Notification;

import java.util.List;

public interface SecretGermanJodelAPI {
    List<Jodel> getPosts(long sort, long amout_loaded);
    List<Jodel> getPost(long id);
    List<Comment> getPostComments(long id);
    List<Notification> getNotifications();
    void subscribeHashtag(String hashtag);
}
