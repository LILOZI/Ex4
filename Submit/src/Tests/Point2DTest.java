package Tests;

import Exe.Ex4.geo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point2DTest {
    private Point2D p = new Point2D(3,5);
    private Point2D p1 = new Point2D(3,-5);
    private Point2D p2 = new Point2D(-3,5);
    private Point2D helper = new Point2D(2,2);
    @Test
    void move() {
        reset();
        p.move(helper);
        p1.move(helper);
        p2.move(helper);
        assertEquals(new Point2D(5,7),p);
        assertEquals(new Point2D(5,-3),p1);
        assertEquals(new Point2D(-1,7),p2);
    }


    @Test
    void scale() {
        reset();
        Point2D s = new Point2D(p);
        s.scale(helper,0.9);
        Point2D s1 = new Point2D(p1);
        s1.scale(helper,0.9);
        Point2D s2 = new Point2D(p2);
        s2.scale(helper,0.9);
        Point2D s4 = new Point2D(p);
        s4.scale(helper,1.1);
        Point2D s5 = new Point2D(p1);
        s5.scale(helper,1.1);
        Point2D s6 = new Point2D(p2);
        s6.scale(helper,1.1);
        assertEquals(s,new Point2D(p.x() *0.9 + helper.x()*(0.1),p.y()*0.9 + helper.y()*(0.1)));
        assertEquals(s1,new Point2D(p1.x() *0.9 + helper.x()*(0.1),p1.y()*0.9 + helper.y()*(0.1)));
        assertEquals(s2,new Point2D(p2.x() *0.9 + helper.x()*(0.1),p2.y()*0.9 + helper.y()*(0.1)));
        assertEquals(s4,new Point2D(p.x() *1.1 + helper.x()*(-0.1),p.y()*1.1 + helper.y()*(-0.1)));
        assertEquals(s5,new Point2D(p1.x() *1.1 + helper.x()*(-0.1),p1.y()*1.1 + helper.y()*(-0.1)));
        assertEquals(s6,new Point2D(p2.x() *1.1 + helper.x()*(-0.1),p2.y()*1.1 + helper.y()*(-0.1)));
    }

    @Test
    void rotate() {
        reset();
        p.rotate(helper,45);
        p1.rotate(helper , 180 );
        p2.rotate(helper,90);

        assertEquals(new Point2D(0.58579,4.8284),p);
        assertEquals(new Point2D(1,9),p1);
        assertEquals(new Point2D(-1,-3),p2);
    }
    /*
    reset the point for the starting values
     */
    public void reset(){
        this.p = new Point2D(3,5);
        this.p1 = new Point2D(3,-5);
        this.p2 = new Point2D(-3,5);
        this.helper = new Point2D(2,2);
    }
}