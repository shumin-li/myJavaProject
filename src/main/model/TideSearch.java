package model;

import java.util.Scanner;

// Represents a search of tidal elevation for the given time and location
public class TideSearch {

    private double time;          // time for search in Julian date
    private String site;          // Site name as a String, e.g. Pt. Atkinson, Steveston ...
    private double longitude;     // longitude of the site location in degrees
    private double latitude;      // latitude oif the site location in degrees
    private double tidalElevation;// tidal Elevation of the given time.
    private int searchID;         // the ID for this particular search

    private Scanner input = new Scanner(System.in);

    private String yearString;
    private String monthString;
    private String dayString;
    private String hourString;
    private String minuteString;

    private int yearInt;
    private int monthInt;
    private int dayInt;
    private int hourInt;
    private int minuteInt;


    // EFFECTS: an empty construct;
    public TideSearch() {

    }


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


    public void doTidalAnalysisForNow() {
        double inputJDay = getJDay(2021, 7, 27, 17, 0);
        printTide(inputJDay);

    }






    public void doTideSearch() {

//        this.yearInt = year;
//        this.monthInt = month;
//        this.dayInt = day;
//        this.hourInt = hour;
//        this.minuteInt = minute;


        getInputTime();
        double inputJDay = getJDay(yearInt, monthInt, dayInt, hourInt, minuteInt);
        printTide(inputJDay);

    }


    private void printTide(double jday) {
        double tidalElevation = calculateElevation(jday);
        double nextHighTideTime = calculateNextHighTideTime(jday);
        double nextHighTideElev = calculateNextHighTideElev(jday);
        double nextLowTideTime = calculateNextLowTideTime(jday);
        double nextLowTideElev = calculateNextLowTideElev(jday);
        System.out.println("Tidal Elevation for this time is" + tidalElevation + " m");
        System.out.println("Next High Tide at (Julian Time) " + nextHighTideTime);
        System.out.println("Next High Tide Elevation is " + nextHighTideElev + " m");
        System.out.println("Next Low Tide at (Julian Time) " + nextLowTideTime);
        System.out.println("Next Low Tide Elevation is " + nextLowTideElev + " m");
    }

    private double calculateElevation(double jday) {
        return 2.0; // stub
    }

    private double calculateNextHighTideTime(double jday) {
        return jday + 1 / 24; // stub
    }

    private double calculateNextHighTideElev(double jday) {
        return 3; // stub
    }

    private double calculateNextLowTideTime(double jday) {
        return jday + 7 / 24; // stub
    }

    private double calculateNextLowTideElev(double jday) {
        return 1; // stub
    }






    private double getJDay(int year, int month, int day, int hour, int minute) {
        return 738364.5416; // stub
    }


    private void getInputTime() {
        System.out.println("\nIn put year: ");
        yearString = input.next();
        yearInt = Integer.parseInt(yearString);

        System.out.println("\nIn put month: (1 - 12)");
        monthString = input.next();
        monthInt = Integer.parseInt(monthString);

        System.out.println("\nIn put day: (1 - 31)");
        dayString = input.next();
        dayInt = Integer.parseInt(dayString);

        System.out.println("\nIn put hour: (0 - 24)");
        hourString = input.next();
        hourInt = Integer.parseInt(hourString);

        System.out.println("\nIn put minute: (0 - 60)");
        minuteString = input.next();
        minuteInt = Integer.parseInt(minuteString);

    }


}
