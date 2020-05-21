package pl.com.pwr.covid_data.lab5.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Stats {

    @SerializedName("Global")
    public Global global;

    @SerializedName("Countries")
    public List<Country> countries;

    public Stats(Global g, List<Country> c){
        global= g;
        countries= c;
    }

    public Global getGlobal() {
        return global;
    }

    public List<Country> getCountries() {
        return countries;
    }






}
