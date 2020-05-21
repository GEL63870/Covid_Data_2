package pl.com.pwr.covid_data.lab5;


import pl.com.pwr.covid_data.lab5.models.Stats;
import retrofit2.Call;
import retrofit2.http.GET;

public interface COVID19StatsAPI {

    @GET("summary")
    Call<Stats> getSummary();



}
