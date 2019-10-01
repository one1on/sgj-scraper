package de.one1on.sgjscraper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.List;

@Data
public class Notification {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("unread")
    @Expose
    private boolean unread;
    @SerializedName("jodel")
    @Expose
    private Jodel jodel;
    @SerializedName("comment")
    @Expose
    private Comment comment;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("value")
    @Expose
    private int value;
    @SerializedName("timestamp")
    @Expose
    private DateTime timestamp;
    @SerializedName("hashtags")
    @Expose
    private List<String> hashtags = null;
}
