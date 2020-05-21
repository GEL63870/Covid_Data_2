package pl.com.pwr.covid_data.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    private ArrayList<Integer> new_due_date, new_description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_list);

        back_to_menu();
        new_country ();
        createOne_Country();

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

        // Most important (and only) call, gets all the info we need.
        // Since the rest can only work after we get this info, the recyclerView has to be built within the response
        Call<Stats> call = covidAPI.getSummary();
        call.enqueue(new Callback<Stats>() {
            public void onResponse(Call<Stats> call, Response<Stats> response) {

                Stats stats = response.body();
                //from now on the worldstats will have the global stats and the allCountries variable will have each country with its stats
                worldStats = stats.getGlobal();
                allCountries = stats.getCountries();

                buildRecyclerView();
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
                        int newCases = c.getNewConfirmed();
                        int totalCase = c.getTotalConfirmed();
                        int newDeaths = c.getNewDeaths();
                        int totalDeath = c.getTotalDeaths();
                        int newRecovered = c.getNewRecovered();
                        int totalRecovered = c.getTotalRecovered();

                     //etc. you can extract anything you want from the chosen country here;
                     // Once you extract all, you need to put into descriptions to use it in fragment
                     // Important to keep the same order in order to use them correctly after

                    new_description.add(newCases);
                    new_description.add(totalCase);
                    new_description.add(newDeaths);
                    new_description.add(totalDeath);
                    new_description.add(newRecovered);
                    new_description.add(totalRecovered);

                    }

                }

                // String description = "PLACEHOLDER";

                int position = 0;
                add_country(position, countryName, new_description);
            }
        });
    }

    public void createOne_Country() {
        pickedCountries = new ArrayList<>();
        //I already had to create that Country class to hold the data from the api, not sure what you want to do,
        //TODO: either stick to Country or adapt this one_country class to all the new stuff
        ArrayList<Integer> france = new ArrayList();
        france.add(200);
        france.add(10000);
        france.add(40);
        france.add(500);
        france.add(200);
        france.add(6000);
        pickedCountries.add(new One_Country(R.drawable.flag_data.ad, "France", "21/05/2020", france));


    }

    // Need to have a database with all the flag of each country and put the good one on the new item
    public void add_country(int position, String title, ArrayList description) {

        //one_country.add(position, new One_Country(R.drawable.flag_france, title, description);
        mRecyclerView.getAdapter().notifyItemInserted(position);
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CustomAdapter(this, pickedCountries);
        new ItemTouchHelper(itemTouchHelperCallbackRight).attachToRecyclerView(mRecyclerView);
        new ItemTouchHelper(itemTouchHelperCallbackLeft).attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

    }

    // Part' in order to Swipe to Right to Delete
    ItemTouchHelper.SimpleCallback itemTouchHelperCallbackRight = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
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

    // Part' in order to Swipe to LEFT to mark as Done

    ItemTouchHelper.SimpleCallback itemTouchHelperCallbackLeft = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            //TODO: Adapt swipe to this app in specific
        /*    if (pickedCountries.get(viewHolder.getAdapterPosition()).getStatus().equals("Not Done")){
                pickedCountries.get(viewHolder.getAdapterPosition()).changeStatus("Done");
                mRecyclerView.getAdapter().notifyItemChanged(viewHolder.getAdapterPosition());
            }
            else {
                pickedCountries.get(viewHolder.getAdapterPosition()).changeStatus("Not Done");
                mRecyclerView.getAdapter().notifyItemChanged(viewHolder.getAdapterPosition());
            }
            */
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
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
