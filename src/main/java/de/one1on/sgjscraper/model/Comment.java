package de.one1on.sgjscraper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Comment {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("imageA")
    @Expose
    private String imageA;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("timestamp")
    @Expose
    private DateTime timestamp;
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
    private boolean pinned;
    @SerializedName("report")
    @Expose
    private int report;
    @SerializedName("additional_information")
    @Expose
    private String additionalInformation;
}
