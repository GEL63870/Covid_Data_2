package pl.com.pwr.covid_data.lab5;


import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class One_Country implements Parcelable {
    private int mFlagResource;
    private String mTitle;
    private String mDueDate;
    private ArrayList<Integer> mDescriptions;


    public One_Country(int ImageResource, String Title, String DueDate, ArrayList<Integer> Descriptions) {
        mFlagResource = ImageResource;
        mTitle = Title;
        mDueDate = DueDate;
        mDescriptions = Descriptions;

    }

    protected One_Country(Parcel in) {
        mFlagResource = in.readInt();
        mTitle = in.readString();
        mDueDate = in.readString();
        mDescriptions = in.readArrayList(String.class.getClassLoader());
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
        return mTitle; }
    public String getDueDate() {
        return mDueDate;
    }
    public ArrayList<Integer> getDescriptions() {
        return mDescriptions;
    }

    public int getDescriptionsIndex(int index) {
        int element = mDescriptions.get(index);
        return element;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeInt(mFlagResource);
        destination.writeString(mTitle);
        destination.writeString(mDueDate);
        destination.writeList(mDescriptions);
    }

}
