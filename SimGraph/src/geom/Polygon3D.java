    package geom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Polygon3D to wielokat inaczej. Sklada sie z listy punktow.
 * @author alebar
 */
public class Polygon3D implements Iterable, Comparable {
    private ArrayList<Point3D> punkty;
    private Point3D srodekCiezkosci;
    private double odlegloscOdKamery;

    public Polygon3D () {
        punkty = new ArrayList<Point3D>();
    }

    public void obliczOdlegloscOdKamery (Point3D c) {
        odlegloscOdKamery = Point3D.odleglosc(srodekCiezkosci, c);
    }

    public double getOdlegloscOdKamery () {
        return odlegloscOdKamery;
    }

    public void wyznaczSrodekCiezkosci() {
        double X = 0;
        double Y = 0;
        double Z = 0;
        for (Point3D p : punkty) {
            X += p.getX();
            Y += p.getY();
            Z += p.getZ();
        }
        X = X/punkty.size();
        Y = Y/punkty.size();
        Z = Z/punkty.size();
        srodekCiezkosci = new Point3D(X, Y, Z);
    }

    public Point3D getSrodekCiezkosci () {
        Point3D s;
        if (srodekCiezkosci==null) s = null;
        else s = srodekCiezkosci.copy();
        return s;
    }

    public void setSrodekCiezkosci (Point3D s) {
        this.srodekCiezkosci = s.copy();
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

    public boolean hasPoint (Point3D p){
        return punkty.contains(p);
    }

    public void setlist(ArrayList<Point3D> pl){
        punkty = pl;
    }

    public Point3D getPoint (int i) {
        return punkty.get(i);
    }

    public Iterator iterator() {
        return new MyIterator();
    }

    public int compareTo(Object o) {
        Polygon3D wall = (Polygon3D) o;
        return Double.compare(odlegloscOdKamery, wall.getOdlegloscOdKamery());
    }

    private class MyIterator implements Iterator {
        int pos;
        
        public boolean hasNext() {
            return ( pos < punkty.size() );
        }

        public Point3D next() throws NoSuchElementException {
            if ( pos >= punkty.size() )
                throw new NoSuchElementException();
            return punkty.get(pos++);
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}