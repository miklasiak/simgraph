/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import geom.Polyhedron;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import math.Matrix;
import math.Vector;
import vertexfinder.IVertex;
import mainp.Main;

/**
 *
 * @author kamil
 */
public class AddingPanel extends JPanel {

    MouseListener ml;
    JButton btnLoadFile,btnStart,btnAddEq;//,btnNextA;
    JTextField tfX1,tfX2,tfX3,tfSumValue,tfFileAdress;
    JComboBox cbEqType;
    JLabel lblx1,lblx2,lblx3;
    JTextArea ta;
    JScrollPane sp;
    JPanel subPanel;
    Window parent;
    int topMargin=10,leftMargin=10,high=20;
    int hm=10,wm=1,widthOfBtns=160,bottomMargin=10;
    int tfXWidth=30,lblXWidth=27;
    ArrayList<Equation> eqList;
    File file;
    IVertex iv;
    Matrix a;
    Vector b;

    public AddingPanel(Window p) {
        parent = p;
        initComponents();
    }
    private void initComponents(){
        setLayout(null);
        setBackground(Color.white);
        tfFileAdress=new JTextField();
        btnLoadFile=new JButton("wczytaj plik");
        //btnNextA = new JButton(">");
        tfX1=new JTextField("");
        tfX2=new JTextField("");
        tfX3=new JTextField("");
        tfSumValue =new JTextField();
        lblx1=new JLabel("*x1+");
        lblx2=new JLabel("*x2+");
        lblx3=new JLabel("*x3");
        cbEqType=new JComboBox();
        ta=new JTextArea("");
        subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        btnAddEq = new JButton("Dodaj ogranicznenie");
        btnStart = new JButton("Start");
        eqList = new ArrayList<Equation>();
        try{
            eqList.add(new Equation(1, 0, 0, '>', 0)) ;
            eqList.add(new Equation(0, 1, 0, '>', 0)) ;
            eqList.add(new Equation(0, 0, 1, '>', 0)) ;
         }catch(Exception ex){
         }
        iv= Main.getVertex();
    }
    private int nextHeight(Component cp){
        return cp.getY()+ cp.getHeight()+hm ;
    }
    private int nextWidth(Component cp){
        return cp.getX()+ cp.getWidth()+wm ;
    }

    //<editor-fold defaultstate="collapsed" desc=" fillComponents">
    public void fillComponents(){
        addMouseListener();

        tfFileAdress.setEditable(false);
        tfFileAdress.setBounds(leftMargin,topMargin,getWidth()-leftMargin,high);
        add(tfFileAdress);

        btnLoadFile.setBounds(leftMargin, nextHeight(tfFileAdress)/*tfFileAdress.getY()+ tfFileAdress.getHeight()+hm*/, widthOfBtns, high);
        btnLoadFile.addMouseListener(ml);
        add(btnLoadFile);

//        btnNextA.setBounds(nextWidth(btnLoadFile),btnLoadFile.getY() , (int)(widthOfBtns/2), high);
//        btnNextA.addMouseListener(ml);
//        add(btnNextA);

        tfX1.setBounds(leftMargin, nextHeight(btnLoadFile), tfXWidth, high);
        add(tfX1);
        lblx1.setBounds(nextWidth(tfX1), tfX1.getY(), lblXWidth, high);
        add(lblx1);

        tfX2.setBounds(nextWidth(lblx1), nextHeight(btnLoadFile), tfXWidth, high);
        add(tfX2);
        lblx2.setBounds(nextWidth(tfX2), tfX2.getY(), lblXWidth, high);
        add(lblx2);

        tfX3.setBounds(nextWidth(lblx2), nextHeight(btnLoadFile), tfXWidth, high);
        add(tfX3);
        lblx3.setBounds(nextWidth(tfX3), tfX3.getY(), lblXWidth, high);
        add(lblx3);

        cbEqType.setBounds(nextWidth(lblx3), tfX3.getY(), 2*lblXWidth, high);
        cbEqType.addItem("<");
        cbEqType.addItem(">");
        add(cbEqType);

        tfSumValue.setBounds(nextWidth(cbEqType), tfX3.getY(), tfXWidth, high);
        add(tfSumValue);

        btnAddEq.setBounds(leftMargin, nextHeight(tfX3), widthOfBtns, high);
        btnAddEq.addMouseListener(ml);
        add(btnAddEq);
        //ta.setBounds(leftMargin, nextHeight( tfX3), getWidth()-leftMargin ,getHeight()- nextHeight( tfX3) - hm - high );

        subPanel.setBounds(leftMargin, nextHeight( btnAddEq ), getWidth()-leftMargin ,getHeight()- nextHeight( tfX3) - 2*hm - high );

        
        ta.setBounds(0, 0, subPanel.getWidth(), subPanel.getHeight());
        ta.setBackground(Color.white);
        ta.setEditable(false);
        sp = new JScrollPane(ta);
        sp.setBackground(Color.white);
        //sp.setBounds(0, 0, subPanel.getWidth(), subPanel.getHeight());
        subPanel.add(sp);
        
        //subPanel.add(ta, BorderLayout.CENTER);
        //subPanel.setBackground(Color.green);
        add(subPanel);

        btnStart.setBounds(leftMargin, nextHeight(subPanel), widthOfBtns, high);
        btnStart.addMouseListener(ml);
        add(btnStart);
        setBackground(Color.white);
    }
    //</editor-fold>
    
    private void addMouseListener(){
        ml= new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                if(e.getSource() == btnStart){
                    startClicked();
                }else if(e.getSource() == btnLoadFile){
                    loadFileClicked();
                }else if(e.getSource() == btnAddEq){
                    addEqClicked();
                }
//                else if(e.getSource() == btnNextA){
//                    //btnNextAClicked();
//                }
            }

            public void mousePressed(MouseEvent e) {
               // throw new UnsupportedOperationException("Not supported yet.");
            }
            public void mouseReleased(MouseEvent e) {
               // throw new UnsupportedOperationException("Not supported yet.");
            }
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
            public void mouseExited(MouseEvent e) {
               // throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }

//    private void btnNextAClicked(){
//        iv.setNextSystem();
//    }
    private void startClicked(){
        if( eqList.size()<=3 ){
            if (file!=null) {
                iv.setSystemFromFile(file.getAbsolutePath());
                if(file.exists())
                System.out.println("isnitnieje plik "+file.getAbsolutePath());
            }
         }else{
            setAand_bFromEqList();
            iv.setSystem(a, b);
            //System.out.println("Eq nie pusty!!"+file.getAbsolutePath());
         }
        Polyhedron p = iv.vertexFind();
        p.print();
        Main.getManager().setVertex(p);
        parent.addDrawingPanel();
        
        Main.getManager().start();
    }
    private void loadFileClicked(){
         //throw new UnsupportedOperationException("DOPISZ tę metodę !!  - Not supported yet.");
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
             file = fc.getSelectedFile();
             tfFileAdress.setText(file.getAbsolutePath());
             eqList.clear();
             try{
                eqList.add(new Equation(1, 0, 0, '>', 0)) ;
                eqList.add(new Equation(0, 1, 0, '>', 0)) ;
                eqList.add(new Equation(0, 0, 1, '>', 0)) ;
             }catch(Exception ex){

             }

             ta.setText("");

        }
    }
    private void addEqClicked(){
        double x1,x2,x3,sum;
        char eq='=';
        if(null==tfX1.getText() || "".equals(tfX1.getText()) )
            x1=0;
        if( null==tfX2.getText() || "".equals(tfX2.getText()) )
            x2=0;
        if( null==tfX3.getText() || "".equals(tfX3.getText()) )
            x3=0;
        if( null==tfSumValue.getText() || "".equals(tfSumValue.getText()) )
            sum=0;
        try{
            x1=Double.valueOf(tfX1.getText());
        }catch(Exception ex){
            x1=0;
        }
        try{
            x2=Double.valueOf(tfX2.getText());
        }catch(Exception ex){
            x2=0;
        }
        try{
            x3=Double.valueOf(tfX3.getText());
        }catch(Exception ex){
            x3=0;
        }
        try{
            sum=Double.valueOf(tfSumValue.getText());
        }catch(Exception ex){
            sum=0;
        }

        if(x1==0 && x2==0 && x3== 0)
            return;

        if(cbEqType.getSelectedIndex()!=-1){
            if(cbEqType.getSelectedIndex()==0) eq='<';
            if (cbEqType.getSelectedIndex() == 1) eq='>';
        }
        try{
            if(addToEqList(new Equation(x1, x2, x3, eq, sum))){
                 ta.setText(ta.getText()+x1+" x1 "+ (x2>=0?"+"+x2:x2)+" x2 "+(x3>=0?"+"+x3:x3)+" x3 "+eq+" "+sum+"\n" );
            }
        }catch(Exception e){ }
       tfX1.setText(""); tfX2.setText(""); tfX3.setText(""); tfSumValue.setText("");

        System.out.println("glugost listy: "+eqList.size());
    }

    private boolean addToEqList(Equation eq){
//        if(eq.getX1()==0 && eq.getX2() == 0 && eq.getX3() == 0)
//            return false;
        if (eqList.contains(eq))
            return false;
        eqList.add(eq);
        tfFileAdress.setText("");
        return true;
    }
    private void setAand_bFromEqList(){
        a = new Matrix(eqList.size(), 3);
        b =new Vector(eqList.size());
        if ( eqList.size()==0 ){
            return;
        }
        for(int i=0;i<eqList.size();i++){
            a.setElement(i, 0, eqList.get(i).getX1());
            a.setElement(i, 1, eqList.get(i).getX2());
            a.setElement(i, 2, eqList.get(i).getX3());
            b.setElement(i,eqList.get(i).getSum() );
        }
    }


    class Equation  {
        private double x1 = 0;
        private double x2 = 0;
        private double x3 = 0;
        private double sum = 0;
        private char sign;

        public Equation(double x1_,double x2_,double x3_, char sign_,double sum_) throws Exception{
            int wsp=1;
            if(x1_ == 0 && x2_ == 0 && x3_ == 0)
                throw new Exception ("nie poprawne dane");
            if( sign_ == '>' ){
                wsp=-1;
                sign_='<';
            }
            x1=x1_*wsp;
            x2=x2_*wsp;
            x3=x3_*wsp;
            sum = sum_*wsp;
            sign=sign_;
        }

        public void print () {
            System.out.println(x1+" "+x2+" "+x3+" "+sum);
        }

         Equation  normalize(Equation e) {
             if(e.getX1()!=0){                 
                 e.setX2(e.getX2()/e.getX1());
                 e.setX3(e.getX3()/e.getX1());
                 e.setSum(e.getSum()/e.getX1());
                 e.setX1(1);
             }else if(e.getX2()!= 0 ){                 
                 e.setX3(e.getX3()/e.getX2());
                 e.setSum(e.getSum()/e.getX2());
                 e.setX2(1);
             }else if(e.getX3()!= 0 ){                 
                 e.setSum(e.getSum()/e.getX3());
                 e.setX3(1);
             }
             return e;
         }

        // @Override
         boolean equals(Equation e1, Equation e2){
             Equation eq1 = e1.normalize(e1);
             Equation eq2 = e2.normalize(e2);
             if(eq1.getX1() == eq2.getX1() && eq1.getX2() == eq2.getX2() && eq1.getX3() == eq2.getX3() && eq1.getSum() == eq2.getSum() )
                 return true;
             return false;
         }

        @Override
        public boolean equals (Object o) {
            if (o == null) return false;
            if (getClass() != o.getClass()) return false;
            
            final Equation e = (Equation) o;
            if ( this.x1==e.getX1()
                    &&  this.x2 == e.getX3()
                    &&  this.x3 == e.getX3()
                    &&  this.sum == e.getSum()
                    &&  this.sign == e.getSign())
                return true;
            else
                return false;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 59 * hash + (int) (Double.doubleToLongBits(this.x1) ^ (Double.doubleToLongBits(this.x1) >>> 32));
            hash = 59 * hash + (int) (Double.doubleToLongBits(this.x2) ^ (Double.doubleToLongBits(this.x2) >>> 32));
            hash = 59 * hash + (int) (Double.doubleToLongBits(this.x3) ^ (Double.doubleToLongBits(this.x3) >>> 32));
            hash = 59 * hash + (int) (Double.doubleToLongBits(this.sum) ^ (Double.doubleToLongBits(this.sum) >>> 32));
            hash = 59 * hash + this.sign;
            return hash;
        }

         double getX1(){
            return x1;
        }

        /**
         * @return the x2
         */
        public double getX2() {
            return x2;
        }

        /**
         * @return the x3
         */
        public double getX3() {
            return x3;
        }

        /**
         * @return the sum
         */
        public double getSum() {
            return sum;
        }

        /**
         * @return the sign
         */
        public int getSign() {
            return sign;
        }

        /**
         * @param x1 the x1 to set
         */
        public void setX1(double x1) {
            this.x1 = x1;
        }

        /**
         * @param x2 the x2 to set
         */
        public void setX2(double x2) {
            this.x2 = x2;
        }

        /**
         * @param x3 the x3 to set
         */
        public void setX3(double x3) {
            this.x3 = x3;
        }

        /**
         * @param sum the sum to set
         */
        public void setSum(double sum) {
            this.sum = sum;
        }

        /**
         * @param sign the sign to set
         */
        private void setSign(char sign) {
            this.sign = sign;
        }
    }

}
