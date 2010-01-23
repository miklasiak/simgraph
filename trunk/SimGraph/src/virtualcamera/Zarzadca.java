package virtualcamera;

import geom.Point2D;
import geom.Point3D;
import geom.Polygon3D;
import geom.Polyhedron;
import gui.IGui;
import math.*;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author alebar
 */
public class Zarzadca implements IManagement {
    private Polyhedron wieloscian;
    private ArrayList<Polygon> zrzutowane;

    private IGui gui;

    private Matrix T;
    private Matrix R;
    private Matrix M;

    private Camera cam;
    private int xd, zd;
    private Timer timer;
    private boolean changed = false;

    public Zarzadca () {
        cam = new Camera( 300.0, 0.0);
        timer = new Timer();
        zrzutowane = new ArrayList<Polygon>();
    }

    private void rzutuj ( Polyhedron polyhedron ) {
        zrzutowane.clear();
        Polygon polygon;
        Polygon3D wall;
        Point3D point;
        Point2D p2d;
        boolean lastVisible, isVisible, firstVisible;
        for (Object w : polyhedron) {
            wall = (Polygon3D) w;
            polygon = new Polygon();
            // zaczynaj z mysla, ze poprzedni punkt jest tak samo widoczny jak ty
            firstVisible = cam.isVisible( wall.getPoint(0) );
            lastVisible = firstVisible;
            for (int i=0; i<wall.numOfPoints(); i++) {
                point = wall.getPoint(i);
                isVisible = cam.isVisible(point);

                if ( isVisible ^ lastVisible ) { // true jeśli różne
                    p2d = rzucPrzeciecie( wall.getPoint(i-1), point );
                    polygon.addPoint(p2d.getX(),p2d.getY());
                }
                if (isVisible) {
                    p2d = rzucPunkt(point);
                    polygon.addPoint(p2d.getX(),p2d.getY());
                    lastVisible = true;
                } else
                    lastVisible = false;
            }
            // sprawdz polaczenie miedzy ostatnim a pierwszym
            if ( firstVisible ^ lastVisible ) {
                p2d = rzucPrzeciecie( wall.getPoint(0), wall.getPoint(wall.numOfPoints()-1) );
                polygon.addPoint(p2d.getX(),p2d.getY());
            }
            zrzutowane.add(polygon);
        }
    }

    /**
     * Metoda wykorzystuje do znalezienia punktu rownanie prostej przechodzacej
     * przez punkty a i b:
     * (x-xa)/(xb-xa) = (y-ya)/(yb-ya) = (z-za)/(zb-za)
     * Do rownania podstawiamy y = VPD + camera.y czyli wspolrzedna y szukanego punktu
     * @param a
     * @param b
     * @return zwraca punkt przeciecia prostej AB z plaszczyzna rzutni
     */
    private Point2D rzucPrzeciecie(Point3D a, Point3D b) {
        int x, z;
        double yc = cam.getVPD() + cam.getY();
        double k = (yc - a.getY()) / ( b.getY() - a.getY() );
        if (a.getX() != b.getX()) {
            x = (int) (k * ( b.getX() - a.getX() ) + a.getX());
        } else
            x = (int) a.getX();
        if (a.getZ() != b.getZ()) {
            z = (int) (k * ( b.getZ() - a.getZ() ) + a.getZ());
        } else
            z = (int) a.getZ();
        return new Point2D(x,z);
    }

    private Point2D rzucPunkt(Point3D p) {
        double k = cam.getVPD() / (p.getY()-cam.getY());
        int x = (int) (k*p.getX()+xd);
        int z = (int) (zd - k*p.getZ());
        return new Point2D (x,z);
    }

    public void start() {
        if (T==null) T = new Matrix(4,4,'I');
        if (R==null) R = new Matrix(4,4,'I');
        timer.scheduleAtFixedRate(new MyTimerTask(), 0, 50);
    }

    //<editor-fold defaultstate="collapsed" desc=" getters">
    public ArrayList<Polygon> getZrzutowane() {
        return zrzutowane;
    }

    public double getCameraY() {
        return cam.getY();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" setters">
    public void setMatrixes (Matrix t, Matrix r) {
        T = t;
        R = r;
    }

    public void setChanged () {
        if (T == null || R==null)
            throw new java.lang.IllegalStateException("Macierze nie zainicjowane. Wywolaj metode setMatrixes()");
        changed = true;
    }

    public void setVertex(Polyhedron p) {
        wieloscian = p;
        rzutuj( Polyhedron.multiplyPoints(wieloscian, new Matrix(4,4,'I')) );
    }

    public void setGuiInterface (IGui ig) {
        gui = ig;
        xd = gui.getPanelWidth()/2;
        zd = gui.getPanelHeight()/2;
    }
    //</editor-fold>

    private class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            if (changed) {
                M= Matrix.multiple(R,T);
                rzutuj( Polyhedron.multiplyPoints(wieloscian, M) );
                gui.reload();
                changed = false;
            }
        }

    }
}
