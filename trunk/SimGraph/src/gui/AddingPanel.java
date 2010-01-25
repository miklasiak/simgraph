/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author kamil
 */
public class AddingPanel extends JPanel {

    MouseListener ml;
    JButton btnLoadFile,btnStart,btnAddEq;
    JTextField tfX1,tfX2,tfX3,tfSumValue,tfFileAdress;
    JComboBox cbEqType;
    JLabel lblx1,lblx2,lblx3;
    JTextArea ta;
    JScrollPane sp;
    JPanel subPanel;
    WindowFrame parent;
    int topMargin=10,leftMargin=10,high=20;
    int hm=10,wm=1,widthOfBtns=160,bottomMargin=10;
    int tfXWidth=30,lblXWidth=27;

    public AddingPanel(WindowFrame p) {
        parent = p;
        initComponents();
    }
    private void initComponents(){
        setLayout(null);
        setBackground(Color.white);
        tfFileAdress=new JTextField();
        btnLoadFile=new JButton("wczytaj plik");
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
    }
    private int nextHeight(Component cp){
        return cp.getY()+ cp.getHeight()+hm ;
    }
    private int nextWidth(Component cp){
        return cp.getX()+ cp.getWidth()+wm ;
    }

    protected void setFocus (boolean f) {
        tfX1.setFocusable(f);
        tfX2.setFocusable(f);
        tfX3.setFocusable(f);
        tfFileAdress.setFocusable(f);
        cbEqType.setFocusable(f);
        tfSumValue.setFocusable(f);
        
        ta.setFocusable(f);

        btnLoadFile.setFocusable(f);
        btnAddEq.setFocusable(f);
        btnStart.setFocusable(f);

        sp.setFocusable(f);
        subPanel.setFocusable(f);
    }

    public void fillComponents(){
        addMouseListener();

        tfFileAdress.setEditable(false);
        tfFileAdress.setBounds(leftMargin,topMargin,getWidth()-leftMargin,high);
        add(tfFileAdress);

        btnLoadFile.setBounds(leftMargin, nextHeight(tfFileAdress)/*tfFileAdress.getY()+ tfFileAdress.getHeight()+hm*/, widthOfBtns, high);
        btnLoadFile.addMouseListener(ml);
        add(btnLoadFile);

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
        cbEqType.addItem("=");
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
        sp = new JScrollPane(ta);
        sp.setBackground(Color.yellow);
        //sp.setBounds(0, 0, subPanel.getWidth(), subPanel.getHeight());
        subPanel.add(sp);
        
        //subPanel.add(ta, BorderLayout.CENTER);
        //subPanel.setBackground(Color.green);
        add(subPanel);

        btnStart.setBounds(leftMargin, nextHeight(subPanel), widthOfBtns, high);
        btnStart.addMouseListener(ml);
        add(btnStart);
        setBackground(Color.BLUE);
    }
    


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
    
    private void startClicked(){
        parent.toggleFocusable();
    }
    private void loadFileClicked(){
         throw new UnsupportedOperationException("DOPISZ tę metodę !!  - Not supported yet.");
    }
    private void addEqClicked(){
        int x1,x2,x3,sum;
        String eq;
        if(null==tfX1.getText() || "".equals(tfX1.getText()) )
            x1=0;
        if( null==tfX2.getText() || "".equals(tfX2.getText()) )
            x2=0;
        if( null==tfX3.getText() || "".equals(tfX3.getText()) )
            x3=0;
        if( null==tfSumValue.getText() || "".equals(tfSumValue.getText()) )
            sum=0;
        try{
            x1=Integer.valueOf(tfX1.getText());
        }catch(Exception ex){
            x1=0;
        }
        try{
            x2=Integer.valueOf(tfX2.getText());
        }catch(Exception ex){
            x2=0;
        }
        try{
            x3=Integer.valueOf(tfX3.getText());
        }catch(Exception ex){
            x3=0;
        }
        try{
            sum=Integer.valueOf(tfSumValue.getText());
        }catch(Exception ex){
            sum=0;
        }

        if(x1==0 && x2==0 && x3== 0)
            return;

        ta.setText(ta.getText()+x1+" x1 "+ (x2>=0?"+"+x2:x2)+" x2 "+(x3>=0?"+"+x3:x3)+" x3 "+"\n" );

    }

}
