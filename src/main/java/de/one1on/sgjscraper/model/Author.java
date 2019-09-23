
package de.one1on.sgjscraper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Author {
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("gender_id")
    @Expose
    private Gender genderId = Gender.MALE;
    public enum Gender {
        @SerializedName("1")
        FEMALE,
        @SerializedName("2")
        MALE
    }
    @SerializedName("verified")
    @Expose
    private boolean verified;
    @SerializedName("donator")
    @Expose
    private boolean donator;
    @SerializedName("premium")
    @Expose
    private boolean premium;
    @SerializedName("user_author")
    @Expose
    private int userAuthor;
    @SerializedName("user_adminflag")
    @Expose
    private boolean userAdminflag;
    @SerializedName("user_messages")
    @Expose
    private UserMessages userMessages;

    public boolean isMale() {
        return genderId.equals(Gender.MALE);
    }

    public boolean isFemale() {
        return genderId.equals(Gender.FEMALE);
    }
}
