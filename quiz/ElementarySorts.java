import java.util.concurrent.Exchanger;



public class ElementarySorts{
	static String[][] str;
	static int m, n;
	
	public static String[] copyString(String[] s){
		String[] ret = new String[s.length];
		for(int i=0;i<s.length;i++)
			ret[i] = s[i];
		return ret;
	}
	
	public static void check(String[] s, String info){
		for(int k=0;k<m;k++){
			boolean flag = true;
			for(int i = 0; i<s.length; i++){
				if(!str[k][i].equals(s[i])){
					flag = false;
					break;
				}
			}
			if(flag == true){
				System.out.println(info+"  "+k);
			}
		}
	}
	
	public static void exch(String[] s, int a, int b){
		String tmp = s[a];
		s[a] = s[b];
		s[b] = tmp; 
	}
	
	public static void selectSort(){
		String[] s = (String[]) str[0].clone();
		for(int i=0;i<n;i++){
			int k = i;
			for(int j=i+1;j<n;j++){
				if(s[j].compareTo(s[k]) < 0)
					k = j;
			}
			exch(s, i, k);
			check(s, "SelectSort");
		}
		print(s);
	}
	
	public static void insertSort(){
		String[] s = (String[]) str[0].clone();
		for(int i=1;i<n;i++){
			for(int j=i; j>0 && (s[j].compareTo(s[j-1])<0) ;j--){
				exch(s, j, j-1);
				check(s, "InsertSort");
			}
		}
		print(s);
	}
	
	public static void Shell(){
		String[] s = (String[]) str[0].clone();
		System.out.println("Shell");
		int h = 1;
		while(h<s.length/3)	
			h = h*3 + 1;
		System.out.println("h: "+h);
		for(; h>0; h= h/3){
			for(int i=h;i<s.length;i++){
				for(int k=i; k>=h && (s[k].compareTo(s[k-h])<0)  ;k-=h){
					exch(s,k,k-h);
					check(s, "ShellSort");
				}
			}
		}
		print(s);
	}
	
	public static void print(String str[]){
		for(int i=0;i<str.length;i++)
			System.out.print(str[i]+"  ");
		System.out.println();
	}
	
	public static void main(String[] args){
		System.out.println("AQUA".compareTo("CAKE"));
		m = 8;
		n = 16;
		str = new String[m][n];
		for (int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				str[j][i] = StdIn.readString();
			}
		}
		System.out.println("OVER");
		selectSort();
		insertSort();
		Shell();
	}
	
	
}
