package virtualcamera;
import geom.*;
import gui.IGui;
import java.awt.Polygon;
import java.util.ArrayList;
/**
 *
 * @author alebar
 */
public interface IManagement {
    public void setVertex (Polyhedron p);
    public void start();
    public ArrayList<Polygon> getRysowanyObiekt();
    public ArrayList<Polygon> getOsie();
    public boolean przeslaniacSciany();
    public void togglePrzeslanianieScian();
    public void setGuiInterface(IGui gui);
}
