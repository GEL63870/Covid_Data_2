package pl.com.pwr.covid_data.lab5.models;

import com.google.gson.annotations.SerializedName;

public class Global{
    @SerializedName("NewConfirmed")
    private int newConfirmed;
    @SerializedName("TotalConfirmed")
    private int totalConfirmed;
    @SerializedName("NewDeaths")
    private int newDeaths;
    @SerializedName("TotalDeaths")
    private int totalDeaths;
    @SerializedName("NewRecovered")
    private int newRecovered;
    @SerializedName("TotalRecovered")
    private int totalRecovered;

    public Global(int nConf, int tConf, int nDeaths, int tDeaths, int nRec, int tRec){
        newConfirmed=nConf;
        totalConfirmed=tConf;
        newDeaths=nDeaths;
        totalDeaths=tDeaths;
        newRecovered=nRec;
        totalRecovered=tRec;
    }

    public int getNewConfirmed() {
        return newConfirmed;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public int getNewRecovered() {
        return newRecovered;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }


}
