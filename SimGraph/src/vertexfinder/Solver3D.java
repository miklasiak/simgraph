package vertexfinder;
import file.IData;
import file.MyReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import math.*;
import geom.*;
import java.io.IOException;
import org.apache.commons.math.linear.*;

/**
 *
 * @author alebar
 */
public class Solver3D implements IVertex {
    Matrix              A;
    Vector              b;
    int                 m;      // liczba nierownosci (plaszczyzn)
    int                 n;      // wymiar przestrzeni
    IData           reader;
    boolean alreadyReaded=false;

    public Solver3D () {
        n = 3;
        reader = new MyReader();
    }

    public void setSystem(Matrix mA, Vector vb) {
        if (n != mA.getNumOfcolumns() || mA.getNumOfRows()<n)
            throw new java.lang.IllegalArgumentException("macierz A ma zly wymiar");
        if (mA==null || vb==null)
            throw new NullPointerException("macierz A i wektor b są puste");

        m = mA.getNumOfRows();
        this.A = new Matrix (mA.getTab());
        this.b = new Vector (vb.getTab());
    }

    /**
     *
     * @param filename
     * czyta macierze z pliku i wczytuje do systemu pierwszą przeczytaną macierz
     */
    public void setSystemFromFile(String filename) {
            try{
                reader.readFile(filename);
                alreadyReaded = true;
            }catch(IOException ioe){
                alreadyReaded = false;
                return;
            }
            setNextSystem();
    }
    /**
     * jeśli przeczytano macierze z pliku to tą metodą przechodzimy do następnej przeczytanej macierzy
     */
    public void setNextSystem(){
        if(alreadyReaded && reader.hasNextA() && reader.hasNexb())
            setSystem(addNaturalEncloseA(reader.getNextA()), addNaturalEnclose_b( reader.getNextb() ) );
    }
    private Matrix addNaturalEncloseA(Matrix a){
        Matrix tmp;
        if(a.getNumOfRows()<1 | a.getNumOfcolumns()<1)
            return null;
        tmp=new Matrix(a.getNumOfRows()+3, a.getNumOfcolumns());
        for(int i=0;i<a.getNumOfRows();i++)
            for(int j=0; j<a.getNumOfcolumns();j++)
                tmp.setElement(i, j, a.getElement(i, j));
        for(int i=a.getNumOfRows();i<tmp.getNumOfRows();i++)
            for(int j=0; j<tmp.getNumOfcolumns();j++)
                tmp.setElement(i, j, (i-a.getNumOfRows())==j ? -1 : 0);
        return tmp;
    }
    private Vector addNaturalEnclose_b(Vector b_){
        Vector tmp;
        if( b_.getSize()<1)
            return null;
        tmp = new Vector(b_.getSize()+3);
        tmp.setElement(b_.getSize(), 0);
        tmp.setElement(b_.getSize()+1, 0);
        tmp.setElement(b_.getSize()+2, 0);
                return tmp;
    }

    public Polyhedron vertexFind() {
        if (A == null)
            throw new java.lang.NullPointerException("wywolaj najpierw setSystem");

        Polygon3D[] polygons = polygonsInitialize(m);
        Point3D punkt;
        /* Iterujemy po rownaniach w ukladzie. Dla dwoch ostatnich rownan
         * nie potrzebujemy juz iteracji: ich punkty przeciec z innymi
         * plaszczyznami zostaly juz znalezione w poprzednich krokach */
        for (int i=0; i<m-2; i++) {    // iteruje po plaszczyznach.
            for (int j = i+1; j< m-1; j++) { // iteruje po pozostalych plaszczyznach.
                for (int k = j+1; k<m; k++) { // iteruje po jeszcze bardziej pozostalych plaszczyznach.
                    try {
                        punkt = this.znajdzPunktPrzecieciaPlaszczyzn(i, j, k);
                        if (punktJestWierzcholkiem(punkt)) {
                            polygons[i].addPointNoRep(punkt);
                            polygons[j].addPointNoRep(punkt);
                            polygons[k].addPointNoRep(punkt);
                        }
                    } catch (PointNotFoundException ex) {
                        //System.out.println("Polygons "+i+","+j+","+k+": no intersection.");
                    }
                }
            }
        }
        Polyhedron w = new Polyhedron(polygons);
        try {
            w.repair();
        } catch (Exception ex) {
            Logger.getLogger(Solver3D.class.getName()).log(Level.SEVERE, null, ex);
        }
        return w;
    }

    private Polygon3D[] polygonsInitialize (int m) {
        Polygon3D [] p = new Polygon3D[m];
        for (int i=0; i<m; i++) {
            p[i] = new Polygon3D();
        }
        return p;
    }

    private boolean punktJestWierzcholkiem (Point3D p) {
        double d;
        for (int i=0; i<m; i++) {
            d = p.wstawDoRownania(A.getRow(i));
            if ( d > b.getElement(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metoda znajduje punkt przeciecia trzech plaszczyzn, o numerach i,j,k
     * @param i
     * @param j
     * @param k
     * @return Punkt przeciecia plaszyzn
     * @throws vertexfinder.PointNotFoundException w przypadku gdy plaszczyzny sie nie przecinaja lub podano zly argument
     */
    private Point3D znajdzPunktPrzecieciaPlaszczyzn (int i, int j, int k) throws PointNotFoundException {
        Point3D punkt;
        /* tab*x=v */
        double [][] tab = new double[3][n];
        double []   v = new double[3];
        // przygotuj macierz nowego ukladu
        tab[0]  = A.getRow(i).getTab();
        tab[1]  = A.getRow(j).getTab();
        tab[2]  = A.getRow(k).getTab();
        v[0]    = b.getElement(i);
        v[1]    = b.getElement(j);
        v[2]    = b.getElement(k);

        RealMatrix coefficients = new Array2DRowRealMatrix(tab, false);
        DecompositionSolver solver = new LUDecompositionImpl(coefficients).getSolver();
        RealVector constants = new ArrayRealVector(v);
        RealVector solution = null;
        try {
            solution = solver.solve(constants);
            punkt = new Point3D (   solution.getEntry(0),
                                    solution.getEntry(1),
                                    solution.getEntry(2));
            return punkt;
        }
        catch (IllegalArgumentException e) {
            throw new PointNotFoundException(e.getMessage());
        }
        catch (InvalidMatrixException e) {
            throw new PointNotFoundException(e.getMessage());
        }
    }

}
