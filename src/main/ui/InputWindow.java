package ui;

import model.Jday;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputWindow implements ActionListener {
    JFrame frame = new JFrame();
    JLabel yearLabel = new JLabel("year");
    JLabel monthLabel = new JLabel("month");
    JLabel dayLabel = new JLabel("day");
    JLabel hourLabel = new JLabel("hour");
    JLabel minuteLabel = new JLabel("minute");

    JTextField yearField = new JTextField();
    JTextField monthField = new JTextField();
    JTextField dayField = new JTextField();
    JTextField hourField = new JTextField();
    JTextField minuteField = new JTextField();

    JButton searchButton = new JButton("Search");
    JButton resetButton = new JButton("Reset");

    TideGUI myGUI;

    public InputWindow(TideGUI gui) {

        initializeLabels();
        initializeTextFields();
        initializeButtons();

        myGUI = gui;

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Time Input Window");
        frame.setSize(250, 300);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    private void initializeButtons() {
        int xbound = 35;
        int ybound = 210;
        int width = 88;
        int height = 20;
        int gap = 4;

        searchButton.setBounds(xbound, ybound, width, height);
        searchButton.addActionListener(this);
//        searchButton.addActionListener(frame);

        resetButton.setBounds(xbound + width + gap, ybound, width, height);
        resetButton.addActionListener(this);

        frame.add(searchButton);
        frame.add(resetButton);

    }

    private void initializeTextFields() {
        int width = 90;
        int xbound = 125;
        int ybound = 50;
        int ygap = 30;
        int height = 20;

        yearField.setBounds(xbound, ybound + 0 * ygap, width, height);
        monthField.setBounds(xbound, ybound + 1 * ygap, width, height);
        dayField.setBounds(xbound, ybound + 2 * ygap, width, height);
        hourField.setBounds(xbound, ybound + 3 * ygap, width, height);
        minuteField.setBounds(xbound, ybound + 4 * ygap, width, height);

        frame.add(yearField);
        frame.add(monthField);
        frame.add(dayField);
        frame.add(hourField);
        frame.add(minuteField);
    }

    private void initializeLabels() {
        int fontSize = 15;
        int width = 80;
        int xbound = 40;
        int ybound = 50;
        int ygap = 30;
        yearLabel.setBounds(xbound, ybound + 0 * ygap, width, 20);
        yearLabel.setFont(new Font(null, Font.PLAIN, fontSize));
        monthLabel.setBounds(xbound, ybound + 1 * ygap, width, 20);
        monthLabel.setFont(new Font(null, Font.PLAIN, fontSize));
        dayLabel.setBounds(xbound, ybound + 2 * ygap, width, 20);
        dayLabel.setFont(new Font(null, Font.PLAIN, fontSize));
        hourLabel.setBounds(xbound, ybound + 3 * ygap, width, 20);
        hourLabel.setFont(new Font(null, Font.PLAIN, fontSize));
        minuteLabel.setBounds(xbound, ybound + 4 * ygap, width, 20);
        minuteLabel.setFont(new Font(null, Font.PLAIN, fontSize));
        frame.add(yearLabel);
        frame.add(monthLabel);
        frame.add(dayLabel);
        frame.add(hourLabel);
        frame.add(minuteLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            int year = Integer.parseInt(yearField.getText());
            int month = Integer.parseInt(monthField.getText());
            int day = Integer.parseInt(dayField.getText());
            int hour = Integer.parseInt(hourField.getText());
            int minute = Integer.parseInt(minuteField.getText());
            myGUI.doTideSearch(year, month, day, hour, minute);
            frame.dispose();

            System.out.println("search");
        } else if (e.getSource() == resetButton) {
            yearField.setText("");
            monthField.setText("");
            dayField.setText("");
            hourField.setText("");
            minuteField.setText("");
            System.out.println("reset");
        }
    }
}
