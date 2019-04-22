package view;

import javax.swing.*;
import java.awt.*;

public class TaskPanel {
    public JPanel panel;
    public JTextField xBeg;
    public JTextField xEnd;
    public JLabel labelXBeg;
    public JButton button;

    public TaskPanel() {
        panel = new JPanel();
        xBeg = new JTextField(3);
        xEnd = new JTextField(3);
        labelXBeg = new JLabel("Область определения: ");
        button = new JButton("Нарисовать");
    }

    JPanel buildComponent() {
        panel.setPreferredSize(new Dimension(350, 400));
        panel.setOpaque(true);
        panel.add(labelXBeg);
        panel.add(xBeg);
        panel.add(xEnd);
        panel.add(button);
        panel.setVisible(true);
        return panel;
    }

    public JButton getMainButton() {
        return button;
    }


    public double getXBegin() {
        double tempX = Integer.valueOf(xBeg.getText());
        return tempX;
    }


    public double getXEnd() {
        double tempX = Integer.valueOf(xEnd.getText());
        return tempX;
    }


    public String getValueXBeg() {
        return xBeg.getText();
    }

    public String getValueXEnd() {
        return xEnd.getText();
    }
}
