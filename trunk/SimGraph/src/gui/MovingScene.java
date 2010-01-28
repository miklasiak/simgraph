package gui;

import java.awt.event.*;import virtualcamera.IManagement;
;
import virtualcamera.IMovement;

/**
 *
 * @author alebar
 */
public class MovingScene implements KeyListener, MouseMotionListener {
    private IMovement ster;
    private IManagement zarzadca;
    private Window frame;

    public MovingScene (IMovement im, IManagement z, Window f) {
        ster = im;
        zarzadca = z;
        frame = f;
    }

    public void keyTyped(KeyEvent e) { }

    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyChar());
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
            ster.rotateZRight();
        if (e.getKeyCode()==KeyEvent.VK_LEFT)
            ster.rotateZLeft();
        if (e.getKeyCode() == 'Q' || e.getKeyCode() == 'q')
            ster.rotateYLeft();
        if (e.getKeyCode() == 'E' || e.getKeyCode() == 'e')
            ster.rotateYRight();
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            frame.mouseRelease();

        // zoom
        if (e.getKeyChar() == ']')
            ster.zoomIn();
        if (e.getKeyChar() == '[')
            ster.zoomOut();

        // toggle przeslanianie
        if (e.getKeyChar() == 'p')
            zarzadca.togglePrzeslanianieScian();
    }

    public void keyReleased(KeyEvent e) { }

    public void mouseDragged(MouseEvent e) {
        //System.out.println("dragged");
    }

    public void mouseMoved(MouseEvent e) {
        //System.out.println("moved");
    }

}
