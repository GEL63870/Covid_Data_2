package pl.com.pwr.covid_data.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button covid_data_btn, barrier_gesture;
    private TextView welcome, authors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MainActivity.this.setTitle("COVID-19 Helper");

        welcome = findViewById(R.id.welcome);
        authors = findViewById(R.id.authors);

        covid_data_btn = findViewById(R.id.covid_list_btn);
        covid_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Data_List.class);
                startActivity(intent);
            }
        });

        barrier_gesture =findViewById(R.id.barrier_gestures_btn);
        barrier_gesture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Barrier_Gesture.class);
                startActivity(intent);
            }
        });

    }
}