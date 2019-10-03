
package de.one1on.sgjscraper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;
import org.joda.time.DateTime;

import java.util.List;

@Data
@ToString(of = {"id", "timestamp", "votes"})
public class Jodel {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("imageA")
    @Expose
    private String imageA;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("timestamp")
    @Expose
    private DateTime timestamp;
    @SerializedName("color")
    @Expose
    private Color color;
    @SerializedName("comments")
    @Expose
    private int comments;
    @SerializedName("votes")
    @Expose
    private Votes votes;
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("pinned")
    @Expose
    private int pinned;
    @SerializedName("report")
    @Expose
    private int report;
    private List<Comment> resolvedComments;

}
