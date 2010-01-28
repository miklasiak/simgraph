package virtualcamera;

import math.*;
/**
 *
 * @author alebar
 */
public class Ruch3D implements IMovement {

    private double tStep = 5.0;                   // krok przesuniecia w translacji
    private double dStep = 1.0;                    // krok przesuniecia ogniskowej
    private double rStep = 0.1;                     // jednostka obrotu
    private Matrix T = new Matrix(4,4,'I');         // macierz translacji
    private Matrix R = new Matrix(4,4,'I');         // zbiorowa macierz obrotu
    private Matrix rXdown = new Matrix(4,4,'I');    // macierz obrotu wokol OX w dol
    private Matrix rXup = new Matrix(4,4,'I');      // macierz obrotu wokol OX w gore
    private Matrix rYright = new Matrix(4,4,'I');   // macierz obrotu wokol OY w prawo
    private Matrix rYleft = new Matrix(4,4,'I');    // macierz obrotu wokol OY w lewo
    private Matrix rZright = new Matrix(4,4,'I');   // macierz obrotu wokol OZ w prawo
    private Matrix rZleft = new Matrix(4,4,'I');    // macierz obrotu wokol OZ w lewo

    private Zarzadca zarzadca;
    private Camera cam;

    public Ruch3D (IManagement z) {
        zarzadca = (Zarzadca) z;
        zarzadca.setMatrixes(T, R);
        cam = zarzadca.getCamera();
        double cam_y = cam.getY();
        rXdown  = Ruch3D.makeRotationXMatrix(rStep, cam_y);
        rXup    = Ruch3D.makeRotationXMatrix(-rStep, cam_y);
        rYright = Ruch3D.makeRotationYMatrix(-rStep);
        rYleft  = Ruch3D.makeRotationYMatrix(rStep);
        rZright = Ruch3D.makeRotationZMatrix(rStep, cam_y);
        rZleft  = Ruch3D.makeRotationZMatrix(-rStep, cam_y);
    }

    //<editor-fold defaultstate="collapsed" desc=" move">
    public void dragMove(int dx, int dy) {
        T.setElement(0, 3, T.getElement(0, 3)+dx);
        T.setElement(2, 3, T.getElement(2, 3)-dy);
        zarzadca.setChanged();
    }

    public void moveForward() {
        double krok = T.getElement(1, 3);
        krok =  krok - tStep;
        T.setElement(1, 3, krok);
        zarzadca.setChanged();
    }

    public void moveBack() {
        double krok = T.getElement(1, 3);
        krok =  krok + tStep;
        T.setElement(1, 3, krok);
        zarzadca.setChanged();
    }

    public void moveLeft() {
        T.setElement(0, 3, T.getElement(0, 3)+tStep);
        zarzadca.setChanged();
    }

    public void moveRight() {
        T.setElement(0, 3, T.getElement(0, 3)-tStep);
        zarzadca.setChanged();
    }

    public void moveUp() {
        T.setElement(2, 3, T.getElement(2, 3)-tStep);
        zarzadca.setChanged();
    }

    public void moveDown() {
        T.setElement(2, 3, T.getElement(2, 3)+tStep);
        zarzadca.setChanged();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" rotate">
    public void rotateXDown() {
        R.multiple(rXdown);
        zarzadca.setChanged();
    }

    public void rotateXUp() {
        R.multiple(rXup);
        zarzadca.setChanged();
    }

    public void rotateYRight() {
        R.multiple(rYright);
        zarzadca.setChanged();
    }

    public void rotateYLeft() {
        R.multiple(rYleft);
        zarzadca.setChanged();
    }

    public void rotateZRight() {
        R.multiple(rZright);
        zarzadca.setChanged();
    }

    public void rotateZLeft() {
        R.multiple(rZleft);
        zarzadca.setChanged();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" zoomIn/Out">
    public void zoomIn() {
        cam.setVPD(cam.getVPD()+dStep);
        zarzadca.setChanged();
    }

    public void zoomOut() {
        cam.setVPD(cam.getVPD()-dStep);
        zarzadca.setChanged();
    }
    //</editor-fold>

    public static Matrix createStartCameraPositionMatrix () {
        Matrix t = new Matrix(4,4,'I');
        t.setElement(0, 3, -500);
        t.setElement(1, 3, -500);
        t.setElement(2, 3, -50);
        Matrix m = new Matrix (4,4,'I');
        m.multiple(t);
        m.multiple(makeRotationZMatrix(-2.36,0));
        return m;
    }

    private static Matrix makeRotationXMatrix (double fi, double c) {
        Matrix m = new Matrix(4,4,'I');
        double cos, sin;
        cos = java.lang.Math.cos(fi);
        sin = java.lang.Math.sin(fi);
        m.setElement(1, 1, cos);
        m.setElement(2, 2, cos);
        m.setElement(2, 1, sin);
        m.setElement(1, 2, -sin);
        // te wartości poniżej powodują obrót nie wokół osi OX świata
        // ale wokół osi OX kamery :)
        m.setElement(1, 3, c * (cos-1));
        m.setElement(2, 3, sin*c);
        return m;
    }

    private static Matrix makeRotationYMatrix (double fi) {
        Matrix m = new Matrix(4,4,'I');
        double cos, sin;
        cos = java.lang.Math.cos(fi);
        sin = java.lang.Math.sin(fi);
        m.setElement(0, 0, cos);
        m.setElement(2, 2, cos);
        m.setElement(0, 2, sin);
        m.setElement(2, 0, -sin);
        return m;
    }

    private static Matrix makeRotationZMatrix (double fi, double c) {
        Matrix m = new Matrix(4,4,'I');
        double cos, sin;
        cos = java.lang.Math.cos(fi);
        sin = java.lang.Math.sin(fi);
        m.setElement(0, 0, cos);
        m.setElement(1, 1, cos);
        m.setElement(1, 0, sin);
        m.setElement(0, 1, -sin);
        // te wartości poniżej powodują obrót nie wokół osi OZ świata
        // ale wokół osi OZ kamery :)
        m.setElement(0, 3, (-1)*sin*c);
        m.setElement(1, 3, (cos-1)*c);
        return m;
    }
}
