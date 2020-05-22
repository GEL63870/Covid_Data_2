package pl.com.pwr.covid_data.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import pl.com.pwr.covid_data.lab5.models.Country;

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
        Country country = (Country) intent.getSerializableExtra("country");
        //int flagRes = (country != null ? country.getImageResource() : 0);
        String mCountry_name =country.getcName();
        Recap_Country.this.setTitle("Situation of "+ mCountry_name);
        String mUpdate_date = String.valueOf(country.getUpdate_date());

        String mNew_case = String.valueOf(country.getNewConfirmed());
        String mTotal_case = String.valueOf(country.getTotalConfirmed());

        String mNew_death = String.valueOf(country.getNewDeaths());
        String mTotal_death = String.valueOf(country.getTotalDeaths());

        String mNew_recover = String.valueOf(country.getNewRecovered());
        String mTotal_recover = String.valueOf(country.getTotalRecovered());

        //flag_country.setImageResource(flagRes);
        country_name.setText(mCountry_name);
        update_date.setText(mUpdate_date);
        new_case.setText(mNew_case);
        total_case.setText(mTotal_case);
        new_death.setText(mNew_death);
        total_death.setText(mTotal_death);
        new_recovered.setText(mNew_recover);
        total_recovered.setText(mTotal_recover);
    }
}