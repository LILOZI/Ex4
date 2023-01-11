package Exe.Ex4.gui;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.*;

import javax.swing.*;

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 */
public class Ex4 implements Ex4_GUI{
	private  ShapeCollectionable _shapes = new ShapeCollection();
	private  GUI_Shapeable _gs;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";
	private  Point2D _p1;
	private Point2D _p2;
	private  static Ex4 _winEx4 = null;
	/////////////////////////// private constants for the functions////////////////////////
	private static boolean flag = false;
	private ArrayList<Point2D> polygon = new ArrayList<>();// Arraylist that stores the polygon points
	////////////////////////////////////////////////////////////////////////////////////////
	
	private Ex4() {
			init(null);
	}
	public void init(ShapeCollectionable s) {
		if(s==null) {_shapes = new ShapeCollection();}
		else {_shapes = s.copy();}
		GUI_Shapeable _gs = null;
		Polygon2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		Point2D _p1 = null;
	}
	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}
	public static Ex4 getInstance() {
		if(_winEx4 ==null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}
	
	public void drawShapes() {
		StdDraw_Ex4.clear();
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shapeable sh = _shapes.get(i);
				
				drawShape(sh);
			}
			if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}
	private static void drawShape(GUI_Shapeable g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if(g.isSelected()) {StdDraw_Ex4.setPenColor(Color.gray);}
		GeoShapeable gs = g.getShape();
		boolean isFill = g.isFilled();
		if(gs!=null) {
			if (gs instanceof Polygon2D polyY) {
				double[] x = polyY.getX(); // stores all the x coordinate of each point
				double[] y = polyY.getY(); // stores all the y coordinate of each point
				if (isFill) {
					StdDraw_Ex4.filledPolygon(x, y);
				} else {
					StdDraw_Ex4.polygon(x, y);
				}
			}

			if (gs instanceof Circle2D) {
				Circle2D c = (Circle2D) gs;
				Point2D cen = c.getPoints()[0];
				double rad = c.getRadius();
				if (isFill) {
					StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
				} else {
					StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
				}
			}
			if (gs instanceof Rect2D erect) {
				Point2D rect = erect.getPoints()[0];
				Point2D angle = erect.getPoints()[1];
				Point2D  p3 = new Point2D(erect.getPoints()[2]);
				Point2D  p4 = new Point2D(erect.getPoints()[3]);
				double[] x ={rect.x(),p3.x(),angle.x(),p4.x()} ; // stores all the x coordinate of each point
				double[] y = {rect.y(),p3.y(),angle.y(),p4.y()}; // stores all the y coordinate of each point
				if (isFill) {
					StdDraw_Ex4.filledPolygon(x,y);
				} else {
					StdDraw_Ex4.polygon(x,y);

				}
			}

			if (gs instanceof Triangle2D) {
				Triangle2D t = (Triangle2D) gs;
				Point2D p1 = t.getPoints()[0];
				Point2D p2 = t.getPoints()[1];
				Point2D p3 = t.getPoints()[2];
				double[] x = {p1.x(), p2.x(), p3.x()};
				double[] y = {p1.y(), p2.y(), p3.y()};
				if (isFill) {
					StdDraw_Ex4.filledPolygon(x, y);
				} else {
					StdDraw_Ex4.polygon(x, y);
				}
			}

			if (gs instanceof Segment2D) {
				Segment2D seg = (Segment2D) gs;
				Point2D p = seg.getPoints()[0];
				Point2D p1 = seg.getPoints()[1];
				StdDraw_Ex4.line(p.x(), p.y(), p1.x(), p1.y());

			}
		}
	}
	private void setColor(Color c) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setColor(c);
			}
		}
	}
	private void setFill() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	public void actionPerformed(String p) {
		_mode = p;
		if (p.equals("Blue")) {
			_color = Color.BLUE;
			setColor(_color);
		}
		if (p.equals("Red")) {
			_color = Color.RED;
			setColor(_color);
		}
		if (p.equals("Green")) {
			_color = Color.GREEN;
			setColor(_color);
		}
		if (p.equals("White")) {
			_color = Color.WHITE;
			setColor(_color);
		}
		if (p.equals("Black")) {
			_color = Color.BLACK;
			setColor(_color);
		}
		if (p.equals("Yellow")) {
			_color = Color.YELLOW;
			setColor(_color);
		}
		if (p.equals("Fill")) {
			_fill = true;
			setFill();
		}
		if (p.equals("Empty")) {
			_fill = false;
			setFill();
		}
		if (p.equals("Clear")) {
			_shapes.removeAll();
		}
		if (_mode.equals("All")) {
			selectAll();
		}
		if (_mode.equals("Anti")) {
			selectAnti();
		}
		if (_mode.equals("None")) {
			selectNone();
		}
		if (_mode.equals("Info")) {
			String log = getInfo();
			System.out.println(log);
		}
		if (_mode.equals("Save")) {
			_shapes.save(findPathS());
		}
		if (_mode.equals("Load")) {
			_shapes.load(findPathL());
		}
		if (_mode.equals("ByToString")) {
			_shapes.sort(ShapeComp.CompByToString);
		}
		if (_mode.equals("ByAntiToString")) {
			_shapes.sort(ShapeComp.CompByAntiToString);
		}
		if (_mode.equals("ByArea")) {
			_shapes.sort(ShapeComp.CompByArea);
		}
		if (_mode.equals("ByAntiArea")) {
			_shapes.sort(ShapeComp.CompByAntiArea);
		}
		if (_mode.equals("ByPerimeter")) {
			_shapes.sort(ShapeComp.CompByPerimeter);
		}
		if (_mode.equals("ByAntiPerimeter")) {_shapes.sort(ShapeComp.CompByAntiPerimeter);}

		if (_mode.equals("ByTag")) {_shapes.sort(ShapeComp.CompByTag);}

		if (_mode.equals("ByAntiTag")) {_shapes.sort(ShapeComp.CompByAntiTag);}



		drawShapes();
	}

	public String findPathS(){
		FileDialog d = new FileDialog(new JFrame(), "Save as text", FileDialog.SAVE);
		d.setVisible(true);
		String filename = d.getFile();
		if(filename != null){
			String path = d.getDirectory() + filename;
			return path;
		}
		return null;
	}
	public String findPathL() {
		FileDialog d = new FileDialog(new JFrame(), "Load text file", FileDialog.LOAD);
		d.setVisible(true);
		String filename = d.getFile();
		if (filename != null) {
			String path = d.getDirectory() + filename;
			return path;
		}
		return null;
	}

	public void mouseClicked(Point2D p) {
		System.out.println("Mode: "+_mode+"  "+p);

		if(_mode.equals("Circle")) {
			if(_gs==null) {
				_p1 = new Point2D(p); // saves the point for futher use
			}
			else {
				// second click means that it needs to draw the shape
				_gs.setColor(_color); // sets the color
				_gs.setFilled(_fill); // set if it needs a fiiling
				_shapes.add(_gs); // adds the object into the shapes array
				_gs = null; //resets the object
				_p1 = null; //resets the point
			}
		}
		if(_mode.equals("Polygon")){
			// flag means that we pressed the right click
			if(flag){
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				Polygon2D poly = new Polygon2D(polygon); // this is the latest polygon by clicks
				_gs =new GUIShape(poly,_fill, _color, 0);
				_shapes.add(_gs);
				// we reset all the constatns to null or false for future use
				_gs = null;
				_p1 = null;
				polygon.clear();
				flag= false;
			}
			// only for the first click
			else if(_gs==null){
				polygon.clear(); // resets the polygon to make sure it's ok to use
				polygon.add(p); // we add the first point into the Arraylist of the polygon
				_p1= new Point2D(p);
			}
			else{
				// we just add the clicked points into the Arrays list
				polygon.add(p);
			}
		}
		if (_mode.equals("Rect")){
			if(_gs==null){
				_p1=new Point2D(p);
			}
			else{
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}


		if(_mode.equals("Triangle")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}
			else if(_p2 == null){
				_p2 = new Point2D(p);
			}
			else{
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				_p2 = null;
			}
		}

		if(_mode.equals("Segment")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}

		if(_mode.equals("Move")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				move();
				_p1 = null;
				_gs=null;
			}
		}


		if(_mode.equals("Rotate")) {
			if (_p1 == null) {
				_p1 = new Point2D(p);
			} else {
				_p2=new Point2D(p);// point input

				//this computes the right angle in radians
				double angleofDegrees = Math.atan2( _p2.y()-_p1.y(),_p2.x()-_p1.x());
				angleofDegrees  = Math.toDegrees(angleofDegrees);
				rotate(angleofDegrees); // rotates the points
				// reset the points for future use
				_p1=null;
				_p2=null;
				_gs=null;
			}
		}
		// watch out of the String
		if(_mode.equals("Copy")) {
			if (_p1 == null) {
				_p1 = new Point2D(p);
			} else {
				_p2 = new Point2D(p.x()-_p1.x(),p.y()-_p1.y());// this is the vector of the place that the copy needs to be in
				copy(_p2);
				_p1 = null;
				_p2= null;
				_gs=null;
			}
		}

		if(_mode.equals("Point")) {
			select(p);
		}

		drawShapes();
	}

	/*
	This function used the contains meatod we implimanted in each class
	so it checks if the latest mouseclick in select is "pressing" on a certain shape
	 */
	private void select(Point2D p) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}
	private void selectAll(){
		for(int i = 0;i< _shapes.size();i++){
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null){
				s.setSelected(true);
			}
		}
	}

	private void selectAnti(){
		for(int i = 0;i< _shapes.size();i++){
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null){
				s.setSelected(!s.isSelected());
			}
		}
	}

	public void selectNone(){
		for(int i = 0;i< _shapes.size();i++){
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null){
				s.setSelected(false);
			}
		}
	}
	/*
	run on an array that stores the shapes and if the object was selected moves it by a vector
	 */
	private void move() {

		for(int i=0;i<_shapes.size();i++) {// runs on the entire array
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {// checks if the object was slected
				g.move(_p1); // moves the point with the vector stored in _p1
			}
		}
	}
	private void copy( Point2D vector) {
		for(int i=0;i<_shapes.size();i++) {// runs on the entire array
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {// checks if the object was slected
				GeoShapeable copy = g.copy(); // saves in the object copy
				copy.move(vector); // moves the object by the vector
				GUI_Shapeable gs = new GUIShape(copy,s.isFilled(), s.getColor(), s.getTag()+1);// defines a new GUI_shapeable
				s= gs;// stores into s
				_shapes.add(gs);
			}
		}
	}
	/*
	run on the array that stores all the objects shown on the screen, check for a object that have been selected
	and changes his cordinantes to the rotated ones. gets angleofDegrees from mouseclicked
	 */
	private void rotate(double angleofDegrees){
		for(int i = 0 ; i<_shapes.size();i++){ // runs on the array
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!= null){// checks if the object contains true in selection
				g.rotate(_p1,angleofDegrees); // changes the cordinanted stored in the object to the new ones
			}
		}
	}
	public void scale(GeoShapeable g,double ratio,Point2D po) {
		g.scale(po,ratio);
	}
	/*
	when we press the right click bottom we get to here, the defult was that we print("right click);
	I added that if mode is Polygon that turn up the flag and go to mouseclicked
 	*/


	public void mouseRightClicked(Point2D p) {
		System.out.println("right click!");

		if(_mode.equals("Polygon")) { // for the polygon when we get right click we stop drawing
			if(_gs!=null) {
				flag = true; // we turn up the flag, the defult flag is false;
				mouseClicked(null); // we give null into mouseclicked so it will finish the polygon
			}
		}
	}

	/*
	The following function gets a constant input (by the mouse dragged), that changes p by the current place
	that our mouse is at.
	 */
	public void mouseMoved(MouseEvent e) {

		// because we don't have a first pick for the start it will throw exception in order to make
		// it run smoothly it enter only after the first click
		if(_p1!=null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShapeable gs = null;

			Point2D p = new Point2D(x1,y1); // the current place of the mouse
			// p is the influncer

			if(_mode.equals("Circle")) {
				double r = _p1.distance(p); //r is influenced by p and the constant point _p1
				gs = new Circle2D(_p1,r); //shows on the canvas the temporary gs
			}

			if(_mode.equals("Polygon")) {
				// technically the following text does nothing but makes me feel good
				if(polygon.size()==0) { // enters the first point that was stored in mousclicked
					polygon.add(_p1);
				}

				else {
					Polygon2D poly = new Polygon2D(polygon); // makes a new polygon with the Arraylist
					poly.addpoint(p);// adds the changing point into the polygn
					gs = poly; // enters it into the canvas (temporarly) when the right clicked is pressed it begones
				}
			}
			if(_mode.equals("Rect")){
				gs=new Rect2D(_p1,p);
			}
			if(_mode.equals("Triangle")) {
				if(_p2 == null) {
					gs = new Triangle2D(_p1, p, p); // so we won't get exceptions between every mouse press
				}
				else{
					gs = new Triangle2D(_p1,_p2,p);
				}

			}
			if(_mode.equals("Segment")) {
				gs = new Segment2D(_p1,p);
			}

			// we enter the gs that stores the current shape
			_gs = new GUIShape(gs,false, Color.pink, 0);

			// draws the shapes stored in _gs
			drawShapes();
		}
	}
	@Override
	public ShapeCollectionable getShape_Collection() {
		return this._shapes;
	}
	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }
	@Override
	public String getInfo() {
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
//	@Override
//	public String getInfo() {
//		String ans = _shapes.toString();
//		return ans;
//	}

}
