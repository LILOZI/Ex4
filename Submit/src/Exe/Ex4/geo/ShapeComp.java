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
		double num1=0, num2=0;
		if(_flag == Ex4_Const.Sort_By_toString) {
			ans = o1.toString().compareTo(o2.toString());
			return ans;
		}
		//////////add your code below ///////////
		else if(_flag == Ex4_Const.Sort_By_Anti_toString) {
			ans = o2.toString().compareTo(o1.toString());
			return ans;
		}
		else if(_flag == Ex4_Const.Sort_By_Area) {
			num1 = o1.getShape().area();
			num2 = o2.getShape().area();
		}
		else if(_flag == Ex4_Const.Sort_By_Anti_Area) {
			num1 = o2.getShape().area();
			num2 = o1.getShape().area();
		}
		else if(_flag == Ex4_Const.Sort_By_Perimeter) {
			num1 = o1.getShape().perimeter();
			num2 = o2.getShape().perimeter();
		}
		else if(_flag == Ex4_Const.Sort_By_Anti_Perimeter) {
			num2 = o1.getShape().perimeter();
			num1 = o2.getShape().perimeter();
		}
		else if(_flag == Ex4_Const.Sort_By_Tag) {
			num1 = o1.getTag();
			num2 = o2.getTag();
		}
		else if(_flag == Ex4_Const.Sort_By_Anti_Tag) {
			num2 = o1.getTag();
			num1 = o2.getTag();
		}
		if (num1 > num2) {
			ans = 1;
		}
		else if (num1 < num2) {
			ans = -1;
		}
		else {
			ans = 0;
		}
		//////////////////////////////////////////
		return ans;
	}

}






















//package Exe.Ex4.geo;
//
//import java.util.Comparator;
//
//import Exe.Ex4.Ex4_Const;
//import Exe.Ex4.GUI_Shapeable;
//
///**
// * This class represents a Comparator over GUI_Shapes -
// * as a linear order over GUI_Shapes.
// * Ex4: you should implement this class!
// * @author I2CS
// *
// */
//public class ShapeComp implements Comparator<GUI_Shapeable>{
//	//////////add your code below ///////////
//
//
//	public static final Comparator<GUI_Shapeable> CompByToString = new ShapeComp(Ex4_Const.Sort_By_toString);
//	public static final Comparator<GUI_Shapeable> CompByAntiToString = new ShapeComp(Ex4_Const.Sort_By_Anti_toString);
//	public static final Comparator<GUI_Shapeable> CompByArea = new ShapeComp(Ex4_Const.Sort_By_Area);
//	public static final Comparator<GUI_Shapeable> CompByAntiArea = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
//	public static final Comparator<GUI_Shapeable> CompByPerimeter = new ShapeComp(Ex4_Const.Sort_By_Perimeter);
//	public static final Comparator<GUI_Shapeable> CompByAntiPerimeter = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
//	public static final Comparator<GUI_Shapeable> CompByTag = new ShapeComp(Ex4_Const.Sort_By_Tag);
//	public static final Comparator<GUI_Shapeable> CompByAntiTag = new ShapeComp(Ex4_Const.Sort_By_Anti_Tag);
//	private int _flag;
//	public ShapeComp(int flag) {
//		_flag = flag;
//
//	}
//	@Override
//	public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
//		int ans=0;
//		if(_flag == Ex4_Const.Sort_By_toString) {
//			ans = o1.toString().compareTo(o2.toString());
//		}
//		else if(_flag == Ex4_Const.Sort_By_Anti_toString) {
//			ans = (o1.toString().compareTo(o2.toString()))*-1;
//		}
//		else if(_flag == Ex4_Const.Sort_By_Area) {
//			if(o1.getShape().area() == o2.getShape().area()){
//				ans = 0;
//			}
//			else if(o1.getShape().area() > o2.getShape().area()){
//				ans = 1;
//			}
//				else {
//				ans = -1;
//				}
//		}
//		else if ((_flag == Ex4_Const.Sort_By_Anti_Area)) {
//			if(o1.getShape().area() == o2.getShape().area()){
//				ans = 0;
//			}
//			else if(o1.getShape().area() > o2.getShape().area()){
//				ans = -1;
//			}
//			else {
//				ans = 1;
//			}
//		}
//		else if(_flag == Ex4_Const.Sort_By_Perimeter) {
//			if (o1.getShape().perimeter() == o2.getShape().perimeter()) {
//				ans = 0;
//			} else if (o1.getShape().perimeter() > o2.getShape().perimeter()) {
//				ans = 1;
//			} else {
//				ans = -1;
//			}
//		}
//			else if ((_flag == Ex4_Const.Sort_By_Anti_Perimeter)) {
//			if (o1.getShape().perimeter() == o2.getShape().perimeter()) {
//				ans = 0;
//			} else if (o1.getShape().perimeter() > o2.getShape().perimeter()) {
//				ans = -1;
//			} else {
//				ans = 1;
//			}
//		}
//		else if(_flag == Ex4_Const.Sort_By_Tag) {
//			if (o1.getTag() == o2.getTag()) {
//				ans = 0;
//			} else if (o1.getTag() > o2.getTag()) {
//				ans = 1;
//			} else {
//				ans = -1;
//			}
//
//		}
//		else if (_flag == Ex4_Const.Sort_By_Anti_Tag) {
//			if (o1.getTag() == o2.getTag()) {
//				ans = 0;
//			} else if (o1.getTag() > o2.getTag()) {
//				ans = -1;
//			} else {
//				ans = 1;
//			}
//
//		}
//
//
//
//
//
//
//
//
////		if(_flag == Ex4_Const.Sort_By_toString) {
////			ans = o1.toString().compareTo(o2.toString());
////		}
////		else if ((_flag == Ex4_Const.Sort_By_Anti_toString)) {
////			ans = (o1.toString().compareTo(o2.toString()))*-1 ;
////
////		}
////		else if(_flag == Ex4_Const.Sort_By_Area) {
////			ans = Compare(o1.getShape().area().(o2.getShape().area()))
////
////		} else if ((_flag == Ex4_Const.Sort_By_Anti_Area)) {
////			ans = (String.valueOf(o1.getShape().area()).compareTo(String.valueOf(o2.getShape().area())))*-1 ;
////
////		}
////		else if(_flag == Ex4_Const.Sort_By_Perimeter) {
////			ans = (String.valueOf(o1.getShape().area()).compareTo(String.valueOf(o2.getShape().area())));
////
////		} else if ((_flag == Ex4_Const.Sort_By_Anti_Perimeter)) {
////			ans = (String.valueOf(o1.getShape().perimeter()).compareTo(String.valueOf(o2.getShape().perimeter())))*-1 ;
////
////		}
////		else if(_flag == Ex4_Const.Sort_By_Tag) {
////			ans = (String.valueOf(o1.getShape().area()).compareTo(String.valueOf(o2.getShape().area())));
////
////		} else if (_flag == Ex4_Const.Sort_By_Anti_Tag) {
////			ans = Integer.compare(o1.getTag(),o2.getTag())*-1;
////
////		}
//		return ans;
//	}
//
//}
