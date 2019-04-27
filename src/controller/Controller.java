package controller;

import model.GraphicPoint;
import model.LinearFunction;
import model.SortFunction;
import view.MainFrame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class Controller {
    public MainFrame window;
    public double xBeg;
    public double xEnd;
    private LinearFunction linearFunction;
    private SortFunction sortFunction;
    private Lock lock;

    public Controller(MainFrame window, Lock lock) {
        this.window = window;
        this.lock = lock;
        this.linearFunction = new LinearFunction(lock);
        this.sortFunction = new SortFunction(1, 2, lock);
    }


    public List<List<Double>> getValues() {
        return window.getValues();

    }


    public List<List<Double>> getLinearFunctionData() {
        List<List<Double>> result = new ArrayList<>();
        for (GraphicPoint i : linearFunction.getData()) {
            result.add(new ArrayList<>(Arrays.asList(i.getX(), i.getY())));
        }
        return result;
    }

    public void startLinearFunctionThread() {
        this.linearFunction = new LinearFunction(lock);
        Thread LinearThread = new Thread(linearFunction);
        LinearThread.start();
    }

    public void startSortFunctionThread() {
        this.sortFunction = new SortFunction(500, 10000, lock);
        Thread sortThread = new Thread(sortFunction);
        sortThread.start();
    }



    public void setXBeg(double xBeg) {
        this.xBeg = xBeg;
    }

    public void setXEnd(double xEnd) {
        this.xEnd = xEnd;
    }


    public double getXBeg() {
        return xBeg;
    }

    public double getXEnd() {
        return xEnd;
    }


}
