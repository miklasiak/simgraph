package vertexfinder;

import geom.Polyhedron;
import math.Matrix;
import math.Vector;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.*;
import org.apache.commons.math.util.MathUtils;

/**
 *
 * @author alebar
 */
public class Solver3DTest {
    private double eps;

    @Before
    public void setUp () {
        eps = 0.0001;
    }

    @Test
    public void vertexFindTest () {
        Polyhedron p;
        double[][] a = {    { 0, 0, 1 },
                            { 0, 0, -1 },
                            { 0, 1, 0 },
                            { 0, -1, 0 },
                            { 1, 0, 0 },
                            { -1, 0, 0 },};
        double [] b_tab = { 10, 0, 10, 0, 10, 0 };
        Matrix A = new Matrix (a);
        Vector b = new Vector(b_tab);
        IVertex ver = new Solver3D();
        ver.setSystem(A, b);
        p = ver.vertexFind();
        p.print();
        assertEquals(6, p.numOfWalls());
    }

    @Test
    public void vertexFindTest2 () {
        Polyhedron p;
        double[][] a = {    { 0, 0, 1 },
                            { 0, 0, -1 },
                            { 0, 1, 0 },
                            { 0, -1, 0 },
                            { 1, 0, 0 },
                            { -1, 0, 0 },
                            { 0, 1, 1 } };
        double [] b_tab = { 10, 0, 10, 0, 10, 0, 10 };
        Matrix A = new Matrix (a);
        Vector b = new Vector(b_tab);
        IVertex ver = new Solver3D();
        ver.setSystem(A, b);
        p = ver.vertexFind();
        p.print();
        assertEquals(5, p.numOfWalls());
    }

    @Test
    public void vertexFindTest3 () {
        Polyhedron p;
        double[][] a = {    { 1, 1, 1 },
                            { -1, 0, 0 },
                            { 0, -1, 0 },
                            { 0, 0, -1 },};
        double [] b_tab = { 10, 0, 0, 0 };
        Matrix A = new Matrix (a);
        Vector b = new Vector(b_tab);
        IVertex ver = new Solver3D();
        ver.setSystem(A, b);
        p = ver.vertexFind();
        p.print();
        assertEquals(4, p.numOfWalls());
    }

    private double dwumianNewtona (int n, int k) {
        return MathUtils.factorial(n) / ( MathUtils.factorial(k)*MathUtils.factorial(n-k) );
    }

    public static void main (String[] args) {
        JUnitCore.main("vertexfinder.MatrixTest");
    }

}
