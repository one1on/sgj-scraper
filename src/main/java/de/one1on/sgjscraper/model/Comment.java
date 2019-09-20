package de.one1on.sgjscraper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("id")
    @Expose
    private Integer id;
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
    private Integer timestamp;
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
    @SerializedName("additional_information")
    @Expose
    private String additionalInformation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageA() {
        return imageA;
    }

    public void setImageA(String imageA) {
        this.imageA = imageA;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public Votes getVotes() {
        return votes;
    }

    public void setVotes(Votes votes) {
        this.votes = votes;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPinned() {
        return pinned;
    }

    public void setPinned(Integer pinned) {
        this.pinned = pinned;
    }

    public Integer getReport() {
        return report;
    }

    public void setReport(Integer report) {
        this.report = report;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
