package model;

import java.util.ArrayList;
import java.util.List;


// Represents a list of tidal elevation research history
public class SearchHistory {
    private ArrayList<TideSearch> newHistory;


    /*
     * Construct a SearchHistory
     */
    public SearchHistory() {
        newHistory = new ArrayList<TideSearch>();
    }

    // MODIFIES: this
    // EFFECTS: add a new TideSearch result on to the list of existing search history
    public void addSearch(TideSearch tideSearch) {
        // stub;

    }

    // MODIFIES: this
    // add a new TideSearch result on to the list of existing search history
    public void removeSearch(TideSearch tideSearch) {
        // stub;

    }

    // EFFECTS: get the size of the current SearchHistory List
    public int size() {
        return 1;
    }


    public void doReviewHistory() {
        System.out.println("Pretend there is a list of research Result");

    }


}
