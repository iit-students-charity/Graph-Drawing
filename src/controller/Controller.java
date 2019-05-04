package controller;

import model.LinearFunction;
import model.SortFunction;
import view.MainFrame;

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
        this.linearFunction = new LinearFunction(lock, window.graphic);
        this.sortFunction = new SortFunction(1, 2, lock, window.graphic);
    }


    public void startLinearFunctionThread() {
        this.linearFunction = new LinearFunction(lock, window.graphic);
        Thread LinearThread = new Thread(linearFunction);
        LinearThread.start();
    }

    public void startSortFunctionThread() {
        this.sortFunction = new SortFunction(500, 10000, lock, window.graphic);
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
