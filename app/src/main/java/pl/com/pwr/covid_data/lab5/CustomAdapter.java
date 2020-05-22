package pl.com.pwr.covid_data.lab5;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import pl.com.pwr.covid_data.lab5.models.Country;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter <CustomAdapter.MyViewHolder> {

    //contains all the countries
    private List<Country> mCountry_List;
    private Context context;

    public CustomAdapter(Context context , List<Country> countries) {
        this.context = context;
        mCountry_List = countries;
        Log.i("TEST40", String.valueOf(mCountry_List.size()));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mCountryName;
        TextView mTotal_Cases;
        CustomAdapter mAdapter;


        MyViewHolder(@NonNull View itemView, CustomAdapter adapter) {
            super(itemView);
            mCountryName = itemView.findViewById(R.id.task_name_txt);
            mTotal_Cases =  itemView.findViewById(R.id.due_date);
            mAdapter=adapter;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int mPosition = getLayoutPosition();
            Country country = mCountry_List.get(mPosition);


            Intent intent = new Intent(v.getContext(), Recap_Country.class);
            intent.putExtra("country", country);

            v.getContext().startActivity(intent);
            }

        }



    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_row_line, parent, false);
        return new MyViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Country currentCountry = mCountry_List.get(position);
        holder.mCountryName.setText(currentCountry.getcName());
        holder.mTotal_Cases.setText("Total Cases : " + currentCountry.getTotalConfirmed());
    }

    @Override
    public int getItemCount() {
        return mCountry_List.size();
    }
}

