package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TideCalculateTest {
    private TideCalculate newCalculate;
    private Jday jdayObj;
    private Jday jdayObj2;
    private double jday;

    @BeforeEach
    void runBefore() {
        jdayObj = new Jday(2021,7,30,0,0);
        jday = jdayObj.getJday();
        newCalculate = new TideCalculate(jday);
    }

    @Test
    void testTimeSeriesArray() {
        double[] timeSeries;
        timeSeries = newCalculate.getJdayArray();
        double testJday = timeSeries[720];
        jdayObj2 = new Jday(testJday);
        assertEquals(2021, jdayObj2.getYear());

        assertEquals(7, jdayObj2.getMonth());
        assertEquals(29,jdayObj2.getDay());
        assertEquals(23, jdayObj2.getHour());
        assertEquals(0, jdayObj2.getMinute());
    }



    @Test
    void testTidalElevationCalculation() {
        double[] elevationArray = newCalculate.getElevationArray();
        assertEquals(4.370161331799687, elevationArray[720]);
    }

    @Test
    void testInLeapYear() {
        jdayObj = new Jday(2020, 1, 12, 23, 34);
        newCalculate = new TideCalculate(jdayObj.getJday());
        double[] elevationArray = newCalculate.getElevationArray();
        assertEquals(0.28369521836802036, elevationArray[720]);
    }

    @Test
    void testGetHighPeak() {
        assertEquals(738367.473611111 , newCalculate.getNextHighPeakJday());
        assertEquals(3.29857351483288, newCalculate.getNextHighPeakElevation());
    }

    @Test
    void testGetLowPeak() {
        assertEquals(738367.2472222223, newCalculate.getNextLowPeakJday());
        assertEquals(2.1879869276333235, newCalculate.getNextLowPeakElevation());
    }


    @Test
    void testGetElevation() {
        assertEquals(4.370161331799687, newCalculate.getElevation());
    }

    @Test
    void testShortMessaage() {
        assertEquals("July 30, 2021, 00:00:00 --> Elevation = 4.37m,"
                + " Next High Tide at 11:22 (3.3m), Next Low Tide at 05:56 (2.19m).", newCalculate.shortMessage());
    }

    @Test
    void testGetJday() {
        assertEquals(jday , newCalculate.getJday());
    }

    @Test
    void testAtPacificStandardTime() {
        jdayObj = new Jday(2024, 1, 12, 0, 0);
//        assertEquals(737802,jdayObj.getJday());
        newCalculate = new TideCalculate(jdayObj.getJday());
        double[] elevationArray = newCalculate.getElevationArray();
        assertEquals(0.7153211354356381, elevationArray[720]);
    }


}
