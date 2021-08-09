package ui;

import model.FavoriteSearch;
import model.TideSearch;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TideGUI extends JFrame implements ActionListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

//    private double jday;

    private JPanel titlePanel;
    private JPanel buttonPanel;
    private SearchPanel searchPanel;
    private JPanel messagePanel;

    private JLabel messageLabel;
    private String messageString = "Message String ...";


    private JButton nowButton;
    private JButton searchButton;
    private JButton reviewButton;
    private JButton addButton;
    private JButton loadButton;
    private JButton saveButton;

    private static int year;

    private TideSearch newSearch = new TideSearch();
    private FavoriteSearch newFavorite = new FavoriteSearch();
    private Scanner input = new Scanner(System.in);

    private static final String JSON_STORE = "./data/myFavoriteTideSearch.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the TideApp application
    public TideGUI() throws FileNotFoundException {
        super("Vancouver Tide Prediction App");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeFrame();
//        runTideApp();
//        messageString = "test new string 22...";
//        messageLabel.setText(messageString);

    }

    // EFFECTS: Initialize Frame
    private void initializeFrame() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));
        setResizable(false);
        initializePanel();


        pack();
        setVisible(true);
    }


    private void initializePanel() {
        initializeTitlePanel();
        initializeMessagePanel();
        initializeButtonPanel();
        initializeSearchPanel();
    }

    private void initializeSearchPanel() {
        JPanel newPanel = new JPanel();
        newPanel.setBackground(new Color(220, 220, 220));
        add(newPanel,BorderLayout.CENTER);
    }

    private void initializeButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(120, 90));
        buttonPanel.setBackground(new Color(200, 255, 200));

        nowButton = new JButton("Now");
        nowButton.addActionListener(this);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        reviewButton = new JButton("Review");
        reviewButton.addActionListener(this);
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        loadButton = new JButton("Load");
        loadButton.addActionListener(this);
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);

        add(buttonPanel, BorderLayout.WEST);
        buttonPanel.add(nowButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(reviewButton);
        buttonPanel.add(addButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);


    }

    private void initializeMessagePanel() {
        messagePanel = new JPanel();
        messageLabel = new JLabel(messageString);
        messagePanel.setPreferredSize(new Dimension(100, 90));
        messagePanel.setBackground(new Color(255, 200, 200));
        messageLabel.setFont(new Font("MV Boli", Font.BOLD, 15));
        messagePanel.add(messageLabel);
        add(messagePanel, BorderLayout.SOUTH);


    }

    private void initializeTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setBackground(new Color(200, 200, 255));
        titlePanel.setPreferredSize(new Dimension(100, 90));

        ImageIcon icon = new ImageIcon("OceanLogoP80.png");
        JLabel titleLabel = new JLabel("Vancouver Tide Search App");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.CENTER);
        titleLabel.setHorizontalTextPosition(JLabel.LEFT);
        titleLabel.setForeground(new Color(0x0072BD));
        titleLabel.setFont(new Font("MV Boli", Font.BOLD, 35));
        titleLabel.setIcon(icon);
        titleLabel.setIconTextGap(40);

        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);
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
            messageString = "Favorite tide searched has been saved to " + JSON_STORE;
            messageLabel.setText(messageString);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadFavoriteSearch() {
        try {
            newFavorite = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
            messageString = "Data loaded from " + JSON_STORE;
            messageLabel.setText(messageString);
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
        System.out.println("\ts -> save tide search to file");
        System.out.println("\tl -> load favorite search results from file");
        System.out.println("\tquit -> quit");
    }


    public void doTideSearch(int year, int month, int day, int hour, int minute) {
        this.newSearch = new TideSearch(year, month, day, hour, minute);
        messageString = newSearch.getShortMessage();
        messageLabel.setText(messageString);
        add(new SearchPanel(newSearch), BorderLayout.CENTER);
    }

    public FavoriteSearch getFavoriteSearchList() {
        return newFavorite;
    }

    public void setFavoriteSearchList(FavoriteSearch f) {
        this.newFavorite = f;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nowButton) {
            System.out.println("Seaching Tidal Elevation For Now.");
            this.newSearch.doTidalAnalysisForNow();
            messageString = newSearch.getShortMessage();
            messageLabel.setText(messageString);
            add(new SearchPanel(newSearch),BorderLayout.CENTER);
        } else if (e.getSource() == searchButton) {
            InputWindow myWindow = new InputWindow(this);
            messageString = "Searching for the new time input...";
            messageLabel.setText(messageString);
        } else if (e.getSource() == reviewButton) {
            newFavorite.doReview();
            new ReviewWindow(this);
        } else if (e.getSource() == addButton) {
            newFavorite.addFavorite(newSearch);
            messageString = "Tide Searching has been added to favorite search list.";
            messageLabel.setText(messageString);
        } else if (e.getSource() == loadButton) {
            loadFavoriteSearch();
        } else if (e.getSource() == saveButton) {
            saveFavoriteSearch();
        }

    }
}
