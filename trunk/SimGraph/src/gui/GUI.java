package gui;
import java.awt.event.*;
import virtualcamera.*;
/**
 * Ta klasa odpowiada za GUI programu. Program wywołuje konstruktor tej klasy
 * by stworzyć okno. Żeby przemalować scenę wystarczy wywołać reload().
 * @author alebar
 */
public class GUI implements KeyListener, IGui {
    private DrawingPanel panel;
    private WindowFrame frame;
    private final int PANEL_HEIGHT = 500;
    private final int PANEL_WIDTH = 500;

    private IMovement ster;
    private IManagement zarzadca;

    public GUI ( IMovement im, IManagement zarzadca ) {
        ster = im;
        panel = new DrawingPanel(PANEL_HEIGHT, PANEL_WIDTH, zarzadca);
        frame = new WindowFrame(panel, this);
        frame.setVisible(true);
    }

    public void setIMovementInterface (IMovement im) {
        ster = im;
    }

    public void setIManagementInterface (IManagement im) {
        zarzadca = im;
    }

    public void showDrawingPanel () {
        panel = new DrawingPanel(PANEL_HEIGHT, PANEL_WIDTH, zarzadca);

    }

    /**
     * Wywołaj tę funkcję, żeby przemalować scenę.
     */
    public void reload () {
        panel.repaint();
    }
    
    public int getPanelWidth () {
        return this.PANEL_WIDTH;
    }

    public int getPanelHeight () {
        return this.PANEL_HEIGHT;
    }
    
    public void keyTyped(KeyEvent e) { }

    public void keyPressed(KeyEvent e) {
        // movement
        if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W')
            ster.moveForward();
        if (e.getKeyChar() == 's' || e.getKeyChar() == 'S')
            ster.moveBack();
        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A')
            ster.moveLeft();
        if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D')
            ster.moveRight();
        if (e.getKeyCode() == 'R' || e.getKeyCode() == 'r')
            ster.moveUp();
        if (e.getKeyCode() == 'F' || e.getKeyCode() == 'f')
            ster.moveDown();

        // rotation
        if (e.getKeyCode()==KeyEvent.VK_UP)
            ster.rotateXUp();
        if (e.getKeyCode()==KeyEvent.VK_DOWN)
            ster.rotateXDown();
        if (e.getKeyCode()==KeyEvent.VK_RIGHT)
            ster.rotateYRight();
        if (e.getKeyCode()==KeyEvent.VK_LEFT)
            ster.rotateYLeft();
        if (e.getKeyCode() == 'Q' || e.getKeyCode() == 'q')
            ster.rotateYLeft();
        if (e.getKeyCode() == 'E' || e.getKeyCode() == 'e')
            ster.rotateYRight();

        // zoom
        if (e.getKeyChar() == ']')
            ster.zoomIn();
        if (e.getKeyChar() == '[')
            ster.zoomOut();
    }

    public void keyReleased(KeyEvent e) { }

}
