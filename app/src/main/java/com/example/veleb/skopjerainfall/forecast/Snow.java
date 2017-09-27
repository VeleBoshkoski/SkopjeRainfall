
package com.example.veleb.skopjerainfall.forecast;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Snow implements Serializable
{

    @SerializedName("3h")
    @Expose
    private double _3h;
    private final static long serialVersionUID = -5431476513346157635L;

    public double get3h() {
        return _3h;
    }

    public void set3h(double _3h) {
        this._3h = _3h;
    }

}
