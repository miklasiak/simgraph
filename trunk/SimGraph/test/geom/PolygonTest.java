package geom;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.*;

/**
 *
 * @author alebar
 */
public class PolygonTest {

    private double eps = 0.00001;

    @Test
    public void addPointNoRepTest () {
        Point3D a = new Point3D (10, 0.0, 10);
        Point3D b = new Point3D (10, -0.0, 10);
        Polygon3D poly = new Polygon3D();
        poly.addPoint(a);
        poly.addPointNoRep(b);
        assertEquals(true, poly.hasPoint(a));
        //assertEquals(1, poly.numOfPoints());
    }

    @Test
    public void polygonIterationTest () {
        Polygon3D poly = new Polygon3D();
        Point3D p;
        Point3D a = new Point3D (10, 1.0, 10);
        Point3D b = new Point3D (10, 2.0, 10);
        Point3D c = new Point3D (10, 3.0, 10);
        poly.addPoint(a);
        poly.addPoint(b);
        poly.addPoint(c);
        double i = 1.0;
        for (Object o : poly) {
            p = (Point3D) o;
            assertEquals(i, p.getY(), eps );
            i++;
        }
    }

    public static void main (String[] args) {
        JUnitCore.main("geom.PolygonTest");
    }
}
