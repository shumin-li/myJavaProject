package persistence;

import model.TideCalculate;
import model.TideSearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTide(String key, double jday, double elevation, TideCalculate tc) {
        assertEquals(key, tc.getSearchKey());
        assertEquals(jday, tc.getJday());
        assertEquals(elevation, tc.getElevation());
    }
}
