package virtualcamera;
import geom.*;
import gui.IGui;
import java.awt.Polygon;
import java.util.ArrayList;
import math.Matrix;
/**
 *
 * @author alebar
 */
public interface IManagement {
    public void setVertex (Polyhedron p);
    public void start();
    public ArrayList<Polygon> getZrzutowane();
    public void setMatrixes (Matrix a, Matrix b);
    public double getCameraY();
    public void setGuiInterface(IGui gui);
    public void setChanged();
}
