package assignments;


/*************************
 * Frequent Words Problem: Find the most frequent k-mers in a string.
 *     Input: A string Text and an integer k.
 *     Output: All most frequent k-mers in Text.
 *     
 * Sample Input:
 * ACGTTGCATGTCGCATGATGCATGAGAGCT
 * 4
 * 
 * Sample Output:
 * CATG GCAT       
 *        
 */


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FrequentSequenceIndex {
	
	public static void main(String[] args) throws IOException{
		
		@SuppressWarnings("resource")
		String OriStr = new Scanner(new File("Thermotoga-petrophila.txt")).useDelimiter("\\A").next();
		
		
		System.out.println("The number of ATGC in original sequence is: " + OriStr.length());
		// System.out.println(OriStr);
		int oriLeng = OriStr.length();
		//String inputStr = "CTTGATCAT";
		// the input length of sequence to be checked
		int subSeqLen = 9;
		
		frequentCompare(OriStr, subSeqLen, oriLeng);
		
	}

	private static void frequentCompare(String oriStr, int length, int oriLength) throws IOException {
		// TO check how many times a subsequence in the DNA sequence repeated
		// to begin with the very first 9 (length) lone-base-pair as the begin sequence
		// check all subsequence in the whole sequence and compare it the the begin sequence
		
		int M = oriStr.length();
		int N = length;
		int Max = 0;
		int [] seqIndex = new int[oriLength];
		for(int i=0; i<oriLength; i++){
			seqIndex[i] = i;
		}
		
		// Max will store the time of most frequently sequence repeated.
		int count =0;
		// count to store the time of each subsequence repeated comparing to the sequence emerge after it.
		// so there might be some repeated output sequence if I want to output all subsequence
		// that repeated over 100 times.
		
		System.out.println("The length of input string is: " + N);
		
		// print out the total lone-base-pair in the original sequence.
		
		File f=new File("freResultThermotoga.txt");
		FileWriter out=new FileWriter(f);
		
		// use the fileWriter to output all subsequence repeated over 100 times.
			int index =0;
			
		while(index < M-N-2){
			
			while(seqIndex[index] == index){
				int i = seqIndex[index];

			String strTemp = oriStr.substring(i, i+N);
									
			for (int j=i+1; j<M-N-1; j++){
				
				String comStr = oriStr.substring(j, j+N);
				
				if (strTemp.equals(comStr)){
					
					seqIndex[j]++;
					
					count++;
				}
			}
			
			index++;
			
			if (count >= 100){
				Max = count;
							
				System.out.println("At circle " + i + " the sequence " + strTemp + " repeated " + count +" times.");

				out.write(strTemp + " ");
			}
			count=0;
			
			// System.out.println("Running " + i + " circles. the index is " + index );
		} // end inner while loop
			
			System.out.println("Running " + " circles. the index is " + index );
			index++;
			
			
	}// end outer while loop;
			
	       System.out.println("\n Max = " +Max+ " Done ..........");
	       out.close();
	//	System.out.print(inputStr);
		
	}
}


