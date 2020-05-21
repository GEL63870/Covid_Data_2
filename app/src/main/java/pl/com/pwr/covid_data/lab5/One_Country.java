package pl.com.pwr.covid_data.lab5;


import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

import pl.com.pwr.covid_data.lab5.models.Country;

public class One_Country implements Parcelable {

    private int mFlagResource;
    private String mCountry_name;
    private String mUpdate_date;
    private int mNewCase;
    private int mTotalCase;
    private int mNewDeaths;
    private int mTotalDeaths;
    private int mNewRecover;
    private int mTotalRecover;


    //public One_Country(int ImageResource, String Title, String DueDate, ArrayList<Integer> Descriptions) {mFlagResource = ImageResource;
        //mTitle = Title;
        //mDueDate = DueDate;
       // mDescriptions = Descriptions;


    protected One_Country(Parcel in) {
        mFlagResource = in.readInt();
        mCountry_name = in.readString();
        mUpdate_date = in.readString();
        mNewCase = in.readInt();
        mTotalCase = in.readInt();
        mNewDeaths = in.readInt();
        mTotalDeaths = in.readInt();
        mNewRecover = in.readInt();
        mTotalRecover = in.readInt();

    }

    public static final Creator<One_Country> CREATOR = new Creator<One_Country>() {
        @Override
        public One_Country createFromParcel(Parcel in) {
            return new One_Country(in);
        }

        @Override
        public One_Country[] newArray(int size) {
            return new One_Country[size];
        }
    };


    public int getImageResource(){
        return mFlagResource;
    }

    public String getTitle() {
        return mCountry_name;
    }
    public String getDueDate() {
        return mUpdate_date;
    }
    public int getNewCase(){
        return mNewCase;
    }
    public int getTotalCase(){
        return mTotalCase;
    }
    public int getNewDeaths(){
        return mNewDeaths;
    }
    public int getTotalDeaths(){
        return mTotalDeaths;
    }
    public int getNewRecover(){
        return mNewRecover;
    }
    public int getTotalRecover(){
        return mTotalRecover;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeInt(mFlagResource);
        destination.writeString(mCountry_name);
        destination.writeString(mUpdate_date);
        destination.writeInt(mNewCase);
        destination.writeInt(mTotalCase);
        destination.writeInt(mNewDeaths);
        destination.writeInt(mTotalDeaths);
        destination.writeInt(mNewRecover);
        destination.writeInt(mTotalRecover);

    }

}
