package Exe.Ex4;
/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 * @author I2CS
 */
import java.awt.Color;
import Exe.Ex4.geo.*;

public class GUIShape implements GUI_Shapeable{
	private GeoShapeable _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;
	
	public GUIShape(GeoShapeable g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}
	
	@Override
	public GeoShapeable getShape() {
		return _g;
	}

	@Override
	public boolean isFilled() {
		return _fill;
	}

	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	@Override
	public Color getColor() {
		return _color;
	}

	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	@Override
	public int getTag() {
		return _tag;
	}

	@Override
	public void setTag(int tag) {
		_tag = tag;
	}

	@Override
	public GUI_Shapeable copy() {
		GUI_Shapeable cp = new GUIShape(this);
		return cp;
	}
	public String toString() {
		String shape = "";
		if(this._g instanceof Circle2D){
			shape = "Circle2D";
		}
		if(this._g instanceof Segment2D){
			shape = "Segment2D";
		}
		if(this._g instanceof Triangle2D){
			shape = "Triangle2D";
		}
		if(this._g instanceof Rect2D){
			shape = "Rect2D";
		}
		if(this._g instanceof Polygon2D){
			shape = "Polygon2D";
		}
		String s = "GuiShape," + (this._color.getBlue() + this._color.getGreen() *256 +
				this._color.getRed() * 65536) + "," + this._fill + "," +this._tag + ","+ shape +
				"," + _g;

		return s;
	}
	private void init(String[] ww) {

	}
	@Override
	public boolean isSelected() {
		return this._isSelected;
	}
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
	@Override
	public void setShape(GeoShapeable g) {
		// TODO Auto-generated method stub
		
	}
}
