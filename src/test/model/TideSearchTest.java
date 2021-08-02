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

        assertEquals(3.2724050492278476, testSearch.getTidalElevation());
    }



}