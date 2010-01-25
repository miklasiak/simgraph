package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;
/**
 *
 * @author alebar
 */
public class WindowFrame extends JFrame {
    private final int X_WINDOW_LOCATION = 300;
    private final int Y_WINDOW_LOCATION = 100;

    private AddingPanel addingPanel;
    private DrawingPanel drawingPanel;
    private boolean focusOnAddingPanel = true;

    public WindowFrame (DrawingPanel drawPan, GUI keyList) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLocation(X_WINDOW_LOCATION, Y_WINDOW_LOCATION);
        this.setPreferredSize(new Dimension(840, 500));

        this.drawingPanel = drawPan;
        this.drawingPanel.addKeyListener(keyList);
        addComponents();
        pack();
    }

    private void addComponents() {
        Container pane = this.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));

        drawingPanel.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        drawingPanel.setFocusable(!focusOnAddingPanel);
        pane.add(drawingPanel);

        addingPanel = new AddingPanel(this);
        addingPanel.setBounds(0, 0, 300, 400);
        addingPanel.setBackground(Color.BLACK);
        addingPanel.fillComponents();
        addingPanel.setFocus(focusOnAddingPanel);
        pane.add(addingPanel);
    }

    public void toggleFocusable() {
        if (focusOnAddingPanel) {
            addingPanel.setFocus(false);
            drawingPanel.setFocusable(true);
            focusOnAddingPanel = false;
        } else {
            addingPanel.setFocus(true);
            drawingPanel.setFocusable(false);
            focusOnAddingPanel = true;
        }
    }

}
