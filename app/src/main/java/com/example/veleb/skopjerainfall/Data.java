package com.example.veleb.skopjerainfall;

/**
 * Created by veleb on 13.8.2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("Raining")
    @Expose
    private boolean raining;
    @SerializedName("UV")
    @Expose
    private int uV;
    @SerializedName("VisibleLight")
    @Expose
    private double visibleLight;
    @SerializedName("Wet")
    @Expose
    private int wet;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("raw")
    @Expose
    private String raw;
    @SerializedName("time")
    @Expose
    private String time;

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public int getUV() {
        return uV;
    }

    public void setUV(int uV) {
        this.uV = uV;
    }

    public double getVisibleLight() {
        return visibleLight;
    }

    public void setVisibleLight(double visibleLight) {
        this.visibleLight = visibleLight;
    }

    public int getWet() {
        return wet;
    }

    public void setWet(int wet) {
        this.wet = wet;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}