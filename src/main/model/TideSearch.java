package model;

// Represents a search of tidal elevation for the given time and location
public class TideSearch {

    private double time;          // time for search in Julian date
    private String site;          // Site name as a String, e.g. Pt. Atkinson, Steveston ...
    private double longitude;     // longitude of the site location in degrees
    private double latitude;      // latitude oif the site location in degrees
    private double tidalElevation;// tidal Elevation of the given time.
    private int searchID;         // the ID for this particular search


    /*
     * REQUIRES: time must be > 0, and location must be one of the few locations
     * EFFECTS: Construct a TideSearch class and set the time and site as given
     */
    public TideSearch(double time, String site) {
        this.time = time;
        this.site = site;
    }

    public double getTime() {
        return time;
    }

    public String getSite() {
        return site;
    }


    // MODIFIES: this
    // EFFECTS: return the calculated the tidal elevation based on the given time and site
    public double getTidalElevation() {
        this.tidalElevation = 1;
        return tidalElevation;  // stub
    }


}
