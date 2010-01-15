package geom;

import java.util.ArrayList;

/**
 * Klasa reprezentuje woeloscian. Ma liste wielokatow: kazdy zdefiniowany
 * jako zbior punktow.
 * @author alebar
 */
public class Polyhedron {
    ArrayList<Polygon> polygons;

    public Polyhedron () {
        polygons = new ArrayList<Polygon>();
    }
    public Polyhedron (Polygon[] p) {
        polygons = new ArrayList<Polygon>();
        int len = p.length;
        for (int i=0; i<len; i++) {
            if (p[i].numOfPoints() >= 3)
                this.addPolygon(p[i]);
        }
    }

    public void addPolygon (Polygon p) {
        this.polygons.add(p);
    }

    public int getNumOfWalls () {
        return this.polygons.size();
    }

    public void print () {
        System.out.println("##### BEGIN POLYHEDRON #####");
        int walls = this.getNumOfWalls();
        Polygon poly;
        Point3D p;
        System.out.println("Num of walls: "+walls);
        for (int i=0; i<walls; i++) {
            System.out.println("Wall "+i+": ");
            poly = this.polygons.get(i);
            for (int j=0; j<poly.numOfPoints(); j++) {
                p = poly.getPoint(j);
                System.out.print("[ "+p.getX()+"; "+p.getY() + "; "+p.getZ()+" ] ");
            }
            System.out.println();
        }

        System.out.println("##### END POLYHEDRON #####");
    }
}
