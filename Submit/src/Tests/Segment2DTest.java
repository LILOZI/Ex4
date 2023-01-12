package Tests;

import Exe.Ex4.geo.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Segment2DTest {
    private Point2D p1 = new Point2D(1,2);
    private Point2D p2 = new Point2D (5,6);
    private Segment2D seg =new Segment2D(p1,p2);
    @Test
    void contains() {
        Point2D test =  new Point2D(3,4);// should be true
        Point2D tester = new Point2D(0,1);// should be false
        assertTrue(seg.contains(test));
        assertFalse(seg.contains(tester));
    }

    @Test
    void area() {
        assertEquals(0,seg.area());
    }

    @Test
    void perimeter() {
        assertEquals(p1.distance(p2),seg.perimeter());
    }

    @Test
    void move() {
        Point2D vector= new Point2D(3,3);
        Point2D antivector = new Point2D(-3,-3);
        seg.move(vector);
        Point2D p11 = new Point2D(4,5);
        Point2D p22 = new Point2D (8,9);
        Point2D [] points = {p11,p22};
        assertArrayEquals(points, seg.getPoints());
        seg.move(antivector);
    }

    @Test
    void copy() {
        Segment2D ment = (Segment2D) seg.copy();
        assertArrayEquals(seg.getPoints(),ment.getPoints());
    }

    @Test
    void scale() {
//        seg = new Segment2D(p1,p2);
        Point2D sal = new Point2D(7,7);
        Point2D an1 = new Point2D(p1);
        an1.scale(sal,0.9);
        Point2D an2 = new Point2D(p2);
        an2.scale(sal,0.9);
        seg.scale(sal,0.9);
        assertEquals(seg.getPoints()[0],an1);
        assertEquals(seg.getPoints()[1],an2);
        seg = new Segment2D(p1,p2);
        an1 = new Point2D(p1);
        an1.scale(sal,1.1);
        an2 = new Point2D(p2);
        an2.scale(sal,1.1);
        seg.scale(sal,1.1);
        assertEquals(seg.getPoints()[0],an1);
        assertEquals(seg.getPoints()[1],an2);

    }

    @Test
    void rotate() {
        Point2D helper = new Point2D(1,3);
        Segment2D ment = (Segment2D) seg.copy();
        ment.rotate(helper, 180);
        Point2D p1 = new Point2D(1,4);
        Point2D p2 = new Point2D(-3,4.4409E-16);
        Point2D [] foreal = {p1,p2};
        assertArrayEquals(foreal,ment.getPoints());
    }

    @Test
    void getPoints() {
        assertEquals(p1, seg.getPoints()[0]);
        assertEquals(p2,seg.getPoints()[1]);
    }
}