package pl.com.pwr.covid_data.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import pl.com.pwr.covid_data.lab5.models.Country;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class Fragment_Country extends AppCompatActivity {

        private SlidrInterface slidr;
        private ProgressBar mProgressBar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recap_country);

        slidr = Slidr.attach(this);
/*
        // You need to put you data in this initialize points
        Intent intent = getIntent();
        Country country = intent.getParcelableExtra("task");
        //int flagRes = (country != null ? country.getImageResource() : 0);
        String mCountry_name = country.getcName();
        String mUpdate_date = String.valueOf(country.getUpdate_date());
        int mNew_case = country.getNewConfirmed()i;
        int mTotal_case = country.getTotalCase();



        // Set of the view
        ImageView country_flag = findViewById(R.id.flag_icon);
      //  country_flag.setImageResource(flagRes);

        TextView country = findViewById(R.id.title_detail);
        country.setText(mCountry_name);

        TextView update_date = findViewById(R.id.due_date_detail);
        update_date.setText(mUpdate_date);

        TextView new_percentage_case = findViewById(R.id.description_detail);
        Log.i("TEST40", String.valueOf(mNew_case));
        new_percentage_case.setText(mNew_case);

        TextView total_case = findViewById(R.id.status_detail);
        Log.i("TEST40", String.valueOf(mTotal_case));
        total_case.setText(mTotal_case);

        mProgressBar = findViewById(R.id.circle_progress_bar);
        mProgressBar.setProgress(10); // Initialisation (100-10 = 90% on the progress bar)
*/
    }
}