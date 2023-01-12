package Tests;

import Exe.Ex4.geo.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Triangle2DTest {
    private Point2D p1 = new Point2D(4,3);
    private Point2D p2 =new Point2D(2,6);
    private Point2D p3 =new Point2D(9,5);
    Triangle2D tri = new Triangle2D(p1,p2,p3);

    @Test
    void contains() {
        Point2D ot = new Point2D(4,4);// in
        Point2D ot1 = new Point2D(4,7); // out
        Point2D ot2 = new Point2D (3,5);// in
        assertTrue(tri.contains(ot));
        assertFalse(tri.contains(ot1));
        assertTrue(tri.contains(ot2));
    }

    @Test
    void area() {
        assertEquals(9.5, tri.area());
    }

    @Test
    void perimeter() {
        assertEquals(16.0617,tri.perimeter(),0.01);
    }

    @Test
    void move() {
        Point2D move = new Point2D(2,2);
        Triangle2D copy = (Triangle2D) tri.copy();
        copy.move(move);
        assertEquals(new Point2D(6,5),copy.getPoints()[0]);
        assertEquals(new Point2D(4,8),copy.getPoints()[1]);
        assertEquals(new Point2D(11,7),copy.getPoints()[2]);
    }

    @Test
    void copy() {
        Triangle2D copy = (Triangle2D) tri.copy();
        assertArrayEquals(tri.getPoints(),copy.getPoints());
    }

    @Test
    void scale() {
        Point2D sal = new Point2D(7,7);
        Point2D an1 = new Point2D(p1);
        an1.scale(sal,0.9);
        Point2D an2 = new Point2D(p2);
        an2.scale(sal,0.9);
        Point2D an3 = new Point2D(p3);
        an3.scale(sal,0.9);
        tri.scale(sal,0.9);
        assertEquals(tri.getPoints()[0],an1);
        assertEquals(tri.getPoints()[1],an2);
        assertEquals(tri.getPoints()[2],an3);

        tri = new Triangle2D(p1,p2,p3);
        an1 = new Point2D(p1);
        an1.scale(sal,1.1);
        an2 = new Point2D(p2);
        an2.scale(sal,1.1);
        an3 = new Point2D(p3);
        an3.scale(sal,1.1);
        tri.scale(sal,1.1);
        assertEquals(tri.getPoints()[0],an1);
        assertEquals(tri.getPoints()[1],an2);
        assertEquals(tri.getPoints()[2],an3);

    }

    @Test
    void rotate() {
        Point2D helper = new Point2D(5,5);
        Triangle2D copy = (Triangle2D) tri.copy();
        copy.rotate(helper,65);
        Point2D p11 = new Point2D(4,3);
        Point2D p22 = new Point2D(2,6);
        Point2D p33 = new Point2D(9,5);

        Point2D [] arr = {p11,p22,p33};
        assertArrayEquals(arr,tri.getPoints());
    }

    @Test
    void getPoints() {
        assertEquals(p1, tri.getPoints()[0]);
        assertEquals(p2, tri.getPoints()[1]);
        assertEquals(p3, tri.getPoints()[2]);
    }
}