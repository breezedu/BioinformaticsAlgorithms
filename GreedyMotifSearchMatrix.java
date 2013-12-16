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
 *
 *
 */

public class GreedyMotifSearchMatrix {
	
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
		
		//	int score = subLen*NumSeq; // the 'max' of profile score we can get;
		
		int Len = DNASeqs[0].length();
		String[] strMatrix = new String[NumSeq];
		double matrixScore =0;
		String[] finalMatrix = new String[NumSeq];
		
		for(int i=0; i<Len-subLen+1; i++){
			String k_mer = DNASeqs[0].substring(i, i+subLen);
			strMatrix[0] = k_mer;
			//System.out.print(" " + k_mer);
			for(int j=1; j<NumSeq; j++){
				String Motif_j="";
				double scoreMin = 0;	
				for(int k=0; k<Len-subLen+1; k++){
					
					strMatrix[j] = DNASeqs[j].substring(k, k+subLen);
					double score = scoreStrMatrix(strMatrix, j+1);

					if(score > scoreMin){
					//	System.out.print(" " + score +". ");
						scoreMin = score;
						Motif_j = strMatrix[j];
					} // end if score >= scoreMin;
					
				} // end for k<Len-subLen+1 loop;
				
				strMatrix[j] = Motif_j;
				
			} // end for j<NumSeq loop;
			
			double thisScore = scoreStrMatrix(strMatrix, NumSeq);
			System.out.println("i=" + i +". End one motif, score is: " + thisScore);
			printMatrixSeq(strMatrix);
			
			if(thisScore > matrixScore){
				matrixScore = thisScore;
				for(int m=0; m<NumSeq; m++){
					finalMatrix[m] = strMatrix[m];
				} // end for m<NumSeq loop; assign the strMatrix with highest score to finalMatrix;

			} // end if (thisScore > matrixScore);
			
		//	score = getScore(k_mer, score, DNASeqs);
				
		} // end for i<Len-subLen+1 loop;
		
		System.out.println("The final best motifs score is: " + matrixScore);
		printMatrixSeq(finalMatrix);
		
	} // end main();

	private static void printMatrixSeq(String[] strMatrix) {
		// TO print out the whole matrix of DNA sub sequences
		int Len = strMatrix.length;
		for(int i=0; i<Len; i++){
			System.out.println(strMatrix[i]);
		} // end for i<Len loop;
	} // end printMatrixSeq() method.

	private static double scoreStrMatrix(String[] matrix, int row) {
		// TO calculate the nucleotides matrix score;
		// TO calculate the score of a Matrix strings;
				double score = 1;	
				int Len = matrix[0].length();
				
				for(int i=0; i<Len; i++){
					
					int[] ACGT ={0, 0, 0, 0};
					for(int j=0; j<row; j++){
								
						switch(matrix[j].charAt(i)){
						
							case 'A' : ACGT[0]++; break;
							case 'C' : ACGT[1]++; break;
							case 'G' : ACGT[2]++; break;
							case 'T' : ACGT[3]++; break;
						} // end switch;
						
					} // end for j<row loop;
					
					int Max = ACGT[0];
					for(int k=0; k<4; k++){
					//	System.out.print(" " + ACGT[k] + " ");
						if (ACGT[k] > Max)
							Max = ACGT[k];
					} // end for k<4 loop;
					
					score *= (double) Max/row;
				//	System.out.println("i=" + i+ "; Max= " + Max +"; score=" + score);
					
				} // end for i<Len; 
				
				return score;
	} // end scoreStrMatrix() method;

} // end of everything;
