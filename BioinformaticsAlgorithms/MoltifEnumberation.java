package assignments;

import java.util.Hashtable;
import java.util.Scanner;

/*************
 * 
 * @author Frog
 * CODE CHALLENGE: Implement MOTIFENUMERATION (reproduced below).
 *   Input: Integers k and d, followed by a collection of strings Dna.
 *   Output: All (k, d)-motifs in Dna.
 *	
 *	Sample Input:
 *     3 1
 *     ATTTGGC
 *     TGCCTTA
 *     CGGTATC
 *     GAAAATT
 *     Sample Output:
 *     ATA ATT GTT TTT
 *
 */

public class MoltifEnumberation {
	
	public static void main(String[] args){
		
		/**********************************
		 * The first section is to input basic information of the question.
		 * How many DNA sequences do we have;
		 * The number of kmers, and the variation of d;
		 * then input strings of DNA sequences;
		 */
		System.out.println("Please input how many DNA sequences? then input k-Mer and d numbers, folling with Strings of DNA:");
		
		Scanner input1 = new Scanner(System.in);
		int DNAs = input1.nextInt();
		int kMer = input1.nextInt();
		int dNum = input1.nextInt();
		// int DNAs = 6;
		
		String[] strDNAs = new String[DNAs];
		
		for(int i=0; i<DNAs; i++){
			strDNAs[i] = input1.next();
		} // end of i<DNAs, finish inputing DNA sequences;
		
		input1.close();
		
		System.out.println("DNAs= " + DNAs +"; kMer= " + kMer + ";  dNum= " + dNum);
		
		for(int i=0; i<DNAs; i++){
			System.out.println(strDNAs[i] + " ");			
		} // end of for i<DNAs; finish printing DNA sequences;

		System.out.println("\n End of first section.");
		
		/*************
		 * the second section is to get different k-mers, 
		 * then generate the mutations with 1, 2, til d mutations;
		 * SubString from each DNA sequence, then from each (i, i+kMers);
		 */
		
		@SuppressWarnings("unchecked")
		Hashtable<String, Integer>[] KMers = new Hashtable[dNum+1];
		for(int i=0; i<=dNum; i++){
			KMers[i] = new Hashtable<String, Integer>();
		} // end of i<dNum; build dNum hastables;
		
		for(int i=0; i< DNAs; i++){
			
			System.out.print("\nThis is i="+i + " loop; ");
			for(int j=0; j<strDNAs[i].length()-kMer+1; j++){
				
				String subStr = strDNAs[i].substring(j, j+kMer);
				
				if(!KMers[0].containsKey(subStr)){
					KMers[0].put(subStr, 0);
				} // end if, put new sub sequences into the first HashTable KMers[0];
				
			//	System.out.print(subStr + " ");
				
			} // end for j<strDNAs[i].length() loop;
			

		} // end for i<DNAs loop; 
		
		for(int i=0; i<dNum; i++){
			System.out.println("\nthe " +i + " hashtable.");

			KMers[i+1] = virationSequence(KMers[i]);
			
		} // end for i< dNum loop;
		
		for(int i=0; i<=dNum; i++){
			
			System.out.println("The " + i + " hashtable:");
			printHash(KMers[i]);
		
		}
		
		
		System.out.println("\nThe last step, to check each key-string in or not in each DNA string:");
		
		for(String key : KMers[dNum].keySet()){
			int count=0;
			for(int i=0; i<DNAs; i++){
				
				count += SeqContainSub(strDNAs[i], key, dNum);
			}
			
			if(count==DNAs){
				System.out.print(key +" ");
			}
			
		} // end String key; KMers[dNum] loop;
		
	
		
	} // end main();

	private static int SeqContainSub(String Sequence, String subSeq, int dNum) {
		// TO check whether or not the string sequence contain substring key with dNum vibrations;
		int LenOri = Sequence.length();
		int LenSub = subSeq.length();
		for(int i=0; i<=LenOri-LenSub; i++){
			String temStr = Sequence.substring(i, i+LenSub);
			int diff = compareTwoStr(temStr, subSeq);
			if (diff<=dNum)
				return 1;
		} // end for i<Lenori-LenSub loop;
		
		return 0;
	}

	private static int compareTwoStr(String temStr, String subSeq) {
		// TO compare two strings of the same length; return # of different chars.
		int Len = temStr.length();
		int diff = 0;
		for(int i=0; i<Len; i++){
			if(temStr.charAt(i) != subSeq.charAt(i))
				diff++;
		}
		return diff;
	}

	private static void printHash(Hashtable<String, Integer> hashtable) {
		// TO print out each key in the hashTable;
		int num = 0;
		for(String key : hashtable.keySet()){
			System.out.print(" " + key);
			num++;
		} // end for key: hashtable loop;
		System.out.println("\n There are " + num + " sub-Sequences in the hashtable.");
	}

	private static Hashtable<String, Integer> virationSequence(Hashtable<String,Integer> oriHash) {
		// TO return all possible sequences with 1 base vibration; 
		Hashtable<String, Integer> retHash = new Hashtable<String, Integer>();
		
		for(String key : oriHash.keySet()){
			
			int Len = key.length();
			char[] subChar = {'A', 'T', 'G', 'C'};
			
			for(int i=0; i<Len; i++){
				for(int j=0; j<4; j++){
					String newStr = key.substring(0, i) + subChar[j] + key.substring(i+1);
					
					if(!retHash.containsKey(newStr)){
						retHash.put(newStr, 0);
					} // end if;
				
				} // end j<4 loop;
				
			} // end for i<Len loop;
		}	
		
		return retHash;
		
	} // end of virationSequence() method;

} // end of the whole class;
