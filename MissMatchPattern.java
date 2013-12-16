package assignments;

/*******************
 * Pattern Matching Problem: Find all occurrences of a pattern in a string.
 *      Input: Two strings, Pattern and Genome.
 *      Output: All starting positions where Pattern appears as a substring of Genome.
 *      
 *      Sample Output:
 *      1 3 9
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MissMatchPattern {
	
	public static void main(String[] args) throws IOException{
		
		@SuppressWarnings("resource")
		String OriStr = new Scanner(new File("dataset_8_3.txt")).useDelimiter("\\A").next();
		// String OriStr = new Scanner(new File("dataset_4_4.txt")).useDelimiter("\\A").next();
		
		
		System.out.println("The number of ATGC in original sequence is: " + OriStr.length());
		String comStr = "CGGTTAGCTG";
		
		int LenMiss = 5;
		missMatchCompare(OriStr, comStr, LenMiss);
		
	} // end main();

	private static void missMatchCompare(String oriStr, String comStr, int MISS) throws IOException {
		// TO check how many times a subsequence in the DNA sequence repeated
		// to begin with the very first 9 (length) lone-base-pair as the begin sequence
		// check all subsequence in the whole sequence and compare it the the begin sequence
				
		int M = oriStr.length();
		int N = comStr.length();
		
		int count =0;
		// count to store the time of each subsequence repeated comparing to the sequence emerge after it.
		// so there might be some repeated output sequence if I want to output all subsequence
		// that repeated over 100 times.
		
		System.out.println("The length of input string is: " + N);
		
		// print out the total lone-base-pair in the original sequence.
		
		File f=new File("MissMatchResult.txt");
		FileWriter out=new FileWriter(f);
		
		// use the fileWriter to output all subsequence repeated over 100 times.
			int index =0;
			
		while(index < M-N+1){

			String strTemp = oriStr.substring(index, index + N);
								
			for (int j=0; j<N; j++){
				
				if (strTemp.charAt(j) == comStr.charAt(j) ){
					count +=1;
				}
			} // compare each 8-elementry string to comStr; count how many characters equal.
		
			if (count >= MISS){
				System.out.print(" " + index);
				//if (index %1000 == 0)
				//	System.out.println();
				
				out.write((index) + " ");
			}
			
			index +=1;
			count=0;
			
		}// end outer while loop;

	       out.close();

	} // end frequent compare;

}