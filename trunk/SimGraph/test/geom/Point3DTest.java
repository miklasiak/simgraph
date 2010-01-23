package geom;

import math.Matrix;
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

    @Test
    public void multiplyTest () {
        Point3D a = new Point3D (2, 3.4, 5);
        Point3D b = new Point3D (2, 3.4, 5);
        double[][] t = {    { 1, 2, 3, 3 },
                            { 2, 2, 14, 5 },
                            { 1, 7, 6, 5 },
                            { 4, 2, 30, 23 }};
        Matrix m = new Matrix(t);
        a.multiply(m);
        assertEquals(true, a.equals( Point3D.multiply(b, m) )  );
    }
    

    public static void main (String[] args) {
        JUnitCore.main("geom.Point3DTest");
    }
}
