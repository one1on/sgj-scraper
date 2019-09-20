
package de.one1on.sgjscraper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class Jodel {

    @SerializedName("id")
    @Expose
    private Integer id;
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
    private Integer timestamp;
    @SerializedName("color")
    @Expose
    private Color color;
    @SerializedName("comments")
    @Expose
    private Integer comments;
    @SerializedName("votes")
    @Expose
    private Votes votes;
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("pinned")
    @Expose
    private Integer pinned;
    @SerializedName("report")
    @Expose
    private Integer report;
    private List<Comment> resolvedComments;

}
