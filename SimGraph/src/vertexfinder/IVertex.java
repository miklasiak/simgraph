package vertexfinder;
import math.*;
import geom.*;

/**
 *
 * @author alebar
 */
public interface IVertex {
    public void setSystem (Matrix A, Vector b);
    public void setSystemFromFile (String filename);
    public Polyhedron vertexFind();
      public void setNextSystem();
}
