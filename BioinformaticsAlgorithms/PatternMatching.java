package assignments;

/*******************
 * Pattern Matching Problem: Find all occurrences of a pattern in a string.
 *   Input: Two strings, Pattern and Genome.
 *   Output: All starting positions where Pattern appears as a substring of Genome.
 *   
 *   Note: Throughout this chapter, we will use 0-based indexing in problem implementations, 
 *   meaning that we count starting at 0 instead of 1. For example, 
 *   the starting positions of ATA in CGATATATCCATAG are 2, 4, and 10 instead of 3, 5, and 11.
 *   
 *   Sample Input:
 *   ATAT
 *   GATATATGCATATACTT
 *   
 *   Sample Output:
 *   1 3 9
 */

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
