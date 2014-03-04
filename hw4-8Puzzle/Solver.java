
public class Solver {
	
	private int flag = -1; 	// -1, no answer; =0, find the way; =1 Can't find the way.
	private Stack<Board> path;
		
	private class Node implements Comparable<Node>{
		private Board bd;
		private int move;	// Step
		private Node pre;
		
		public Node (Board bd, int move, Node pre){
			this.bd = bd;
			this.move = move;
			this.pre = pre;
		}
		@Override
		public int compareTo(Node o) {
			return (this.bd.manhattan()+this.move)-(o.bd.manhattan()+o.move) ;
		}
	}
	
	public Solver(Board initial){
//		System.out.println("Begin Solver");
//		System.out.println(initial.hamming()+"   "+initial.manhattan());
		if(initial.isGoal()){
			flag = 0;
		}
		
		// init: Q1 is right Search, Q2 is Wrong Search;
		MinPQ<Node> q1 = new MinPQ<Node>();	
		q1.insert(new Node(initial, 0, null));
		MinPQ<Node> q2 = new MinPQ<Node>();
		q2.insert(new Node(initial.twin(), 0, null));
		
		
		// find;
		while (!q1.isEmpty() && !q2.isEmpty() && flag<0) {
			Node now;
			now = q1.delMin();
			for(Board i : now.bd.neighbors()){
				if (i.equals(now.bd))
					continue;
				if(i.isGoal()){
					flag = 0;	// find the way
					path = new Stack<Board>();
					Node last  = new Node(i, now.move+1, now);
					while(last.pre != null ){
						path.push(last.bd);
						last = last.pre;
					}
					path.push(initial);
					break;
				}
				q1.insert(new Node(i, now.move+1, now));
			}
			
			if(flag >= 0)
				break;
			
			now = q2.delMin();
			for (Board i : now.bd.neighbors()){
				if (i.equals(now.bd))
					continue;
				if(i.isGoal()){
					flag = 1;	// Can't find the way
					break;
				}
				q2.insert(new Node(i, now.move+1, now));
			}
		
		}
		
		
	}
	
	// is the initial board solvable?
    public boolean isSolvable() {
    	return flag == 0;
    }
    
    // 	min number of moves to solve initial board; -1 if no solution
    public int moves(){
    	if(path == null)
    		return -1;
    	return path.size()-1;
    }
    
    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution(){
    	return path;
    }
    
 // solve a slider puzzle (given below)
    public static void main(String[] args){
    	
		int n = StdIn.readInt();
		int mp[][] = new int[n][n];
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				mp[i][j] = StdIn.readInt();	
		Solver sol = new Solver(new Board(mp));
		
		System.out.println("Moves : "+sol.moves());
		for(Board i : sol.solution()){
			System.out.println(i.toString());
		}
		
    }


}
