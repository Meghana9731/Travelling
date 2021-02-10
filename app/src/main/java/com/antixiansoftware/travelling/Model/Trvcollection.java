package com.antixiansoftware.travelling.Model;

import com.google.gson.annotations.SerializedName;

public class Trvcollection {

    @SerializedName("id")
    private int id;

    @SerializedName("driver_name")
    private String driver_name;

    @SerializedName("vehical_name")
    private String vehical_name;

    @SerializedName("seating_capacity")
    private String seating_capacity;

    @SerializedName("km")
    private String km;


    @SerializedName("rating")
    private String rating;

    @SerializedName("image")
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getVehical_name() {
        return vehical_name;
    }

    public void setVehical_name(String vehical_name) {
        this.vehical_name = vehical_name;
    }

    public String getSeating_capacity() {
        return seating_capacity;
    }

    public void setSeating_capacity(String seating_capacity) {
        this.seating_capacity = seating_capacity;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
