package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TideSearchTest {
    private TideSearch testSearch;

    @BeforeEach
    void runBefore() {
        testSearch = new TideSearch(75918, "Vancouver");
    }

    @Test
    void testConstructor() {
        assertEquals(75918, testSearch.getTime());
        assertEquals("Vancouver", testSearch.getSite());
    }

    @Test
    void testGetTidalElevation() {
        assertEquals(1, testSearch.getTidalElevation());
    }


}