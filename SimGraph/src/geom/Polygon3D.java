package geom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Polygon3D to wielokat inaczej. Sklada sie z listy punktow.
 * @author alebar
 */
public class Polygon3D implements Iterable {
    private ArrayList<Point3D> punkty;

    public Polygon3D () {
        punkty = new ArrayList<Point3D>();
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

    public boolean hasIt (Point3D p) {
        return punkty.contains(p);
    }

    public Point3D getPoint (int i) {
        return punkty.get(i);
    }


    public Iterator iterator() {
        return new MyIterator();
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