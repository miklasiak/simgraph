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
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setMaximumSize(d);
        
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
        g.setColor(Color.WHITE);
        polygons = zarzadca.getZrzutowane();
        for (Polygon p : polygons) {
            g.drawPolygon(p);
        }
    }

}
