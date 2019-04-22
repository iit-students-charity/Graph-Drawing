package model;

import controller.Controller;

public class LinearFunction implements Runnable {

    public double x;
    public double beginI;
    public double endI;
    public double h = 0.1;
    public Controller controller;

    public LinearFunction(Controller controller) {

        this.controller = controller;
        beginI = controller.getXBeg();
        endI = controller.getXEnd();
    }

    public double function(double x) {
        return 5 * x - 3;
    }

    @Override
    public void run() {
        double beginX = beginI;
        double endX = endI;
        double tempFx = 0;
        double h = 0.1;
        for (double x = beginX; x <= endX; x += h) {
            tempFx = function(x);
            tempFx = Math.round(tempFx * 10d) / 10d;
            x = Math.round(x * 10d) / 10d;
            controller.addValues(x, tempFx);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
