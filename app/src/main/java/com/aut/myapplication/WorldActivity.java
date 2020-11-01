package com.aut.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Align;
import com.anychart.enums.Anchor;
import com.anychart.enums.LegendLayout;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);
        loadGlobalData();
    }

    private void loadGlobalData(){
        //Create a handler for the RetrofitInstance interface//
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<GlobalSummary> call = service.getGlobalSummary();


        //Execute the request asynchronously//
        call.enqueue(new Callback<GlobalSummary>() {
            @Override
            //Handle a successful response//
            public void onResponse(Call<GlobalSummary> call, Response<GlobalSummary> response) {
                loadDataListCovid(response.body());
            }
            @Override
            //Handle execution failures//
            public void onFailure(Call<GlobalSummary> call, Throwable throwable) {
                //If the request fails, then display the following toast//
                Toast.makeText(WorldActivity.this, "Unable to load", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private AnyChartView loadDataListCovid(GlobalSummary summary) {
        AnyChartView anyChartView = findViewById(R.id.any_chart_pie_view);
        //anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Pie pie = AnyChart.pie();

        pie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
                Toast.makeText(WorldActivity.this, event.getData().get("x") + ":" + event.getData().get("value"), Toast.LENGTH_SHORT).show();
            }
        });


        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("New Confirmed", summary.getGlobal().getNewConfirmed()));
        data.add(new ValueDataEntry("Total Confirmed", summary.getGlobal().getTotalConfirmed()));
        data.add(new ValueDataEntry("New Deaths", summary.getGlobal().getNewDeaths()));
        data.add(new ValueDataEntry("Total Deaths", summary.getGlobal().getTotalDeaths()));
        data.add(new ValueDataEntry("New Recovered", summary.getGlobal().getNewRecovered()));
        data.add(new ValueDataEntry("Total Recovered", summary.getGlobal().getTotalRecovered()));

        pie.data(data);

        pie.title("COVID Summary");

        pie.labels().position("outside");

        pie.legend().title().enabled(true);
        pie.legend().title()
                .text("Retail channels")
                .padding(0d, 0d, 10d, 0d);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        anyChartView.setChart(pie);
        return anyChartView;
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