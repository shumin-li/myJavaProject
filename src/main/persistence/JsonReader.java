package persistence;

import model.FavoriteSearch;
import model.Jday;
import model.TideSearch;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Cite: This class is written based on the JsonSerializationDemo project provided in UBC CPSC 210
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Julian dates from file and returns a tideSearchList;
    // throws IOException if an error occurs reading data from file
    public FavoriteSearch read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFavoriteSearch(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private FavoriteSearch parseFavoriteSearch(JSONObject jsonObject) {
        FavoriteSearch favoriteSearch = new FavoriteSearch();
        addSearches(favoriteSearch, jsonObject);
        return favoriteSearch;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addSearches(FavoriteSearch fs, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("searches");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addSearch(fs, nextThingy);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addSearch(FavoriteSearch fs, JSONObject jsonObject) {
        String searchKey = jsonObject.getString("key");

        String yearStr = searchKey.substring(0,4);
        int year = Integer.parseInt(yearStr);
        String monthStr = searchKey.substring(4,6);
        int month = Integer.parseInt(monthStr);
        String dayStr = searchKey.substring(6,8);
        int day = Integer.parseInt(dayStr);
        String hourStr = searchKey.substring(8,10);
        int hour = Integer.parseInt(hourStr);
        String minuteStr = searchKey.substring(10,12);
        int minute = Integer.parseInt(minuteStr);

        TideSearch newSearch = new TideSearch(year, month, day, hour, minute);
        fs.addFavorite(newSearch);
    }


}
