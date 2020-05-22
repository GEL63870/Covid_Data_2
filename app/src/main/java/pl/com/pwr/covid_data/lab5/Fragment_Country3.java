package pl.com.pwr.covid_data.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class Fragment_Country3 extends AppCompatActivity {

        private SlidrInterface slidr;
        private ProgressBar mProgressBar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_detail3);

        slidr = Slidr.attach(this);

        Intent intent = getIntent();
        One_Country one_country = intent.getParcelableExtra("task");

        String mCountry_name = (one_country != null) ? one_country.getTitle() : null;
        String mUpdate_date = one_country != null ? one_country.getDueDate() : null;

        // Here we have the 2 data Int that will be use in the progress bar
        Integer mNew_Percentage_recover = (one_country != null) ? one_country.getNewRecover() : null;
        Integer mTotal_recover = (one_country != null) ? one_country.getTotalRecover() : null;



        // Set of the view \\

        TextView country = findViewById(R.id.title_detail);
        country.setText(mCountry_name);

        TextView update_date = findViewById(R.id.due_date_detail);
        update_date.setText(mUpdate_date);

        TextView new_percentage_recover = findViewById(R.id.description_detail);
        new_percentage_recover.setText(mNew_Percentage_recover);

        TextView total_recover = findViewById(R.id.status_detail);
        total_recover.setText(mTotal_recover);

        mProgressBar = findViewById(R.id.circle_progress_bar);
        mProgressBar.setProgress(10); // Initialisation (100-10 = 90% on the progress bar)
    }
}