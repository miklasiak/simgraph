package math;

/**
 *
 * @author kamil&alek
 */
public class Matrix implements Cloneable {
    private double matrix[][];
    private int r;
    private int c;

    /**
     * Konstruktor tworzy pusty obiekt
     */
    public Matrix () {
        this.r=0;
        this.c=0;
    };

    /**
     * Konstruuje macierz na bazie tablicy doubli
     * @param mtx
     */
    public Matrix(double mtx[][]){
        this.matrix = mtx.clone();
        this.r = this.getNumOfRows();
        this.c = this.getNumOfcolumns();
    }

    /**
     * Konstruuje macierz o wymiarach x na y
     * @param x ilosc wierszy
     * @param y ilosc kolumn
     */
    public Matrix (int x, int y) {
        this.matrix = new double[x][y];
        this.r = x;
        this.c = y;
    }

    /**
     * Zwraca dany wiersz macierzy
     * @param w numer wiersza
     * @return wiersz macierzy
     */
    public Vector getRow (int w) {
        double[]v = new double[c];
        for (int i=0; i<c; i++) {
            v[i] = this.matrix[w][i];
        }
        return new Vector (v);
    }
    
    /**
     * Macierz B dokleja do macierzy A z jej prawej strony
     * @param A
     * @param B
     * @return
     */
    public static Matrix doklejMacierzZPrawej (Matrix A, Matrix B) {
        if (A.getNumOfRows() != B.getNumOfRows()) 
            throw new java.lang.IllegalArgumentException("Macierze maja rozne ilosci kolumn");
        
        int r,c, Ac, Bc;
        Ac = A.getNumOfcolumns();
        Bc = B.getNumOfcolumns();
        double[][] mTab = new double[A.getNumOfRows()][Ac+Bc];
        
        for (r=0; r<A.getNumOfRows(); r++) {
            c=0;
            while (c<Ac) {
                mTab[r][c] = A.getElement(r, c);
                c++;
            }
            while (c < Ac+Bc) {
                mTab[r][c] = B.getElement(r, c-Ac);
                c++;
            }
        }

        return new Matrix(mTab);
    }

    public static Matrix doklejKolumneZPrawej (Matrix A, Vector b) {
        if (b==null || b.getSize()<=0)
            throw new java.lang.IllegalArgumentException("wektor jest pusty");
        int r,c, Ac = A.getNumOfcolumns();
        double [][] u = new double [A.getNumOfRows()][Ac+1];
        for (r=0; r<A.getNumOfRows(); r++) {
            c=0;
            while (c<Ac) {
                u[r][c] = A.getElement(r, c);
                c++;
            }
            u[r][c] = b.getElement(r);
        }
        return new Matrix(u);
    }

    public void doklejKolumne (Vector b) {
        if (b==null || b.getSize()<=0)
            throw new java.lang.IllegalArgumentException("wektor jest pusty");
        if (b.getSize() != r)
            throw new java.lang.IllegalArgumentException("wektor ma zly rozmiar");
        int w,k;
        double [][]u = new double [r][c+1];
        for (w=0; w<r; w++) {
            k=0;
            while (k<c) {
                u[w][k] = this.matrix[w][k];
                k++;
            }
            u[w][k] = b.getElement(w);
        }
        this.matrix = u;
        this.c++;
    }

    /**
     * teorzy manierz zerowa wyiarow x na y
     * @param x ilosc wierszy
     * @param y ilosc kolumn
     * @return macierz zerowa wymiaru x na y
     */
    public static double[][] createZeroMatrix(int x,int y){
        double tmp [][] = new double[x][y];
        for(int i=0; i<x;i++)
            for(int j=0; j<y;j++)
                tmp[i][j]=0;
        return tmp;
    }
    
    /**
     * ustawia macierz z danymi
     * @param tablica dwuwymiarowa z danymi
     */
    private void setMatrix(double mtx[][]){
        matrix = mtx.clone();
    }

    /**
     * ustawia macierz z danymi
     * @param m macierz z danymi
     */
    private void setMatrix(Matrix m){
        setMatrix(m.getTab());
    }

    /**
     *
     * @return tablice Double[][]
     */
    public double[][] getTab(){
        return matrix.clone();
    }

    /**
     *
     * @return liczbe wierszy macierzy
     */
    public int getNumOfRows(){
        if (matrix == null) return 0;
        else                return this.matrix.length;
    }

    /**
     *
     * @return liczbe kolumn macierzy
     */
    public int getNumOfcolumns(){
        if (matrix == null) return 0;
        else                return this.matrix[0].length;
    }

    /**
     *
     * @param r nr wiersza
     * @param c nr kolumny
     * @return  element o wspolzednych r,c
     */
    public double getElement(int row, int col) {
        if ( row<this.r && row>-1 && col<this.c && col>-1 ) {
            return this.matrix[row][col];
        }
        else
            throw new java.lang.ArrayIndexOutOfBoundsException("zly indeks: brak elementu w macierzy");
    }    

    /**
     * ustawia wartosc w macierzy, jesli nr wiersza ani nr kolumy nie sa za duze
     * @param row  nr wiersza
     * @param column nr kolumny
     * @param value wartosc
     */
    public void setElement(int row, int col, double val){
        if( row < this.r && row > -1 && col < this.c && col>-1 )
            this.matrix[row][col]=val;
        else{
            throw new java.lang.ArrayIndexOutOfBoundsException("zly indeks: brak elementu w macierzy");
        }
    }

    /**
     * Pokazuje macierz na standardowym wyjsciu konsoli
     */
    public void printMatrix( ){
        if(matrix==null)
            throw new java.lang.IllegalStateException();
        for(int i=0;i<this.r;i++){
            for(int j=0;j<this.c;j++){
                System.out.printf("%4.1f ",matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
