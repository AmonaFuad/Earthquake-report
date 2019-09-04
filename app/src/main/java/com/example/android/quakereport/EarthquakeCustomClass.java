package com.example.android.quakereport;
// create custom class to bass data to array list
/**
 * An {@link EarthquakeCustomClass} object contains information related to a single earthquake.
 */
public class EarthquakeCustomClass {
    // declare the information we need and make them private
    private String mmagnitude ;
    private String mlocation ;
    private String mdate ;
    /**
    // create a constructor with three parameters
     * @param magnitude is magnitude size of earthquake
     * @param location  is location ot earthquake
     * @param date  is date when earthquake happen
     **/
    public EarthquakeCustomClass (String magnitude ,String location ,String date  ){
mmagnitude=magnitude;
mlocation=location;
mdate=date;
    }
    // method to return the magnitude of earthquake
    public String getmagnitude (){
        return mmagnitude;
    }
    // method to return the location of earthquake
    public String getlocation (){
        return mlocation;
    }

    // method to return the date of earthquake
    public String getMdate (){
        return mdate;
    }


}
