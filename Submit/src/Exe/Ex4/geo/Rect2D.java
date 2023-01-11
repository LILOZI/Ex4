package Exe.Ex4.geo;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {
	private Point2D Scorn;
	private Point2D Ecor;
	private Point2D _p3;
	private Point2D _p4;
	private double length = 0;
	private double width = 0;

	public Rect2D(Point2D p1, Point2D p2) {
		this.Scorn = p1;
		this.Ecor = p2;
		this._p3 = new Point2D(p1.x(),p2.y());
		this._p4 = new Point2D(p2.x(),p1.y());
		this.length = Scorn.distance(_p3);
		this.width = Ecor.distance(_p3);
	}
	// for the copy
	public Rect2D(Point2D p1, Point2D p2,Point2D p3,Point2D p4){
		this.Scorn = new Point2D(p1);
		this.Ecor = new Point2D(p2);
		this._p3 = new Point2D(p3);
		this._p4 = new Point2D(p4);
		this.length = Scorn.distance(_p3);

		this.width = Ecor.distance(_p3);

	}

	@Override
	public boolean contains(Point2D ot) {
		double Area = area();
		Triangle2D t1 = new Triangle2D(this.Scorn,this._p3,ot);
		double A1 = t1.area();
		Triangle2D t2 = new Triangle2D(this.Scorn, this._p4 ,ot);
		double A2 = t2.area	();
		Triangle2D t3 = new Triangle2D(this.Ecor , this._p3 , ot);
		double A3 = t3.area();
		Triangle2D t4 = new Triangle2D(this.Ecor , this._p4 , ot);
		double A4 = t4.area();
		return (A1+A2+A3+A4 - Area)<0.001;

	}
	@Override
	public double area() {
		return this.length*this.width;
	}

	@Override
	public double perimeter() {
		return length*2+width*2;
	}

	@Override
	public void move(Point2D vec) {
		Scorn.move(vec);
		Ecor.move(vec);
		_p3.move(vec);
		_p4.move(vec);
	}

	@Override

	public GeoShapeable copy() {
		Point2D Ecor = new Point2D(this.Ecor);
		Point2D Scorn = new Point2D(this.Scorn);
		Point2D _p3 = new Point2D(this._p3);
		Point2D _p4 = new Point2D(this._p4);
		return new Rect2D( Scorn , Ecor,_p3,_p4);}

	@Override
	public void scale(Point2D center, double ratio) {
		this.Ecor.scale(center,ratio);
		this.Scorn.scale(center,ratio);
		this._p3.scale(center,ratio);
		this._p4.scale(center,ratio);

	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		ArrayList<Point2D> polyiester = new ArrayList<>();
		polyiester.add(Scorn);
		polyiester.add(_p3);
		polyiester.add(Ecor);
		polyiester.add(_p4);
		Polygon2D poly = new Polygon2D(polyiester);
		poly.rotate(center,angleDegrees);
		Scorn = new Point2D(poly.getPoints()[0]);
		_p3 = new Point2D(poly.getPoints()[1]);
		Ecor = new Point2D(poly.getPoints()[2]);
		_p4 = new Point2D(poly.getPoints()[3]);
	}


	@Override
	public Point2D[] getPoints() {
		Point2D[] ans = new Point2D[4];
		ans[0] =new Point2D(Scorn);
		ans[1] = new Point2D(Ecor);
		ans[2] = new Point2D(_p3);
		ans[3] = new Point2D(_p4);
		return ans;
	}

}
