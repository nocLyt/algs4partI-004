public class PercolationStats {
	private double[] data;
	private int nSize, times;
	
	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int N, int T){
		if(N <=0 || T <=0 )
			throw new IllegalArgumentException("SB");
		data = new double[T]; 
		nSize= N;
		times= T;
		for (int i=0; i<T; i++){
			// System.out.println(i+"th.");
			Percolation pcl = new Percolation(N);
			data[i] = 0;
			while(!pcl.percolates()){
				int rand = (int) (Math.random()*(N*N));
				int x = (rand/N) +1;
				int y = (rand%N) +1;
//				System.out.println("("+x+","+y+")");
				if(pcl.isOpen(x,y))
					continue;
				data[i]++;
				pcl.open(x, y);
			}
			data[i] /= (nSize*nSize);
		}
		
	}
	
	// sample mean of percolation threshold
	public double mean(){
		double sum = 0;
		for(int i= 0; i<times; i++ ){
			sum+= data[i];
		}
		return sum/times;
	}
	
	// sample standard deviation of percolation threshold
	public double stddev(){
		double avg = mean();
		double std = 0;
		for (int i=0; i<times; i++){
			std += (data[i]-avg)*(data[i]-avg);
		}
		std /= times-1;
		return Math.sqrt(std);
	}
	
	// returns lower bound of the 95% confidence interval
	public double confidenceLo(){
		double avg = mean();
		return avg - 1.96*stddev()/Math.sqrt(times);
	}
	
	// returns upper bound of the 95% confidence interval
	public double confidenceHi(){
		double avg = mean();
		return avg + 1.96*stddev()/Math.sqrt(times);
	}
	
	// test client, described below
	public static void main(String[] args){
		PercolationStats sb = new PercolationStats(2, 100000);
		System.out.println("mean: "+ sb.mean());
		System.out.println("stddev: "+ sb.stddev());
		System.out.println("95% confidence interval: ["+ sb.confidenceLo()+","+sb.confidenceHi()+")");
	}
}
