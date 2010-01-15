package geom;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.*;

/**
 *
 * @author alebar
 */
public class PolygonTest {

    @Test
    public void addPointNoRepTest () {
        Point3D a = new Point3D (10, 0.0, 10);
        Point3D b = new Point3D (10, -0.0, 10);
        Polygon poly = new Polygon();
        poly.addPoint(a);
        poly.addPointNoRep(b);
        assertEquals(true, poly.hasIt(a));
        //assertEquals(1, poly.numOfPoints());
    }

    public static void main (String[] args) {
        JUnitCore.main("geom.PolygonTest");
    }
}
