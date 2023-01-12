package Tests;

import Exe.Ex4.geo.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Rect2DTest {
    private Point2D p = new Point2D(2,4);

    private Point2D p2 = new Point2D(6,0);
 private Rect2D rect = new Rect2D(p,p2);
    @Test
    void contains() {
        Point2D ot = new Point2D(2,3);
        assertEquals(true, rect.contains(ot));
        Rect2D copy = (Rect2D) rect.copy();
        copy.move(ot);
        assertFalse( copy.contains(ot));
        Rect2D copy1 = (Rect2D) rect.copy();
        Point2D p2 = new Point2D(4.82,3);
        copy1.rotate(this.p ,45);
        assertTrue( copy1.contains(p2));
    }

    @Test
    void area() {
        assertEquals(16,rect.area() );
        Rect2D copy = (Rect2D) rect.copy();
        copy.rotate(this.p, 45);
        assertEquals(16,copy.area(),0.001 );
    }

    @Test
    void perimeter() {
        assertEquals(16,rect.perimeter());
        Rect2D copy = (Rect2D) rect.copy();
        copy.rotate(this.p, 45);
        assertEquals(16,copy.perimeter());
    }

    @Test
    void move() {
        Rect2D copy = (Rect2D) rect.copy();
        copy.move(this.p);
        Point2D p1 = new Point2D(4,8);
        Point2D p2 = new Point2D(8,4);
        assertEquals(p1,copy.getPoints()[0]);
        assertEquals(p2, copy.getPoints()[3]);
    }
    @Test
    void copy(){
        Rect2D copy = (Rect2D) rect.copy();
        assertArrayEquals(rect.getPoints(), copy.getPoints());
    }

    @Test
    void scale() {
        Point2D sal = new Point2D(7,7);
        Point2D an1 = new Point2D(p);
        an1.scale(sal,0.9);
        Point2D an2 = new Point2D(p2);
        an2.scale(sal,0.9);
        rect.scale(sal,0.9);
        assertEquals(rect.getPoints()[0],an1);
        assertEquals(rect.getPoints()[3],an2);
        rect = new Rect2D(p,p2);
        an1 = new Point2D(p);
        an1.scale(sal,1.1);
        an2 = new Point2D(p2);
        an2.scale(sal,1.1);
        rect.scale(sal,1.1);
        assertEquals(rect.getPoints()[0],an1);
        assertEquals(rect.getPoints()[3],an2);
    }

    @Test
    void rotate() {
        Rect2D copy = (Rect2D) rect.copy();
        copy.rotate(this.p,45);
        Point2D p = new Point2D(7.6569,4.0);
        assertEquals(p,copy.getPoints()[3]);
    }

    @Test
    void getPoints() {
        assertEquals(this.p,rect.getPoints()[0]);
        assertEquals(this.p2,rect.getPoints()[3]);
    }
}