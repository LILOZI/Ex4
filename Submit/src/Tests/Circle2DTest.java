package Tests;

import Exe.Ex4.geo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Circle2DTest {
    private Point2D p = new Point2D(3,4);
    private double rad = 5;
    private Point2D helper = new Point2D(1,1);
    Circle2D cir = new Circle2D(p,rad);

    @Test
    void scale() {

        cir.scale(helper,0.9);
        assertEquals(cir.getPoints()[0],new Point2D(p.x() *0.9 + helper.x()*(0.1),p.y()*0.9 + helper.y()*(0.1)));
        assertEquals(cir.getRadius(),rad*0.9);
        cir = new Circle2D(p,rad);
        cir.scale(helper,1.1);
        assertEquals(cir.getPoints()[0],new Point2D(p.x() *1.1 + helper.x()*(-0.1),p.y()*1.1 + helper.y()*(-0.1)));
        assertEquals(cir.getRadius(),rad*1.1);



//        Point2D s = p;
//        s.scale(helper,0.9);
//        double r = rad*0.9;
//        helper = new Point2D(1,1);
//        Point2D s1 = p;
//        s1.scale(helper,1.1);
//        double r1 = rad*1.1;
//        helper = new Point2D(1,1);
//        assertEquals(s,new Point2D(p.x() *0.9 + helper.x()*(0.1),p.y()*0.9 + helper.y()*(0.1)));
//        assertEquals(r,rad*0.9);
//        assertEquals(s1,new Point2D(p.x() *1.1 + helper.x()*(-0.1),p.y()*1.1 + helper.y()*(-0.1)));
//        assertEquals(r1,rad*1.1);
    }

    @Test
    void rotate() {
        cir.rotate(p,180);// shouldn't do anything
        assertEquals(p,cir.getPoints()[0]);
        cir.rotate(helper, 90);
        assertEquals(new Point2D(-2,3),cir.getPoints()[0]);
    }
}