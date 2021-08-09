package ui;

import model.Jday;
import model.TideCalculate;
import model.TideSearch;

import javax.swing.*;
import java.awt.*;

import static java.util.Objects.isNull;

public class SearchPanel extends JPanel {

    private TideSearch tideSearch;
    private TideCalculate tideCalculate;
    private double[] jdayArray;
    private double[] elevationArray;
    private int[] xpoints = new int[2161];
    private int[] ypoints = new int[2161];

//    public SearchPanel() {
//        setBackground(new Color(220, 220, 220));
//    }

    public SearchPanel(TideSearch ts) {
        this.tideSearch = ts;

        if (!isNull(ts.getJday())) {
            this.tideCalculate = ts.getTideCalculate();
            this.jdayArray = tideCalculate.getJdayArray();
            this.elevationArray = tideCalculate.getElevationArray();
            ypoints = makeYPoints(elevationArray);
        }
        xpoints = makeXPoints();

        initialize();

    }

    private int[] makeYPoints(double[] ele) {
        for (int i = 0; i < 2161; i++) {
            ypoints[i] = elevationToY(ele[i]);
        }
        return ypoints;

    }

    public int elevationToY(double e) {
        return (int) Math.rint(374.0 - e * 72.0);
    }

    public int indexToX(int i) {
        return (int) Math.rint((double) i * 840.0 / 2161.0 + 18);
    }

    private int[] makeXPoints() {
        for (int i = 0; i < 2161; i++) {
            xpoints[i] = indexToX(i);
        }
        return xpoints;
    }

    public void initialize() {
        setBackground(new Color(220, 220, 220));
    }


    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;
        g2D.setFont(new Font("Comic Sans", Font.BOLD, 24));
        g2D.setStroke(new BasicStroke(2));
        g2D.setColor(new Color(0x0072BD));
        g2D.drawPolyline(xpoints, ypoints,2161);
        g2D.setFont(new Font("Comic Sans", Font.BOLD, 20));
        addHighTides(g2D);
        addLowTides(g2D);
        g2D.setFont(new Font("Comic Sans", Font.BOLD, 24));
        addNowPoint(g2D);

    }

    private void addLowTides(Graphics2D g2D) {
        int[] indexArray = tideCalculate.getLowPeakIdx();
        double[] elevationArray = tideCalculate.getLowPeaksElevations();
        double[] peakJdayArray = tideCalculate.getLowPeakJdays();

        int i = 0;
        while (indexArray[i] > 0) {
            drawPoint(g2D, indexToX(indexArray[i]),
                    elevationToY(elevationArray[i]),
                    new Color(0x77AC30), peakJdayArray[i]);
            i++;
        }

    }



    private void addHighTides(Graphics2D g2D) {
        int[] indexArray = tideCalculate.getHighPeakIdx();
        double[] elevationArray = tideCalculate.getHighPeaksElevations();
        double[] peakJdayArray = tideCalculate.getHighPeakJdays();
//        g2D.setColor(new Color(0xA2142F));

        int i = 0;
        while (indexArray[i] > 0) {
            drawPoint(g2D, indexToX(indexArray[i]),
                    elevationToY(elevationArray[i]),
                    new Color(0xA2142F), peakJdayArray[i]);
            i++;
        }

    }


    private void drawPoint(Graphics2D g2D, int x, int y, Color color, double jday) {
        Jday peakJday = new Jday(jday);
        g2D.setColor(color);
        g2D.fillOval(x - 5, y - 5, 10, 10);
        g2D.drawString(peakJday.getJdayStringHourMinute(), x - 25, y + 30);
    }





    private void addNowPoint(Graphics2D g2D) {
        double jdayDouble = tideCalculate.getJday();
        Jday titleJday = new Jday(jdayDouble);
        g2D.setFont(new Font("Comic Sans", Font.BOLD, 30));
        g2D.setColor(Color.darkGray);
        g2D.drawString(titleJday.getJdayString(), 10, 30);
        g2D.setColor(Color.black);
        g2D.fillOval(indexToX(720) - 7,elevationToY(elevationArray[720]) - 7,14,14);

        g2D.setFont(new Font("Comic Sans", Font.BOLD, 24));

        g2D.drawString(titleJday.getJdayStringHourMinute(), indexToX(720) - 25,
                elevationToY(elevationArray[720]) + 35);

    }


}
