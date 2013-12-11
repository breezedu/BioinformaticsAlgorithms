package assignments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FrequentSequenceIndex4th {
	
	public static void main(String[] args) throws IOException{
		
		@SuppressWarnings("resource")
		String OriStr = new Scanner(new File("Thermotoga-petrophila.txt")).useDelimiter("\\A").next();
		//String OriStr = new Scanner(new File("dataset_4_4.txt")).useDelimiter("\\A").next();
		OriStr += "MN";
		// Add another char 'M' to the end of the OriStr, as the end point of the String.
		
		String subStr1 = OriStr.substring(0, 10);
						
		System.out.println("The number of ATGC in original sequence is: " + (OriStr.length()-2));
		System.out.println("The first 10 elements are :" + subStr1);
				
		// System.out.println(OriStr);
		int oriLeng = OriStr.length();
		
		// the input length of sequence to be checked
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
		
		File f=new File("freResultThermotoga3rd.txt");
		FileWriter out=new FileWriter(f);
		
		// use the fileWriter to output all subsequence repeated over 100 times.
			int index =0;
			
		while(index < M-N){
			
			int i = newRoot(seqIndex, index);

			String strTemp = oriStr.substring(i, i+N);
									
			/******************
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
					
					// System.out.print("Change seqindex["+j+"]'s value to ");
					seqIndex[startPoint]=newRoot(seqIndex, startPoint+1);
					
					// System.out.print(seqIndex[j] + "  \n");
					count++;
				}
				j = newRoot(seqIndex, j+1);
				
			}
			
			// System.out.println("\n Running at " + index + " point. the real index is " + i );
			
			/****************
			 * previous run test showed some 9-element sub sequences repeated around 120 times
			 * so we set monitor point at 110, if a sub-sequence repeated over 110 time
			 * print out the sub-sequence and the time it repeated in the whole string.
			 */
			
			if (count >= 110){
				paternNumber++;
				
				System.out.println(paternNumber +" At charAt " + index + " root " + i + " the sequence " + strTemp + " repeated " + (count+1) +" times.");

				out.write(count + ": " + strTemp + ". ");
			}
			
			if (count >= Max){
				Max = count;
			}
			
			count=0;
			
			/*************************
			 * make i+1 the next index to count.
			 * every time pass i+1 to newRoot() method, 
			 * figure out the final root then generate new sub-sequence 
			 * begin sequence compare
			 */
			index = i+1;
			
		}// end outer while loop;
		
		/*********
		 * for a short sequence (ATGC < 100,000)
		 * we can use the following code to check the weighted-union-tree.
		 */
			
		/****
		for (int i=0; i<seqIndex.length; i++){
			if(i%100 == 0)
				System.out.println("");
			
			System.out.print(" [" + i +"]="+ seqIndex[i]);
		}
		
		**/
		
		
		/**********
		 * Print out the Max, the repeated time of most frequent sequence.
		 * then close the FileWriter OUT.
		 */
	       System.out.println("\n Max = " + Max + " Done ..........");
	       out.close();
		
	} // end frequent compare;

	private static int newRoot(int[] seqIndex, int j) {
		// TO find out the final root of seqIndex[j];
		
		while(j != seqIndex[j]){
			j =seqIndex[j];
		} // the recursion!

		return j;
	} // end of newRoot method.

}


