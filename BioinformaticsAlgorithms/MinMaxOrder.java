package assignments;

public class MinMaxOrder {
	public static void main(String[] args){
		
		int a=1, b=2, c=3;
		
		int d = Math.max(a, b);
		int e = Math.max(d, c);
		
		int f = Math.min(a, b);
		int g = Math.min(f, c);
		
		int mid = a + b + c - e -g;
 
		System.out.println("The max is: " + e);
		System.out.println("The mid is: " + mid);
		System.out.println("The min is: " + g);
		
	}
}
