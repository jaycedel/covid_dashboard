package com.aut.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryActivity extends AppCompatActivity {

    private Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        LoadCountryData();
    }

    public void ButtonClick(View view) {
        String countrySlug;
        //countrySlug = "south-africa";
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        countrySlug = spinner2.getSelectedItem().toString();
        LoadCovidData(countrySlug);
    }
    private void LoadCountryData()
    {
        //Create a handler for the RetrofitInstance interface//
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Country>> call = service.getAllCountries();

        //Execute the request asynchronously//
        call.enqueue(new Callback<List<Country>>() {
            @Override
            //Handle a successful response//
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                loadDataListCountry(response.body());
            }
            @Override
            //Handle execution failures//
            public void onFailure(Call<List<Country>> call, Throwable throwable) {
                //If the request fails, then display the following toast//
                Toast.makeText(CountryActivity.this, "Unable to load", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void LoadCovidData(String countrySlug){
        //Create a handler for the RetrofitInstance interface//
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<CovidData>> call = service.getAllCovidData("/total/country/" + countrySlug);


        //Execute the request asynchronously//
        call.enqueue(new Callback<List<CovidData>>() {
            @Override
            //Handle a successful response//
            public void onResponse(Call<List<CovidData>> call, Response<List<CovidData>> response) {
                loadDataListCovid(response.body());
            }
            @Override
            //Handle execution failures//
            public void onFailure(Call<List<CovidData>> call, Throwable throwable) {
                //If the request fails, then display the following toast//
                Toast.makeText(CountryActivity.this, "Unable to load", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadDataListCovid(List<CovidData> covidDataList) {
        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.line();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title("Covid-19");

        cartesian.yAxis(0).title("Number of People (thousands)");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        List<DataEntry> seriesData = new ArrayList<>();

        final int size = covidDataList.size();
        for (int i = 0; i < size; i++)
        {
            try {
                seriesData.add(new CustomDataEntry(covidDataList.get(i).getDate(), covidDataList.get(i).getActive().doubleValue(), covidDataList.get(i).getRecovered().doubleValue(), covidDataList.get(i).getDeaths().doubleValue()));

            }catch(Exception e)
            {

            }
            //seriesData.add(new CustomDataEntry("Feb 15", 3.6, 2.3, 2.8));
        }

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("Active");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series2 = cartesian.line(series2Mapping);
        series2.name("Recovered");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series3 = cartesian.line(series3Mapping);
        series3.name("Deaths");
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series3.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        anyChartView.setChart(cartesian);

    }

    private void loadDataListCountry(List<Country> countryList) {
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();

        final int size = countryList.size();
        for (int i = 0; i < size; i++)
        {
            //list.add(countryList.get(i).getCountry());
            list.add(countryList.get(i).getSlug());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }


    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.back_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getBaseContext(),"back to main",Toast.LENGTH_SHORT).show();
        return true;
    }
}