package geom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import math.Matrix;

/**
 * Klasa reprezentuje woeloscian. Ma liste wielokatow: kazdy zdefiniowany
 * jako zbior punktow.
 * @author alebar
 */
public class Polyhedron implements Iterable {
    private ArrayList<Polygon3D> walls;

    public Polyhedron () {
        walls = new ArrayList<Polygon3D>();
    }
    
    public Polyhedron (Polygon3D[] p) {
        walls = new ArrayList<Polygon3D>();
        int len = p.length;
        for (int i=0; i<len; i++) {
            if (p[i].numOfPoints() >= 3)
                this.addWall(p[i]);
        }
    }

    public Polyhedron (ArrayList<Polygon3D> a) {
        this.walls = a;
    }

    public static Polyhedron multiplyPoints (Polyhedron polyhedron, Matrix M) {
        ArrayList<Polygon3D> listaPolygonow = new ArrayList<Polygon3D>();
        Polygon3D newpoly;
        Point3D newpoint;
        for (Object wall : polyhedron) {
            newpoly = new Polygon3D();
            for (Object point : (Polygon3D) wall) {
                newpoint = Point3D.multiply((Point3D) point, M);
                newpoint.normalize();
                newpoly.addPoint(newpoint);
            }
            listaPolygonow.add(newpoly);
        }
        return new Polyhedron(listaPolygonow);
    }

    public void addWall (Polygon3D p) {
        this.walls.add(p);
    }

    public Polygon3D getWall (int i) {
        return walls.get(i);
    }

    public int numOfWalls () {
        return this.walls.size();
    }

    public void print () {
        System.out.println("##### BEGIN POLYHEDRON #####");
        int wallsNum = this.numOfWalls();
        Polygon3D poly;
        Point3D p;
        System.out.println("Num of walls: "+wallsNum);
        for (int i=0; i<wallsNum; i++) {
            System.out.println("Wall "+i+": ");
            poly = this.walls.get(i);
            for (int j=0; j<poly.numOfPoints(); j++) {
                p = poly.getPoint(j);
                System.out.print("[ "+p.getX()+"; "+p.getY() + "; "+p.getZ()+" ] ");
            }
            System.out.println();
        }
        System.out.println("##### END POLYHEDRON #####");
    }

    public Iterator iterator() {
        return new MyPolyhedronIterator();
    }

    private class MyPolyhedronIterator implements Iterator {
        int pos;

        public boolean hasNext() {
            return ( pos < walls.size() );
        }

        public Object next() {
            if ( pos >= walls.size() )
                throw new NoSuchElementException();
            return walls.get(pos++);
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }
}
