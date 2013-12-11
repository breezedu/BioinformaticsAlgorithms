package assignments;

public class GenerateAllAGTCStr {
	
	public static void main(String[] args){
	String missStr = "AGTCCTGCGGT";
	String newStr ="";
	int N = missStr.length();
	System.out.println("Ori String is: " + missStr +"\n");
	char[] mid = new char[4];
	mid[0] = 'A';
	mid[1] = 'G';
	mid[2] = 'T';
	mid[3] = 'C';
	String[] toStr = new String[40*N];
	int k=0;
	
	for (int i=0; i<N; i++){
		for (int j=0; j<4; j++){
			
			String newsubStr = missStr.substring(0, i) + mid[j] + missStr.substring(i+1, N);
			System.out.println("New String is: " + newsubStr);
			toStr[k] = newsubStr;
			k++;
			newStr += newsubStr;
			}
		}
	for(int i=0; i<40*N; i++){
		System.out.print(" " + toStr[i]);
	}
	
	System.out.println("before remove duplicate, the string is: " + newStr);
	
	removeDuplicates(toStr);
	
	System.out.println("after remove duplicate, the new string is: " + toStr);
	} // end Main();
	

	
	private static String removeDuplicates(String[] toStr) {
		// TO remove duplicated strings in a String[] array;

		
		return null;
	}
}
