/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package file;

import java.io.IOException;
import math.Matrix;
import math.Vector;
import org.apache.commons.math.geometry.Vector3D;

/**
 *
 * @author kamil
 */
public interface IEqRead {
    public void readFile(String filePath) throws IOException;
    public Matrix getA();
    public Vector getb();
}
