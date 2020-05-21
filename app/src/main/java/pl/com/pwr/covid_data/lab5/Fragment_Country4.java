package pl.com.pwr.covid_data.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class Fragment_Country4 extends AppCompatActivity {

        private SlidrInterface slidr;
        private ProgressBar mProgressBar_Death, mProgressBar_Recover;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_detail);

        slidr = Slidr.attach(this);

        Intent intent = getIntent();
        One_Country one_country = intent.getParcelableExtra("task");

        int flagRes = (one_country != null ? one_country.getImageResource() : 0);
        String mCountry_name = (one_country != null) ? one_country.getTitle() : null;
        String mUpdate_date = one_country != null ? one_country.getDueDate() : null;


        // Here we have the 3 data Int that will be use in the 2 progress bar !
        // We need to calculate them first
        Integer mTotal_recover = (one_country != null) ? one_country.getTotalRecover() : null;
        Integer mTotal_death = (one_country != null) ? one_country.getTotalDeaths(): null;
        Integer mTotal_case = (one_country != null) ? one_country.getTotalCase() : null;

        Integer mNew_Percentage_case = (mTotal_case != null) ? mTotal_recover/ mTotal_case : null;
        Integer mNew_Percentage_death = (mTotal_case != null) ? mTotal_death / mTotal_case : null;

        // Set of the view
        ImageView country_flag = findViewById(R.id.flag_icon);
        country_flag.setImageResource(flagRes);

        TextView country = findViewById(R.id.title_detail);
        country.setText(mCountry_name);

        TextView update_date = findViewById(R.id.due_date_detail);
        update_date.setText(mUpdate_date);

        TextView ratio_death = findViewById(R.id.description_detail);
        ratio_death.setText(mNew_Percentage_case);

        TextView ratio_recover = findViewById(R.id.description_detail2);
        ratio_recover.setText(mNew_Percentage_death);

        TextView total_case = findViewById(R.id.status_detail);
        total_case.setText(mTotal_case);

        // Initialisation (100-10 = 90% on the progress bar)
        mProgressBar_Death = findViewById(R.id.circle_progress_bar);
        mProgressBar_Death.setProgress(10);

        mProgressBar_Recover = findViewById(R.id.circle_progress_bar2);
        mProgressBar_Recover.setProgress(10); // Initialisation (100-10 = 90% on the progress bar)

        }
}