package model;

// This class deals with the index, time and elevation of of tidal elevation array at high tides and low tides
public class TidePeaks {

    // Fields

    double[] jdayArray;
    double[] elevationArray;
    int locationOfGivenTimeInArray = 720;

    int[] highPeakIndex = new int[5];
    double[] highPeakJdays = new double[5];
    double[] highPeakElevations = new double[5];

    int nextHighPeakIndex;
    double nextHighPeakJday;
    double nextHighPeakElevation;


    int[] lowPeakIndex = new int[5];
    double[] lowPeakJdays = new double[5];
    double[] lowPeakElevations = new double[5];

    int nextLowPeakIndex;
    double nextLowPeakJday;
    double nextLowPeakElevation;

    // Construct: Construct a TidePeaks Object based on a time series of jdayArray and elevationArray

    public TidePeaks(double[] jdayArray, double[] elevationArray) {
        this.jdayArray = jdayArray;
        this.elevationArray = elevationArray;
        doPeakFindingCalculation();
    }


    private void doPeakFindingCalculation() {
        findHighPeaks();
        findNextHighPeak();
        findLowPeaks();
        findNextLowPeak();
    }

    private void findHighPeaks() {
        int n = 0;
        for (int i = 1; i < elevationArray.length - 1; i++) {
            if (elevationArray[i - 1] < elevationArray[i] && elevationArray[i] > elevationArray[i + 1]) {
                highPeakIndex[n] = i;
                highPeakJdays[n] = jdayArray[i];
                highPeakElevations[n] = elevationArray[i];
                n += 1;
            }
        }
    }

    private void findNextHighPeak() {
        int n = 0;
        while (highPeakIndex[n] < locationOfGivenTimeInArray) {
            n += 1;
        }
        nextHighPeakIndex = highPeakIndex[n];
        nextHighPeakJday = jdayArray[nextHighPeakIndex];
        nextHighPeakElevation = elevationArray[nextHighPeakIndex];
    }

    private void findLowPeaks() {
        int n = 0;
        for (int i = 1; i < elevationArray.length - 1; i++) {
            if (elevationArray[i - 1] > elevationArray[i] && elevationArray[i] < elevationArray[i + 1]) {
                lowPeakIndex[n] = i;
                lowPeakJdays[n] = jdayArray[i];
                lowPeakElevations[n] = elevationArray[i];
                n += 1;
            }
        }
    }

    private void findNextLowPeak() {
        int n = 0;
        while (lowPeakIndex[n] < locationOfGivenTimeInArray) {
            n += 1;
        }
        nextLowPeakIndex = lowPeakIndex[n];
        nextLowPeakJday = jdayArray[nextLowPeakIndex];
        nextLowPeakElevation = elevationArray[nextLowPeakIndex];
    }


}
