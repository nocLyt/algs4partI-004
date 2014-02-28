import java.util.Vector;

// Max

public abstract class MyHeap <Key extends Comparable<Key>> {
	
	Vector<Key> arr;
	MyHeap(){
		arr = new Vector<Key>();
	}
	MyHeap(Key a[]){
		arr = new Vector<Key>(a.length);
		for(int i=0; i<a.length; i++)
			arr.add(a[i]);
		for(int i=a.length/2; i>=1; i--)
			sink(i);
	}
	// Get top element & delect
	public Key delTop() {
		Key top = arr.elementAt(0);
		int N = arr.size();
		arr.set(0, arr.elementAt(N-1));
		sink(1);
		arr.remove(N-1);
		return top;
	}
	
	public void insert(Key key){
		arr.add(key);
		swin(arr.size());
	}
	
	// UpWrad
	private void swin(int k){
		while (k>=1 && less(k/2, k) ){
			exch(k, k/2);
			k /= 2;
		}
	}
	
	// DownWrad
	private void sink(int k){
		int N = arr.size();
		while (2*k <=N){
			int j = 2*k;
			if ( j<N && less(j, j+1) )
				j++;
			if (!less(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}
	
	// Abstract
	// Compare arr[x], arr[y]
	abstract protected boolean less(int x, int y);

	
	private void exch(int i, int j){
		i--; j--;
		Key tmp = arr.elementAt(i);
		arr.set(i, arr.elementAt(j));
		arr.set(j, tmp);
	}
	
	public boolean isEmpty(){
		return arr.size() == 0;
	}
	
	public void printAll(){
		for(Key i: arr){
			System.out.print(i+" ");
		}
		System.out.println();
	}
	public boolean isHeapSorted(){
		int N = arr.size();
		for(int i=1; i<=N/2; i++){
			if(less(i,i*2))
				return false;
			if(i*2+1<=N && less(i,i*2+1))
				return false;
		}
		return true;
	}
	
	public static void main(String args[]){
		MyMaxHeap<String> hp = new MyMaxHeap<String>(args);
		hp.printAll();
		System.out.println(hp.isHeapSorted());
//		hp.delTop();
//		hp.delTop();
//		hp.delTop();
		hp.insert("53");
		hp.insert("40");
		hp.insert("57");
		hp.printAll();
		System.out.println(hp.isHeapSorted());
	}
	
}

class MyMaxHeap<Key extends Comparable<Key>> extends MyHeap{

	public MyMaxHeap() {
		// TODO Auto-generated constructor stub
	}

	public MyMaxHeap(Key a[]){
		super(a);
	}
	
	@Override
	protected boolean less(int x, int y) {
		x -- ; 
		y -- ;
		Key a = (Key)arr.elementAt(x);
		Key b = (Key)arr.elementAt(y);
		return a.compareTo(b)<0;
	}
	
}
class MyMinHeap<Key extends Comparable<Key>> extends MyHeap{
	public MyMinHeap(){
		
	}
	public MyMinHeap(Key a[]){
		super(a);
	}

	@Override
	protected boolean less(int x, int y) {
		x -- ; 
		y -- ;
		Key a = (Key)arr.elementAt(x);
		Key b = (Key)arr.elementAt(y);
		return a.compareTo(b)>0;
	}
}







