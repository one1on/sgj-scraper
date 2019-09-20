package de.one1on.sgjscraper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Notification {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("unread")
    @Expose
    private Integer unread;
    @SerializedName("jodel")
    @Expose
    private Jodel jodel;
    @SerializedName("comment")
    @Expose
    private Comment comment;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("hashtags")
    @Expose
    private List<String> hashtags = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUnread() {
        return unread;
    }

    public void setUnread(Integer unread) {
        this.unread = unread;
    }

    public Jodel getJodel() {
        return jodel;
    }

    public void setJodel(Jodel jodel) {
        this.jodel = jodel;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

}
