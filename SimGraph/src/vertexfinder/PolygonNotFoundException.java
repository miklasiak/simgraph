/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vertexfinder;

import java.io.InvalidObjectException;

/**
 *
 * @author kamil
 */
public class PolygonNotFoundException extends Exception {

    public PolygonNotFoundException() {
        super();
    }
    public PolygonNotFoundException(String errMsg) {
        super(errMsg);
    }

}
