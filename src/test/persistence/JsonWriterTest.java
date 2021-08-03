package persistence;

import model.FavoriteSearch;
import model.Jday;
import model.TideCalculate;
import model.TideSearch;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            FavoriteSearch fs = new FavoriteSearch();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFavoriteSearch() {
        try {
            FavoriteSearch fs = new FavoriteSearch();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFavoriteTideSearch.json");
            writer.open();
            writer.write(fs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFavoriteTideSearch.json");
            fs = reader.read();
            assertEquals(0, fs.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFavoriteSearch() {
        try {
            FavoriteSearch fs = new FavoriteSearch();
            fs.addFavorite(new TideSearch(2021,1,2,3,4));
            fs.addFavorite(new TideSearch(2021,3,4,5,6));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFavoriteTideSearch.json");
            writer.open();
            writer.write(fs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFavoriteTideSearch.json");
            fs = reader.read();
            assertEquals(2, fs.size());
            Jday testJday = new Jday();
            checkTide("202101020304", testJday.calculateJday(2021,1,2,3,4),
                    1.1850648259871728, fs.getTideCalculate("202101020304"));
            checkTide("202103040506", testJday.calculateJday(2021,3,4,5,6),
                    3.1490361388238295, fs.getTideCalculate("202103040506"));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
