
public class Mergesort {
	
	int a[];
	
	public static boolean lessEqual(Comparable x, Comparable y){
		
		if(x.compareTo(y) <= 0)	return true;
		return false;
		
	}
	
	public static boolean less(Comparable x, Comparable y){
		
		if(x.compareTo(y) < 0)	return true;
		return false;
		
	}
	
	// 要想保证稳定，如果相等的情况下，则先放左边的。
	public static void merge(Comparable a[], Comparable aux[], int l, int mid, int r){
		
		for(int i=l;i<=r;i++)
			aux[i] = a[i];
		
		int i = l, j = mid+1;
		for(int k=l; k<=r; k++){
			if (i > mid)	a[k] = aux[j++];
			else if (j > r)	a[k] = aux[i++];
			else if (lessEqual(aux[i], aux[j]))	a[k] = aux[i++];
			else 	a[k] = aux[j++];
		}
		
	}
	
	public static void merge(int a[], int aux[], int l, int mid, int r){
		
		for(int i=l;i<=r;i++)
			aux[i] = a[i];
		
		int i = l, j = mid+1;
		
		for(int k=l; k<=r; k++){
			if (i > mid)	a[k] = aux[j++];
			else if (j > r)	a[k] = aux[i++];
			else if (aux[i]<= aux[j])	a[k] = aux[i++];
			else 	a[k] = aux[j++];
		}
		
	}
	
	// 对 a[] 执行归并排序，限制执行 times次 merge操作
	public static void bottomUpSort(int a[], int times){
		
		int N = a.length;
		int aux[] = new int[N];
		for (int sz=1; sz<N; sz+= sz){
			for(int lo = 0; lo < N-sz; lo += sz+sz){
				if(times == 0)	
					break;
				else 
					times--;
				merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
			}
		}
		
	}
	
	public static void print(int a[]){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+"  ");
		}
		System.out.println();
	}
	
	public static void problem1(String[] args){
		int n = args.length;
		int a[] = new int[n];
		for(int i=0;i<n;i++){
			a[i] = Integer.parseInt(args[i]);
		}
		
		// 
		bottomUpSort(a, 7);
		print(a);
	}
	
	
	// 复制一个字符串数组。
	public static String[] copyString(String[] s){
		String[] ret = new String[s.length];
		for(int i=0;i<s.length;i++)
			ret[i] = s[i];
		return ret;
	}
	// 输出字符串数组
	public static void print(String str[]){
		for(int i=0;i<str.length;i++)
			System.out.print(str[i]+"  ");
		System.out.println();
	}
	public static void print(Comparable str[]){
		for(int i=0;i<str.length;i++)
			System.out.print(str[i]+"  ");
		System.out.println();
	}

	// 判断字符串数组 s[]， 与 str[][]中的哪个相同 
	public static void check(String[] s, String str[][], String info){
		for(int k=0;k<str.length;k++){
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
	
	//
	public static void bottomUpSort(Comparable[] a, String str[][]){
		
		print(a);
		
		int N = a.length;
		Comparable[] aux = new Comparable[N];
		for (int sz=1; sz<N; sz+= sz){
			for(int lo = 0; lo < N-sz; lo += sz+sz){
				merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
				check((String[])a, str, "BottomUpSort");
			}
		}
		
	}
	
	public static void sort(Comparable[] a, Comparable aux[], int l, int r, String str[][]){
		if(l>=r)	return;
		int mid = l + (r-l)/2;
		sort(a, aux, l, mid, str);
		sort(a, aux, mid+1, r, str);
		merge(a, aux, l, mid, r);
		check((String[])a, str, "MergeSort");
	}
	
	public static void mergeSort(Comparable[] a, String str[][]){
		Comparable aux[] = new Comparable[a.length];
		sort(a, aux, 0, a.length-1, str);
	}
	
	public static void problem2(String[] args){
		int n = 12;
		int m = 6;
		String str[][] = new String[m][n];
		for(int i=0; i<args.length; i++)
			str[i%m][i/m] = args[i];
		
		String tmp[] = copyString(str[0]);
		bottomUpSort(tmp, str);
		String sb[] = copyString(str[0]);
		mergeSort(sb, str);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		problem1(args);
		// problem2(args);
	}

}
