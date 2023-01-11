package Exe.Ex4.geo;

import java.util.ArrayList;
import java.awt.geom.Line2D;



/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 *
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
public class Polygon2D implements GeoShapeable{

	private ArrayList<Point2D> _polygon =new ArrayList<>();
	public Polygon2D(ArrayList<Point2D> points ) {
		this._polygon.addAll(points); // we add al the Arraylist into the private Arraylist of this class
	}
	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		int counter = 0;
		Point2D p = new Point2D(100, ot.y());
		Line2D line1 = new Line2D.Double(ot.x(), ot.y(), p.x(), p.y() );
		Line2D line2 = new Line2D.Double(this._polygon.get(0).x(), this._polygon.get(0).y(),
				this._polygon.get(this._polygon.size()-1).x(), this._polygon.get(this._polygon.size()-1).y());
		boolean clash = line2.intersectsLine(line1);
		if (clash == true) {
			counter++;
		}
		for (int i = 0, j = 1; i < this._polygon.size()-1; i++, j++) {
			line2 = new Line2D.Double(this._polygon.get(i).x(), this._polygon.get(i).y(),
					this._polygon.get(j).x(), this._polygon.get(j).y());
			clash = line2.intersectsLine(line1);
			if(clash == true) {
				counter++;
			}
		}
		if ((counter%2)==0 || counter == 0)
			return false;
		return true;
	}





	@Override
	public double area() {
		double area = 0;
		for(int i = 0; i < _polygon.size()-1 ; i++){
			area += (_polygon.get(i).x()* _polygon.get(i+1).y()- (_polygon.get(i).y()* _polygon.get(i+1).x()));
		}
		area += (_polygon.get(_polygon.size()-1).x()* _polygon.get(0).y()- (_polygon.get(_polygon.size()-1).y()* _polygon.get(0).x()));
		return Math.abs(area*0.5);
	}

	@Override
	public double perimeter() {
		double sum = 0 ;
		for(int i = 1; i < _polygon.size()-2; i++){
			sum += _polygon.get(i).distance(_polygon.get(i+1));
		}
		return sum+ _polygon.get(0).distance(_polygon.get(_polygon.size()-1));
	}

	@Override
	public void move(Point2D vec) {
		for(int i = 0; i < _polygon.size() ; i ++){
			Point2D temp  = _polygon.get(i);
			temp = new Point2D(_polygon.get(i).x()+vec.x(), _polygon.get(i).y()+vec.y());
			_polygon.set(i, temp);
		}
	}

	@Override
	public GeoShapeable copy() {return new Polygon2D(_polygon); }

	@Override
	public void scale(Point2D center, double ratio) {
		for(int i = 0; i < this._polygon.size(); i++){
			this._polygon.get(i).scale(center,ratio);
		}
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		for(int i = 0; i < _polygon.size() ; i++){
			Point2D p = new Point2D(_polygon.get(i));
			p.rotate(center,angleDegrees);
			_polygon.set(i,p);
		}

	}

	@Override
	public Point2D[] getPoints() {
		Point2D [] p = new Point2D[_polygon.size()];
		for (int i = 0; i < this._polygon.size(); i++) {
			p[i] = new Point2D( _polygon.get(i));
		}
		return p;
	}
	// to string
	public String tostring() {
		String ans = "";
		for (int i = 0; i < _polygon.size(); i++){
			ans+= _polygon.get(i)+" ";
		}
		return ans;
	}


	///////////////////////////////////helping functions////////////////////////
	/*
	adds a new point into the class array list -> adds new point to the polygon
	 */
	public void addpoint (Point2D aimashlha){this._polygon.add(new Point2D(aimashlha));}
	/*
	returns array of the x coordinates of the points of the polygon
	 */
	public  double [] getX(){
		double [] x = new double[_polygon.size()];
		for(int i = 0; i< _polygon.size(); i++){
			x[i]= _polygon.get(i).x();
		}
		return x;
	}
	/*
	returns array of the y coordinates of the points of the polygon
	 */
	public  double [] getY(){
		double [] y = new double[_polygon.size()];
		for(int i = 0; i< _polygon.size(); i++){
			y[i]= _polygon.get(i).y();
		}
		return y;
	}
}
