package model;

import controller.Controller;
import view.GraphicComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class SortFunction implements Runnable {
    public static final int FUNCTION_ID = 1;
    private static final int STRAP_DIGIT = 100;
    private int n; //длина массивов
    private int k; //количество сортируемых массивов
    private int currentArraySize;
    private Controller controller;
    private Lock lock;
    private List<GraphicPoint> data;
    private GraphicComponent graphicComponent;

    public SortFunction(int n, int k, Lock lock, GraphicComponent graphicComponent) {
        this.n = n;
        this.k = k;
        this.currentArraySize = 2;
        this.lock = lock;
        data = new ArrayList<>();
        this.graphicComponent = graphicComponent;
    }

    @Override
    public void run() {
        for (int currentSize = 2; currentSize < n; currentSize++) {
            lock.lock();
            currentArraySize = currentSize;
            try {
                int commonTime = 0;
                int currentStep;
                for (int currentArrayCount = 1; currentArrayCount < k; currentArrayCount++) {
                    commonTime += sortTime(generateRandomArray());


                }
                int averageTime = commonTime / k;
                //data.add(new GraphicPoint(currentArraySize, averageTime));
                graphicComponent.addValue(FUNCTION_ID, new GraphicPoint(currentArraySize, averageTime));
                System.out.print(currentArraySize);
                System.out.print(" ");
                System.out.println(averageTime);
            } finally {
                lock.unlock();
            }
            /*try {
                Thread.sleep(500);
            }
            catch (InterruptedException e)
            {
                System.out.println("thread interrupted");
            }*/

        }
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

    private long sortTime(int[] arrayToSort) {
        long startTime = System.nanoTime();
        countingSort(arrayToSort);
        long endTime = System.nanoTime();
        return (endTime - startTime) / STRAP_DIGIT;
    }

    private int[] generateRandomArray() {
        int[] result = new int[currentArraySize];
        Random random = new Random();
        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(currentArraySize);
        }
        return result;
    }

    public List<GraphicPoint> getData() {
        List<GraphicPoint> result;
        lock.lock();
        try {
            result = this.data;
        } finally {
            lock.unlock();
        }
        return result;
    }
}
