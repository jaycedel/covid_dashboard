package com.aut.myapplication;

import com.anychart.AnyChart;

public class WorldData {

    private Integer totalRecovered;
    private Integer totalDeath;
    private Integer totalConfirmed;
    private AnyChart worldChart;

    public Integer getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(Integer totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public Integer getTotalDeath() {
        return totalDeath;
    }

    public void setTotalDeath(Integer totalDeath) {
        this.totalDeath = totalDeath;
    }

    public Integer getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(Integer totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public AnyChart getWorldChart() {
        return worldChart;
    }

    public void setWorldChart(AnyChart worldChart) {
        this.worldChart = worldChart;
    }
}
