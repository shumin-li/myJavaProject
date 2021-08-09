package ui;


import model.FavoriteSearch;
import model.TideSearch;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TideApp {

//    public static final int WIDTH = 1000;
//    public static final int HEIGHT = 600;

//    private ReviewPanel rp;
//    private SearchPanel sp;

    private TideSearch newSearch = new TideSearch();
    private FavoriteSearch newFavorite = new FavoriteSearch();
    private Scanner input = new Scanner(System.in);

    private static final String JSON_STORE = "./data/myFavoriteTideSearch.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the TideApp application
    public TideApp() throws FileNotFoundException {
//        super("Vancouver Tide Prediction App");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
//        initializeFrame();
        runTideApp();

    }

//    // EFFECTS: Initialize Frame
//    private void initializeFrame() {
//        setLayout(new BorderLayout());
//        setMinimumSize(new Dimension(WIDTH, HEIGHT));
//
//        initializePanel();
//
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        pack();
//        setVisible(true);
//    }

//
//    private void initializePanel() {
//        sp = new SearchPanel();
//        sp.setLayout(new GridLayout(1,0, 0, 5));
//        sp.setSize(new Dimension(0, 0));
//        add(sp, BorderLayout.NORTH);
//
//        JLabel label = new JLabel(" a very very very very very very very very long label");
//        label.setMinimumSize(new Dimension(800,0));
//
//
//        JButton nowButtion = new JButton("now");
//        nowButtion.addActionListener(this);
//        JButton searchButton = new JButton("search");
//        JButton reviewButton = new JButton("review");
//        JButton saveButton = new JButton("save");
//        JButton loadButton = new JButton("load");
//
//        sp.add(nowButtion);
//        sp.add(searchButton);
//        sp.add(reviewButton);
//        sp.add(saveButton);
//        sp.add(loadButton);
//
//        JPanel sp2 = new JPanel();
//        sp2.setLayout(new GridLayout(1,0, 0, 5));
//        sp2.setSize(new Dimension(0, 0));
//        add(sp2, BorderLayout.CENTER);
//        sp2.add(label);
//
//
//    }
//
//





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
//        newSearch = new TideSearch();
//        newFavorite = new FavoriteSearch();

        if (command.equals("now")) {
            this.newSearch.doTidalAnalysisForNow();
            processSave();
        } else if (command.equals("search")) {
            this.newSearch.doTideSearch();
            processSave();
        } else if (command.equals("review")) {
            this.newFavorite.doReview();
            if (newFavorite.size() != 0) {
                processDelete();
            }
        } else if (command.equals("s")) {
            saveFavoriteSearch();
        } else if (command.equals("l")) {
            loadFavoriteSearch();
        } else {
            System.out.println("Selection not valid...");
        }


    }


    private void saveFavoriteSearch() {
        try {
            jsonWriter.open();
            jsonWriter.write(newFavorite);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadFavoriteSearch() {
        try {
            newFavorite = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    private void processDelete() {
        System.out.println("Do you want to delete any of your favorite searches? ('yes' or 'no')");
        String commandForDelete = null;
        commandForDelete = input.next();
        commandForDelete = commandForDelete.toLowerCase();

        if (commandForDelete.equals("yes")) {
            doDelete();
        } else if (commandForDelete.equals("no")) {
            System.out.println("Nothing is deleted!");
        } else {
            System.out.println("Input not valid.");
        }
    }

    private void doDelete() {
        if (newFavorite.size() == 0) {
            System.out.println("Empty Records, nothing to delete!");
        } else {
            System.out.println("Which record do you want to delete?");
            System.out.println("type 'all' to delete all records "
                    + "or type the key to delete a specific record.");
            String commandWhichToDelete = null;
            commandWhichToDelete = input.next();
            commandWhichToDelete = commandWhichToDelete.toLowerCase();

            if (commandWhichToDelete.equals("all")) {
                this.newFavorite.removeAll();
                System.out.println("All records are deleted");
            } else {
                this.newFavorite.removeFavorite(commandWhichToDelete);
                System.out.println("Favorite Tide Search with 'key = " + commandWhichToDelete + "' has been deleted!");
            }
        }
    }


    private void processSave() {
        System.out.println("Do you want to save this search to your favorite search? ('yes' or 'no')");
        String commandAfterSearch = null;
        commandAfterSearch = input.next();
        commandAfterSearch = commandAfterSearch.toLowerCase();

        if (commandAfterSearch.equals("yes")) {
            this.newFavorite.addFavorite(newSearch);
        } else if (commandAfterSearch.equals("no")) {
            System.out.println("Search not saved to MyFavorite Search");
        } else {
            System.out.println("Input not valid.");
        }

    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tnow -> Tidal Elevation Information for now");
        System.out.println("\tsearch -> Search for Tidal Prediction for a Specific Time");
        System.out.println("\treview -> Review Saved Search History");
        System.out.println("\ts -> save work room to file");
        System.out.println("\tl -> load work room from file");
        System.out.println("\tquit -> quit");
    }


//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
}
