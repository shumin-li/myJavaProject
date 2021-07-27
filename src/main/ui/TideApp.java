package ui;


import model.SearchHistory;
import model.TideSearch;

import java.util.Locale;
import java.util.Scanner;

public class TideApp {

    private TideSearch newSearch;
    private SearchHistory newHistory;
//    private TideSearch winterSearch;
    private SearchHistory yearHistory;
    private Scanner input = new Scanner(System.in);

    private String yearString;
    private String monthString;
    private String dayString;
    private String hourString;
    private String minuteString;

    private int yearInt;
    private int monthInt;
    private int dayInt;
    private int hourInt;
    private int minuteInt;

    // EFFECTS: runs the TideApp application
    public TideApp() {
        runTideApp();
    }

    // MODIFIES: this
    // Effects: processes user input
    private void runTideApp() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }



        }
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        newSearch = new TideSearch();
        newHistory = new SearchHistory();

        if (command.equals("now")) {
            newSearch.doTidalAnalysisForNow();
        } else if (command.equals("search")) {
            newSearch.doTideSearch();
        } else if (command.equals("review")) {
            newHistory.doReviewHistory();
        } else {
            System.out.println("Selection not valid...");
        }
    }



    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tnow -> Tidal Elevation Information for now");
        System.out.println("\tsearch -> Search for Tidal Prediction for a Specific Time");
        System.out.println("\treview -> Review Saved Search History");
        System.out.println("\tquit -> quit");
    }






}
