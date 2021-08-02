package model;

import java.text.DecimalFormat;
import java.util.Arrays;

// calculate tidal elevation in Vancouver based on the given julian time, with the option to give a array of tidal chart
// and next high tide (time and elevation), next low tide (time and elevation),
// !!! remember to better implement the method of isPacificDayTime
public class TideCalculate {
    // I need a lot of fields to store the data of different tidal constituents.
    // datum, nodeFactor, avalue, speed, equilibarg, kappa
    private double jday;
    private double jdayStandard;
    private Jday jdayObj;
    private TidePeaks tidePeaks;
    private double datum = 3.06100000000000;

    private double[] nodeFactor = {
            1.08360000000000,
            1.05290000000000,
            1.12010000000000,
            1.06500000000000,
            0.987000000000000,
            0.974100000000000,
            0.961400000000000,
            0.987000000000000,
            0.987000000000000,
            1.08500000000000,
            1,
            1.08500000000000,
            1.08500000000000,
            1,
            1,
            0.987000000000000,
            0.987000000000000,
            0.987000000000000,
            1.08500000000000,
            1.03920000000000,
            0.974100000000000,
            0.987000000000000,
            1,
            1,
            1,
            0.699100000000000,
            1.30410000000000,
            1.22250000000000,
            1.09110000000000,
            0.961400000000000,
            0.974100000000000,
            1.08360000000000,
            1.10550000000000,
            1.07090000000000,
            0.974100000000000,
            1.07090000000000,
            1,
            1,
            1,
            1.08500000000000,
            1.05290000000000,
            1.08360000000000,
            1.08500000000000
    };

    private double[] avalue = {
            0.0460000000000000,
            0.856000000000000,
            0.0650000000000000,
            0.0320000000000000,
            0.931000000000000,
            0.0130000000000000,
            0.0120000000000000,
            0.194000000000000,
            0.0210000000000000,
            0.471000000000000,
            0.269000000000000,
            0.0760000000000000,
            0.00900000000000000,
            0.229000000000000,
            0.0150000000000000,
            0.0130000000000000,
            0.0100000000000000,
            0.0390000000000000,
            0.0150000000000000,
            0.0100000000000000,
            0.00500000000000000,
            0.00600000000000000,
            0.0360000000000000,
            0.0650000000000000,
            0.0170000000000000,
            0.0420000000000000,
            0.0340000000000000,
            0.00200000000000000,
            0.00400000000000000,
            0.00600000000000000,
            0.0100000000000000,
            0.00600000000000000,
            0.00600000000000000,
            0.0130000000000000,
            0.00500000000000000,
            0.0410000000000000,
            0.0160000000000000,
            0.0160000000000000,
            0.0150000000000000,
            0.0160000000000000,
            0.00500000000000000,
            0.0350000000000000,
            0.00600000000000000
    };

    private double[] speed = {
            15.5854433000000,
            15.0410686000000,
            30.0821373000000,
            29.5284789000000,
            28.9841042000000,
            57.9682084000000,
            86.9523126000000,
            28.4397295000000,
            27.8953548000000,
            13.9430356000000,
            14.9589314000000,
            13.3986609000000,
            12.8542862000000,
            30,
            29.9589333000000,
            29.4556253000000,
            27.9682084000000,
            28.5125831000000,
            13.4715145000000,
            44.0251729000000,
            57.4238337000000,
            58.9841042000000,
            0.0821373000000000,
            0.0410667000000000,
            1.09803310000000,
            15.0000020000000,
            16.1391017000000,
            30.0410667000000,
            88.0503457000000,
            86.4079379000000,
            87.9682084000000,
            14.5695476000000,
            59.0662415000000,
            42.9271398000000,
            30.5443747000000,
            14.4966939000000,
            15.1232059000000,
            14.9178647000000,
            15.0821353000000,
            12.9271398000000,
            45.0410686000000,
            16.0569644000000,
            43.9430356000000
    };

    private double[] equilibarg = {
            269.740000000000,
            3.07000000000000,
            185.620000000000,
            17.2600000000000,
            303.980000000000,
            247.950000000000,
            191.930000000000,
            33.9700000000000,
            123.960000000000,
            304.240000000000,
            349.130000000000,
            34.2400000000000,
            124.230000000000,
            0,
            2.43000000000000,
            267.990000000000,
            249.960000000000,
            159.970000000000,
            160.230000000000,
            307.040000000000,
            337.940000000000,
            303.980000000000,
            201.750000000000,
            357.570000000000,
            255.770000000000,
            28.4800000000000,
            228.150000000000,
            183.350000000000,
            73.5700000000000,
            281.920000000000,
            247.950000000000,
            215.720000000000,
            129.590000000000,
            248.220000000000,
            270.010000000000,
            89.7200000000000,
            212.620000000000,
            351.550000000000,
            8.45000000000000,
            250.230000000000,
            3.07000000000000,
            53.7500000000000,
            304.240000000000
    };

    private double[] kappa = {
            205.700000000000,
            170.200000000000,
            183.900000000000,
            181.700000000000,
            166.600000000000,
            176.300000000000,
            133.400000000000,
            143.800000000000,
            120.300000000000,
            156.100000000000,
            169.800000000000,
            154.800000000000,
            162.900000000000,
            189.100000000000,
            177.800000000000,
            177.400000000000,
            65.4000000000000,
            148.900000000000,
            147.200000000000,
            242,
            163.300000000000,
            184.600000000000,
            224,
            347,
            124,
            124.600000000000,
            208.800000000000,
            190,
            151.300000000000,
            108.800000000000,
            158.300000000000,
            173.600000000000,
            282.300000000000,
            289.400000000000,
            20.8000000000000,
            189.900000000000,
            187.500000000000,
            169.700000000000,
            146.900000000000,
            206.100000000000,
            293.600000000000,
            287.700000000000,
            282
    };


    // Create a per-minute array of double values (size 2161), half day before, and one day after
    private int arraySize = 2161;
    private int locationInputJday = 720; // arrayJday[locationInputJday] will give us the same as jday;
    private double[] jdayArray = new double[arraySize];
    private double[] elevationArray = new double[arraySize];


    public double[] getJdayArray() {
        return jdayArray;
    }

    public double getJday() {
        return jday;
    }

    //EFFECTS: Construct a TideCalculate Object based on given julian time
    public TideCalculate(double jday) {
        this.jday = jday;

        if (isPacificDayTime(jday)) {
            this.jdayStandard = jday - 1.0 / 24;
        } else {
            this.jdayStandard = jday;
        }

        setJdayArray();
        calculateElevationArray();
        this.tidePeaks = new TidePeaks(jdayArray, elevationArray);
    }

    private void setJdayArray() {

        for (int i = 0; i < arraySize; i++) {
            jdayArray[i] = jdayStandard - 0.5 + (double) i / 24 / 60;
        }

    }


    // Function: isPacificDayTime
    // !!! Note: this function currently is just an approximation, need to further implement it later.
    private boolean isPacificDayTime(double jday) {
        jdayObj = new Jday(jday);
        int month = jdayObj.getMonth();
        int day = jdayObj.getDay();
        int hour = jdayObj.getHour();
        if (month > 3 && month <= 11) {
            return true;
        } else {
            return false;
        } // stub;
    }

    // Function: TideArrayCalculate; return a array of tidal elevations;


    public double[] getElevationArray() {
        return elevationArray;
    }

    public void calculateElevationArray() {
        jdayObj = new Jday(jdayStandard);
        int numOfYearAfter1970 = jdayObj.getYear() - 1970 + 1;
        double numOfHoursThisYear = 0;

        for (int i = 0; i < arraySize; i++) {
            jdayObj = new Jday(jdayArray[i]);
            Jday jdayYearStart = new Jday(jdayObj.getYear(), 1, 1, 0, 0);
            double jdayThisYear = jdayYearStart.getJday();
            numOfHoursThisYear = (jdayArray[i] - jdayThisYear) * 24;

            double[] resultStep1 = doStep1();
            double[] resultStep2 = doStep2(numOfHoursThisYear);
            double[] resultStep3 = doStep3(resultStep2);
            double[] resultStep4 = doStep4(resultStep1, resultStep3);
            double resultStep5 = doStep5(resultStep4);
            elevationArray[i] = resultStep5;
        }

        this.elevationArray = elevationArray;
    }

    private double[] doStep1() {
        return arrayMultiplication(nodeFactor, avalue);
    }

    private double[] doStep2(double t) {
        double[] sub1 = arrayMultiplication(speed,t);
        double[] sub2 = arrayMultiplication(kappa, -1.0);
        return arrayPlus(sub1, sub2, equilibarg);
    }

    private double[] doStep3(double[] res2) {
        double radCons = Math.PI / 180.0;
        double[] radius = arrayMultiplication(res2, radCons);
        return arrayCos(radius);
    }

    private double[] doStep4(double[] res1, double[] res3) {
        return arrayMultiplication(res1, res3);
    }

    private double doStep5(double[] res4) {
        return Arrays.stream(res4).sum() + datum;
    }

    private double[] arrayCos(double[] array) {
        double[] array3 = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            array3[i] = Math.cos(array[i]);
        }
        return array3;
    }



    private double[] arrayMultiplication(double[] array1, double[] array2) {
        double[] array3 = new double[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array3[i] = array1[i] * array2[i];
        }
        return array3;
    }

    private double[] arrayMultiplication(double[] array1, double number2) {
        double[] array3 = new double[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array3[i] = array1[i] * number2;
        }
        return array3;
    }

    private double[] arrayPlus(double[] array1, double number2) {
        double[] array3 = new double[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array3[i] = array1[i] + number2;
        }
        return array3;
    }

    private double[] arrayPlus(double[] array1, double[] array2) {
        double[] array3 = new double[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array3[i] = array1[i] + array2[i];
        }
        return array3;
    }



    private double[] arrayPlus(double[] array1, double[] array2, double[] array4) {
        double[] array3 = new double[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array3[i] = array1[i] + array2[i] + array4[i];
        }
        return array3;
    }



    public double getNextHighPeakJday() {
        if (isPacificDayTime(jday)) {
            return tidePeaks.nextHighPeakJday + 1.0 / 24;
        } else {
            return tidePeaks.nextHighPeakJday;
        }
    }

    public double getNextHighPeakElevation() {
        return tidePeaks.nextHighPeakElevation;
    }

    public double getNextLowPeakJday() {
        if (isPacificDayTime(jday)) {
            return tidePeaks.nextLowPeakJday + 1.0 / 24;
        } else {
            return tidePeaks.nextLowPeakJday;
        }
    }

    public double getNextLowPeakElevation() {
        return tidePeaks.nextLowPeakElevation;
    }

    public double getElevation() {
        return elevationArray[locationInputJday];
    }

    public String shortMessage() {
        Jday jdayMessage = new Jday(jday);
        return jdayMessage.getJdayString() + " --> Elevation = "
                + new DecimalFormat("##.##").format(elevationArray[locationInputJday]) + "m, "
                + "Next High Tide at " + jdayMessage.getJdayStringHourMinute(getNextHighPeakJday())
                + " (" +  new DecimalFormat("##.##").format(getNextHighPeakElevation()) + "m), "
                + "Next Low Tide at " + jdayMessage.getJdayStringHourMinute(getNextLowPeakJday())
                + " (" +  new DecimalFormat("##.##").format(getNextLowPeakElevation()) + "m).";
    }


}
