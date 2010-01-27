
package gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import vertexfinder.IVertex;
import virtualcamera.IManagement;
import virtualcamera.IMovement;

/**
 *
 * @author alebar
 */
public class Window extends JFrame implements IGui {
    private IMovement move;
    private IManagement zarzadca;
    private IVertex vertex;

    private AddingPanel addingPanel;
    private MovingScene movingListener;
    private DrawingPanel drawingPanel;
    private final int PANEL_HEIGHT = 500;
    private final int PANEL_WIDTH = 500;

    private final int X_WINDOW_LOCATION = 300;
    private final int Y_WINDOW_LOCATION = 100;
    private Container pane = this.getContentPane();

    public Window (IMovement im, IManagement z) {
        this.move = im;
        this.zarzadca = z;
        this.movingListener = new MovingScene(move, this);

        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(X_WINDOW_LOCATION, Y_WINDOW_LOCATION);
        this.setPreferredSize(new Dimension(320, 500));

        this.addComponents();
    }

    private void addComponents () {
        addingPanel = new AddingPanel(this);
        addingPanel.setBounds(0, 0, 300, 400);
        addingPanel.fillComponents();
        this.add(addingPanel);
        this.pack();
    }

    public void addDrawingPanel () {
        drawingPanel = new DrawingPanel(PANEL_HEIGHT, PANEL_WIDTH, zarzadca);
        drawingPanel.addKeyListener(movingListener);
        drawingPanel.addMouseMotionListener(movingListener);

        pane.removeAll();
        pane.add(drawingPanel);
        pane.add(addingPanel);
        this.setPreferredSize(new Dimension(820, 500));
        setFocusOnDrawingPanel();
        this.pack();
    }

    public void setFocusOnDrawingPanel() {
            this.drawingPanel.requestFocusInWindow();
    }

    public void reload() {
        drawingPanel.repaint();
    }
    
    public void start() {
        this.setVisible(true);
    }

    /**
     * You're so naughty cat! Release that poor mouse now!
     */
    public void mouseRelease() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int getPanelWidth() {
        return this.PANEL_WIDTH;
    }

    public int getPanelHeight() {
        return this.PANEL_HEIGHT;
    }

    public void setIMovementInterface(IMovement im) {
        this.move = im;
    }

    public void setIManagementInterface(IManagement z) {
        this.zarzadca = z;
    }

}
