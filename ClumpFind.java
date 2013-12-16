package assignments;

/********************
 * Clump Finding Problem: Find patterns forming clumps in a string.
 * Input: A string Genome, and integers k, L, and t.
 *   Output: All distinct k-mers forming (L, t)-clumps in Genome.
 *   
 *   Sample Input:
 *   CGGACTCGACAGATGTGAAGAACGACAATGTGAAGACTCGACACGACAGAGTGAAGAGAAGAGGAAACATTGTAA
 *   5 50 4
 *       
 *   Sample Output:
 *   CGACA GAAGA
<<<<<<< HEAD
 *   these 5-nucleotides sub strings repeated 4 times in the original string;
 *   
 *   In the this program, we first read in a long DNA sequences;
 *   The length of sub-sequence is 12 nucleotides; distance 524, repeated 16 times;
 *   Then check which sub-sequence repeated more than 16 times in 524 distances;
=======
>>>>>>> 241499ba07e252d4f93e8233c8df32a84fc21ec5
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ClumpFind {
	
	public static void main(String[] args) throws IOException{
		
<<<<<<< HEAD
		Scanner inputStr = new Scanner(new File("dataset_4_4.txt"));
		
		String OriStr = inputStr.useDelimiter("\\A").next();
	
		inputStr.close();
=======
		@SuppressWarnings("resource")
		String OriStr = new Scanner(new File("dataset_4_4.txt")).useDelimiter("\\A").next();
	
		
>>>>>>> 241499ba07e252d4f93e8233c8df32a84fc21ec5
		
		System.out.println("The number of ATGC in original sequence is: " + OriStr.length());
		// System.out.println(OriStr);
		
		//String inputStr = "CTTGATCAT";
		// the input length of sequence to be checked
		int length = 12;
		
		frequentCompare(OriStr, length);
		
	}

	private static void frequentCompare(String oriStr, int length) throws IOException {
		// TO reverse the input string, A to T, G to C, C to G, and T to A
		// Then back reversing: AAGGCT to TCGGAA
		// Final result: AGCTTTC ==> GAAAGCT
<<<<<<< HEAD
		int M = oriStr.length(); 	// the length of original DNA sequence;
		int N = length; 			// the length of sub sequence;
		int count =0;
		
		System.out.println("The length of input string is: " + N);
		System.out.println("The length of original string is: " + M);
=======
		int M = oriStr.length();
		int N = length;
		int count =0;
		
		System.out.println("The length of input string is: " + N);
>>>>>>> 241499ba07e252d4f93e8233c8df32a84fc21ec5
		
		File f=new File("dataset_4_4res.txt");
		FileWriter out=new FileWriter(f);
		
		for (int i=0; i<M-N-2; i++){
			
<<<<<<< HEAD
			String strTemp = oriStr.substring(i, i+N);
			
			for (int j=i+1; j<i+524-N && j<M-N; j++){
				
				String comStr = oriStr.substring(j, j+N);

				if (strTemp.equals(comStr)){
					count++;
				} // end if; 
				
			} // end for j<i+524-N && j<M-N;
			
			if (count == 16){
				//System.out.println("Now the sequence " + strTemp + " repeated " + count +" times.");
				System.out.println(strTemp + " ");
=======
			String strTemp ="";
			
			for (int j=0; j<N; j++){
			strTemp += oriStr.charAt(i+j);
			
			}
			// System.out.print(" " + strTemp);
			
			for (int j=i+1; j<i+524-N && j<M-N; j++){
				String comStr = "";
				
				for (int k=0; k<N; k++){
					comStr += oriStr.charAt(j + k);
				}
				
				if (strTemp.equals(comStr)){
					count++;
				}
				
			}
			
			if (count == 16){
				//System.out.println("Now the sequence " + strTemp + " repeated " + count +" times.");
				System.out.print(strTemp + " ");
>>>>>>> 241499ba07e252d4f93e8233c8df32a84fc21ec5
				out.write(strTemp + " ");
			}
			count=0;
		}
			
<<<<<<< HEAD
	       System.out.println("\nDone ..........");
	       out.close();
	//	System.out.print(inputStr);
		
	} // end of frequentCompare() method;
	
} // end of everything;
=======
	       System.out.println("\n Done ..........");
	       out.close();
	//	System.out.print(inputStr);
		
	}			
}
>>>>>>> 241499ba07e252d4f93e8233c8df32a84fc21ec5
