
package de.one1on.sgjscraper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author {

    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("gender_id")
    @Expose
    private Integer genderId;
    @SerializedName("verified")
    @Expose
    private Integer verified;
    @SerializedName("donator")
    @Expose
    private Integer donator;
    @SerializedName("premium")
    @Expose
    private Integer premium;
    @SerializedName("user_author")
    @Expose
    private Integer userAuthor;
    @SerializedName("user_adminflag")
    @Expose
    private Integer userAdminflag;
    @SerializedName("user_messages")
    @Expose
    private UserMessages userMessages;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    public Integer getDonator() {
        return donator;
    }

    public void setDonator(Integer donator) {
        this.donator = donator;
    }

    public Integer getPremium() {
        return premium;
    }

    public void setPremium(Integer premium) {
        this.premium = premium;
    }

    public Integer getUserAuthor() {
        return userAuthor;
    }

    public void setUserAuthor(Integer userAuthor) {
        this.userAuthor = userAuthor;
    }

    public Integer getUserAdminflag() {
        return userAdminflag;
    }

    public void setUserAdminflag(Integer userAdminflag) {
        this.userAdminflag = userAdminflag;
    }

    public UserMessages getUserMessages() {
        return userMessages;
    }

    public void setUserMessages(UserMessages userMessages) {
        this.userMessages = userMessages;
    }

}
