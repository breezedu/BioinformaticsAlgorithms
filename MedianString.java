package assignments;

import java.util.Hashtable;
import java.util.Scanner;


/********************************************************
 * 
 * @author Frog
 * a brute force solution to the Median String Problem;
 * MedianString(DNA, k);
 *  BestPattern <--- AAA...AAA/ TTTT...TTT
 *  for each k-mer Pattern from AAA...AA to TTT...TT
 *      if d(Pattern, DNA) < d(BestPattern, DNA)
 *      	BestPattern <--- Pattern
 *  output BestPattern;
 */

public class MedianString {
	
	public static void main(String[] args){
		
		/**********
		 * 1st we have to readin all the sequences and the length of sub-sequence
		 * here I use Scanner input
		 */
		
		System.out.println("Please 1st input how many sequences, then input the length of the kmer.");
		Scanner input = new Scanner(System.in);
		
		int Num = input.nextInt();
		int subLen = input.nextInt(); // this is the number of subsequences;
		
		String seqStr[] = new String[Num];
		for(int i=0; i<Num; i++){
			seqStr[i] = input.next();
		} // end for i<Num input loop;
		
		input.close();
		
		System.out.println("the length of subsequence is:" + subLen);
		System.out.println("Three are " + Num +" subSequences inputed.");
		for(int i=0; i<Num; i++){
			System.out.println(seqStr[i] + ".");
		} // end for i<Num output loop;
		
		/***********
		 * Here we finished section I of the program;
		 * the input-output parts.
		 */
		
		Hashtable<String, Integer> kmerTable = new Hashtable<String, Integer>();
		
		for(int i=0; i<Num; i++){
			int temLen = seqStr[i].length();
			for(int j=0; j<temLen-subLen; j++){
				
				String temStr = seqStr[i].substring(j, j+subLen);
				if(!kmerTable.containsKey(temStr)){
					kmerTable.put(temStr, 1);
				} 
				
			} // end for j<temLen loop;
			
		} // end for i<Nul loop;
		
		printTable(kmerTable);
		
		/******
		 * Here use oneReplace() method to get just 1-substituted k-mers;
		 * if we need 2-miss or more, just repeat the followint step 
		 * with a for(i=0; i<N; i++) loop, we can get a N-miss k-mer hashTable;
		 * I privately think 2-miss is enough for this programming problem:
		 */
		kmerTable = oneReplace(kmerTable); // to get the 1miss k-mer hashtable;
		printTable(kmerTable);
		
		kmerTable = oneReplace(kmerTable); // to get the 2miss k-mer hashtable;
		printTable(kmerTable);
		
		/*************
		 * Here we finished the section II of our program
		 * the hashTable with 1-miis or 2-miss k-mers;
		 */
		
		
		getMin_d(kmerTable, seqStr, subLen, Num);
		
	} // end main();

	private static void getMin_d(Hashtable<String, Integer> kmerTable, String[] seqStr, int subLen, int Num) {
		// TO figure out the minium d_value of subString comparing to all seqStr[i] strings;
		// and print out the subStrings with the minum d-value;
		int d_min = Num*2; // this is an arbitrary assigning.
		
		for(String key: kmerTable.keySet()){
			int d_value=0;
			for(int i=0; i<Num; i++){
				d_value += getEachD(key, seqStr[i]);
				
			} // end for i<Num loop;
			
			if(d_value <= d_min){
				d_min = d_value;
				System.out.println(" d_min= " + d_min +" " + key);
			}
			
		} // end for key:kmerTable.keySet() loop;
		
	}

	private static int getEachD(String key, String Sequence) {
		// TO get the d_value of string key, comparing to String Sequence;
		
		int Len = Sequence.length();
		int subLen = key.length();
		int dMin = subLen; // suppost there's no match with key in the Sequence at first;
		
		for(int i=0; i<Len-subLen+1; i++){
			String temStr = Sequence.substring(i, i+subLen);
			int diff = compare(temStr, key);
			if(diff <= dMin){
				dMin = diff;
			} // end if ;
		} // end for i<Len-subLen+1 loop;
		
		return dMin;
	}

	private static int compare(String temStr, String key) {
		// TO count how many different chars between two strings;
		int Len = temStr.length();
		int diff=0;
		for(int i=0; i<Len; i++){
			if(temStr.charAt(i) != key.charAt(i))
				diff++;
		}
		return diff;
	}

	private static void printTable(Hashtable<String, Integer> kmerTable) {
		// TO printout all keys in the hashtable;
		System.out.println("Print out the keys:");
		int Keys = 0;
		for(String key : kmerTable.keySet()){
			Keys++;
			System.out.print(" " + key);
			if(Keys%100 == 0){
				System.out.println();
			}
		} //end for key:kmerTable.keySet();
		
		System.out.println("\nThere are " + Keys + " keys in the table.");
	}

	private static Hashtable<String, Integer> oneReplace( Hashtable<String, Integer> kmerTable) {
		// TO return another hashtable with 1 base replaced with AGTC;
		
		Hashtable<String, Integer> retTable = new Hashtable<String, Integer>();
		for(String key : kmerTable.keySet()){
			int Len = key.length();
			char[] subChar = {'A', 'C', 'G', 'T'};
			
			for(int i=0; i<Len; i++){
				for(int j=0; j<4; j++){
					String temString = key.substring(0, i) + subChar[j] + key.substring(i+1);
					
					if( !retTable.containsKey(temString)){
						retTable.put(temString, 1);
					
					} // end if 
				}
			} //end for i<Len loop;
			
		} // end for key:kmerTable.keySet(); 
		
		return retTable;
	} // end oneReplace method;

} // EE (end of everything);