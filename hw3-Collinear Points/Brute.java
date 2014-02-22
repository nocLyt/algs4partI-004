import java.util.Arrays;


/**
 * 就是直接4个for遍历就行啦
 * @author nocly_000
 *
 */

public class Brute {
	
	private static Point arr[];
	private static int n;
	
	private static void read(String filename){
        In in = new In(filename);
        n = in.readInt();
		arr = new Point[n];
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);
		for(int i=0;i<n;i++){
			int x = in.readInt();
			int y = in.readInt();
			arr[i] = new Point(x,y);
			arr[i].draw();
		}
	}
	
	private static String toStringLine(Point a, Point b, Point c, Point d){
		String str = a.toString()+" -> "+b.toString()+" -> "+c.toString()+" -> "+d.toString();
		return str;
	}
	
	public static void main(String[] args){
		read(args[0]);
		// sort
		Arrays.sort(arr);
		// find
		for(int i=0; i<n; i++){
			for(int j=i+1; j<n; j++){
				double s1 = arr[i].slopeTo(arr[j]);
				for(int k=j+1; k<n; k++){
					double s2 = arr[i].slopeTo(arr[k]);
					if(s1 != s2)
						continue;
					for(int l=k+1; l<n; l++){
						double s3 = arr[i].slopeTo(arr[l]);
						if(s1 != s3)
							continue;
						System.out.println(toStringLine(arr[i], arr[j], arr[k], arr[l]));
						arr[i].drawTo(arr[l]);
					}
				}
			}
		}
		
		StdDraw.show(0);
	}

}