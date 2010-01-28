package gui;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import virtualcamera.IManagement;


/**
 * Ta klasa, rozszerzająca JPanel, odpowiada za malowanie sceny na ekranie.
 * @author alebar
 */
public class DrawingPanel extends JPanel {
    private IManagement zarzadca;
    private ArrayList<Polygon> polygons;
    private Color [] c;
    private int i;

    /**
     * Konstruktor tworzy obiekt klasy DrawingPanel. Ustawia odpowiedni rozmiar, kolor tła.
     * Klasa DrawingPanel wywołuje metodę z głównego programu, dlatego
     * potrzebuje mieć uchwyt do niego.
     * @param mP jest to uchwyt do obiektu głównego programu 
     * @param mouseList to obiekt który będzie reagował na zachowania myszy.
     */
    public DrawingPanel (int rz_h, int rz_w, IManagement zarz) {
        //this.addMouseListener(mouseList);
        zarzadca = zarz;
        Dimension d = new Dimension(rz_w, rz_h);
        this.setSize(d);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setMaximumSize(d);

        c = new Color[3];
        c[0] = Color.red;
        c[1] = Color.green;
        c[2] = Color.blue;

        this.setBackground(Color.BLACK);
    }

    /**
     * Przesłonięta metoda paintComponent(g) pobiera odpowiednie dane
     * z programu głównego i maluje je na ekranie w panelu.
     * @param g obiekt klasy Graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // show osie
        polygons = zarzadca.getOsie();
        i = 0;
        for (Polygon p : polygons) {
            g.setColor(c[i]);
            g.drawPolygon(p);
            i++;
        }
        // show obiekt
        polygons = zarzadca.getRysowanyObiekt();
        for (i = polygons.size()-1; i>=0; i--) {
            if (zarzadca.przeslaniacSciany()) {
                g.setColor(Color.lightGray);
                g.fillPolygon(polygons.get(i));
            }
            g.setColor(Color.white);
            g.drawPolygon(polygons.get(i));
        }
    }

}
