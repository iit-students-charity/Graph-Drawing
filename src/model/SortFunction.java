package model;

import controller.Controller;

public class SortFunction implements Runnable {
    public static final int FUNCTION_ID = 1;
    private int n; //длина массивов
    private int k; //количество сортируемых массивов
    private Controller controller;

    public SortFunction(int n, int k, Controller controller) {
        this.n = n;
        this.k = k;
        this.controller = controller;
    }

    @Override
    public void run() {

    }

    private void countingSort(int[] arrayToSort) {
        int n = arrayToSort.length;

        // The output character array that will have sorted arr
        int output[] = new int[n];

        // Create a count array to store count of individual
        // characters and initialize count array as 0
        int count[] = new int[256];
        for (int i = 0; i < 256; ++i)
            count[i] = 0;

        // store count of each character
        for (int i = 0; i < n; ++i)
            ++count[arrayToSort[i]];

        // Change count[i] so that count[i] now contains actual
        // position of this character in output array
        for (int i = 1; i <= 255; ++i)
            count[i] += count[i - 1];

        // Build the output character array
        // To make it stable we are operating in reverse order.
        for (int i = n - 1; i >= 0; i--) {
            output[count[arrayToSort[i]] - 1] = arrayToSort[i];
            --count[arrayToSort[i]];
        }

        // Copy the output array to arr, so that arr now
        // contains sorted characters
        for (int i = 0; i < n; ++i)
            arrayToSort[i] = output[i];
    }

    private long sortTime(int[] arrayToSort) {
        long startTime = System.nanoTime();
        countingSort(arrayToSort);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
