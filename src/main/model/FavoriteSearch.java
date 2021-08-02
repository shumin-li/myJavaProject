package model;

import java.util.HashMap;


// Represents a list of tidal elevation research history
public class FavoriteSearch {
    private HashMap<String, TideCalculate> favoriteList;
    private String searchKey;
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


}
