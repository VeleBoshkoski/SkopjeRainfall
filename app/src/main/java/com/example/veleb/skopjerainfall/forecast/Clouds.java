
package com.example.veleb.skopjerainfall.forecast;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds implements Serializable
{

    @SerializedName("all")
    @Expose
    private int all;
    private final static long serialVersionUID = -5487401691403226314L;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

}
