

// number's row = (N-1)/3, col = (N-1)%3

public class Board {
	private char[][] mp;
	private int N;
	private int hamming, manhattan;
	private static int dx[] = {0, 1, 0, -1};	// 
	private static int dy[] = {1, 0, -1, 0};	//
	
	private int xBlank;
	private int yBlank;
	
	// construct a board from an N-by-N array of blocks
	// (where blocks[i][j] = block in row i, column j)
	public Board(int[][] blocks){
		N = blocks.length;
		mp = new char[N][N];
		hamming = 0;
		manhattan = 0;
		
		for(int i=0; i<N;i++)
			for(int j=0; j<N; j++){
				mp[i][j] = (char)blocks[i][j];
				if(mp[i][j] != 0){
					int n = i*N+j+1;
					if(mp[i][j] != n)
						hamming ++;
					int x = (mp[i][j]-1) / N;
					int y = (mp[i][j]-1) % N;
					manhattan += Math.abs(x-i) + Math.abs(y-j);
				} else {
					xBlank = i;
					yBlank = j;
				}
			}

	}
	private Board(char[][] blocks){
		N = blocks.length;
		mp = new char[N][N];
		hamming = 0;
		manhattan = 0;
		
		for(int i=0; i<N;i++)
			for(int j=0; j<N; j++){
				mp[i][j] = blocks[i][j];
				if(mp[i][j] != 0){
					int n = i*N+j+1;
					if(mp[i][j] != n)
						hamming ++;
					int x = (mp[i][j]-1) / N;
					int y = (mp[i][j]-1) % N;
					manhattan += Math.abs(x-i) + Math.abs(y-j);
				} else {
					xBlank = i;
					yBlank = j;
				}
			}
		
	}
	    
	// board dimension N
	public int dimension(){
		return this.N;
	}
	
	// number of blocks out of place
	public int hamming(){
		return hamming;
	}
	
	// sum of Manhattan distances between blocks and goal
	public int manhattan(){
		return manhattan;
	}
	
	// is this board the goal board?
	public boolean isGoal(){
		return (manhattan + hamming)==0;
	}
	
	// a board obtained by exchanging two adjacent blocks in the same row
	// if 0 point in row 0 then swap (1,0)&(1,1) else swap(0,0)&(0,1)
	public Board twin(){
		
		Board ret;
		if (xBlank == 0){
			swap(mp, 1, 0, 1, 1);
			ret = new Board(mp);
			swap(mp, 1, 0, 1, 1);
		} else {
			swap(mp, 0, 0, 0, 1);
			ret = new Board(mp);
			swap(mp, 0, 0, 0, 1);
		}
		
		return ret;
	}
	
	// does this board equal y?
	public boolean equals(Object y){
		
		if (y == this)
			return true;
		if (y == null)	
			return false;
		if(y.getClass() != this.getClass())
			return false;
		Board that = (Board) y;
		if(that.N != this.N)
			return false;
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++){
				if (that.mp[i][j] != this.mp[i][j])
					return false;
			}

		return true;
	}
	
	// all neighboring boards
	public Iterable<Board> neighbors(){
		
		// find 0's point -> (x,y)
		Stack<Board> stk = new Stack<Board>();
		for(int i=0;i<4;i++){
			int nx = xBlank + dx[i];
			int ny = yBlank + dy[i];
			if(nx<0 || nx>=N || ny<0 || ny>=N)
				continue;
			swap(mp, xBlank, yBlank, nx, ny);
			Board tmp = new Board(mp);
			stk.push(tmp);
			swap(mp, xBlank, yBlank, nx, ny);
		}
		
		return stk;
	}
	
	// string representation of the board (in the output format specified below)
	public String toString(){
	    StringBuilder s = new StringBuilder();
	    s.append(N + "\n");
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
            	s.append(String.format("%2d ", (int)mp[i][j]));
	        }
	        s.append("\n");
	    }
	    return s.toString();
	}
	private void swap(char[][] p, int x, int y, int nx, int ny){
		char tmp = p[x][y];
		p[x][y] = p[nx][ny];
		p[nx][ny] = tmp;
	}
	
	
	public static void main(String args[]){
		int n = StdIn.readInt();
		int mp[][] = new int[n][n];
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				mp[i][j] = StdIn.readInt();
		Board bd = new Board(mp);
		System.out.println(bd.toString());
		System.out.println("________________________");
		for(Board i : bd.neighbors()){
			System.out.println(i.toString());
		}
		System.out.println("________________________");
		System.out.println(bd.twin().toString());
		
	}
	


}	
