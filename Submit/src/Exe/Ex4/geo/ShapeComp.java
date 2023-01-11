package Exe.Ex4.geo;

import java.util.Comparator;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUI_Shapeable;

/**
 * This class represents a Comparator over GUI_Shapes - 
 * as a linear order over GUI_Shapes.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeComp implements Comparator<GUI_Shapeable>{
	//////////add your code below ///////////


	public static final Comparator<GUI_Shapeable> CompByToString = new ShapeComp(Ex4_Const.Sort_By_toString);
	public static final Comparator<GUI_Shapeable> CompByAntiToString = new ShapeComp(Ex4_Const.Sort_By_Anti_toString);
	public static final Comparator<GUI_Shapeable> CompByArea = new ShapeComp(Ex4_Const.Sort_By_Area);
	public static final Comparator<GUI_Shapeable> CompByAntiArea = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
	public static final Comparator<GUI_Shapeable> CompByPerimeter = new ShapeComp(Ex4_Const.Sort_By_Perimeter);
	public static final Comparator<GUI_Shapeable> CompByAntiPerimeter = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
	public static final Comparator<GUI_Shapeable> CompByTag = new ShapeComp(Ex4_Const.Sort_By_Tag);
	public static final Comparator<GUI_Shapeable> CompByAntiTag = new ShapeComp(Ex4_Const.Sort_By_Anti_Tag);
	private int _flag;
	public ShapeComp(int flag) {
		_flag = flag;

	}
	@Override
	public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
		int ans=0;
		if(_flag == Ex4_Const.Sort_By_toString) {
			ans = o1.toString().compareTo(o2.toString());
		}
		else if ((_flag == Ex4_Const.Sort_By_Anti_toString)) {
			ans = (o1.toString().compareTo(o2.toString()))*-1 ;

		}
		else if(_flag == Ex4_Const.Sort_By_Area) {
			ans = (String.valueOf(o1.getShape().area()).compareTo(String.valueOf(o2.getShape().area())));

		} else if ((_flag == Ex4_Const.Sort_By_Anti_Area)) {
			ans = (String.valueOf(o1.getShape().area()).compareTo(String.valueOf(o2.getShape().area())))*-1 ;

		}
		else if(_flag == Ex4_Const.Sort_By_Perimeter) {
			ans = (String.valueOf(o1.getShape().area()).compareTo(String.valueOf(o2.getShape().area())));

		} else if ((_flag == Ex4_Const.Sort_By_Anti_Perimeter)) {
			ans = (String.valueOf(o1.getShape().perimeter()).compareTo(String.valueOf(o2.getShape().perimeter())))*-1 ;

		}
		else if(_flag == Ex4_Const.Sort_By_Tag) {
			ans = (String.valueOf(o1.getShape().area()).compareTo(String.valueOf(o2.getShape().area())));

		} else if ((_flag == Ex4_Const.Sort_By_Anti_Tag)) {
			ans = Integer.compare(o1.getTag(),o2.getTag())*-1;

		}



//		if(this._flag == Ex4_Const.Sort_By_Area)
		return ans;
	}

}
