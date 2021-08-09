package ui;

import model.FavoriteSearch;
import model.TideCalculate;

import javax.swing.*;
import java.awt.*;

public class ReviewWindow {

    private JFrame frame = new JFrame();
    private TideGUI myGUI;
    private FavoriteSearch favorites;
    private String[] favoritesStrings;




    public ReviewWindow(TideGUI gui) {
        myGUI = gui;
        favorites = myGUI.getFavoriteSearchList();
        initializeFavoriteString();
        initializeFrame();

    }

    public void initializeFrame() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new FlowLayout());
        frame.setTitle("Review Window");


        addLabels();


        frame.setVisible(true);
    }


    public void addLabels() {
        if (favoritesStrings.length == 0) {
            frame.add(new JLabel("No Record"));
        } else {

            for (int i = 0; i < favoritesStrings.length; i++) {
                String labelString = favoritesStrings[i];
                frame.add(new JLabel(labelString));
            }
        }

    }

    private void initializeFavoriteString() {
        favoritesStrings = new String[favorites.size()];
        int pointer = 0;
        for (String key : favorites.getKeySet()) {
            TideCalculate favoriteCalculate = favorites.getTideCalculate(key);
//            model.addElement(favoriteCalculate.shortMessage());
            favoritesStrings[pointer] = favoriteCalculate.shortMessage();
            pointer++;
        }

    }


}
