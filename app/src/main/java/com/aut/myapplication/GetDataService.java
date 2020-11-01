package com.aut.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {

    //https://api.covid19api.com/countries
    @GET("/countries")
    Call<List<Country>> getAllCountries();

    //https://api.covid19api.com/total/country/south-africa
    //@GET("/total/country/{country_slug}")
    @GET("{country_slug}")
    Call<List<CovidData>> getAllCovidData(@Path(value = "country_slug", encoded = true) String countrySlug);

    //@GET("/total/country/philippines")
    //Call<List<CovidData>> getAllCovidData();
}