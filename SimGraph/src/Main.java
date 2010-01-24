/**
 *
 * @author alebar
 */
import gui.*;
import math.*;
import vertexfinder.*;
import virtualcamera.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IManagement manager = new Zarzadca();
        IMovement move = new Ruch3D(manager);
        IVertex vertex = new Solver3D();
        IGui gui = new GUI();

        gui.setIManagementInterface(manager);
        gui.setIMovementInterface(move);

        manager.setGuiInterface(gui);

        double[][] a = {    { 0, 0, 1 },
        { 0, 0, -1 },
        { 0, 1, 0 },
        { 0, -1, 0 },
        { 1, 0, 0 },
        { -1, 0, 0 },
        { 0, 1, 1 } };
        double [] b = { 100, 0, 100, 0, 100, 0, 100 };
        vertex.setSystem(new Matrix(a), new Vector(b));
        manager.setVertex(vertex.vertexFind());
        
        gui.show();
        manager.start();
    }

}
