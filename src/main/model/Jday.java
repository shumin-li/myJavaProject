package model;

// This class can store, return and manipulate Julian date
public class Jday {

    // Fields
    private double jday;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int[] datevec = {year, month, day, hour, minute};

    private int jday1970 = 719529;
    private int[] monthDaysNoLeapArray = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int[] monthDaysIsLeapArray = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private String[] myMonthString;

    {
        myMonthString = new String[]{"January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November", "December"};
    }


    // EFFECTS: Construct a Jday Object
    public Jday() {

    }

    // EFFECTS: Construct a Jday Object based on given jday value
    public Jday(double jday) {
        this.jday = jday;
        this.datevec = jdayToDatevec(jday);
        this.year = datevec[0];
        this.month = datevec[1];
        this.day = datevec[2];
        this.hour = datevec[3];
        this.minute = datevec[4];
    }

    // EFFECTS: Construct a Jday Object based on given year, month, day, hour, minute
    public Jday(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.jday = calculateJday(year, month, day, hour, minute);
    }


    public double getJday() {
        return jday;
    }

    public int[] getDatevec() {
        return datevec;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setJday(double jday) {
        this.jday = jday;
        this.datevec = jdayToDatevec(jday);
        this.year = datevec[0];
        this.month = datevec[1];
        this.day = datevec[2];
        this.hour = datevec[3];
        this.minute = datevec[4];
    }

    public double calculateJday(int year, int month, int day, int hour, int minute) {
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
        if (isLeapYear(year)) {
            if (month == 1) {
                daysInYear = day - 1;
            } else {
                for (int monthIdx = 1; monthIdx < month - 1; monthIdx++) {
                    daysInYear += monthDaysIsLeapArray[monthIdx];
                }
                daysInYear = daysInYear + day - 1;
            }

        } else {
            if (month == 1) {
                daysInYear = day - 1;
            } else {
                for (int monthIdx = 0; monthIdx < month - 1; monthIdx++) {
                    daysInYear += monthDaysNoLeapArray[monthIdx];
                }
                daysInYear = daysInYear + day - 1;
            }

        }
        return daysInYear;
    }

    private boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    public int[] jdayToDatevec(double jday) {
        int numberOfDaysAfter1970 = (int) jday - jday1970;
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


    public String getJdayString(double jday) {
        int[] returnedDatevec = jdayToDatevec(jday);
        int yearInt = returnedDatevec[0];
        int monthInt = returnedDatevec[1];
        int dayInt = returnedDatevec[2];
        int hourInt = returnedDatevec[3];
        int minuteInt = returnedDatevec[4];

        return myMonthString[monthInt - 1] + " " + dayInt + ", " + yearInt + ", "
                + (hourInt < 10 ? "0" : "") + hourInt + ":" + (minuteInt < 10 ? "0" : "") + minuteInt + ":00";
    }

    public String getJdayString() {
        return myMonthString[month - 1] + " " + day + ", " + year + ", "
                + (hour < 10 ? "0" : "") + hour + ":" + (minute < 10 ? "0" : "") + minute + ":00";
    }

    public String getJdayStringHourMinuteSeconds(double jday) {
        int[] returnedDatevec = jdayToDatevec(jday);

        int hourInt = returnedDatevec[3];
        int minuteInt = returnedDatevec[4];

        return (hourInt < 10 ? "0" : "") + hourInt + ":" + (minuteInt < 10 ? "0" : "") + minuteInt;
    }

    public String getJdayStringHourMinuteSeconds() {
        return (hour < 10 ? "0" : "") + hour + ":" + (minute < 10 ? "0" : "") + minute;
    }


    public String getTimeString(double juliandate) {
        int[] datevec = jdayToDatevec(juliandate);
        return myMonthString[datevec[1] - 1] + " " + datevec[2] + ", " + datevec[0] + ", "
                + (datevec[3] < 10 ? "0" : "") + datevec[3] + ":" + (datevec[4] < 10 ? "0" : "") + datevec[4] + ":00";
    }


}
