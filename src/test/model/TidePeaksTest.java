package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Test the method to find high tides and low tides
public class TidePeaksTest {
    private Jday jdayObj;
    private TideCalculate newCalculate;
    private TidePeaks newPeaks;

    @BeforeEach
    void runBefore() {
        jdayObj = new Jday(2021,7, 30, 12, 0);
        newCalculate = new TideCalculate(jdayObj.getJday());
        double[] jdayArray = newCalculate.getJdayArray();
        double[] elevationArray = newCalculate.getElevationArray();
        newPeaks = new TidePeaks(jdayArray, elevationArray);
    }

    @Test
    void testHighPeaks() {
        assertEquals(682, newPeaks.highPeakIndex[0]);
        assertEquals(1412, newPeaks.highPeakIndex[1]);
        assertEquals(1412, newPeaks.nextHighPeakIndex);
        assertEquals(4.347078253134718, newPeaks.nextHighPeakElevation);
    }

    @Test
    void testLowPeaks() {
        assertEquals(356, newPeaks.lowPeakIndex[0]);
        assertEquals(1010, newPeaks.lowPeakIndex[1]);
        assertEquals(1010, newPeaks.nextLowPeakIndex);
        assertEquals(2.308024386776875, newPeaks.nextLowPeakElevation);

    }



}
