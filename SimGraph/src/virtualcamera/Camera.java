package virtualcamera;
import geom.*;
/**
 * Klasa reprezentuje piramidę widzenia kamery. Kamera siedzi w punkcie [0,yc,0]
 * i skierowana jest na środek układu współrzędnych.
 * @author alebar
 */
public class Camera {
    /**
     * VPD (View Plane Distance) czyli odległość rzutni od środka rzutowania
     */
    private double VPD;
    /**
     * Współrzędne kamery w osi OY.
     */
    private double yc;


    public Camera (double vpd, double y) {
        VPD = vpd;
        yc = y;
    }
    

    /**
     * Funkcja sprawdza, czy przekazany jej punkt p
     * znajduje się przed rzutnią.
     * @param p Punkt do sprawdzenia
     * @return true jeśli p jest przed rzutnią, false jeśli nie
     */
    protected boolean isVisible(Point3D p) {
        if (p.getY() >= yc+VPD) return true;
        else return false;
    }

    /**
     * Zmienia odległość rzutni od środka rzutowania.
     * @param d - Nowa odległość rzutni.
     */
    protected void setVPD (double d) {
        if (d>0)
            this.VPD = d;
    }

    /**
     * Zwraca odległość rzutni od środka rzutowania
     * @return odległość rzutni od środka rzutowania
     */
    protected double getVPD () {
        return this.VPD;
    }

    /**
     * Zwraca współrzędną y pozycji kamery.
     * @return współrzędna y pozycji kamery
     */
    protected double getY () {
        return yc;
    }

    protected void setY(double y) {
        yc += y;
    }
}
