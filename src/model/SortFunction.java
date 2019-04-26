package model;

import controller.Controller;

import java.util.Random;

public class SortFunction {
    public static final int FUNCTION_ID = 1;
    private static final int OLEG = 100000000;
    private int n; //длина массивов
    private int k; //количество сортируемых массивов
    private int currentArraySize;
    private Controller controller;

    public SortFunction(int n, int k, Controller controller) {
        this.n = n;
        this.k = k;
        this.currentArraySize = 2;
        this.controller = controller;
    }

    public void countStep() {
        int commonTime = 0;
        int currentStep = 1;
        for (int i = 0; i < currentStep; i++) {
            commonTime += sortTime(generateRandomArray());
        }
        int averageTime = commonTime / currentStep;
        currentArraySize++;
    }

    private void countingSort(int[] arrayToSort) {
        int n = arrayToSort.length;

        // The output character array that will have sorted arr
        int output[] = new int[n];

        // Create a count array to store count of individual
        // characters and initialize count array as 0
        int count[] = new int[currentArraySize];
        for (int i = 0; i < currentArraySize; ++i)
            count[i] = 0;

        // store count of each character
        for (int i = 0; i < n; ++i)
            ++count[arrayToSort[i]];

        // Change count[i] so that count[i] now contains actual
        // position of this character in output array
        for (int i = 1; i <= currentArraySize - 1; ++i)
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

    private int sortTime(int[] arrayToSort) {
        long startTime = System.nanoTime();
        countingSort(arrayToSort);
        long endTime = System.nanoTime();
        return (int) (endTime - startTime) / OLEG;
    }

    private int[] generateRandomArray() {
        int[] result = new int[currentArraySize];
        Random random = new Random();
        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(currentArraySize);
        }
        return result;
    }
}
