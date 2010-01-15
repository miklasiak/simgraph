package math;

/**
 *
 * @author alebar
 */
public class Vector {
    double [] v;
    int size;

//    public Vector () { }
    /**
     * Konstruktor tworzy n elementowy wektor wartości double
     */
    public Vector (int n) {
        if (n<=0)
            throw new java.lang.IllegalArgumentException("dozwolona liczba wieksza od zera");
        v = new double [n];
        size = n;
    }

    public Vector (double [] a) {
        if (a == null || a.length<=0 )
            throw new java.lang.IllegalArgumentException("tablica nie moze byc == null lub pusta");
        this.v = a.clone();
        this.size = a.length;
    }

    /**
     * @return ilość elementów wektora
     */
    public int getSize () {
        return size;
    }

    public void setTab (double[] tab) {
        this.v = tab.clone();
        this.size = tab.length;
    }

    public double [] getTab () {
        return v.clone();
    }

    public double getElement (int i) {
        return this.v[i];
    }

    public void setElement (int i, double val) {
        this.v[i] = val;
    }

    public void print () {
        for (int i=0; i<size; i++) {
            System.out.println(this.v[i]);
        }
    }

}
