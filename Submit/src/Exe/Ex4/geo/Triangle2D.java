package Exe.Ex4.geo;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{
	private Point2D p1;
	private Point2D p2;
	private Point2D p3;


	public Triangle2D(Point2D p1, Point2D p2, Point2D p3){
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	@Override
	public boolean contains(Point2D ot) {
		double A = this.area();
		Triangle2D t1 = new Triangle2D(ot,this.p2,this.p3);
		double A1 = t1.area();
		Triangle2D t2 = new Triangle2D(ot,this.p1,this.p2);
		double A2 = t2.area();
		Triangle2D t3 = new Triangle2D(ot,this.p1,this.p3);
		double A3 = t3.area();
		boolean con = (A == A1 + A2 + A3);
		return con;
	}

	public String toString()
	{ return p1.toString()+","+ p2.toString()+","+ p3.toString();}

	@Override
	public double area() {
		double f1 = this.p1.x()*(this.p2.y() - this.p3.y());
		double f2 = this.p2.x()*(this.p3.y() - this.p1.y());
		double f3 = this.p3.x()*(this.p1.y() - this.p2.y());
		return Math.abs((f1 + f2 + f3)/2);
	}

	@Override
	public double perimeter() {
		double S1 = this.p1.distance(this.p2);
		double S2 = this.p1.distance(this.p3);
		double S3 = this.p2.distance(this.p3);
		double per = S1 + S2 + S3;
		return per;
	}

	@Override
	public void move(Point2D vec) {
		this.p1.move(vec);
		this.p2.move(vec);
		this.p3.move(vec);

	}

	@Override
	public GeoShapeable copy() {
		Point2D p1 = new Point2D(this.p1);
		Point2D p2 = new Point2D(this.p2);
		Point2D p3 = new Point2D(this.p3);
		return new Triangle2D(p1,p2,p3);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		this.p1.scale(center,ratio);
		this.p2.scale(center,ratio);
		this.p3.scale(center,ratio);

	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		p1.rotate(center,angleDegrees);
		p2.rotate(center,angleDegrees);
		p3.rotate(center,angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] p = new Point2D[3];
		p[0] = new Point2D(this.p1);
		p[1] = new Point2D(this.p2);
		p[2] = new Point2D(this.p3);
		return p;
	}

}
