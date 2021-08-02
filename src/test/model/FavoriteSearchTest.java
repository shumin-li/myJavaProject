package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Writable;

import static org.junit.jupiter.api.Assertions.*;

class FavoriteSearchTest {
    private TideSearch testSearch1;
    private TideSearch testSearch2;
    private TideSearch testSearch3;
    private FavoriteSearch testFavoriate = new FavoriteSearch();

    @BeforeEach
    void runBefore() {
        testSearch1 = new TideSearch(2021, 7, 30, 12, 23);
        testSearch2 = new TideSearch(1970, 12, 21, 3, 12);
        testSearch3 = new TideSearch(2029, 5, 4, 9, 43);
    }

    @Test
    void testAddsearchToFavorite() {
        assertEquals(0, testFavoriate.size());
        testFavoriate.addFavorite(testSearch1);
        assertEquals(1, testFavoriate.size());
        testFavoriate.addFavorite(testSearch2);
        assertEquals(2, testFavoriate.size());
    }

    @Test
    void testAddDuplicatedRecords() {
        assertEquals(0, testFavoriate.size());
        testFavoriate.addFavorite(testSearch1);
        assertEquals(1, testFavoriate.size());
        testFavoriate.addFavorite(testSearch1);
        assertEquals(1, testFavoriate.size());
    }

    @Test
    void testRemoveFavorite() {
        testFavoriate.addFavorite(testSearch1);
        assertEquals(1, testFavoriate.size());
        testFavoriate.removeFavorite(testSearch1.getSearchKey());
        assertEquals(0, testFavoriate.size());
    }

    @Test
    void testRemoveAll() {
        testFavoriate.addFavorite(testSearch1);
        testFavoriate.addFavorite(testSearch2);
        testFavoriate.addFavorite(testSearch3);
        assertEquals(3, testFavoriate.size());
        testFavoriate.removeAll();
        assertEquals(0, testFavoriate.size());
    }


}