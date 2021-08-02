package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TideSearchTest {
    private TideSearch testSearch;
    private Jday newJday;

    @BeforeEach
    void runBefore() {
        testSearch = new TideSearch(2021, 7, 31, 13, 30);
        newJday = new Jday(2021, 7, 31, 13, 30);
    }

//    @Test
//    void testGetTimeString() {
//        assertEquals("July 31, 2021, 13:30:00", testSearch.getTimeString());
//    }

    @Test
    void testGetTidalElevation() {
        assertEquals(1, testSearch.getTidalElevation());
    }

//    @Test
//    void testJdayToDatevec() {
//        double jday = testSearch.getJday();
//        int[] testDatevec = testSearch.jdayToDatevec(jday);
//        assertEquals(2021, testDatevec[0]);
//        assertEquals(7, testDatevec[1]);
//        assertEquals(31, testDatevec[2]);
//        assertEquals(13, testDatevec[3]);
//        assertEquals(30, testDatevec[4]);
//    }

//    @Test
//    void testStringOutputOfJulianDate() {
//        double jday = testSearch.getJday();
//        assertEquals("July 31, 2021, 13:30:00", testSearch.getTimeString(jday));
//    }


}