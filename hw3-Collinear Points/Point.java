

import java.util.Comparator;

/**
 * 主要是学会如何用 Comparable 和 Comparator 接口
 * 
 * @author nocly_000
 *
 */

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrderComparator();       // YOUR DEFINITION HERE
    
    private class SlopeOrderComparator implements Comparator<Point>{
		@Override
		public int compare(Point arg0, Point arg1) {
			// TODO Auto-generated method stub
			double s0 = slopeTo(arg0);
			double s1 = slopeTo(arg1);
			if(s0 == s1)
				return 0;
			else if (s0<s1)
				return -1;
			else 
				return 1;
		}
    }
    
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
	public Point(int x, int y) {
	    /* DO NOT MODIFY */
	    this.x = x;
	    this.y = y;
	}

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if(this.x == that.x){
        	if(that.y != this.y)
        		return Double.POSITIVE_INFINITY;
        	else 
        		return Double.NEGATIVE_INFINITY;
        } else if(that.y == this.y) {
        	return 0.0;
        }else {
        	return (double)(that.y - this.y)/(that.x - this.x);
        }
        
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
    	if(this.y > that.y) 
    		return 1;
    	else if(this.y == that.y){
    		if(this.x < that.x)
    			return -1;
    		else if (this.x == that.x)
    			return 0;
    		else 
    			return 1;
    	}else
    		return -1;
    	
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    	Point p = new Point(229, 459);
    	Point q = new Point(229, 427);
    	System.out.println(p.slopeTo(q));
    	
    }
}