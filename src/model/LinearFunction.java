package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class LinearFunction implements Runnable {

    public double x;
    public double beginI;
    public double endI;
    public double h = 0.1;
    private Lock lock;
    public static final int FUNCTION_ID = 0;
    private List<GraphicPoint> data;

    public LinearFunction(Lock lock) {
        this.lock = lock;
        beginI = 0;
        endI = 500;
        this.data = new ArrayList<>();
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
        lock.lock();
        try {
            for (double x = beginX; x <= endX; x += h) {
                tempFx = function(x);
                tempFx = Math.round(tempFx * 10d) / 10d;
                x = Math.round(x * 10d) / 10d;
                data.add(new GraphicPoint(x, tempFx));
                /*try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        } finally {
            lock.unlock();
        }
    }

    public List<GraphicPoint> getData() {
        lock.lock();
        List<GraphicPoint> result;
        try {
            result = this.data;
        } finally {
            lock.unlock();
        }
        return result;
    }
}
