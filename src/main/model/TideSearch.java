package model;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Scanner;

// Represents a search of tidal elevation for the given time and location
public class TideSearch {

    private Scanner input = new Scanner(System.in);

    private String yearString;
    private String monthString;
    private String dayString;
    private String hourString;
    private String minuteString;
    private int jday1970 = 719529;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    private int searchID;
    private String searchKey;

    private int[] monthDaysNoLeapArray = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int[] monthDaysIsLeapArray = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private String[] myMonthString;

    {
        myMonthString = new String[]{"January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November", "December"};
    }

    private double jday;
    private TideCalculate tideCalculate;
    private Jday jdayObj;


    // EFFECTS: an empty construct;
    public TideSearch() {

    }


    /*
     * REQUIRES: time must be > 0, and location must be one of the few locations
     * EFFECTS: Construct a TideSearch class and set the time and site as given
     */
    public TideSearch(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        jdayObj = new Jday(year, month, day, hour, minute);
        this.jday = jdayObj.getJday();
        tideCalculate = new TideCalculate(jday);
        setSearchID();
        setSearchKey();
    }

    public double getJday() {
        return jday;
    }


    // MODIFIES: this
    // EFFECTS: return the calculated the tidal elevation based on the given time and site
    public double getTidalElevation() {
        return tideCalculate.getElevation();  // stub
    }


    public void doTidalAnalysisForNow() {
        LocalDateTime timeNow = LocalDateTime.now();
        this.year = timeNow.getYear();
        this.month = timeNow.getMonthValue();
        this.day = timeNow.getDayOfMonth();
        this.hour = timeNow.getHour();
        this.minute = timeNow.getMinute();
        Jday jdayNow = new Jday(year, month, day, hour, minute);
        this.jday = jdayNow.getJday();
        setSearchID();
        setSearchKey();
        tideCalculate = new TideCalculate(jdayNow.getJday());
        printTide(tideCalculate);

    }


    public void doTideSearch() {
        getInputTime();
        Jday jdaySearch = new Jday(year, month, day, hour, minute);
        this.jday = jdaySearch.getJday();
        setSearchID();
        setSearchKey();
        tideCalculate = new TideCalculate(jdaySearch.getJday());
        printTide(tideCalculate);

    }


    private void printTide(TideCalculate tideCalculate) {
        Jday jdayObjPrint = new Jday(tideCalculate.getJday());
        System.out.println("Time input is --> " + jdayObjPrint.getJdayString());
        System.out.println("Tidal Elevation for this time is "
                + new DecimalFormat("##.##").format(tideCalculate.getElevation()) + " m");
        System.out.println("Next High Tide at "
                + jdayObjPrint.getJdayStringHourMinute(tideCalculate.getNextHighPeakJday()));
        System.out.println("Next High Tide Elevation is "
                + new DecimalFormat("##.##").format(tideCalculate.getNextHighPeakElevation()) + " m");
        System.out.println("Next Low Tide at "
                + jdayObjPrint.getJdayStringHourMinute(tideCalculate.getNextLowPeakJday()));
        System.out.println("Next Low Tide Elevation is "
                + new DecimalFormat("##.##").format(tideCalculate.getNextLowPeakElevation()) + " m");
    }



    private void getInputTime() {
        System.out.println("\nIn put year: (1970 to 2038)");
        yearString = input.next();
        year = Integer.parseInt(yearString);

        System.out.println("\nIn put month: (1 - 12)");
        monthString = input.next();
        month = Integer.parseInt(monthString);

        System.out.println("\nIn put day: (1 - 31)");
        dayString = input.next();
        day = Integer.parseInt(dayString);

        System.out.println("\nIn put hour: (0 - 24)");
        hourString = input.next();
        hour = Integer.parseInt(hourString);

        System.out.println("\nIn put minute: (0 - 60)");
        minuteString = input.next();
        minute = Integer.parseInt(minuteString);

    }


    public void setSearchID() {
        this.searchID = (int) Math.rint((jday - jday1970) * 24.0 * 60.0);
    }

    public int getSearchID() {
        return searchID;
    }


    public TideCalculate getTideCalculate() {
        return tideCalculate;
    }

    public void setSearchKey() {
        this.searchKey = Integer.toString(year) + (month < 10 ? "0" : "") + Integer.toString(month)
                + (day < 10 ? "0" : "") + Integer.toString(day) + (hour < 10 ? "0" : "")
                + Integer.toString(hour) + (minute < 10 ? "0" : "") + Integer.toString(minute);
    }

    public String getSearchKey() {
        return searchKey;
    }


}
