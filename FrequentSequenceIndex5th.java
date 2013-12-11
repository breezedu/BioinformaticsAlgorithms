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

public class FrequentSequenceIndex5th {
	
	public static void main(String[] args) throws IOException{
		

		//String OriStr = new Scanner(new File("Thermotoga-petrophila.txt")).useDelimiter("\\A").next();
		@SuppressWarnings("resource")
		String OriStr = new Scanner(new File("Vibrio_cholerae.txt")).useDelimiter("\\A").next();
		
		OriStr += "MN";

		/***************************************************************************************
		 *  Add another 2 chars 'MN' to the end of the OriStr, as the end point of the String.
		 *  after adding a char different from AGTC, the array.length would be 2 ele-longer than string.length();
		 *  the last two sub-sequences would never be the same to any sub-sequence ahead, thus the index.value of
		 *  the last two sub-sequences would never change.
		 *  even during recursion, the very last sub-sequence would never change. 
		 *  to ensure in the following count and comparing progress, there won't be over flow.
		 */
		
		
		
		String subStr1 = OriStr.substring(0, 10);
						
		System.out.println("The number of ATGC in original sequence is: " + (OriStr.length()-2));
		System.out.println("The first 10 elements are :" + subStr1);
				
		// System.out.println(OriStr);
		int oriLeng = OriStr.length();
		
		/***************************************************************************************
		 * input length of sequence to be checked; 
		 * normally the 9-element subsequence would be the k-mer
		 * for the ribosome to check and bind to, :) HERE we just input the number directly;
		 * In real program we have to use arg[0], or write a method to ask user to input the subSeqLen.
		 */
		int subSeqLen = 9;
		
		frequentCompare(OriStr, subSeqLen, oriLeng);
		
	} // end main(); ATGCTGAGT

	private static void frequentCompare(String oriStr, int subLength, int oriLength) throws IOException {
		// TO check how many times a subsequence in the DNA sequence repeated
		// to begin with the very first 9 (length) lone-base-pair as the begin sequence
		// check all subsequence in the whole sequence and compare it the the begin sequence
		
		int M = oriLength;
		int N = subLength;
		int Max = 0;
		int [] seqIndex = new int[oriLength];
		
		for(int i=0; i<oriLength; i++){
			seqIndex[i] = i;
		}
		
		// Max will store the time of most frequently sequence repeated.
		int count =0;
		int paternNumber=0;
		// count to store the time of each subsequence repeated comparing to the sequence emerge after it.
		// so there might be some repeated output sequence if I want to output all subsequence
		// that repeated over 100 times.
		
		System.out.println("The length of input string is: " + (N));
		
		// print out the total lone-base-pair in the original sequence.
		
		File f=new File("ResultVibrio.txt");
		FileWriter out=new FileWriter(f);
		
		// use the fileWriter to output all subsequence repeated over 100 times.
			int index =0;
			
		while(index < M-N){
			
			int i = newRoot(seqIndex, index);

			String strTemp = oriStr.substring(i, i+N);
									
			/****************************************************************************
			 * after we confirm the startpoing of temStr at i;
			 * we should compare all N-length sequence after i till the end of OriStr.
			 * Meanwhile, all the N-length subsequence begins at charAt(i+1), and ofcourse we
			 * have to avoid all sequence which has already been compared.
			 * so every time j = newRoot(seqIndex(j+1)); instead of j++;
			 */
			int j = newRoot(seqIndex, i+1);
			while (j<(M-N)){
				int startPoint = j;
				int endPoint = startPoint+N;
				
				String comStr = oriStr.substring(startPoint, endPoint);
				
				if (strTemp.equals(comStr)){
					
					/*******************************
					 *  System.out.print("Change seqIndex["+j+"]'s value to ");
					 *  Check the final root of seqIndex[j];
					 *  then pass the value to seqIndex[j];
					 *  next time this start point will be ignored;
					 */
					
					
					seqIndex[startPoint]=newRoot(seqIndex, startPoint+1);

					count++;
				}
				
				
				/************************************************************ 
				 * pass new value, the final root of seqIndex[j+1] to a new j;
				 * then repeat the string compare.
				 */
				j = newRoot(seqIndex, j+1);
				
			}
			
			// System.out.println("\n Running at " + index + " point. the real index is " + i );
			
			/***********************************************************************************
			 * previous run test showed some 9-element sub sequences repeated around 120 times
			 * so we set monitor point at 110, if a sub-sequence repeated over 110 time
			 * print out the sub-sequence and the time it repeated in the whole string.
			 */
			
			if (count >= Max){
				paternNumber++;
				
				System.out.println(paternNumber +" At charAt " + index + " root " + i + " the sequence " + strTemp + " repeated " + (count+1) +" times.");

				out.write(count + ": " + strTemp + ". ");
			}
			
			
			/**********************************************************************************
			 * store the new count value to Max if we got a new value bigger than 
			 * the Max we got last time.
			 * we can put the out.write() method here to ensure the last few sequences are the 
			 * most frequently repeat sub-sequences.
			 * here in this program, all sub-sequences repeated over 110 time will be written to 
			 * document ResultThermotoga34th.txt
			 */
			if (count >= Max){
				Max = count;
			}
			
			count=0;
			
			/**********************************************************************************
			 * make i+1 the next index to count.
			 * every time pass i+1 to newRoot() method, 
			 * figure out the final root then generate new sub-sequence 
			 * begin sequence compare
			 */
			index = i+1;
			
		}// end outer while loop;
		
		/**********************************************************************************
		 * for a short sequence (ATGC < 100,000)
		 * we can use the following code to check the weighted-union-tree.
		 ***********************************************************************************
		
		for (int i=0; i<seqIndex.length; i++){
			if(i%100 == 0){
				System.out.println("");
			}
			
			System.out.print(" [" + i +"]="+ seqIndex[i]);
		}
		
		*/
		
		
		/***********************************************************************************
		 * Print out the Max, the repeated time of most frequent sequence.
		 * then close the FileWriter OUT.
		 */
	       System.out.println("\n Max = " + Max + " :) Done ..........");
	       out.close();
		
	} // end frequent compare method;

	private static int newRoot(int[] seqIndex, int j) {
		// TO find out the final root of seqIndex[j];
		
		while(j != seqIndex[j]){
			j =seqIndex[j];
		} // the recursion!

		return j;
	} // end of newRoot method.

}


