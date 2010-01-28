package virtualcamera;

/**
 *
 * @author alebar
 */
public interface IMovement {
    public void dragMove(int dx, int dy);
    public void moveForward();
    public void moveBack();
    public void moveLeft();
    public void moveRight();
    public void moveUp();
    public void moveDown();
    public void rotateXDown();
    public void rotateXUp();
    public void rotateYRight();
    public void rotateYLeft();
    public void rotateZRight();
    public void rotateZLeft();
    public void zoomIn();
    public void zoomOut();
}