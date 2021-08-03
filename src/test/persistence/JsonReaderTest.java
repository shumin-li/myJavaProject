package persistence;

import model.FavoriteSearch;
import model.TideSearch;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FavoriteSearch fs = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFavoriteTideSearch() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyFavoriteTideSearch.json");
        try {
            FavoriteSearch fs = reader.read();
            assertEquals(0, fs.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFavoriteTideSearch() {
        JsonReader reader = new JsonReader("./data/testReaderGeneral.json");
        try {
            FavoriteSearch fs = reader.read();
            Set<String> keySet = fs.getKeySet();
            assertEquals(2, fs.size());
;
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
