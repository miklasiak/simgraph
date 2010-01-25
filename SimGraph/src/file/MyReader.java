/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import math.Matrix;
import math.Vector;

/**
 *
 * @author kamil
 */
public class MyReader implements IData {
    
    
    private File f;
    private RandomAccessFile raf;
    private ArrayList<Matrix> listA;
    private ArrayList<Vector> listb;
    private int actualIndexOfListA;
    private int actualIndexOfListb;
    private final int preUnnededRows=5;
    private int iloscOgraniczen=-preUnnededRows;
    private final int pastUnnededRows=4;
    private boolean d3;

    public MyReader(){
        initComponents();
    }

    private void initComponents(){
        listA = new ArrayList<Matrix>();
        actualIndexOfListA=0;
        listb = new ArrayList<Vector>();
        actualIndexOfListb=0;
    }

    private void testowaFunkcja(){
        try{
            readFile("C:\\WYNIK3.SPX");
        }catch(IOException e){ e.printStackTrace();}
        System.out.print("\n************* macierze A *****************\n");
        for (Matrix ma:listA)
            ma.printMatrix();
        System.out.print("\n************* werkory b *****************\n");
        for(Vector ve: listb){
            ve.print();
            System.out.println("");
        }
    }

    private void analyzeSubTab(ArrayList<String> a,int firstLinesToRead) throws IOException{
        Vector v= new Vector(firstLinesToRead);
        Matrix m =null;
        String tmp="";
        Character c;
        ArrayList<String> strNums= new ArrayList<String>();
        for(int i=0; i<firstLinesToRead;i++){
            //t=a.get(i);
            for(int j=0; j<a.get(i).length();j++){
                c=a.get(i).charAt(j);
                if((c.toString()).equals("-") || Character.isDigit(c) || (c.toString()).equals(".") ){
                    //minus=true;
                    tmp+=c.toString();
                }else {
                    if(!tmp.equals(""))
                        strNums.add(tmp);                    
                    tmp="";
                }
            }
            if(strNums.size()<5){
                throw new IOException("nie porawna tablica simplexów- za mało danych");
            }
            if(m==null){
                m=new Matrix(firstLinesToRead, d3 ?  3 : 2 /*strNums.size()-4*/);
            }
            v.setElement(i, Double.valueOf(strNums.get(3)) );
            for(int k=4;k<4+m.getNumOfcolumns()/*strNums.size()*/;k++){
                m.setElement(i, k-4, Double.valueOf(strNums.get(k)) );
            }
            strNums.clear();
        }
        listb.add(v);
        listA.add(m);
    }

    /*private void simToA() throws ArithmeticException{
        int columns ,rows;
        //Matrix mx ;
        if( null == listA.get( listA.size() )  ){
            throw new ArithmeticException("nie poprawna długość listy macierzy A");
        }
        columns = listA.get( listA.size() ).getNumOfcolumns();
        rows = listA.get( listA.size() ).getNumOfRows();

        for(int i =0; i< listA.size();i++){
            if(listA.get(i).getNumOfcolumns()>columns){
                Matrix mx= new Matrix(rows, columns);
                for(int j=0;j<rows;j++){
                    for(int k=0;k<columns;k++){
                        mx.setElement(j, k, listA.get(i).getElement(j, k));
                    }
                }
                listA.remove(i);
                listA.add(i, mx);
            }

        }
    }*/
//////////////////////// implemented methodes - start/////////////////////////
    public void readFile(String filePath) throws IOException{
        String line,prevLine=null;
        ArrayList<String> preTab=new ArrayList<String>();
        boolean startReadTab=false,canAnalize=false;
        boolean beforeFirstTabReaded=true;
        int ln=5;
        listA.clear();
        listb.clear();
        d3 =false;
        //d3[0]=false; d3[1]=false; d3[2]=false;
        f = new File(filePath);
        if(!f.exists() )
            throw new IOException("nie istnieje plik o scieżce:\n"+filePath);
        if(!f.canRead())
            throw new IOException("brak prawa odczytu dla pliku:\n"+f.getAbsolutePath());
        if(f.isDirectory())
            throw new IOException("podaj scieżkę do pliku a nie katalogu! ( durniu :P )");        
        try{
            raf = new RandomAccessFile(f, "r");
            while((line=raf.readLine()) != null ){//pr("linia: "+line);
                if(beforeFirstTabReaded){
                    if(line.startsWith("Tablica nr")){
                        startReadTab= !startReadTab;
                        canAnalize = true;
                    }else {
                        if(line.contains("x3"))
                            d3=true;
                    }
                    if(startReadTab){
                        if(iloscOgraniczen<0){
                            iloscOgraniczen++;
                            continue;
                        }else if (iloscOgraniczen==0){
                            preTab.clear();
                            iloscOgraniczen++;
                            continue;
                        }
                        preTab.add(line);
                    }else if(!startReadTab && canAnalize){
                            analyzeSubTab(preTab,preTab.size()-pastUnnededRows);
                            iloscOgraniczen = preTab.size()-pastUnnededRows;// ustaw iloscOrg na -wart.Start i zmien flage startReadTab
                            beforeFirstTabReaded=false;
                            prevLine=line;
                    }
                }else{ //gdy czytam y po raz kolejny tablice simplexów (znamy juz ilosc ograniczen)
                    if(prevLine==null)
                        throw new IOException("Poprzednio czytana linia jest nullem");
                    if(prevLine.startsWith("Tablica nr")){
                        ln=-4;
                        preTab.clear();
                    }else if(line.startsWith("Tablica nr")){
                        ln=-5;
                        preTab.clear();
                    }
                    if(ln>0){
                        preTab.add(line);
                    }
                    if(ln==iloscOgraniczen){
                        analyzeSubTab(preTab,preTab.size());       //ln=-5; //nie potrzebne
                    }
                    ln++;
                    prevLine=line;
                }

            }
        }catch(IOException ex){
            throw new IOException("nie moge odczytać pliku");
        }



    }

    public boolean hasNextA() {
        if(listA.size()<= actualIndexOfListA || listA.get(actualIndexOfListA) == null){
            if(listA.size()> actualIndexOfListA )
                actualIndexOfListA++;
            return false;
        }
        actualIndexOfListA++;
        return true;
    }

    public Matrix getNextA() {
        if(listA.size()> actualIndexOfListA)
            return listA.get(actualIndexOfListA);
        return null;
    }

    public Matrix[] getAllA() {
        return (Matrix[])listA.toArray();
    }

    public boolean hasNexb() {
        if(listb.size()<= actualIndexOfListb || listb.get(actualIndexOfListb) == null){
            if(listb.size()> actualIndexOfListb)
                actualIndexOfListb++;
            return false;
        }
        actualIndexOfListb++;
        return true;
    }

    public Vector getNextb() {
        if(listb.size()> actualIndexOfListb)
            return listb.get(actualIndexOfListb);
        return null;
    }

    public Vector[] getAllb() {
        return (Vector[])listb.toArray();
    }
    /////////////////////////// implemented methodes - end/////////////////////////


    public static void main(String args[]){
        MyReader mr = new MyReader();
        mr.testowaFunkcja();
    }
//    static void pr(String s){
//        System.out.println(s);
//    }
}
