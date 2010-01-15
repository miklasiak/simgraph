package geom;

import java.util.ArrayList;

/**
 * Polygon to wielokat inaczej. Sklada sie z listy punktow.
 * @author alebar
 */
public class Polygon {
    private ArrayList<Point3D> punkty;

    public Polygon () {
        punkty = new ArrayList<Point3D>();
    }

    public void addPoint (Point3D p) {
        punkty.add(p);
    }

    public void addPointNoRep (Point3D p) {
        Point3D punkt = new Point3D( p.getX(), p.getY(), p.getZ(), p.getN() );
        if (!punkty.contains(punkt)) {
            punkty.add(punkt);
        }
    }

    public int numOfPoints () {
        return punkty.size();
    }

    public boolean hasIt (Point3D p) {
        return punkty.contains(p);
    }

    public Point3D getPoint (int i) {
        return punkty.get(i);
    }

}
