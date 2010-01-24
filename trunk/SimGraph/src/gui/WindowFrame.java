package gui;

import java.awt.Container;
import javax.swing.*;
/**
 *
 * @author alebar
 */
public class WindowFrame extends JFrame {
    private final int X_WINDOW_LOCATION = 300;
    private final int Y_WINDOW_LOCATION = 100;

    private JPanel menuPanel = new JPanel();
    private JTextArea textArea = new JTextArea();

    public WindowFrame (JPanel drawingPanel, GUI keyList) {
        this.addKeyListener(keyList);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLocation(X_WINDOW_LOCATION, Y_WINDOW_LOCATION);

        addComponents(this.getContentPane(), drawingPanel);
        pack();
    }

    private void addComponents(Container pane, JPanel drawingPanel) {

        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));

        drawingPanel.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        pane.add(drawingPanel);

        textArea.setText("Wirtualna Kamera\n\n" +
                "Autor: Aleksander Bartnikiewicz\n"+
                "\nRuch:\n" +
                    "prawo/lewo\tD/A\n" +
                    "gora/dol\tR/F\n" +
                    "przod/tyl\tW/S\n" +
                "\nObroty: \n" +
                    "OX\t   up/down\n" +
                    "OZ\t   right/left\n" +
                    "OY\t   Q/E\n" +
                "\nZoom:\n" +
                    "ZoomIn\t   ]\n" +
                    "ZoomOut\t  [");
        textArea.setEditable(false);
        textArea.setFocusable(false);
        this.menuPanel.setSize(430, 500);
        this.menuPanel.add(textArea);
        pane.add(menuPanel);

    }
    public void addDrawingPanel (JPanel drawingPanel) {
        this.getContentPane().add(drawingPanel);
        this.getContentPane().add(this.menuPanel);
    }

}
