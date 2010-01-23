package geom;

/**
 *
 * @author alebar
 */
public class Point2D {
    private int x,y;

    public Point2D () { }
    public Point2D (int a, int b) {
        x = a;
        y = b;
    }

    public void setX (int a) {
        x = a;
    }

    public void setY (int a) {
        y = a;
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }
}
