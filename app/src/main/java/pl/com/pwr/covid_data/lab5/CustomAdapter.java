package pl.com.pwr.covid_data.lab5;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import pl.com.pwr.covid_data.lab5.models.Country;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter <CustomAdapter.MyViewHolder> {

    //contains all the countries
    private ArrayList<Country> mCountry_List;
    private Context context;

    public CustomAdapter(Context context , ArrayList<Country> countries) {
        this.context = context;
        mCountry_List = countries;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;
        TextView mCountryName;
        TextView mUpdate_date;
        CustomAdapter mAdapter;


        MyViewHolder(@NonNull View itemView, CustomAdapter adapter) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iconView);
            mCountryName = itemView.findViewById(R.id.task_name_txt);
            mUpdate_date =  itemView.findViewById(R.id.due_date);
            mAdapter=adapter;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int mPosition = getLayoutPosition();
            Country country = mCountry_List.get(mPosition);


            Intent intent = new Intent(v.getContext(), Fragment_Country.class);
            intent.putExtra("task", mPosition);

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
        //holder.mImageView.setImageResource(currentCountry.getFlagResource());
        holder.mCountryName.setText(currentCountry.getcName());
        holder.mUpdate_date.setText(currentCountry.getUpdate_date());
        //holder.mStatus.setText(currentCountry.getStatus());
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}

