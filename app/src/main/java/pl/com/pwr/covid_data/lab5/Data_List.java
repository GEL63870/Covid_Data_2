package pl.com.pwr.covid_data.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pl.com.pwr.covid_data.lab5.models.Country;
import pl.com.pwr.covid_data.lab5.models.Global;
import pl.com.pwr.covid_data.lab5.models.Stats;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class Data_List extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    //list of all the countries in the api
    private List<Country> allCountries;

    COVID19StatsAPI covidAPI;

    // In order to manage and implement RecyclerView and Adapter
    private RecyclerView mRecyclerView;

    // In order to select the country which we want to follow Covid Data
    private Spinner selectedCountry;

    // Button to go back to Main Menu of the app & Btn to add a country we selected with the spinner
    private Button menu_btn, add_country_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_list);

        Data_List.this.setTitle("List of countries");
        back_to_menu();

        //get spinner
        selectedCountry = findViewById(R.id.country_spinner);
        if (selectedCountry != null) {
            selectedCountry.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.country_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (selectedCountry != null) {
            selectedCountry.setAdapter(adapter);
        }

        // Initialise API to collect data
        APIRetrofit client = new APIRetrofit();
        covidAPI =  client.start();

        // Most important (and only) call, gets all the info we need.
        // Since the rest can only work after we get this info, the recyclerView has to be built within the response
        Call<Stats> call = covidAPI.getSummary();
        call.enqueue(new Callback<Stats>() {
            public void onResponse(Call<Stats> call, Response<Stats> response) {

                Stats stats = response.body();
                //from now on the worldstats will have the global stats and the allCountries variable will have each country with its stats
                assert stats != null;
                allCountries = stats.getCountries();
                buildRecyclerView();

            }

            @Override
            public void onFailure(Call<Stats> call, Throwable t) {
                call.cancel();
            }
        });



    }


    // Build RecyclerView with LinearLayout Manager, Adapter and ItemTouch Helper
    public void buildRecyclerView() {

        // Link to the XML
        mRecyclerView = findViewById(R.id.my_recycler_view);
        // Initialise the LinearLayout Manager with RecyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Initialise the Custom Adapter and the ItemTouchHelper
        CustomAdapter mAdapter = new CustomAdapter(this, allCountries);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        Log.i("TEST4", "passed");

    }

    // With this part, if you switch an item to the right OR to the left, it will be deleted
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            allCountries.remove(viewHolder.getAdapterPosition());
            mRecyclerView.getAdapter().notifyDataSetChanged();

        }
    };


    // Button to go back to the Main Menu
    private void back_to_menu() {
        menu_btn = findViewById(R.id.menu_button);
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Data_List.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
