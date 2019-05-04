package view;

import controller.Controller;
import model.GraphicPoint;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicComponent extends JPanel {

    private int width = 600;
    private int height = 500;
    public List<GraphicPoint> valuesLinear;
    public int fontSize;
    public Dimension firstSize;
    public Dimension size;
    public Dimension center;
    public int initialFontSize = 9;
    private Controller controller;
    private Graphics graph;
    private List<List<GraphicPoint>> functionsData;

    public GraphicComponent(Controller controller) {
        valuesLinear = new ArrayList<GraphicPoint>();
        size = new Dimension(width, height);
        center = new Dimension(width / 2, height / 2);
        setPreferredSize(size);
        setSize(size);
        fontSize = 9;
        firstSize = new Dimension(600, 500);
        this.controller = controller;
        functionsData = new ArrayList<>();
        functionsData.add(new ArrayList<>());
        functionsData.add(new ArrayList<>());
    }

    private void drawAxis(Graphics graph) {
        center = new Dimension(size.width / 2, size.height / 2);
        graph.drawLine(10, size.height / 2, size.width - 1, size.height / 2);
        graph.drawLine(size.width / 2, 10, size.width / 2, size.height - 1);
        graph.drawLine(size.width - 30, size.height / 2 - 10, size.width - 1, size.height / 2);
        graph.drawLine(size.width - 30, size.height / 2 + 10, size.width - 1, size.height / 2);
        graph.drawLine(size.width / 2, 10, size.width / 2 - 10, 30);
        graph.drawLine(size.width / 2, 10, size.width / 2 + 10, 30);
        graph.drawString("X", size.width - 20, size.height / 2 + 20);
        graph.drawString("Y", size.width / 2 - 20, 20);
        graph.drawString("0", size.width / 2 - 10, size.height / 2 + 10);
        for (int index = (int) size.getWidth() / 2; index < size.width; index += 20) {
            graph.drawLine((index), size.height / 2, (index),
                    size.height / 2 + 3);
        }
        for (int index = (int) size.getHeight() / 2; index < size.height; index += 20) {
            graph.drawLine(size.width / 2 - 3, index, size.width / 2, index);
        }
        for (int index = (int) size.getWidth() / 2; index > 0; index -= 20) {
            graph.drawLine((index), size.height / 2, (index),
                    size.height / 2 + 3);
        }
        for (int index = (int) size.getHeight() / 2; index > 0; index -= 20) {
            graph.drawLine(size.width / 2 - 3, index, size.width / 2, index);
        }
    }

    public void drawFunction(List<GraphicPoint> values) {
        for (int index = 1; index < values.size(); index++) {
            double tempFx = (values.get(index)).getY();
            double tempX = (values.get(index)).getX();
            double prevFx = (values.get(index - 1)).getY();
            double prevX = (values.get(index - 1)).getX();
            int newFx = (int) (10 * tempFx);
            int newX = (int) (10 * tempX);
            int newprevFx = (int) (10 * prevFx);
            int newPrevX = (int) (10 * prevX);
            graph.setColor(Color.BLUE);
            int drawPrevX = center.width + 4 * newPrevX;
            int drawPrevY = center.height - newprevFx;
            int drawX = center.width + 4 * newX;
            int drawY = center.height - newFx;
            graph.drawLine(drawPrevX, drawPrevY, drawX, drawY);
            graph.setColor(Color.DARK_GRAY);
            /*if (index == 1) {
                String stringX = (values.get(0)).getX().toString();
                String stringY = (values.get(0)).getY().toString();
                graph.drawString(stringX, drawPrevX, size.height / 2 + 20);
                graph.drawString(stringY, size.width / 2 - 30, drawPrevY);
            }
            if (values.get(index).get(0) % 1 == 0) {
                String stringX = (values.get(index)).get(0).toString();
                String stringY = (values.get(index)).get(1).toString();
                graph.drawString(stringX, drawX, size.height / 2 + 20);
                graph.drawString(stringY, size.width / 2 - 30, drawY);
            }*/
        }
    }

    @Override
    public void paintComponent(Graphics graphic) {
        //setData();
        super.paintComponent(graphic);
        graphic.setColor(Color.DARK_GRAY);
        size = new Dimension(this.getWidth(), this.getHeight());
        Graphics2D graph = (Graphics2D) graphic;
        this.graph = graph;
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graph.setFont(new Font("Comic Sans", Font.PLAIN, fontSize));
        drawAxis(graph);
        drawFunction(valuesLinear);
        for (List<GraphicPoint> function : functionsData) {
            drawFunction(function);
        }
    }

    public void drawPoints(GraphicPoint firstPoint, GraphicPoint secondPoint) {
        int drawPrevX = /*center.width + 4 * */(int) firstPoint.getX();
        int drawPrevY = /*center.height - */(int) firstPoint.getY();
        int drawX = /*center.width + 4 * */(int) secondPoint.getX();
        int drawY = /*center.height - */(int) secondPoint.getY();
        graph.drawLine(drawPrevX,
                drawPrevY,
                drawX,
                drawY);
    }

    public List<GraphicPoint> getValuesLinear() {
        return valuesLinear;
    }


    /*public void setData() {
        this.valuesLinear = controller.getLinearFunctionData();
    }*/


    public void clear() {
        valuesLinear.clear();
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontSize() {
        return fontSize;
    }

    public int getInitialFontSize() {
        return initialFontSize;
    }

    public Dimension getFirstSize() {
        return firstSize;
    }

    public void changeFirstSize(Dimension tempSize) {
        firstSize = tempSize;
    }

    public void addValue(int id, GraphicPoint point) {
        functionsData.get(id).add(point);
    }
}
