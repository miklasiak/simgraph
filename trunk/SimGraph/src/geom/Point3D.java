package geom;
import math.Vector;
import math.Matrix;
import util.HashCodeUtil;
/**
 *
 * @author alebar
 */
public class Point3D extends Vector {

    public Point3D () {
        super(4);
        this.setX(0);
        this.setY(0);
        this.setZ(0);
        this.setN(1);
    }

    public Point3D (double x, double y, double z) {
        super(4);
        this.setX(x);
        this.setY(y);
        this.setZ(z);
        this.setN(1);
    }

    public Point3D (double x, double y, double z, double n) {
        super(4);
        this.setX(x);
        this.setY(y);
        this.setZ(z);
        this.setN(n);
    }

    /**
     * Mnoży punkt przez macierz m. Wynik nadpisuje dotychczasowe współrzędne punktu.
     * @param m Macierz o wymiarach 4x4
     */
    public void multiply (Matrix m) {
        if (m.getNumOfRows()!=4 || m.getNumOfcolumns()!=4)
            throw new java.lang.IllegalArgumentException("macierz zlych wymiarow");
        
        double [] tmp = new double[4];
        double[][] mat = m.getTab();

        for (int w =0; w<4; w++) {
            tmp[w] = 0.0;
            for (int k=0; k<4; k++) {
                tmp[w] += mat[w][k]*this.getElement(k);
            }
        }
        this.setTab(tmp);
    }
    
    public static Point3D multiply (Point3D p, Matrix M) {
        Point3D x = new Point3D();
        double[][] m = M.getTab();
        for (int w=0; w<4; w++) {
            x.setElement(w, 0.0);
            for (int k=0; k<4; k++) {
                x.setElement(w, x.getElement(w)+m[w][k]*p.getElement(k));
            }
        }
        return x;
    }

    /**
     * Ta metoda normalizuje punkt o współrzędnych jednorodnych.
     */
    public void normalize () {
        double[] v = this.getTab();
        v[0] = v[0]*(1/v[3]);
        v[1] = v[1]*(1/v[3]);
        v[2] = v[2]*(1/v[3]);
        v[3] = 1.0;
        this.setTab(v);
    }

    public double wstawDoRownania (Vector a) {
        double result;
        result =    a.getElement(0)*this.getX()
                +   a.getElement(1)*this.getY()
                +   a.getElement(2)*this.getZ();
        return result;
    }

    /**
     * Metoda sprawdza czy punkt spełnia podane równanie liniowe.
     * @param a wektor współczynników równania
     * @param res żądany wynik równania
     * @param eps dokładność porównań
     * @return true jeśli spełnia bądź false jeśli nie
     */
    public boolean czySpelniaRownanie (Vector a, double res, double eps) {
        if (a.getSize()!=3 || a==null)
            throw new java.lang.IllegalArgumentException("wektor pusty lub o zlej dlugosci");
        throw new java.lang.UnsupportedOperationException();
    }

    public boolean czyLezyNaPlaszczyznie (Vector u, double eps) {
        if ( u.getSize()!=4 || u == null )
            throw new java.lang.IllegalArgumentException("wektor pusty lub o zlej dlugosci");
        double[] t = new double[3];
        for (int i=0; i<3; i++)
            t[i] = u.getElement(i);
        return this.czySpelniaRownanie(new Vector(t), u.getElement(3), eps);
    }

    public void setX (double val) {
        this.setElement(0, val);
    }

    public void setY (double val) {
        this.setElement(1, val);
    }

    public void setZ(double val) {
        this.setElement(2, val);
    }

    public void setN(double val) {
        this.setElement(3, val);
    }

    public double getX () {
        return this.getElement(0);
    }

    public double getY () {
        return this.getElement(1);
    }

    public double getZ() {
        return this.getElement(2);
    }

    public double getN () {
        return this.getElement(3);
    }

    @Override
    public int hashCode () {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getX());
        result = HashCodeUtil.hash(result, this.getY());
        result = HashCodeUtil.hash(result, this.getZ());
        result = HashCodeUtil.hash(result, this.getN());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point3D p = (Point3D) obj;
        if (    Math.abs( p.getX() - this.getX() ) == 0
             && Math.abs( p.getY() - this.getY() ) == 0
             && Math.abs( p.getZ() - this.getZ() ) == 0
             && Math.abs( p.getN() - this.getN() ) == 0 ) {
            return true;
        } else {
            return false;
        }
    }
    public String printPoint(){
        return "punkt: x:"+getX()+" y:"+getY()+" z:"+getZ() ;
    }
}
