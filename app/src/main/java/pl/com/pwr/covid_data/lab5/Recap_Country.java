package pl.com.pwr.covid_data.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class Recap_Country extends AppCompatActivity {

    private SlidrInterface slidr;

    private ImageView flag_country;
    private TextView country_name, update_date, new_case, total_case, new_death, total_death,
            new_recovered, total_recovered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recap_country);

        slidr = Slidr.attach(this);

        flag_country = findViewById(R.id.country_flag);
        country_name = findViewById(R.id.country_name);
        update_date = findViewById(R.id.update_date);
        new_case = findViewById(R.id.new_cases);
        total_case = findViewById(R.id.total_cases);
        new_death = findViewById(R.id.new_deaths);
        total_death = findViewById(R.id.total_deaths);
        new_recovered = findViewById(R.id.new_recovered);
        total_recovered = findViewById(R.id.total_recovered);

        Intent intent = getIntent();
        One_Country one_country = intent.getParcelableExtra("task");
        int flagRes = (one_country != null ? one_country.getImageResource() : 0);
        String mcountry_name = (one_country != null) ? one_country.getTitle() : null;
        String mUpdate_date = one_country != null ? one_country.getDueDate() : null;

        Integer mNew_case = (one_country != null) ? one_country.getNewCase() : null;
        Integer mTotal_case = (one_country != null) ? one_country.getTotalCase() : null;

        Integer mNew_death = (one_country != null) ? one_country.getNewDeaths() : null;
        Integer mTotal_death = (one_country != null) ? one_country.getTotalDeaths() : null;

        Integer mNew_recover = (one_country != null) ? one_country.getNewDeaths() : null;
        Integer mTotal_recover = (one_country != null) ? one_country.getTotalDeaths() : null;

        flag_country.setImageResource(flagRes);
        country_name.setText(mcountry_name);
        update_date.setText(mUpdate_date);
        new_case.setText(mNew_case);
        total_case.setText(mTotal_case);
        new_death.setText(mNew_death);
        total_death.setText(mTotal_death);
        new_recovered.setText(mNew_recover);
        total_recovered.setText(mTotal_recover);
    }
}