package com.aut.myapplication;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("Country")
    private String country;
    @SerializedName("Slug")
    private String slug;
    @SerializedName("ISO2")
    private String iso2;

    public Country(){

    }

    public String getCountry(){
        return country;
    }

    public String getSlug() {
        return slug;
    }

    public String getIso2(){
        return iso2;
    }

}
