package model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
    private int jday1970 = 719529;

    private int yearInt;
    private int monthInt;
    private int dayInt;
    private int hourInt;
    private int minuteInt;

    private int[] monthDaysNoLeapArray = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int[] monthDaysIsLeapArray = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private String[] myMonthString;
    private int[] jadayToDatevecResult;

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
        this.yearInt = year;
        this.monthInt = month;
        this.dayInt = day;
        this.hourInt = hour;
        this.minuteInt = minute;
        jdayObj = new Jday(year, month, day, hour, minute);
        this.jday = jdayObj.getJday();
        tideCalculate = new TideCalculate(jday);
    }

    public double getTime() {
        return jday;
    }

    public String getTimeString() {
        return myMonthString[monthInt - 1] + " " + dayInt + ", " + yearInt + ", "
                + (hourInt < 10 ? "0" : "") + hourInt + ":" + (minuteInt < 10 ? "0" : "") + minuteInt + ":00";
    }

    public String getTimeString(double juliandate) {
        int[] datevec = jdayToDatevec(juliandate);
        return myMonthString[datevec[1] - 1] + " " + datevec[2] + ", " + datevec[0] + ", "
                + (datevec[3] < 10 ? "0" : "") + datevec[3] + ":" + (datevec[4] < 10 ? "0" : "") + datevec[4] + ":00";
    }


    // MODIFIES: this
    // EFFECTS: return the calculated the tidal elevation based on the given time and site
    public double getTidalElevation() {
        return tideCalculate.getElevation();  // stub
    }


    public void doTidalAnalysisForNow() {
        LocalDateTime timeNow = LocalDateTime.now();
        int year = timeNow.getYear();
        int month = timeNow.getMonthValue();
        int day = timeNow.getDayOfMonth();
        int hour = timeNow.getHour();
        int minute = timeNow.getMinute();
        Jday jdayNow = new Jday(year, month, day, hour, minute);
        tideCalculate = new TideCalculate(jdayNow.getJday());
        printTide(tideCalculate);

    }


    public void doTideSearch() {
        getInputTime();
        double inputJDay = calculateJDay(yearInt, monthInt, dayInt, hourInt, minuteInt);
        tideCalculate = new TideCalculate(inputJDay);
        printTide(tideCalculate);

    }


    private void printTide(TideCalculate tideCalculate) {
        double jdayIntPrint = tideCalculate.getJday();
        Jday jdayObjPrint = new Jday();
        System.out.println("Time input is --> " + getTimeString(jdayIntPrint));
        System.out.println("Tidal Elevation for this time is " + tideCalculate.getElevation() + " m");
        System.out.println("Next High Tide at "
                + jdayObjPrint.getJdayStringHourMinuteSeconds(tideCalculate.getNextHighPeakJday()));
        System.out.println("Next High Tide Elevation is " + tideCalculate.getNextHighPeakElevation() + " m");
        System.out.println("Next Low Tide at "
                + jdayObjPrint.getJdayStringHourMinuteSeconds(tideCalculate.getNextLowPeakJday()));
        System.out.println("Next Low Tide Elevation is " + tideCalculate.getNextLowPeakElevation() + " m");
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


    protected double calculateJDay(int year, int month, int day, int hour, int minute) {
        int numberOfDaysAfter1970;
        int plusExtraDays;
        int daysInYear;
        int numberOfYearsAfter1970 = year - 1970;
//        double hourInDouble = hour;
        plusExtraDays = (numberOfYearsAfter1970 + 1) / 4;

        daysInYear = calculateDaysInYear();
        numberOfDaysAfter1970 = 365 * numberOfYearsAfter1970 + plusExtraDays + daysInYear;


        jday = jday1970 + numberOfDaysAfter1970 + (double) hour / 24 + (double) minute / (24 * 60);

        return jday; // stub
    }

    private int calculateDaysInYear() {
        int daysInYear = 0;
        if (isLeapYear(yearInt)) {
            if (monthInt == 1) {
                daysInYear = dayInt - 1;
            } else {
                for (int monthIdx = 1; monthIdx < monthInt - 1; monthIdx++) {
                    daysInYear += monthDaysIsLeapArray[monthIdx];
                }
                daysInYear = daysInYear + dayInt - 1;
            }

        } else {
            if (monthInt == 1) {
                daysInYear = dayInt - 1;
            } else {
                for (int monthIdx = 0; monthIdx < monthInt - 1; monthIdx++) {
                    daysInYear += monthDaysNoLeapArray[monthIdx];
                }
                daysInYear = daysInYear + dayInt - 1;
            }

        }
        return daysInYear;

    }


    private boolean isLeapYear(int year) {
        return year % 4 == 0;
    }


    public int[] jdayToDatevec(double jday) {
        int numberOfDaysAfter1970 = (int) jday - jday1970;
        int plusExtraDays;
        int daysInYear;
        int[] yearFindResults = calculateNumberOfYears(numberOfDaysAfter1970);
        int year = yearFindResults[0];
        int daysRemains = yearFindResults[1];

        int[] monthFindResults = calculateNumberOfMonths(daysRemains, year);
        int month = monthFindResults[0];
        int day = monthFindResults[1];

        double hourRemains = jday - (int) jday;
        int hour = (int) (hourRemains * 24);
        double minuteRemains = hourRemains * 24 - hour;
        int minute = (int) Math.rint(minuteRemains * 60);

        return new int[]{year, month, day, hour, minute};
    }

    private int[] calculateNumberOfMonths(int days, int year) {
        int monthAttempt = 1;
        int lastDays = 1;
        while (days >= 0) {

            if (isLeapYear(year)) {
                lastDays = days + 1;
                days -= monthDaysIsLeapArray[monthAttempt];
                if (days >= 0) {
                    monthAttempt += 1;
                }
            } else {
                lastDays = days + 1;
                days -= monthDaysNoLeapArray[monthAttempt];
                if (days >= 0) {
                    monthAttempt += 1;
                }
            }
        }
        return new int[]{monthAttempt, lastDays};
    }

    private int[] calculateNumberOfYears(int num) {
        int yearAttempt = 1970;

        while (num >= 365) {
            if (isLeapYear(yearAttempt)) {
                if (num >= 366) {
                    num -= 366;
                    yearAttempt += 1;
                }
            } else {
                num -= 365;
                yearAttempt += 1;
            }
        }
        return new int[]{yearAttempt, num};
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
