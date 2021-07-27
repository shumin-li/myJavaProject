package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchHistoryTest {
    private TideSearch testSearch1;
    private TideSearch testSearch2;
    private SearchHistory testHistory;

    @BeforeEach
    void runBefore() {
        testSearch1 = new TideSearch(75918, "Vancouver");
        testSearch2 = new TideSearch(75919, "Pt. Atkinson");
        testHistory = new SearchHistory();
    }

    @Test
    void testAddSearch() {
        testHistory.addSearch(testSearch1);
        assertEquals(1, testHistory.size());
        testHistory.addSearch(testSearch2);
        assertEquals(2, testHistory.size());
    }

    @Test
    void testRemoveSearch() {
        testHistory.addSearch(testSearch1);
        testHistory.addSearch(testSearch2);
        testHistory.removeSearch(testSearch1);
        assertEquals(1, testHistory.size());
        testHistory.removeSearch(testSearch2);
        assertEquals(0, testHistory);

    }


}