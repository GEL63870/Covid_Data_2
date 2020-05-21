package pl.com.pwr.covid_data.lab5;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class APIRetrofit {

    private static final String BASE_URL = "https://api.covid19api.com/";

    COVID19StatsAPI start(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(COVID19StatsAPI.class);
    }

}