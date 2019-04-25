package controller;

import view.MainFrame;

import java.util.List;

public class Controller {
    public MainFrame window;
    public double xBeg;
    public double xEnd;

    public Controller(MainFrame window) {
        this.window = window;
    }

    public synchronized void addValues(double x, double fx) {
        window.addValues(x, fx);
        window.update();
    }


    public List<List<Double>> getValues() {
        return window.getValues();

    }

    public synchronized void clear() {
        if (window.getValues().isEmpty() == false)
            window.clear();
        window.update();
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
