import java.util.Iterator;



public class RandomizedQueue<Item> implements Iterable<Item> {

	private int first, last;
	private Item[] que;
	
	@Override
	public Iterator<Item> iterator() { return  new ListIterator(); }
	// Iterator Class
	@SuppressWarnings("unchecked")
	private class ListIterator implements Iterator<Item>{
		private int current;
		private Item[] arr;

		ListIterator(){
			arr = (Item[]) new Object[size()];
			for (int i=0;i<arr.length;i++){
				arr[i] = que[i+first];
			}
			StdRandom.shuffle(arr);
			current = 0;
		}
		
		// 
		public void remove(){
			throw new UnsupportedOperationException();
		}
		public Item next(){
			if(!hasNext())
				throw new java.util.NoSuchElementException();
			Item item = arr[current++];
			return item;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != arr.length;
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	private void resize(int len){
		Item[] tmp = (Item[]) new Object[len];
		for(int i=first;i<last;i++){
			tmp[i] = que[first+i];
			que[first+i] = null;
		}
		first = 0;
		last -= first;
		que = tmp;
	}
	
	@SuppressWarnings("unchecked")
	// construct an empty randomized queue
	public RandomizedQueue(){
		que = (Item[]) new Object[64];
		first = 0;
		last = 0;
	}
	
	// is the queue empty?
	public boolean isEmpty(){
		return first == last;
	}
	
	// return the number of items on the queue
	public int size(){
		return last - first;
	}
	
	// add the item	
	public void enqueue(Item item){
		// Error
		if(item == null)
			throw new NullPointerException();
		// Resize
		if(last == que.length)
			resize(size()*2);
		que[last++] = item;
	}
	
	// delete and return a random item
	public Item dequeue(){
		// Error
		if(isEmpty())
			throw new java.util.NoSuchElementException();
		// Resize
		if(size() == que.length/4){
			resize(que.length/2);
		}
		// random;
		int k = StdRandom.uniform(last-first);
		Item item = que[first+k];
		que[first+k] = que[--last];
		que[last] = null;
		return item;
	}
	
	// return (but do not delete) a random item
	public Item sample(){
		// Error 
		if(isEmpty())
			throw new java.util.NoSuchElementException();
		int k = StdRandom.uniform(last-first);
		return que[k];
	}
	

	public static void main(String[] args){
		
		
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		
		while(true){
			int x = StdIn.readInt();
			if(x<0) break;
			switch (x){
			case 1:
				int y = StdIn.readInt();
				rq.enqueue(y);
				break;
			case 2:
				for(Integer i:rq){
					System.out.print(i+" ");
				}
				System.out.println();
				break;
			case 3:
				int k = rq.dequeue();
				System.out.println("del : "+k);
				break;
			}
		}
		
		
	}
	
}
