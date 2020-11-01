package com.aut.myapplication;

import com.google.gson.annotations.SerializedName;

public class Global {

    @SerializedName("NewConfirmed")
    private Integer newConfirmed;
    @SerializedName("TotalConfirmed")
    private Integer totalConfirmed;
    @SerializedName("NewDeaths")
    private Integer newDeaths;
    @SerializedName("TotalDeaths")
    private Integer totalDeaths;
    @SerializedName("NewRecovered")
    private Integer newRecovered;
    @SerializedName("TotalRecovered")
    private Integer totalRecovered;

    public Integer getNewConfirmed() {
        return newConfirmed;
    }

    public void setNewConfirmed(Integer newConfirmed) {
        this.newConfirmed = newConfirmed;
    }

    public Integer getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(Integer totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public Integer getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(Integer newDeaths) {
        this.newDeaths = newDeaths;
    }

    public Integer getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(Integer totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Integer getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(Integer newRecovered) {
        this.newRecovered = newRecovered;
    }

    public Integer getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(Integer totalRecovered) {
        this.totalRecovered = totalRecovered;
    }
}
