package view;

import javax.swing.*;
import java.awt.*;

public class TaskPanel {
    public JPanel panel;
    public JTextField nValue;
    public JTextField kValue;
    public JButton buttonStart;
    JButton buttonStop;

    public TaskPanel() {
        panel = new JPanel();
        nValue = new JTextField(3);
        kValue = new JTextField(3);
        buttonStart = new JButton("Рисовать");
        buttonStop = new JButton("Остановить и очистить");
    }

    JPanel buildComponent() {
        panel.setPreferredSize(new Dimension(350, 400));
        panel.setOpaque(true);
        panel.add(nValue);
        panel.add(kValue);
        panel.add(buttonStart);
        panel.add(buttonStop);
        panel.setVisible(true);
        return panel;
    }

    public JButton getMainButton() {
        return buttonStart;
    }


}
