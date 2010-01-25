package virtualcamera;

import geom.*;

/**
 *
 * @author alebar
 */
public class Osie extends Polyhedron {

    public Osie () {
        super();

        Polygon3D ox = new Polygon3D();
        Polygon3D oy = new Polygon3D();
        Polygon3D oz = new Polygon3D();

        Point3D p0 = new Point3D();

        ox.addPoint(p0);
        ox.addPoint(new Point3D( 1000, 0, 0 ));

        oy.addPoint(p0);
        oy.addPoint(new Point3D( 0, 1000, 0 ));

        oz.addPoint(p0);
        oz.addPoint(new Point3D( 0, 0, 1000 ));

        this.addWall(ox);
        this.addWall(oy);
        this.addWall(oz);
    }
}
