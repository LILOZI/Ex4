package Exe.Ex4;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import Exe.Ex4.geo.*;
import Exe.Ex4.gui.Ex4;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable{
	private ArrayList<GUI_Shapeable> _shapes;
	
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shapeable>();
	}
	@Override
	public GUI_Shapeable get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shapeable removeElementAt(int i) {
		return _shapes.remove(i);
	}

	@Override
	public void addAt(GUI_Shapeable s, int i) {
		_shapes.add(i,s);
	}
	@Override
	public void add(GUI_Shapeable s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	@Override
	public ShapeCollectionable copy() {
		ShapeCollection copy = new ShapeCollection();
		copy._shapes = new ArrayList<>(this._shapes);
		return copy;
	}

	@Override
	public void sort(Comparator<GUI_Shapeable> comp) {
		Collections.sort(_shapes,comp);
	}
	@Override
	public void removeAll() {
		_shapes.clear();
	}

	@Override
	public void save(String file) {
		if (file != null) {
			try {
				FileWriter w = new FileWriter(file);
				w.write(this.toString());
				w.close();
			}
			catch (IOException e) {
			}
		}
	}

	@Override
	public void load(String file) {
		this._shapes.clear();
		if(file != null){
			try {
				File f = new File(file);
				Scanner s = new Scanner(f);
				while (s.hasNextLine()) {
					Ex4._tag++;
					String line = s.nextLine();
					String[] sp = line.trim().split(",");
					String g = sp[4];
					if(g.equals("Circle2D")){
						Point2D cen = new Point2D(Double.valueOf(sp[5]),Double.valueOf(sp[6]));
						Circle2D c = new Circle2D(cen,Double.valueOf(sp[7]));
						GUI_Shapeable gs = new GUIShape(c,Boolean.valueOf(sp[2]),
								Color.decode(sp[1]),Integer.valueOf(sp[3]));
						this._shapes.add(gs);
					}
					if(g.equals("Segment2D")){
						Point2D p1 = new Point2D(Double.valueOf(sp[5]),Double.valueOf(sp[6]));
						Point2D p2 = new Point2D(Double.valueOf(sp[7]),Double.valueOf(sp[8]));
						Segment2D seg = new Segment2D(p1,p2);
						GUI_Shapeable gs = new GUIShape(seg,Boolean.valueOf(sp[2]),
								Color.decode(sp[1]),Integer.valueOf(sp[3]));
						this._shapes.add(gs);
					}
					if(g.equals("Triangle2D")){
						Point2D p1 = new Point2D(Double.valueOf(sp[5]),Double.valueOf(sp[6]));
						Point2D p2 = new Point2D(Double.valueOf(sp[7]),Double.valueOf(sp[8]));
						Point2D p3 = new Point2D(Double.valueOf(sp[9]),Double.valueOf(sp[10]));
						Triangle2D t = new Triangle2D(p1,p2,p3);
						GUI_Shapeable gs = new GUIShape(t,Boolean.valueOf(sp[2]),
								Color.decode(sp[1]),Integer.valueOf(sp[3]));
						this._shapes.add(gs);
					}
					if(g.equals("Rect2D")){
						Point2D p1 = new Point2D(Double.valueOf(sp[5]),Double.valueOf(sp[6]));
						Point2D p2 = new Point2D(Double.valueOf(sp[7]),Double.valueOf(sp[8]));
						Point2D p3 = new Point2D(Double.valueOf(sp[9]),Double.valueOf(sp[10]));
						Point2D p4 = new Point2D(Double.valueOf(sp[11]),Double.valueOf(sp[12]));
						Rect2D rect = new Rect2D(p1,p2,p3,p4);
						GUI_Shapeable gs = new GUIShape(rect,Boolean.valueOf(sp[2]),
								Color.decode(sp[1]),Integer.valueOf(sp[3]));
						this._shapes.add(gs);
					}
					if(g.equals("Polygon2D")){
						ArrayList<Point2D> arr = new ArrayList();
						for(int i = 5; i < sp.length-1; i+= 2){
							arr.add(new Point2D(Double.valueOf(sp[i]),Double.valueOf(sp[i+1])));
						}
						Polygon2D poly = new Polygon2D(arr);
						GUI_Shapeable gs = new GUIShape(poly,Boolean.valueOf(sp[2]),
								Color.decode(sp[1]),Integer.valueOf(sp[3]));
						this._shapes.add(gs);

					}
				}
				s.close();

			}
			catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
	}
	/// bounding box with doc
	public Rect2D getBoundingBox() {
		{
			double maxX = Integer.MIN_VALUE;
			double minX = Integer.MAX_VALUE;
			double maxY = Integer.MIN_VALUE;
			double minY = Integer.MAX_VALUE;
			for (int i = 0; i < _shapes.size(); i++) {
		/*
		if the shape is circle we need to treat it differently because we need to find the
		the max and min points using the center point and the radius
		 */
				if (_shapes.get(i).getShape() instanceof Circle2D cir) {
					if (maxX < cir.getPoints()[0].x() + cir.getRadius()) {
						maxX = cir.getPoints()[0].x() + cir.getRadius();
					}
					if (minX > cir.getPoints()[0].x() - cir.getRadius()) {
						minX = cir.getPoints()[0].x() - cir.getRadius();
					}
					if (maxY < cir.getPoints()[0].y() + cir.getRadius()) {
						maxY = cir.getPoints()[0].y() + cir.getRadius();
					}
					if (minY > cir.getPoints()[0].y() - cir.getRadius()) {
						minY = cir.getPoints()[0].y() - cir.getRadius();
					}
				} else {
				/*
				for the other shapes we take all the shape points and check if it could go out of the bounds
				of the potential bounding box.
				 */
					Point2D[] points = _shapes.get(i).getShape().getPoints();
					for (int j = 0; j < points.length; j++) {
						if (maxX < points[j].x()) {
							maxX = points[j].x();
						}
						if (minX > points[j].x()) {
							minX = points[j].x();
						}
						if (maxY < points[j].y()) {
							maxY = points[j].y();
						}
						if (minY > points[j].y()) {
							minY = points[j].y();
						}
					}
				}

			}
			Point2D p = new Point2D(maxX, maxY);
			Point2D p1 = new Point2D(minX, minY);
			Rect2D rect = new Rect2D(p, p1);
			return rect;
		}
	}
	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<this._shapes.size();i++) {
			GUI_Shapeable s = this._shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}

}
