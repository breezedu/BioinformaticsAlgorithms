package assignments;

/***********************
 * Profile-most Probable k-mer Problem: Find a Profile-most probable k-mer in a string.
 *   Input: A string Text, an integer k, and a k ¡Á 4 matrix Profile.
 *     Output: A Profile-most probable k-mer in Text.
 *     
 *     CODE CHALLENGE: Solve the Profile-most Probable k-mer Problem.
 *     
 *     Sample Input:
 *     ACCTGTTTATTGCCTAAGTTCCGAACAAACCCAATATAGCCCGAGGGCCT
 *     5
 *     A C G T
 *     0.2 0.4 0.3 0.1
 *     0.2 0.3 0.3 0.2
 *     0.3 0.1 0.5 0.1
 *     0.2 0.5 0.2 0.1
 *     0.3 0.1 0.4 0.2
 *     
 *     Sample Output:
 *     CCGAG
 */


import java.util.Scanner;

public class ProfileMostProbable {
	
	public static void main(String[] args){
		
		/**********
		 * 1st we have to read in the sequence and the Matrix
		 * here I use Scanner input mathod;
		 */
		
		System.out.println("Please 1st input the sequence, followed by the Length of the subString;"
							+ " then input the matrix.");
		Scanner input = new Scanner(System.in);
		
		String OriSeq = input.next(); // this is string of original DNA sequence;
		int subLen = input.nextInt();
		int Len = OriSeq.length();
		
		/****
		 * input A C G T
		 * Actually this part is useless
		 * just save some extra time to avoid copy twice;
		 */
		String ACGT = "  ";
		for(int i=0; i<4; i++){
			ACGT += input.next() + "    ";
		}
		
		double[][] Matrix = new double[subLen][4];
		for(int i=0; i<subLen; i++){
			for(int j=0; j<4; j++){
				Matrix[i][j] = input.nextDouble();
		
			} // end for j<4 loop;

		} // end for i<subLen input loop;
		
		input.close();
		
		System.out.println("\nThe length of original sequence is: " +Len+ "; lehgth of subsequence is:" + subLen);
		System.out.println("Three is a " + subLen +"*4 Matrix inputed. \n" + ACGT);
		for(int i=0; i<subLen; i++){
			for(int j=0; j<4; j++){
				
				System.out.print(" " + Matrix[i][j] +" ");
			} // end for j<4 loop;
			
			System.out.println();

		} // end for i<subLen output loop;
		
		/***********
		 * Here we finished section I of the program;
		 * the input-output parts.
		 * For the second part: get sub-sequence from the original sequence;
		 * compare the sub-sequence to the matrix; Suppose profile_Max=0;
		 * get the profile score, compare to the profile_Max, 
		 * if bigger, profile_max= the new profile score;
		 * Printout the new score and the sub-sequence;
		 */
		
		double profile_Max =0;
		
		for(int i=0; i<Len-subLen+1; i++){
			String subStr = OriSeq.substring(i, i+subLen);
			// System.out.print(" " + subStr);
			
			double newScore = profileScore(subStr, Matrix);
			if(newScore >= profile_Max){
				profile_Max = newScore;
				System.out.println("got a new subString: " + subStr);
<<<<<<< HEAD
				System.out.println("new profile score is: %3d" + newScore*1000);
=======
				System.out.println("new profile score is: " + newScore);
>>>>>>> 241499ba07e252d4f93e8233c8df32a84fc21ec5
			} // end if;
		} // end for i<Len-subLen+1 loop;
		
		
	} // end of main();

	private static double profileScore(String subStr, double[][] matrix) {
		// TO return a profile score of a string comparing to the matrix;
		int subLen = subStr.length();
		
		double score = 1.0;
<<<<<<< HEAD
		
=======
>>>>>>> 241499ba07e252d4f93e8233c8df32a84fc21ec5
		for(int i=0; i<subLen; i++){
			int col=5;
			char temChar = subStr.charAt(i);
			switch( temChar ){
				case 'A': col = 0; break;
				case 'C': col = 1; break;
				case 'G': col = 2; break;
				case 'T': col = 3; break;
				
			} // end switch;
			
			score *= matrix[i][col];
			//System.out.print(subStr.charAt(i) + " Matrix[" + i + "][" + col +"]=" + matrix[i][col] + "..  ");
			
		} // end of for loop;
		//System.out.print(" Score= %03d" + score);
		
		return score;
	} // end profileScore method;

} // end of everything :)
