/**
 * The problem is how to avoid the backwash and quick to answer percolates()!
 * I use two UF(topUf & buttomUf). one union Top point. one union Buttom point.
 * when open point (i,j), if topUf's (i,j) is unioned Top Point and buttomUf's (i,j) is unioned Buttom Point,
 * 		then it's percolates!
 * 
 * 
 * @author nocly_000
 *
 */

public class Percolation {
	
	private boolean[][] map;
	private int n;
	private boolean isPercolates;
	
	private int dx[] = {0, 1, 0, -1};
	private int dy[] = {1, 0, -1, 0};
	

	private WeightedQuickUnionUF topUf, buttomUf;
	private int tp;	// top  point.  for topUf
	private int bp; // buttom point. 	for buttomUf
	
	// create N-by-N grid, with all sites blocked
	public Percolation(int N){
		map= new boolean[N+1][N+1];
		for (int i=0;i<=N;i++) 
			for (int j=0;j<=N;j++)
				map[i][j] = false;
		this.n = N;
		// init UF
		topUf = new WeightedQuickUnionUF(N*N+1);
		buttomUf = new WeightedQuickUnionUF(N*N+1);
		tp = N*N;
		bp = N*N;
	}
	
	// open site (row i, column j) if it is not already
	public void open(int i, int j) {
		if (i <= 0 || i > n ) 
			throw new IndexOutOfBoundsException("row index i out of bounds");
		if (j <= 0 || j > n ) 
			throw new IndexOutOfBoundsException("colume index j out of bounds");
		
		if(map[i][j] == true)
			return; 
		map[i][j] = true;
		// if in row 1, connect Begin point
		if( i == 1 ){
			topUf.union(tp, hash(i,j));
		}
		// if in row n,  add in Vector vec.
		if( i == n ){	
			buttomUf.union(bp, hash(i,j));
		}
		for (int k=0; k<4; k++){
			int ni = i+dx[k];
			int nj = j+dy[k];
			if(outBound(ni, nj) || map[ni][nj] == false)	
				continue;
			topUf.union(hash(i,j), hash(ni,nj));
			buttomUf.union(hash(i,j), hash(ni,nj));
		}
		// Judge isPercolates?
		if ( isPercolates == false && topUf.connected(tp, hash(i,j)) && buttomUf.connected(bp, hash(i,j)) ) 
			isPercolates = true;
		
	}
	
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j){
		if (i <= 0 || i > n ) 
			throw new IndexOutOfBoundsException("row index i out of bounds");
		if (j <= 0 || j > n ) 
			throw new IndexOutOfBoundsException("colume index j out of bounds");
		return map[i][j];
	}
	
	// is site (row i, column j) full?
	public boolean isFull(int i, int j){
		if (i <= 0 || i > n ) 
			throw new IndexOutOfBoundsException("row index i out of bounds");
		if (j <= 0 || j > n ) 
			throw new IndexOutOfBoundsException("colume index j out of bounds");

		return (topUf.connected(tp, hash(i,j))) ;
		
	}
	
	// does the system percolate?
	public boolean percolates(){
		return isPercolates;
	}
	
	// 
	private int hash(int i, int j){
		return n*(i-1)+j-1;
	}
	// 
	private boolean outBound(int x, int y){
		if(x<=0 || y<=0 || x>n || y>n)
			return true;
		return false;
	}
	
	public static void main(String[] args){
		Percolation pp = new Percolation(3);
		while(true){
			int x = StdIn.readInt();
			if(x==0){
				System.out.println("Percolate? : "+pp.percolates());
				continue;
			}
			int i = StdIn.readInt();
			int j = StdIn.readInt();
			switch (x){
			case 1:	// (i,j) isOpen?
				System.out.println("IsOpen? : "+pp.isOpen(i, j));
				break;
			case 2:
				pp.open(i, j);
				System.out.println("Open.");
				break;
			case 3:
				System.out.println("IsFull? : "+pp.isFull(i, j));
				break;
			case 4:
				break;
			}
		}
	}
	
	
}