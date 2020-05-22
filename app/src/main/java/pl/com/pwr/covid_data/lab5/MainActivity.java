package pl.com.pwr.covid_data.lab5;

import androidx.appcompat.app.AppCompatActivity;
import pl.com.pwr.covid_data.lab5.models.Global;
import pl.com.pwr.covid_data.lab5.models.Stats;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    COVID19StatsAPI covidAPI;
    public TextView global_case;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        global_case = findViewById(R.id.global);

        // Initialise API to collect data
        APIRetrofit client = new APIRetrofit();
        covidAPI =  client.start();

        MainActivity.this.setTitle("COVID-19 Helper");

        Button covid_data_btn = findViewById(R.id.covid_list_btn);
        covid_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Data_List.class);
                startActivity(intent);
            }
        });

        Button barrier_gesture = findViewById(R.id.barrier_gestures_btn);
        barrier_gesture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Barrier_Gesture.class);
                startActivity(intent);
            }
        });

        Call<Stats> call = covidAPI.getSummary();
        call.enqueue(new Callback<Stats>() {
            public void onResponse(Call<Stats> call, Response<Stats> response) {

                Stats stats = response.body();
                //from now on the worldstats will have the global stats and the allCountries variable will have each country with its stats
                assert stats != null;
                Global global = stats.getGlobal();


                String mNew_case = String.valueOf(global.getNewConfirmed());
                String mTotal_case = String.valueOf(global.getTotalConfirmed());

                String mNew_death = String.valueOf(global.getNewDeaths());
                String mTotal_death = String.valueOf(global.getTotalDeaths());

                String mNew_recover = String.valueOf(global.getNewRecovered());
                String mTotal_recover = String.valueOf(global.getTotalRecovered());

                global_case.setText("New Cases : " +mNew_case+"|" + "Total : " + mTotal_case +"\n" +
                        "New Death : " +mNew_death+"|" + "Total : " + mTotal_death +"\n" + "New Recover : " + mNew_recover +"|" + "Total : " + mTotal_recover);


            }

            @Override
            public void onFailure(Call<Stats> call, Throwable t) {
                call.cancel();
            }
        });
    }
}