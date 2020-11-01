package com.aut.myapplication;

import com.anychart.scales.DateTime;
import com.google.gson.annotations.SerializedName;

public class CovidData {

    @SerializedName("Country")
    private String country;

    @SerializedName("CountryCode")
    private String countrycode;

    @SerializedName("Province")
    private String province;

    @SerializedName("City")
    private String city;

    @SerializedName("CityCode")
    private String citycode;

    @SerializedName("Lat")
    private Integer lat;

    @SerializedName("Lon")
    private Integer lon;

    @SerializedName("Confirmed")
    private Integer confirmed;

    @SerializedName("Deaths")
    private Integer deaths;

    @SerializedName("Recovered")
    private Integer recovered;

    @SerializedName("Active")
    private Integer active;

    @SerializedName("Date")
    private String date;

    public String getCountry() {
        return country;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getCitycode() {
        return citycode;
    }

    public Integer getLat() {
        return lat;
    }

    public Integer getLon() {
        return lon;
    }

    public Integer getConfirmed(){
        return confirmed;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public Integer getActive() {
        return active;
    }

    public String getDate() {
        return date;
    }

}















