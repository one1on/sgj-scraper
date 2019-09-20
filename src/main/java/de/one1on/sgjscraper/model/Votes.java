
package de.one1on.sgjscraper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Votes {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("user_voted")
    @Expose
    private Integer userVoted;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getUserVoted() {
        return userVoted;
    }

    public void setUserVoted(Integer userVoted) {
        this.userVoted = userVoted;
    }

}
