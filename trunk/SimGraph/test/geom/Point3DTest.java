package geom;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.*;

/**
 *
 * @author alebar
 */
public class Point3DTest {
    @Test
    public void equalsTest () {
        Point3D a = new Point3D (10, 0.0, 10);
        Point3D b = new Point3D (10, 0.0, 10);
        assertEquals( true, a.equals(b)==b.equals(a) );
        assertEquals( true, a.equals(b) );
        assertEquals( true, b.equals(a) );
        assertEquals( true, a.equals(a) );
        assertEquals( true, b.equals(b) );
    }

    public static void main (String[] args) {
        JUnitCore.main("geom.Point3DTest");
    }
}
