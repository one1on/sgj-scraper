
package de.one1on.sgjscraper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserMessages {

    @SerializedName("all")
    @Expose
    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

}
