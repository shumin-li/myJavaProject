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


}
