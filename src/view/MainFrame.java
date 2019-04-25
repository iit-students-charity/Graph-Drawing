package view;

import controller.Controller;
import model.LinearFunction;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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

    public MainFrame() {
        controller = new Controller(MainFrame.this);
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
        graphic = new GraphicComponent();
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
        if (isInt(taskPanel.getValueXBeg()) == false || isInt(taskPanel.getValueXEnd()) == false) {
            JOptionPane.showMessageDialog(null, "Введите корректные данные");
            return;
        } else {
            tempXBeg = taskPanel.getXBegin();
            controller.setXBeg(tempXBeg);
            tempXEnd = taskPanel.getXEnd();
            controller.setXEnd(tempXEnd);
        }
        calc = new LinearFunction(controller);
        Thread thread = new Thread(calc);
        thread.start();
    }

    public void update() {
        List<List<Double>> values = controller.getValues();
        if (values.size() == 1 + 10 * (Integer.valueOf(taskPanel.getValueXEnd()) - Integer.valueOf(taskPanel.getValueXBeg()))) {
            double maxY = Math.abs((values.get(0)).get(1));
            double maxX = Math.abs((values.get(0)).get(0));
            for (int index = 0; index < values.size(); index++) {
                if (Math.abs((values.get(index)).get(1)) > maxY)
                    maxY = Math.abs((values.get(index)).get(1));
                if (Math.abs((values.get(index)).get(0)) > maxX)
                    maxX = Math.abs((values.get(index)).get(0));
            }
            int newFx = (int) (10 * maxY);
            int newX = (int) (10 * maxX);
            Dimension firstSize = new Dimension(602, 502);
            int drawY = (int) (firstSize.getHeight() / 2 - /*0.02 **/ newFx * 2);
            int drawX = (int) (firstSize.getWidth() / 2 + 4 * newX);
            if (Math.abs(drawY) > firstSize.getHeight() && Math.abs(drawX) > firstSize.getWidth()) {
                Dimension newSize = new Dimension((Math.abs(drawX) * 2), (int) (Math.abs(drawY) * 2.5));
                graphic.changeFirstSize(newSize);
                graphic.setPreferredSize(newSize);
                graphic.setSize(newSize);
            } else if (Math.abs(drawY) > firstSize.getHeight()) {
                Dimension newSize = new Dimension(graphic.getWidth(), (Math.abs(drawY) * 3));
                graphic.changeFirstSize(newSize);
                graphic.setPreferredSize(newSize);
                graphic.setSize(newSize);
            } else if (drawY < 0) {
                Dimension newSize = new Dimension(graphic.getWidth(), (int) (502 + Math.abs(drawY) * 2.5));
                graphic.changeFirstSize(newSize);
                graphic.setPreferredSize(newSize);
                graphic.setSize(newSize);
            } else if (Math.abs(drawX) > firstSize.getWidth()) {
                Dimension newSize = new Dimension((Math.abs(drawX) * 2), graphic.getHeight());
                graphic.changeFirstSize(newSize);
                graphic.setPreferredSize(newSize);
                graphic.setSize(newSize);
            } else if (Math.abs(drawY) <= 502 && Math.abs(drawX) <= 602) {
                Dimension newSize = new Dimension(602, 502);
                graphic.changeFirstSize(newSize);
                graphic.setPreferredSize(newSize);
                graphic.setSize(newSize);
            }
        }
        frame.repaint();
        mainPointsTable.update();
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
}
