package assignments;

/**********************
 * Frequent Words with Mismatches Problem: 
 * Find the most frequent k-mers with mismatches in a string.
 *  Input: A string Text as well as integers k and d. (You may assume k ¡Ü 12 and d ¡Ü 3.)
 *    Output: All most frequent k-mers with up to d mismatches in Text.
 *    
 *    CODE CHALLENGE: Solve the Frequent Words with Mismatches Problem.
 *    
 *    Sample Input:
 *    ACGTTGCATGTCGCATGATGCATGAGAGCT 4 1
 *    Sample Output:
 *    GATG ATGC ATGT
 */


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FreWordMiss3 {
	
	public static void main(String[] args) throws IOException{
		
		@SuppressWarnings("resource")
		//String OriStr = new Scanner(new File("Thermotoga-petrophila.txt")).useDelimiter("\\A").next();
		String OriStr = new Scanner(new File("dataset_8_45.txt")).useDelimiter("\\A").next();
		
		OriStr += "MN";
		/***************************************************************************************
		 *  Add another 2 chars 'MN' to the end of the OriStr, as the end point of the String.
		 *  after adding a char different from AGTC, the array.length would be 2 ele-longer than string.length();
		 *  the last two sub-sequences would never be the same to any sub-sequence ahead, thus the index.value of
		 *  the last two sub-sequences would never change.
		 *  even during recursion, the very last sub-sequence would never change. 
		 *  to ensure in the following count and comparing progress, there won't be over flow.
		 */
		
		// System.out.println(OriStr);
		int oriLeng = OriStr.length()-2;
		
		/***************************************************************************************
		 * input length of sequence to be checked; 
		 * normally the 9-element subsequence would be the k-mer
		 * for the ribosome to check and bind to, :) HERE we just input the number directly;
		 * In real program we have to use arg[0], or write a method to ask user to input the subSeqLen.
		 */
		int subSeqLen = 10;
		
		String NoMissStr="";
		
		NoMissStr = frequentCompare(OriStr, subSeqLen, oriLeng, NoMissStr);
		
		missMatch(OriStr, NoMissStr, subSeqLen);
		
	} // end main(); ATGCTGAGT

	private static void missMatch(String oriStr, String missStr, int subSeqLen) {
		System.out.println("Begin OneMissMatch");
		
		// TO check 1-miss Most Frequent Str, by replacing each element of the missStr to be ATGC
		
		System.out.println("New String is: " + missStr +" \n");
		int numOfStr = missStr.length()/subSeqLen;
		String[] newStrings = new String[numOfStr];
		
		System.out.println("There should be " + (numOfStr) + " sub strings in the new string.");
		for (int i=0; i<numOfStr; i++){
			newStrings[i] = missStr.substring(i*10, i*10 +10); 
			System.out.print( newStrings[i] +" ");
		}
		
		char[] mid = new char[4];
		mid[0] = 'A';
		mid[1] = 'G';
		mid[2] = 'T';
		mid[3] = 'C';
		
		String miss1String = "";
		
		for (int i=0; i<numOfStr; i++){
			for (int j=0; j<4; j++){
				for(int k=0; k<subSeqLen; k++){
					String miss1Str = newStrings[i].substring(0, k) + mid[j] +newStrings[i].substring(k+1);					
					
					miss1String = miss1MatchCount(miss1Str, oriStr, miss1String);
					
				} // enf for k loop;
				
			} // end for j loop;
		} // end for i loop;
		
		System.out.println("\n New 1miss String: " + miss1String);

		System.out.println("Well done the One Miss Match()");
	}

	private static String miss1MatchCount(String miss1Str, String oriStr, String miss1String) {
		// TO check if the miss1Str matches 1-char-miss in the oriStr;
		int N = miss1Str.length();
		int M = oriStr.length();
		
		for(int i=0; i<M-N; i++){
			String tempStr = oriStr.substring(i, i+N);
			int match =0;
			
			for(int k=0; k<N; k++){
				if(tempStr.charAt(k) == miss1Str.charAt(k))
					match++;
			}
			
			if (match >= N-2)
				miss1String += miss1Str;
		}
		
		return miss1Str;
		
	}

	private static String frequentCompare(String oriStr, int subLength, int oriLength, String missStr) throws IOException {
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
		
		File f=new File("dataset_4_45res.txt");
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
			
			if (count >= 1){
				paternNumber++;
				missStr += strTemp;
				// System.out.println("missStr: " + missStr);
				
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
		
		/***********************************************************************************
		 * Print out the Max, the repeated time of most frequent sequence.
		 * then close the FileWriter OUT.
		 */
	       System.out.println("\n Max = " + Max + " :) Done ..........");
	       out.close();
		return missStr;
	} // end frequent compare method;

	
	
	private static int newRoot(int[] seqIndex, int j) {
		// TO find out the final root of seqIndex[j];
		
		while(j != seqIndex[j]){
			j =seqIndex[j];
		} // the recursion!

		return j;
	} // end of newRoot method.

}

/**************************************************************************************
what you want to generate is a set of strings that contains the base k-mer, 
plus all strings with 1-mismatch, plus all strings with 2-mismatches, and so on
 up to d-mismatches.  to get the set of 1-mismatches, you take the base k-mer 
 and for each character of it you generate a new string by replacing that 
 character with each character of 'ACGT'.  add all these generated strings 
 to your set.  for 2-mismatches, we realize that we can take the 1-mismatch
  strings and generate 1-mismatches from all those.  hope this explanation helps.  
  there's a lot of steps to figure out and it's a series of several loops to do this,
   but start with smaller steps by breaking the problem down, that should help get 
   you to working code.
****************/