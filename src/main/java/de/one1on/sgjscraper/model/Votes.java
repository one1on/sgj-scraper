
package de.one1on.sgjscraper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Votes {

    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("user_voted")
    @Expose
    private int userVoted;
}
