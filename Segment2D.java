package Exe.Ex4.geo;


import java.awt.*;

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment2D implements GeoShapeable{
	private Point2D p1;
	private Point2D p2;


	public Segment2D(Point2D p1, Point2D p2) {
		this.p1 = new Point2D(p1);
		this.p2 = new Point2D(p2);
	}

	@Override
	public boolean contains(Point2D ot) {
		double slope = (p2.y() - p1.y())/(p2.x() - p1.x());
		double b = p1.y() - (slope*p1.x());
		if (ot.y() == (slope*ot.x()) + b){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public double area() {
		// Segment has no area
		return 0;
	}

	@Override
	public double perimeter() {
		return p1.distance(p2);
	}

	@Override
	public void move(Point2D vec) {
		this.p2.move(vec);
		this.p1.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		return new Segment2D(p1,p2);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] p = new Point2D[2];
		p[0] = new Point2D(this.p1);
		p[1] = new Point2D(this.p2);
		return p;
	}
	
}