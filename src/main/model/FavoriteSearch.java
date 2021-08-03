package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


// Represents a list of tidal elevation research history
public class FavoriteSearch implements Writable {
    private HashMap<String, TideCalculate> favoriteList;
    private String jsonSaveName;
    private TideCalculate tideCalculate;


    /*
     * Construct a HashMap of Favorite Search
     */
    public FavoriteSearch() {
        favoriteList = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: add a new TideSearch result on to the list of existing search history
    public void addFavorite(TideSearch tideSearch) {
        this.favoriteList.put(tideSearch.getSearchKey(), tideSearch.getTideCalculate());
    }

    // MODIFIES: this
    // add a new TideSearch result on to the list of existing search history
    public void removeFavorite(String key) {
        this.favoriteList.remove(key);
    }

    public void removeAll() {
        this.favoriteList.clear();
    }

    public int size() {
        return this.favoriteList.size();
    }


    public void doReview() {
        if (favoriteList.size() == 0) {
            System.out.println("No record!");
        } else {
            for (String key : favoriteList.keySet()) {
                TideCalculate favoriteCalculate = favoriteList.get(key);
                System.out.println("Key: " + key + ", Values: " + favoriteCalculate.shortMessage());

            }
        }

    }

    public Set<String> getKeySet() {
        return favoriteList.keySet();
    }

    public TideCalculate getTideCalculate(String key) {
        return favoriteList.get(key);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", jsonSaveName);
        json.put("searches", tideCalculateToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray tideCalculateToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String key : favoriteList.keySet()) {
            TideCalculate newCalculate = favoriteList.get(key);
            jsonArray.put(newCalculate.toJson());
        }

        return jsonArray;
    }
}
