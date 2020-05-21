package pl.com.pwr.covid_data.lab5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Barrier_Gesture extends AppCompatActivity {

    private ImageView mgestureView;
    private Button menu_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barrier_gesture);

        back_to_menu();

        mgestureView = findViewById(R.id.gestureView);
        mgestureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.barrier_gesture_link)));
                startActivity(browserIntent);
            }
        });
    }


    // Button to go back to the Main Menu

        private void back_to_menu(){
        menu_btn = findViewById(R.id.menu_button);
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Barrier_Gesture.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
