package pl.com.pwr.covid_data.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
      //  implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

   // private ArrayList<One_Country> pickedCountries;
    //list of countries that we picked
    private ArrayList<Country> pickedCountries;

    //list of all the countries in the api
    private List<Country> allCountries;

    //contains stats about the world
    private Global worldStats;
    COVID19StatsAPI covidAPI;

    // In order to manage and implement RecyclerView and Adapter
    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayAdapter<CharSequence> adapter;

    // In order to select the country which we want to follow Covid Data
    private Spinner selectedCountry;

    // Button to go back to Main Menu of the app & Btn to add a country we selected with the spinner
    private Button menu_btn, add_country_btn;

    //boolean that confirms if data is ready
    private boolean ready;

    // Contain the list of the Country Codes of all the flag we have in drawable.
    // All the flag are in Drawable > flag_data (you can see it with Project view, not with Android view
    private static final String[] countries = new String[] {
            "ad", "ae", "af", "ag", "ai", "al", "am", "ao", "ar", "at", "au", "ax", "az", "ba", "bb",
            "bd", "be", "bf", "bg", "bh", "bi", "bj", "bm", "bn", "bo", "br", "bs", "bt", "bw", "by",
            "bz", "ca", "caf", "cas", "cd", "ceu", "cf", "cg", "ch", "ch", "ci", "cl", "cm", "cn",
            "cna", "co", "coc", "cr", "csa", "cu", "cv", "cy", "cz", "de", "dj", "dk", "dm", "dz", "ec",
            "ee", "eg", "er", "es", "et", "eu", "fi", "fj", "fm", "fr", "ga", "gb", "gd", "ge", "gh",
            "gm", "gn", "gq", "gr", "gt", "gw", "gy", "hk", "hn", "hr", "ht", "hu", "id", "ie", "il",
            "in", "iq", "ir", "is", "it", "jm", "jo", "jp", "ke", "kg", "kh", "km", "kn", "kp", "kr",
            "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly", "ma",
            "mc", "md", "me", "mg", "mk", "ml", "mm", "mn", "mo", "mr", "ms", "mt", "mu", "mv", "mw",
            "mx", "my", "mz", "na", "ne", "ng", "ni", "nl", "no", "np", "nz", "om", "pa", "pe", "pg",
            "ph", "pk", "pl", "pr", "pt", "pw", "py", "qa", "ro", "rs", "ru", "rw", "sa", "sb", "sc",
            "sd", "se", "sg", "si", "sk", "sl", "sm", "sn", "so", "sr", "st", "sv", "sy", "sz", "tc",
            "td", "tg", "th", "tj", "tl", "tm", "tn", "to", "tr", "tt", "tw", "tz", "ua", "ug", "us",
            "uy", "uz", "vc", "ve", "vg", "vn", "ws", "ww", "ye", "za", "zw"
    };

    // Will contain all the data from a country we wand to add to recyclerview

    public ImageView flagRessources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_list);

        ready=false;
        pickedCountries = new ArrayList<>();

        Data_List.this.setTitle("List of countries");
        Log.i("TEST1", "created");
        back_to_menu();
       // new_country ();
        //get spinner
        selectedCountry = findViewById(R.id.country_spinner);
        if (selectedCountry != null) {
            selectedCountry.setOnItemSelectedListener(this);
        }
        adapter = ArrayAdapter.createFromResource(this, R.array.country_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (selectedCountry != null) {
            selectedCountry.setAdapter(adapter);
        }

        // Initialise API to collect data
        APIRetrofit client = new APIRetrofit();
        covidAPI =  client.start();

        Log.i("TEST2", "passed");
        // Most important (and only) call, gets all the info we need.
        // Since the rest can only work after we get this info, the recyclerView has to be built within the response
        Call<Stats> call = covidAPI.getSummary();
        call.enqueue(new Callback<Stats>() {
            public void onResponse(Call<Stats> call, Response<Stats> response) {

                Stats stats = response.body();
                //from now on the worldstats will have the global stats and the allCountries variable will have each country with its stats
                assert stats != null;
                worldStats = stats.getGlobal();
                allCountries = stats.getCountries();
                Log.i("TEST2", String.valueOf(allCountries.size()));
                buildRecyclerView();
                ready=true;

            }

            @Override
            public void onFailure(Call<Stats> call, Throwable t) {
                call.cancel();
            }
        });



    }

    // Will add a new country to the recyclerview list, 1st select a country with spinner, then add
    private void new_country() {
        add_country_btn = findViewById(R.id.new_country_button);
        add_country_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add function to add country to Recyclerview
                String countryName = selectedCountry.getItemAtPosition(selectedCountry.getSelectedItemPosition()).toString();

                // Finds country in the list of all countries and extracts the needed info
                for(Country c : allCountries){
                    if(c.getcName() != null && c.getcName().contains(countryName)){
                        String country_code = c.getcCode();
                        int newCases = c.getNewConfirmed();
                        int totalCase = c.getTotalConfirmed();
                        int newDeaths = c.getNewDeaths();
                        int totalDeath = c.getTotalDeaths();
                        int newRecovered = c.getNewRecovered();
                        int totalRecovered = c.getTotalRecovered();
                        int update_date = c.getUpdate_date();

                     //etc. you can extract anything you want from the chosen country here;

                    // flagRessources = findViewById(country_code);
                    //new_description.add(newCases);
                    //new_description.add(totalCase);
                    //new_description.add(newDeaths);
                    //new_description.add(totalDeath);
                    //new_description.add(newRecovered);
                    //new_description.add(totalRecovered);

                        int position = 0;
                        add_country(position, countryName, country_code, newCases, totalCase, newDeaths, totalDeath, newRecovered, totalRecovered, update_date);
                    }
                }
            }
        });
    }

    // Need to have a database with all the flag of each country and put the good one on the new item
    public void add_country(int position, String CountryName, String country_code, int NewCases,
                            int TotalCases, int NewDeaths, int TotalDeath, int NewRecover, int TotalRecover, int Update_date) {

        pickedCountries.add(position, new Country(CountryName, country_code, NewCases, TotalCases, NewDeaths, TotalDeath, NewRecover, TotalRecover));

        mRecyclerView.getAdapter().notifyItemInserted(position);
    }

    // Build RecyclerView with LinearLayout Manager, Adapter and ItemTouch Helper
    public void buildRecyclerView() {

        // Link to the XML
        mRecyclerView = findViewById(R.id.my_recycler_view);
        // Initialise the LinearLayout Manager with RecyclerView
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Initialise the Custom Adapter and the ItemTouchHelper
        mAdapter = new CustomAdapter(this, allCountries);
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
            pickedCountries.remove(viewHolder.getAdapterPosition());
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
