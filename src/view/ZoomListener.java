package view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ZoomListener implements MouseWheelListener {

    public GraphicComponent graphic;
    public TaskPanel buttons;
    public MainFrame mainFrame;

    public ZoomListener(MainFrame mainFrame, GraphicComponent graphic, TaskPanel buttons) {
        this.mainFrame = mainFrame;
        this.graphic = graphic;
        this.buttons = buttons;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {
        if (event.getPreciseWheelRotation() < 0 && KeyEvent.VK_CONTROL != 0) {
            Dimension newSize = new Dimension(graphic.getWidth() + 150, graphic.getHeight() + 100);
            graphic.setPreferredSize(newSize);
            graphic.setSize(newSize);
            int scale = (int) (Math.abs(graphic.getSize().getHeight() / 5 - 100));
            graphic.revalidate();
        }
        if (event.getPreciseWheelRotation() > 0 && KeyEvent.VK_CONTROL != 0) {
            if (graphic.getWidth() > graphic.firstSize.getWidth()) {
                Dimension newSize = new Dimension(graphic.getWidth() - 150, graphic.getHeight() - 100);
                graphic.setPreferredSize(newSize);
                graphic.setSize(newSize);
                mainFrame.repaintGraph();
            }
        }
    }
}
