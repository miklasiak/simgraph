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
    public ArrayList<Polygon> getZrzutowane();
    public void setGuiInterface(IGui gui);
}
