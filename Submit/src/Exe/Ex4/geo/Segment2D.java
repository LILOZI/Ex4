package Exe.Ex4.geo;


/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment2D implements GeoShapeable{
	private Point2D _p1;
	private Point2D _p2;

	public Segment2D(Point2D p1, Point2D p2) {
		this._p1=p1;
		this._p2 = p2;
	}

	@Override
	public boolean contains(Point2D ot) {
		return (_p1.distance(ot)+_p2.distance(ot)-_p1.distance(_p2)==0);
	}

	@Override
	public double area() {
		return 0;
	}

	@Override
	public double perimeter() {
		return _p1.distance(_p2);
	}

	@Override
	public void move(Point2D vec) {
		_p1.move(vec);
		_p2.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		Point2D p1 = new Point2D(this._p1);
		Point2D p2 = new Point2D(this._p2);

		return  new Segment2D(p1, p2);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		this._p1.scale(center,ratio);
		this._p2.scale(center,ratio);

	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		_p1.rotate(center,angleDegrees);
		_p2.rotate(center,angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] ans = new Point2D[2];
		ans[0] =new Point2D(_p1);
		ans[1] = new Point2D(_p2);
		return ans;
	}

}