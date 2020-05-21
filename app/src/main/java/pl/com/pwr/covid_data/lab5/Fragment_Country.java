package pl.com.pwr.covid_data.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class Fragment_Country extends AppCompatActivity {

        private SlidrInterface slidr;
        private ProgressBar mProgressBar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_detail);

        slidr = Slidr.attach(this);

        // You need to put you data in this initialize points
        Intent intent = getIntent();
        One_Country one_country = intent.getParcelableExtra("task");
        int flagRes = (one_country != null ? one_country.getImageResource() : 0);
        String mCountry_name = (one_country != null) ? one_country.getTitle() : null;
        String mUpdate_date = one_country != null ? one_country.getDueDate() : null;

        // Here we have the 2 data Int that will be use in the progress bar
        Integer mNew_Percentage_case = (one_country != null) ? one_country.getDescriptionsIndex(0) : null;
        Integer mTotal_case = (one_country != null) ? one_country.getDescriptionsIndex(1) : null;



        // Set of the view
        ImageView country_flag = findViewById(R.id.flag_icon);
        country_flag.setImageResource(flagRes);

        TextView country = findViewById(R.id.title_detail);
        country.setText(mCountry_name);

        TextView update_date = findViewById(R.id.due_date_detail);
        update_date.setText(mUpdate_date);

        TextView new_percentage_case = findViewById(R.id.description_detail);
        new_percentage_case.setText(mNew_Percentage_case);

        TextView total_case = findViewById(R.id.status_detail);
        total_case.setText(mTotal_case);

        mProgressBar = findViewById(R.id.circle_progress_bar);
        mProgressBar.setProgress(10); // Initialisation (100-10 = 90% on the progress bar)

    }
}