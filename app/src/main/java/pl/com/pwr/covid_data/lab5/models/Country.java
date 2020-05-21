package pl.com.pwr.covid_data.lab5.models;


import com.google.gson.annotations.SerializedName;

public  class Country{
    @SerializedName("Country")
    private String cName;
    @SerializedName("CountryCode")
    private String cCode;
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
   // @SerializedName("Date")
    private int update_date;


    public Country(String cName, String cCode, int nConf, int tConf,
                   int nDeaths, int tDeaths, int nRec, int tRec){
        this.cName=cName;
        this.cCode=cCode;
        newConfirmed=nConf;
        totalConfirmed=tConf;
        newDeaths=nDeaths;
        totalDeaths=tDeaths;
        newRecovered=nRec;
        totalRecovered=tRec;
        update_date=0;
    }

    public String getcName() {
        return cName;
    }

    public String getcCode() {
        return cCode;
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

    public int getUpdate_date() {
        return update_date;
    }

}