import java.util.Iterator;

/**
 * 
 * 
 * @author nocly_000
 *
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
	
	// first save the fist node 
	// last don't do save information.
	
	private Node first, last;
	private int size;	// the length of Dequeue;
	
	/**
	 * Node class 
	 * @author nocly_000
	 *
	 */
	private class Node {
		Item item;
		Node next, pre;
		Node() {  }
		Node(Item item){
			this.item = item;
			next = null;
			pre = null;
		}
		private void loitering(){
			item = null;
			next = null;
			pre = null;
		}
	}

	@Override
	public Iterator<Item> iterator() {
		return (Iterator<Item>)new ListIterator();
	}
	
	/**
	 * Iterator Class 
	 * 
	 */
	private class ListIterator implements Iterator<Item>{
		private Node current = first;
		
		public boolean hasNext(){
			return current != last && first.item!=null;
		}

		public void remove(){
			throw new UnsupportedOperationException();
		}
		public Item next(){
			if(current == last)
				throw new java.util.NoSuchElementException();
			Item ret = current.item;
			current = current.next;
			return ret;
		}
		
	}
	
	public Deque(){
		first = new Node();
		last = new Node();
		first.next = last;
		last.pre = first;
		size = 0;
	}

	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
	
	
	public void addFirst(Item item){
		if (item == null)
			throw new NullPointerException();
		if(isEmpty()){
			first.item = item;
		} else {
			Node node = new Node(item);
			node.next = first;
			first.pre = node;
			first = node;
		}
		size ++;
	}
	
	public void addLast(Item item){
		if (item == null)
			throw new NullPointerException();
		if(isEmpty()){
			first .item = item;
		} else {
			Node node = new Node(item);
			last.pre.next = node;
			node.pre = last.pre;
			node.next = last;
			last.pre = node;
		}
		size ++;
	}
	
	public Item removeFirst(){
		if(isEmpty())
			throw new java.util.NoSuchElementException();
		size --;
		Item item = first.item;
		if(size == 0){
			first.item = null;
		} else {
			Node node = first;
			first = first.next;
			node.loitering();
		}
		return item;
	}
	
	public Item removeLast(){
		if(isEmpty())
			throw new java.util.NoSuchElementException();
		size --;
		Item item = last.pre.item;
		if(size==0){
			first.item = null;
		} else {
			Node node = last.pre;
			last.pre = last.pre.pre;
			last.pre.next = last;
			node.loitering();
		}
		return item;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deque<Integer> q = new Deque<Integer>();
		while(true){
			int x = StdIn.readInt();
			int y;
			if(x<0) break;
			switch (x){
			case 1:	// addFirst
				y = StdIn.readInt();
				q.addFirst(y);
				break;
			case 2:
				y = StdIn.readInt();
				q.addLast(y);
				break;
			case 3:
				System.out.println("del : "+q.removeFirst());
				break;
			case 4:
				System.out.println("del : "+q.removeLast());
				break;
			case 5:
				for(Integer i:q){
					System.out.print(i+" ");
				}
				System.out.println();
				break;

			}
		}
	}


}
