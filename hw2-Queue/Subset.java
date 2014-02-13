
public class Subset {
	public static void main(String args[]){
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		while(!StdIn.isEmpty()){
			String str = StdIn.readString();
			// System.out.println(str);
			rq.enqueue(str);
		}
		int k = Integer.parseInt(args[0]);
		for(int i=0;i<k;i++){
			System.out.println(rq.dequeue());
		}
		
	}
}
