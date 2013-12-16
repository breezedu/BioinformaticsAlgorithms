package assignments;

import java.util.Scanner;

/****************************
 * The greedy motif search algorithm iteratively finds k-mers in the 1st, string from Dna, 
 * 2nd string from Dna, 3rd string from Dna, etc. 
 * After finding i - 1 k-mers Motifs in the first i - 1 strings of Dna, 
 * this algorithm constructs Profile(Motifs) and selects the Profile-most probable k-mer 
 * from the i-th string based on this profile matix (ties are broken arbitrarily).
 * 
 * To form the initial motif matrix BestMotifs, 
 * the algorithm starts by selecting arbitrary k-mers in each string from Dna; 
 * the following pseudocode selects the first k-mer in each string.
 * 
 *  GREEDYMOTIFSEARCH(Dna, k,t)
 *       form a set of k-mers BestMotifs by selecting 1st k-mers in each string from Dna
 *       for each k-mer Motif in the 1st string from Dna
 *           Motif1 ¡û Motif
 *           for i = 2 to t
 *               form Profile from motifs Motif1, ¡­, Motifi - 1
 *               Motifi ¡û Profile-most probable k-mer in the i-th string in Dna
 *           Motifs ¡û (Motif1, ¡­, Motift)
 *           if Score(Motifs) < Score(BestMotifs)
 *               BestMotifs ¡û Motifs
 *       output BestMotifs
 *       
 * @author Frog Du
 *
 */

public class GreedyMotifSearch {
	
	public static void main(String[] args){
		/****
		 * the first part
		 * input all numbers and DNA sequences
		 */
		System.out.println("Please input the length of k-mer, and the number of sequences, "
							+ "then followed the DNA sequences.");
		Scanner input = new Scanner(System.in);
		
		int subLen = input.nextInt();
		int NumSeq = input.nextInt();
		String[] DNASeqs = new String[NumSeq];
		
		for(int i=0; i<NumSeq; i++){
			DNASeqs[i] = input.next();
		} // end for i<NumSeq loop;
		
		input.close();
		
		System.out.println("The length of sub-sequence is: " + subLen +". There are " + NumSeq +" DNA sequences.");
		for(int i=0; i<NumSeq; i++){
			System.out.println("DNASeq["+i+"]: " + DNASeqs[i]);
		} // end for i<NumSeq output;
		
		/****************
		 * this finishes the first part of the program: inputing.
		 * the following step will generate k-mer from 1st string;
		 * then calculate the matrix from all inputed DNA sequences;
		 */
		
		int score = subLen*NumSeq; // the 'max' of profile score we can get;
		
		int Len = DNASeqs[0].length();
		for(int j=0; j<NumSeq; j++){
			
			for(int i=0; i<Len-subLen+1; i++){
				String k_mer = DNASeqs[j].substring(i, i+subLen);
				//System.out.print(" " + k_mer);
				
				score = getScore(k_mer, score, DNASeqs);
				
			} // end for i<Len-subLen+1 loop;
		}
		
	} // end main();

	private static int getScore(String k_mer, int score, String[] dnaSeqs) {
		// To calculate profile score of string k_mer to matrix dnaSeqs;
		int newScore =0;
		int numDNAs = dnaSeqs.length;
		String[] motifs = new String[numDNAs];
		
		for(int i=0; i<numDNAs; i++){
			int noMatch = k_mer.length();
			String matrix = "";
			for(int j=0; j<dnaSeqs[i].length()-k_mer.length()+1; j++){
				String temStr = dnaSeqs[i].substring(j, j+k_mer.length());
				int MissMatch = compareMatches(temStr, k_mer);
				
				if(MissMatch < noMatch){
					noMatch = MissMatch;
					matrix = temStr;
				}
				//newScore = matchMax;
				
			} // end j<dnaseqs[i].length()-
			
			//System.out.println(matrix +" ");
			motifs[i] = matrix;
			newScore += noMatch;
		}
		
		if(newScore <= score){
			System.out.println("the score of this cycle is: " + newScore);
			for(int i=0; i<numDNAs; i++){
				System.out.println(motifs[i] + " ");
			}
			return newScore;
		}
		
		return score;
		
	} // end getScore() method;

	private static int compareMatches(String temStr, String k_mer) {
		// TO calculate how many characters match in two strings;
		int missMatch =0;
		int Len = temStr.length();
		
		for(int i=0; i<Len; i++){
			if (temStr.charAt(i) != k_mer.charAt(i)){
				missMatch++;
			} // end if;
		} // end for i<Len loop;
		
		return missMatch;
	} // end compareMatches() method;

} // end of everything;
