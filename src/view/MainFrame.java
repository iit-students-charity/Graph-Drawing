package view;

import controller.Controller;
import model.LinearFunction;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MainFrame {
    public JFrame frame;
    public Controller controller;
    private int width = 900;
    private int height = 700;
    public double tempXBeg, tempXEnd;
    private TaskPanel taskPanel;
    public PointsTable mainPointsTable;
    public GraphicComponent graphic;
    public LinearFunction calc;
    public JScrollPane scroll;
    private ReentrantLock lock;

    public MainFrame() {
        lock = new ReentrantLock();
        controller = new Controller(MainFrame.this, lock);
        frame = new JFrame();
        taskPanel = new TaskPanel();
    }

    public JFrame buildFrame() {
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new BorderLayout());
        graphic = new GraphicComponent(controller);
        mainPointsTable = new PointsTable(this);
        scroll = new JScrollPane(graphic);
        scroll.setPreferredSize(new Dimension(605, 505));
        scroll.setAutoscrolls(true);
        scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        frame.add(mainPointsTable.buildComponent(), BorderLayout.WEST);
        frame.add(scroll, BorderLayout.CENTER);
        frame.add(taskPanel.buildComponent(), BorderLayout.EAST);
        HoldAndDragListener listener = new HoldAndDragListener(graphic);
        scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        scroll.getViewport().addMouseListener(listener);
        scroll.getViewport().addMouseMotionListener(listener);
        ZoomListener zoomListener = new ZoomListener(MainFrame.this, graphic, taskPanel);
        scroll.addMouseWheelListener(zoomListener);
        taskPanel.getMainButton().addActionListener(event -> {
            try {
                if (taskPanel.getValueXBeg().equals("") || taskPanel.getValueXEnd().equals("")
                        || Integer.valueOf(taskPanel.getValueXBeg()) >= Integer
                        .valueOf(taskPanel.getValueXEnd())) {
                    JOptionPane.showMessageDialog(null, "Введите корректные данные");
                } else {
                    graphic.clear();
                    startCalculation();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return frame;
    }

    public void startCalculation() throws InterruptedException {
        controller.startLinearFunctionThread();
        //controller.startSortFunctionThread();
    }


    public void repaintGraph() {
        frame.repaint();
    }

    public List<List<Double>> getValues() {
        return graphic.getValues();
    }

    public void addValues(double x, double fx) {
        graphic.addValues(x, fx);
    }

    public void addValueOnPlace(int place, double x, double fx) {
        graphic.addValueOnPlace(place, x, fx);
    }

    public void clear() {
        graphic.clear();
    }

    public void show() {
        frame.setVisible(true);
    }

    public boolean isInt(String value) throws NumberFormatException {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public ReentrantLock getLock() {
        return lock;
    }
}
