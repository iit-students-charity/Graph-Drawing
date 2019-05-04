package model;

import view.GraphicComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class LinearFunction implements Runnable, Function {

    public double x;
    public double beginI;
    public double endI;
    private Lock lock;
    private GraphicPoint firstPoint;
    private GraphicPoint secondPoint;
    public static final int FUNCTION_ID = 0;
    private List<GraphicPoint> data;
    private double step;
    private GraphicComponent graphicComponent;

    public LinearFunction(Lock lock, GraphicComponent graphic) {
        this.lock = lock;
        beginI = 0;
        endI = 500;
        this.data = new ArrayList<>();
        this.step = 0.1;
        this.graphicComponent = graphic;
    }

    public double function(double x) {
        return 5 * x - 3;
    }

    @Override
    public void run() {
        double beginX = beginI;
        double endX = endI;
        double tempFx = 0;
        for (double x = beginX; x <= endX; x += step) {
            tempFx = function(x);
            tempFx = Math.round(tempFx * 10d) / 10d;
            x = Math.round(x * 10d) / 10d;
            secondPoint = new GraphicPoint(x, tempFx);
            data.add(secondPoint);
            lock.lock();
            try {
                graphicComponent.addValue(FUNCTION_ID, secondPoint);
                graphicComponent.repaint();
            } finally {
                lock.unlock();
            }
            try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
            }
        }

    }


    public List<GraphicPoint> getData() {
        //lock.lock();
        List<GraphicPoint> result;
        //try {
        result = this.data;
        // } finally {
        //    lock.unlock();
        //}
        return result;
    }
}
