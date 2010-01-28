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
public class MyEqalReader implements IEqRead{

    private Matrix a;
    private ArrayList<Vector> listA_rows;
    private ArrayList<Double> listOfB;
    private Vector b;
    private File f;
    private RandomAccessFile raf;
    private boolean revertSing;

    public MyEqalReader() {
        initComponents();
    }

    private void initComponents(){
        listA_rows = new ArrayList<Vector>();
        listOfB = new ArrayList<Double>();
    }



    public Matrix getA() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return a;
    }

    public Vector getb() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return b;
    }

    public void readFile(String filePath) throws IOException {
        //throw new UnsupportedOperationException("Not supported yet.");
        String line;
          f = new File(filePath);
        if(!f.exists() )
            throw new IOException("nie istnieje plik o scieżce:\n"+filePath);
        if(!f.canRead())
            throw new IOException("brak prawa odczytu dla pliku:\n"+f.getAbsolutePath());
        if(f.isDirectory())
            throw new IOException("podaj scieżkę do pliku a nie katalogu! ( durniu :P )");
        try{
            raf = new RandomAccessFile(f, "r");
            while((line =raf.readLine()) != null ){//pr("linia: "+line);

                    if(line.startsWith("Tablica nr")){
                        break;
                    }else if(  line.startsWith("MAX")  || line.startsWith("MIN") ){
                        continue;
                    } else{// if(line.length()>1 && line.charAt(1)=='x' || Character.isDigit(line.charAt(0))  ){
                        analyseLine(line);
                    }
            }
            a=new Matrix(listA_rows.size(), 3);
            b=new Vector(listOfB.size());
            for(int i=0;i< listA_rows.size();i++){
                a.setElement(i, 0, listA_rows.get(i).getElement(0));
                a.setElement(i, 1, listA_rows.get(i).getElement(1));
                a.setElement(i, 2, listA_rows.get(i).getElement(2));
                b.setElement(i, listOfB.get(i));
            }
        }catch(IOException ioe){
        }
    }
    private void analyseLine(String s){
        boolean readBnow =false;
        double x1=0,x2=0,x3=0 ,bValue=0;

        String tmp="",prev="";
        Character c1,c2;
        revertSing = false;

        for(int i=0; i<s.length();i++){
            //t=a.get(i);
            //for(int j=0; j<a.get(i).length();j++){

                c1=s.charAt(i);
                if(s.length()>i+1)
                    c2=s.charAt(i+1);

                if  (( c1.toString()).equals("-") || (  Character.isDigit(c1) && (i==0 || !((Character)s.charAt(i-1)).toString().equals("x") )  ) || (c1.toString()).equals(".") ){
                    //minus=true;
                    tmp+=c1.toString();
                }else if( c1.toString().equals(">")  || c1.toString().equals("<")  || c1.toString().equals("=")  ) {
                    readBnow=true;
                    if(  c1.toString().equals(">")    )
                        revertSing = true;
                }else if(  c1.toString().equals("x")  ){
                    if( "1".equals( String.valueOf( s.charAt(i+1) ) ) ){
                        try{
                        x1= Double.valueOf(prev);
                        }catch(NumberFormatException e){
                            tmp="";
                            continue;
                        }
                    }else if( "2".equals( String.valueOf( s.charAt(i+1) ) ) ){
                        try{
                        x2= Double.valueOf(prev);
                        }catch(NumberFormatException e){
                            tmp="";
                            continue;
                        }
                    }else if( "3".equals( String.valueOf( s.charAt(i+1) ) ) ){
                        try{
                        x3= Double.valueOf(prev);
                        }catch(NumberFormatException e){
                            tmp="";
                            continue;
                        }
                    }
                } else {
                   prev=tmp;
                    tmp="";
                }

                if( readBnow && !"".equals(tmp.trim())){
                   try{
                        bValue= Double.valueOf(tmp);
                        }catch(NumberFormatException e){
                            tmp="";
                            continue;
                        }
                }

                 System.out.println("tmp: " +tmp);

                 // można zrobic na substringach od x do x
            //}
//            if(strNums.size()<5){
//                throw new IOException("nie porawna tablica simplexów- za mało danych");
//            }
//            if(m==null){
//                m=new Matrix(firstLinesToRead, d3 ?  3 : 2 /*strNums.size()-4*/);
//            }
//            v.setElement(i, Double.valueOf(strNums.get(3)) );
//            for(int k=4;k<4+m.getNumOfcolumns()/*strNums.size()*/;k++){
//                m.setElement(i, k-4, Double.valueOf(strNums.get(k)) );
//            }
//            strNums.clear();
        }
        if(x1 ==0 && x2 ==0 && x3 ==0)
            return;
        if(revertSing){
            x1*=(-1); x2*=(-1); x3*=(-1);
        }
        listA_rows.add(new Vector( new double[] {x1,x2,x3} ));
        listOfB.add(bValue);

    }


    private void testowaFunkcja(){
        try{
            readFile("C:\\WYNIK3.SPX");
        }catch(IOException e){ e.printStackTrace();}
        System.out.print("\n************* macierz A *****************\n");
            a.printMatrix();
        System.out.print("\n************* werkor b *****************\n");
            b.print();
            System.out.println("");
    }
    public static void main(String args[]){
        MyEqalReader meq = new MyEqalReader();
        meq.testowaFunkcja();
    }
}
