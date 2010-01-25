/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package file;

import java.io.IOException;
import math.Matrix;
import math.Vector;

/**
 *
 * @author kamil
 */
public interface IData {

    /**
     * czyta tablice sumplexów z pliku o podanej ścieżce
     * @param filePath - ścieżka do pliku z tablicami siplexów 
     */
    public void readFile(String filePath) throws IOException;
    /**     
     * @return true tylko wtedy gdy istnieje następna tablica simpleksów
     */
    public boolean hasNextA();
    /**     
     * @return następna tablica simplexów, jeśli takowa nie istnieje zwracany jest null
     */
    public Matrix getNextA();
    /**
     * @return tablicę wszsytkich macierzy simplexów (ulożonych zgodnie z klejnością ich występowania)
     *         jeśli nie udało się przeczytać tablic z pliku we zwracany jest null
     */
    public Matrix[] getAllA();



    /*następne t metody analogiczne do hasNextA getNextb i gettAllA */
    
    public boolean hasNexb();
    public Vector getNextb();
    public Vector[] getAllb();


}
