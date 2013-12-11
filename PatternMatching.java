package assignments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PatternMatching {
	
	public static void main(String[] args) throws IOException{
		
		@SuppressWarnings("resource")
		String OriStr = new Scanner(new File("Vibrio_cholerae.txt")).useDelimiter("\\A").next();
		
		
		System.out.println("The number of ATGC in original sequence is: " + OriStr.length());
		// System.out.println(OriStr);
		
		String inputStr = "CTTGATCAT";
		
		patternCompare(inputStr, OriStr);
		
	}

	private static void patternCompare(String inputStr, String oriStr) throws IOException {
		// TO reverse the input string, A to T, G to C, C to G, and T to A
		// Then back reversing: AAGGCT to TCGGAA
		// Final result: AGCTTTC ==> GAAAGCT
		int M = oriStr.length();
		int N = inputStr.length();
		System.out.println("The length of input string is: " + N);
		File f=new File("patternMatchResult.txt");
		FileWriter out=new FileWriter(f);
		
		for (int i=0; i<M-N; i++){
			
			String strTemp ="";
			
			for (int j=0; j<N; j++){
			strTemp += oriStr.charAt(i+j);
			
			}
			// System.out.print(" " + strTemp);
			
			if (strTemp.equals(inputStr))
			{
				System.out.print(" " +i);
			    out.write(i+" ");
			}

		}
			
	       System.out.println("\n Done ..........");
	       out.close();
	//	System.out.print(inputStr);
		
	}			
}
