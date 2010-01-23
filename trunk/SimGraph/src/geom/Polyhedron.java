package geom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import math.Matrix;
import vertexfinder.PolygonNotFoundException;

/**
 * Klasa reprezentuje woeloscian. Ma liste wielokatow: kazdy zdefiniowany
 * jako zbior punktow.
 * @author alebar
 */
public class Polyhedron implements Iterable {
    private ArrayList<Polygon3D> walls;

    public Polyhedron () {
        walls = new ArrayList<Polygon3D>();
    }
    
    public Polyhedron (Polygon3D[] p) {
        walls = new ArrayList<Polygon3D>();
        int len = p.length;
        for (int i=0; i<len; i++) {
            if (p[i].numOfPoints() >= 3)
                this.addWall(p[i]);
        }
        try{
            repair();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public Polyhedron (ArrayList<Polygon3D> a) {
        this.walls = a;
        try{
            repair();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static Polyhedron multiplyPoints (Polyhedron polyhedron, Matrix M) {
        ArrayList<Polygon3D> listaPolygonow = new ArrayList<Polygon3D>();
        Polygon3D newpoly;
        Point3D newpoint;
        for (Object wall : polyhedron) {
            newpoly = new Polygon3D();
            for (Object point : (Polygon3D) wall) {
                newpoint = Point3D.multiply((Point3D) point, M);
                newpoint.normalize();
                newpoly.addPoint(newpoint);
            }
            listaPolygonow.add(newpoly);
        }
        return new Polyhedron(listaPolygonow);
    }

    public void addWall (Polygon3D p) {
        this.walls.add(p);
    }

    public Polygon3D getWall (int i) {
        return walls.get(i);
    }

    public int numOfWalls () {
        return this.walls.size();
    }

    public void print () {
        System.out.println("##### BEGIN POLYHEDRON #####");
        int wallsNum = this.numOfWalls();
        Polygon3D poly;
        Point3D p;
        System.out.println("Num of walls: "+wallsNum);
        for (int i=0; i<wallsNum; i++) {
            System.out.println("Wall "+i+": ");
            poly = this.walls.get(i);
            for (int j=0; j<poly.numOfPoints(); j++) {
                p = poly.getPoint(j);
                System.out.print("[ "+p.getX()+"; "+p.getY() + "; "+p.getZ()+" ] ");
            }
            System.out.println();
        }
        System.out.println("##### END POLYHEDRON #####");
    }

    public void repair() throws Exception {
        Point3D[][] tabOfP;
        Polygon3D po;
        for(int i=0;i<walls.size();i++ ){
            ArrayList<Point3D[]> pary=new ArrayList<Point3D[]>();
            //pary.clear();
            po=walls.get(i);
            tabOfP= everyPairOfPoints(po);
            for(int j=0;j<tabOfP.length;j++){
                Point3D[] pair =null;
                for(int k=0;k<walls.size();k++){

                    if(i!=k && walls.get(k).hasPoint(tabOfP[j][0]) && walls.get(k).hasPoint(tabOfP[j][1]) ){
                        pair =new Point3D[2];
                        pair[0]=tabOfP[j][0];
                        pair[1]=tabOfP[j][1];
                        pary.add(pair);
//                        System.out.println("dodano parę: x:"+tabOfP[j][0].getX()+" y:"+tabOfP[j][0].getY()+" z:"+tabOfP[j][0].getZ() );
//                        System.out.println("           : x:"+tabOfP[j][1].getX()+" y:"+tabOfP[j][1].getY()+" z:"+tabOfP[j][1].getZ() );
                    }
                }

            }
//            System.out.println("długośc par__=: "+pary.size());

            if(pary.size()!=0)
                walls.get(i).setlist( reOrderPairs(pary) );
            //poukładaj pary
        }

    }

    private ArrayList<Point3D> reOrderPairs(ArrayList<Point3D[]> pary) throws PolygonNotFoundException{
        ArrayList<Point3D> ordered = new ArrayList<Point3D>();
        Point3D start/*,end*/,prev;//,next=null ;
        Point3D[][] copy ;
        boolean emptyFlag=false,endFlag=false;
        if(pary==null){
            throw new PolygonNotFoundException("brak listy par !! ");
        }
        if(/*pary==null ||*/ pary.size()<=0)
            throw new PolygonNotFoundException("pusta lista par !! rozmiar: "+pary.size());
        if(pary.size()==1){
            ordered.add(pary.get(0)[0]);
            ordered.add(pary.get(0)[1]);
            return ordered;
        }
        copy = new Point3D[pary.size()][2];
        for(int k=0; k<pary.size();k++){
            copy[k][0]=pary.get(k)[0];
            copy[k][1]=pary.get(k)[1];
        }

        start =copy[0][0];// pary.get(0)[0];
        prev =new Point3D(start.getX(), start.getY(), start.getZ() );//start;
        while(!emptyFlag || endFlag){

            for(int i=0; i<copy.length;i++){
                if(prev!= null && copy[i]!=null && copy[i][0]!= null && copy[i][0]!= null ){
                    if(prev!= null && prev.equals(copy[i][0]) ){
                        //if()
                        //ordered.add(copy[i][0]);
                        ordered.add(copy[i][1]);
                        prev =new Point3D(copy[i][1].getX(), copy[i][1].getY(), copy[i][1].getZ() );//copy[i][1];
                        copy[i]=null;
                    }
                    if(prev!= null && copy[i]!=null && copy[i][1]!= null && prev.equals(copy[i][1])  ){
                        //ordered.add(copy[i][1]);
                        ordered.add(copy[i][0]);
                        prev =new Point3D(copy[i][0].getX(), copy[i][0].getY(), copy[i][0].getZ() );//copy[i][0];
                        copy[i]=null;
                    }
                }
            }

            emptyFlag=true;
            for(Point3D[] pt:copy){
                if(pt!=null){
                    emptyFlag=false;
                    break;
                }
            }
        }

        return ordered;
    }
    private Point3D[][] everyPairOfPoints(Polygon3D po) throws Exception{
        Point3D[][] tab;
        int numOfPointsPermut = 0;
        if(po==null)
            throw new PolygonNotFoundException("nie znaleziono wielościanu");
        if(po.numOfPoints()>46342) //max value of points in one wall
            throw new PolygonNotFoundException("prawdopodobnie przekroczono zakres wierchołków na jednej ścianie");
        if(po.numOfPoints()>3){
            numOfPointsPermut= (int)( (po.numOfPoints() -3)*((double)po.numOfPoints()/2) ) +po.numOfPoints()  ;
        }else if (po.numOfPoints()==3 ){
            numOfPointsPermut =po.numOfPoints();
        }else if (po.numOfPoints()==2 ){
            numOfPointsPermut =1;
        }else{//numOfPointsPermut=3;
            throw new PolygonNotFoundException("nie właściwa liczba wierzchołków wielościanu");
        }
        tab=new Point3D[numOfPointsPermut][2];
        int licz=0;
        for(int i=0;i<po.numOfPoints()-1;i++){

            for(int j=i+1;j<po.numOfPoints();j++){
                if(licz>=tab.length){
                    System.out.println("przekroczono licznik: "+licz);
                    break;
                }
                tab[licz][0]=po.getPoint(i);
                tab[licz][1]=po.getPoint(j);
                licz++;
            }
        }
//        for(int r =0;r<tab.length;r++){
//            String a,b;
//            if(tab[r][0]==null)    a="NuLL";
//            else a = tab[r][0].printPoint();
//            if(tab[r][1]==null)    b="NuLL";
//            else b = tab[r][1].printPoint();
//
//            System.out.println("tab["+r+"]= a)"+a +" b)"+b );
//        }
        return tab;
    }

    public Iterator iterator() {
        return new MyPolyhedronIterator();
    }

    private class MyPolyhedronIterator implements Iterator {
        int pos;

        public boolean hasNext() {
            return ( pos < walls.size() );
        }

        public Object next() {
            if ( pos >= walls.size() )
                throw new NoSuchElementException();
            return walls.get(pos++);
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }
}
