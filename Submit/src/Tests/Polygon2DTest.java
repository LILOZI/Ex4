package Tests;

import Exe.Ex4.geo.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Polygon2DTest {
    private double [] x = {2,4,5,10,20,11};
    private double [] y = {8,7,3,1,2,1};
    private ArrayList<Point2D> lst = new ArrayList<>();
    private void push (double [] x , double [] y ){
        for(int i = 0 ; i < x.length ; i++){
         Point2D p = new Point2D(x[i],y[i]);
            this.lst.add(p);
        }
    }


    @Test
    void contains() {
        ArrayList<Point2D> poly = new ArrayList<>();
        Point2D p1 = new Point2D(2,8);
        Point2D p2 = new Point2D(4,7);
        Point2D p3 = new Point2D(5,3);
        Point2D p5 = new Point2D(10,1);
        Point2D p6 = new Point2D(3,2);
        Point2D p7 = new Point2D(5,1);
        poly.add(p1);
        poly.add(p2);
        poly.add(p3);
        poly.add(p5);
        poly.add(p6);
        Polygon2D poliester = new Polygon2D(poly);
        Point2D ot = new Point2D(3,6);
        Point2D otf = new Point2D(1,7);
        assertEquals(true, poliester.contains(ot));
     //   assertEquals(false, poliester.contains(otf));
    }

    @Test
    void area() {
        double [] x = {1,2,6,3,6};
        double [] y = {2,5,3,4,2};
        push(x, y );
        Polygon2D poly = new Polygon2D(lst);
         assertEquals(8, poly.area());


    }

    @Test
    void perimeter() {
        push(this.x, this.y );
        Polygon2D poly = new Polygon2D(lst);
        double perimeter = poly.perimeter();
        assertEquals(30.96,perimeter,0.01);

    }

    @Test
    void move() {
        Point2D[] ans = new Point2D[x.length];
        push(this.x, this.y );
        Polygon2D poly = new Polygon2D(lst);
        poly.move(new Point2D(3,3));
        for(int i = 0; i<x.length;i++){
            ans[i] = new Point2D(x[i],y[i]);
            ans[i].move(new Point2D(3,3));
        }
        assertArrayEquals(poly.getPoints(),ans);
    }

    @Test
    void copy() {
        push(this.x, this.y );
        Polygon2D poly = new Polygon2D(lst);
        Point2D[]  real =  poly.copy().getPoints();
        Point2D[] excpected = poly.getPoints();
        assertArrayEquals(excpected, real );
    }

    @Test
    void scale() {
        Point2D[] ans = new Point2D[x.length];
        push(this.x, this.y );
        Polygon2D poly = new Polygon2D(lst);
        Point2D sal = new Point2D(8,9);
        poly.scale(sal,0.9);
        for(int i = 0; i<x.length;i++){
            ans[i] = new Point2D(x[i],y[i]);
            ans[i].scale(sal,0.9);
        }
        assertArrayEquals(poly.getPoints(),ans);

        lst.clear();
        push(this.x,this.y);
        poly = new Polygon2D(lst);
        poly.scale(sal,1.1);
        for(int i = 0; i<x.length;i++){
            ans[i] = new Point2D(x[i],y[i]);
            ans[i].scale(sal,1.1);
        }
        assertArrayEquals(poly.getPoints(),ans);
    }

    @Test
    void rotate() {
        // for 180 should stay the same twice should come back to the same place
        push(this.x, this.y );
        Polygon2D poly = new Polygon2D(lst);
        Polygon2D same = new Polygon2D (lst);
        Point2D center = new Point2D(4,4);
        poly.rotate(center , (180));
        poly.rotate(center , (180));
        String real = poly.toString();
        String Excpected = same.toString();
        assertEquals(Excpected, real);
        // 90 degree
        poly.rotate(center,(90));
        Point2D p1= new Point2D(0,2);
        Point2D p2= new Point2D(1,4);
        Point2D p3= new Point2D(5,5);
        Point2D p4= new Point2D(7,10);
        Point2D p5= new Point2D(6,20);
        Point2D p6= new Point2D(7 , 11);
        Point2D [] excpected = {p1,p2,p3,p4,p5,p6 };
        Point2D [] realp = poly.getPoints();
        assertArrayEquals( excpected, realp);
    }

    @Test
    void getPoints() {
      push(this.x, this.y);
        Polygon2D poly = new Polygon2D(lst);
        Point2D  [] real  = new Point2D[lst.size()];
        for(int i = 0 ; i < lst.size();i++){
            real[i] = lst.get(i);
        }
        Point2D  [] p  =poly.getPoints();
        assertArrayEquals(real,p);
    }
}