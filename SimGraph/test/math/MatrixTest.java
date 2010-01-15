package math;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.*;

/**
 *
 * @author alebar
 */
public class MatrixTest {
    private double eps;

    @Before
    public void setUp () {
        eps = 0.0001;
    }

    @Test
    public void sklejanieTest () {
        double[][] m1 = {   { 1, 2, 3 },
                            { 4, 5, 6 },
                            { 7, 8, 9 } };
        double[][] m2 = { {10, 20}, {11, 22}, {12, 24} };
        Matrix A = new Matrix (m1);
        Matrix B = new Matrix (m2);
        Matrix U = Matrix.doklejMacierzZPrawej(A, B);
        assertEquals(A.getNumOfcolumns()+B.getNumOfcolumns(), U.getNumOfcolumns());
        assertEquals(A.getNumOfRows(), U.getNumOfRows());
        assertEquals(10, U.getElement(0, 3), eps);
        assertEquals(20, U.getElement(0, 4), eps);
        assertEquals(11, U.getElement(1, 3), eps);
        assertEquals(22, U.getElement(1, 4), eps);
    }

    @Test
    public void staticDoklejTest () {
        Vector v = new Vector ( new double[]{1, 2, 3} );
        Matrix m = new Matrix (Matrix.createZeroMatrix(3,3));
        Matrix mat = Matrix.doklejKolumneZPrawej(m, v);
        assertEquals(1, mat.getElement(0, 3), eps);
        assertEquals(2, mat.getElement(1, 3), eps);
        assertEquals(3, mat.getElement(2, 3), eps);
    }

    @Test
    public void doklejKolumneTest () {
        Vector v = new Vector ( new double[]{1, 2, 3} );
        Matrix m = new Matrix (Matrix.createZeroMatrix(3,3));
        m.doklejKolumne(v);
        assertEquals(1, m.getElement(0, 3), eps);
        assertEquals(2, m.getElement(1, 3), eps);
        assertEquals(3, m.getElement(2, 3), eps);
    }

    @Test
    public void getRowTest () {
        Matrix m = new Matrix (Matrix.createZeroMatrix(3,3));
        m.setElement(1, 0, 2);
        Vector v = m.getRow(1);
        assertEquals(2, v.getElement(0), eps);
    }


    public static void main (String[] args) {
        JUnitCore.main("math.MatrixTest");
    }
}
