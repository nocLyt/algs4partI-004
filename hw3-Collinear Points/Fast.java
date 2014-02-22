import java.util.Arrays;
import java.util.Comparator;


/**
 * ��Ϊб���Ѿ�������ŵ��ˡ� 
 * ά��һ��ջ������ͬб�ʵĶ��ӽ�ȥ�� 
 * ��δ���ÿ������ֻ����һ�β��Ҳ����������У�
 * ������ ջ�е�Ԫ������������ǰ�������ǰ������������������
 * ע�⣺ ��ôά��ջ�����Ҫ����һ��Ԫ�ء�
 * 
 * @author nocly_000
 *
 */
public class Fast {

	private static Point arr[];
	private static int n;
	private static double slop_stk;	// ջ��Ԫ�� �͵�ǰ���б�ʡ�
	
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

	// 
	private static void printString(Point tmp[]){
		System.out.print(tmp[0].toString());
		for(int i=1;i<tmp.length;i++){
			System.out.print(" -> "+tmp[i].toString());
		}
		System.out.println();
	}
	
	
	// Judge if Linear
	// number of Point >= 4
	// to avoid "permutations "&"subsegments ", judge if tmp[] is sorted.
	private static void judge(Stack<Point> stk, Point pp){
		Point tmp[] = new Point[stk.size()];
		int cnt = 0;
		while(!stk.isEmpty()){
			tmp[cnt++] = stk.pop();
		}
		if(cnt<4)	
			return;

		// Judge if sorted
		Arrays.sort(tmp);
		// 
		if(tmp[0].compareTo(pp) == 0){
			printString(tmp);
			tmp[0].drawTo(tmp[cnt-1]);
		}
		
	}

	
	public static void main(String[] args){
		read(args[0]);
		// Begin
		Arrays.sort(arr);
		for(int i=0;i<n;i++){
			Point pp = arr[i];
			Arrays.sort(arr, pp.SLOPE_ORDER);
			Stack<Point> stk = new Stack<Point>();
			slop_stk = 0;	//  the slop of Stack&pp 
			for(int k=0;k<n;k++){
				if(arr[k].compareTo(pp)==0){
					continue;
				} else if(stk.isEmpty()){    	// Stack is empty then add a Point
					stk.push(arr[k]);
					slop_stk = pp.slopeTo(arr[k]);
				} else {
					double slop_now = pp.slopeTo(arr[k]);
					if(slop_stk == slop_now){	// If slop equal, then add to Stack
						stk.push(arr[k]);
					} else {	//  Judge Line(Pop All item of Stack,clear Stack)
						stk.push(pp);
						judge(stk, pp);
						// Don't forget add now Point !
						stk.push(arr[k]);
						slop_stk = pp.slopeTo(arr[k]);
					}
				}
			}
			// Judge End !
			stk.push(pp);
			judge(stk, pp);
		
			// sort back
			Arrays.sort(arr);
			
		}
		StdDraw.show(0);
	
		
	}
	
	
}