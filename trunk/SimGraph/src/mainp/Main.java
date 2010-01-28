/**
 *
 * @author alebar
 */
package mainp;

import gui.*;
import math.*;
import vertexfinder.*;
import virtualcamera.*;

public class Main {

    static IVertex ver;
    private static IManagement manager;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        manager = new Zarzadca();
        IMovement move = new Ruch3D(getManager());
        ver = new Solver3D();
//        double[][] a = {    { 0, 0, 1 },
//                            { 0, 0, -1 },
//                            { 0, 1, 0 },
//                            { 0, -1, 0 },
//                            { 1, 0, 0 },
//                            { -1, 0, 0 },
//                            { 0, 1, 1 } };
//        double [] b_tab = { 100, 0, 100, 0, 100, 0, 100 };
//        Matrix A = new Matrix (a);
//        Vector b = new Vector(b_tab);
//        ver.setSystem(A, b);
//        getManager().setVertex(ver.vertexFind());
        IGui gui = new Window(move,getManager());
        getManager().setGuiInterface(gui);
        gui.start();

    }
    public static IVertex getVertex(){
        return ver;
    }

    public static IManagement getManager() {
        return manager;
    }

}
