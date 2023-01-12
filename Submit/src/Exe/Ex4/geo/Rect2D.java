package Exe.Ex4.geo;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {
	private Point2D Scorn;
	private Point2D Ecor;
	private Point2D p2;
	private Point2D _p4;
	private double length = 0;
	private double width = 0;

	/*
	this is the constructor for new object Rect2D that gets two points and creates the object
	because it's a new object we initialize all variables, we calculate the length and width;
	 */
	public Rect2D(Point2D p1, Point2D p2) {
		this.Scorn = p1;
		this.Ecor = p2;
		this.p2 = new Point2D(p1.x(), p2.y());
		this._p4 = new Point2D(p2.x(), p1.y());
		this.length = Scorn.distance(this.p2);
		this.width = Ecor.distance(this.p2);
	}

	public String toString()
	{ return Scorn.toString()+","+ p2.toString()+"," + Ecor.toString()+","+
			_p4.toString();}

	/*
	in order to not lose points in copy for example we input 4 points of the rectangle
	with this we couldn't rotate and then copy
	 */
	public Rect2D(Point2D p1, Point2D p2,Point2D p3,Point2D p4){
		this.Scorn = new Point2D(p1);
		this.Ecor = new Point2D(p3);
		this.p2 = new Point2D(p2);
		this._p4 = new Point2D(p4);
		this.length = Scorn.distance(this.p2);
		this.width = Ecor.distance(this.p2);

	}

	/*
	in order to know if a point is in the rectangle we made 4 triangles areas, if the points is outside
	then it's area will be bigger then the rectangle area, if it's in it then the area of the four
	triangles should be close to the area of the rectangle
	 */
	@Override
	public boolean contains(Point2D ot) {
		double Area = area();
		Triangle2D t1 = new Triangle2D(this.Scorn,this.p2,ot);
		double A1 = t1.area();
		Triangle2D t2 = new Triangle2D(this.Scorn, this._p4 ,ot);
		double A2 = t2.area	();
		Triangle2D t3 = new Triangle2D(this.Ecor , this.p2, ot);
		double A3 = t3.area();
		Triangle2D t4 = new Triangle2D(this.Ecor , this._p4 , ot);
		double A4 = t4.area();
		return (A1+A2+A3+A4 - Area)<0.001;

	}
	/*
	simple rectangle area
	 */
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
		p2.move(vec);
		_p4.move(vec);
	}

	@Override
	// we create new points in order to not change the current rectangle (deep copy)
	public GeoShapeable copy() {
		Point2D Ecor = new Point2D(this.Ecor);
		Point2D Scorn = new Point2D(this.Scorn);
		Point2D _p3 = new Point2D(this.p2);
		Point2D _p4 = new Point2D(this._p4);
		return new Rect2D( Scorn ,_p3, Ecor,_p4);}

	@Override
	public void scale(Point2D center, double ratio) {
		Scorn.scale(center,ratio);
		p2.scale(center,ratio);
		_p4.scale(center, ratio);
		Ecor.scale(center, ratio);

	}

	/*
	calls the point2D rotate function
	 */
	@Override
	public void rotate(Point2D center, double angleDegrees) {
		Scorn.rotate(center,angleDegrees);
		Ecor.rotate(center,angleDegrees);
		p2.rotate(center,angleDegrees);
		_p4.rotate(center,angleDegrees);
	}


	@Override
	public Point2D[] getPoints() {
		Point2D[] ans = new Point2D[4];
		ans[0] =new Point2D(Scorn);
		ans[1] = new Point2D(p2);
		ans[2] = new Point2D(Ecor);
		ans[3] = new Point2D(_p4);
		return ans;
	}
}