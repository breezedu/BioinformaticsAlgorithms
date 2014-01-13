package assignments;

import java.util.Scanner;

/***********************
 *      Input: Two strings s and t.
 *      Output: A longest common subsequence of s and t.
 *      
 *      @_@ author GuangJian
 * 
 *     OUTPUTLCS(backtrack, v, i, j)
 *      if i = 0 or j = 0
 *          return
 *      if backtrack[i, j] = ¡ý
 *          OUTPUTLCS(backtrack, v, i - 1, j)
 *      else if backtrack[i, j] = ¡ú
 *          OUTPUTLCS(backtrack, v, i, j - 1)
 *      else
 *          OUTPUTLCS(backtrack, v, i - 1, j - 1)
 *          output vi
 *
 *    Sample Input:
 *    AACCTTGG
 *    ACACTGTGA
 *    
 *    Sample Output:
 *    AACTGG or AACTTG?
 *
 */

public class LongestCommonSubsequence {

	public static void main(String[] args){
		
		/**************
		 * this is the main() class;
		 * input two DNA sequences;
		 */
		System.out.println("Please input the two original sequences:");
		Scanner inputSeq = new Scanner(System.in);
		String SeqOne = inputSeq.next(); // the upper sequence;
		String SeqTwo = inputSeq.next(); // the left sequence;
		
		inputSeq.close();
		
		int row = SeqTwo.length(); // the length of SeqTwo;
		int col = SeqOne.length(); // the length of SeqOne;
		
		System.out.println("Two sequences inputed.");
		
		/*********
		 * After two sequences inputed.
		 * build the matchMatrix base on the two sequences inputed;
		 * 	if(SeqOne.charAt(i) == SeqTwo.charAt(j))
		 * 		Match[i,j] = 1;
		 * 	else Match[i,j] = 0;
		 * END;
		 */
		int[][] matchMatrix = new int[row][col]; 
		matchMatrix = buildMatchMatrix(SeqOne, SeqTwo); // build matchMatrix base on two sequences;
		System.out.println("Print the matchMatrix:");
		printMatrix(matchMatrix, row, col); // print out the matchMatrix;
		
		
		/*********
		 * After the matchMatrix is calculated;
		 * build the scoreMatrix base on the matchMatrix;
		 * the first col and first row contain only '0';
		 * for i 1 to row
		 * 	  for j 1 to col
		 * 		if Match[i,j] ==0;
		 * 			Score [i,j] = max{ Score[i-1][j], Score[i][j-1] }
		 * 		else if Match[i,j] ==1;
		 * 			Score[i,j] = max{ Score[i-1][j], Score[i][j-1], Score[i-1][j-1]+1 }
		 * END
		 */
		int[][] scoreMatrix = new int[row+1][col+1];
		scoreMatrix = buildScoreMatrix(matchMatrix, row, col);
		System.out.println("Print the scoreMatrix:");
		printMatrix(scoreMatrix, row+1, col+1);
		
		
		/**************
		 * build the backTracting pointer matrix;
		 *  if(Score[i,j] == Score[i-1,j])
		 * 		Back[i,j] = Up
		 *  else if (Score[i,j] == Score[i,j-1])
		 *  	Back[i,j] = Left
		 *  else if (Score[i,j] == Score[i-1, j-1]+1
		 *  	Back[i,j] = UpLeft;
		 * END
		 * 
		 * Here, I use char 'U' = Up, 'L' = Left; 'O' = UpLeft;
		 */
		char[][] backTractMatrix = new char[row+1][col+1];
		backTractMatrix = buildBackTractMatrix(scoreMatrix, row+1, col+1);
		
		/**********
		 * printout the longest common sequence:
		 * base on the backTract Matrix, we can tract the routine 
		 * from the last sink point back to the source point, with Recursion method;
		 * if the Back[i,j] == 'O', it means we get one matched pair.
		 * along the routine and the second sequence we inputed, we can output the LCS;
		 */
		System.out.println("\nPrint the Longest Common Sequence (LCS):");
		printLCS(backTractMatrix, SeqTwo, row, col); // actually we send the backTractMatrix[row][col] to the method;
		//printMatrix(backTractMatrix, row, col);

		System.out.println("\nDone! :)");
		/*********
		 * well done :)
		 */
	} // end main();
	

	private static void printLCS(char[][] backTractMatrix, String seqTwo, int Row, int Col) {
		// TODO printout the longest common sequence;
		if(Row==0 || Col==0)
			return;
		
		if (backTractMatrix[Row][Col] == 'O'){
			
			printLCS(backTractMatrix, seqTwo, Row-1, Col-1);
			System.out.print("" + seqTwo.charAt(Row-1) );
		} else if (backTractMatrix[Row][Col] == 'U'){
			
			printLCS(backTractMatrix, seqTwo, Row-1, Col);
		} else if (backTractMatrix[Row][Col] == 'L'){
			
			printLCS(backTractMatrix, seqTwo, Row, Col-1);
		} // end else-if loop;
		
		
	} // end printLCS() method; 
	

	private static char[][] buildBackTractMatrix(int[][] scoreMatrix, int Row, int Col) {
		// TODO Build the back-tract pointer matrix;
		char[][] BTMatrix = new char[Row][Col];
		
		for(int i=0; i<Row; i++){
			BTMatrix[i][0] = 'U'; // all first Column follow the UP element;
		} // end i<Row, the first column assigned; 
		
		for(int j=0; j<Col; j++){
			BTMatrix[0][j] = 'L'; // all first Row follow the Left element;
		} // end j<Col loop, the first row assigned;
		
		for(int i=1; i<Row; i++){
			for(int j=1; j<Col; j++){
				if (scoreMatrix[i][j] == scoreMatrix[i-1][j]){
					BTMatrix[i][j] = 'U';
				} else if (scoreMatrix[i][j] == scoreMatrix[i][j-1]){
					BTMatrix[i][j] = 'L';
				} else if (scoreMatrix[i][j] == scoreMatrix[i-1][j-1]+1){
					BTMatrix[i][j] = 'O';
				} // end if-else loop;
				
			} // end for j<Col loop;
		} // end for i<Row loop;
		
		/*********
		 * print out the BTMatrix
		 */
		System.out.println("Printout the BackTract Matrix.");
		for(int i=0; i<Row; i++){
			
			for(int j=0; j<Col; j++){
			
				System.out.print(" " + BTMatrix[i][j]);
			} // end for j<Col loop;
			
			System.out.println();
		} // end for i<Row loop; finish printing BTMatrix;
		/* ******
		 * so far, everything works well;
		 */
		
		return BTMatrix;
		
	} // end of buildBackTrackMatrix() method;
	

	private static int[][] buildScoreMatrix(int[][] matchMatrix, int Row, int Col) {
		// TODO build the scoreMatrix base on matchMatrix;
		/***********
		 * first, move every element in the matchMatrix Left-and-Down
		 * get a new Row+1, Col+1 matrix;
		 * then, calculate the score of each new element in the new matrix;
		 */
		int newRow = Row+1;
		int newCol = Col+1;
		int[][] newMatrix = new int[newRow][newCol];
		
		for(int i=0; i<newRow; i++){
			newMatrix[i][0] = 0;
		} // the first row of new matrix assigned. '0';
		for(int j=0; j<newCol; j++){
			newMatrix[0][j] = 0;
		} // the first col of new matrix assigned. '0';
		
		for(int i=1; i<newRow; i++){
			for(int j=1; j<newCol; j++){
				newMatrix[i][j] = matchMatrix[i-1][j-1];
				
			}
		} // end for i<newRow loop; newMatrix[][] built;
		
		System.out.println("Printout the new matrix based on matchMatrix:");
		printMatrix(newMatrix, newRow, newCol);
		
		/**************
		 * 0 0 0 0 0 0 0
		 * 0 0 0 0 1 0 1
		 * 0 1 0 0 0 1 0
		 * 0 0 0 1 0 0 0
		 * 0 1 0 0 0 1 0
		 * 0 0 1 0 0 0 0
		 * 0 0 0 0 1 0 1
		 * 0 1 0 0 0 1 0
		 */
		
		int[][] scoreMatrix = new int[newRow][newCol];
		for(int i=0; i<newRow; i++){
			for(int j=0; j<newCol; j++){
				scoreMatrix[i][j] = newMatrix[i][j];
				
			}  // end for j<newCol loop;
		}  // end for i<newRow loop;
		
		/*****************
		 * To get the scoreMatrix:
		 * if matchMatrix[i-1][j-1]==0; scoreMatrix[i][j] = max{ scoreMatrix[i-1][j], scoreMatrix[i][j-1]}
		 * else scoreMatrix[i][j] = max{scoreMatrix[i-1][j], scoreMatrix[i][j-1], scoreMatrix[i-1][j-1]+1 }
		 * 
		 */
		
		for(int i=1; i<newRow; i++){
			
			for(int j=1; j<newCol; j++){
				
				if(scoreMatrix[i][j] == 1){
					scoreMatrix[i][j] = maxOfThree(scoreMatrix[i-1][j], scoreMatrix[i][j-1], (scoreMatrix[i-1][j-1]+1));
				} else {
					scoreMatrix[i][j] = maxOfThree(scoreMatrix[i-1][j], scoreMatrix[i][j-1], 0);
				} // end if-else;
				
			} // enf for j<newCol loop;
		} // end for i<newRow loop;
		
		return scoreMatrix;
	} // end buildScoreMatrix() method; 
	

	private static int maxOfThree(int i, int j, int k) {
		// TODO Return the max of three integers;
		int Max = i;
		
		if(j>Max)
			Max = j;
		if(k>Max)
			Max = k;
		
		return Max;
	} // end maxOfThree() method; 

	
	private static void printMatrix(int[][] matrix, int Row, int Col) {
		// TODO print out the matrix;
		System.out.println("Printing......");
		for(int i=0; i<Row; i++){
			for(int j=0; j<Col; j++){
				System.out.print(" " + matrix[i][j]);
				
			}
			System.out.println();
		} // end for i<Col loop;
		
	} // end of printMatrix() method;

	
	private static int[][] buildMatchMatrix(String seqOne, String seqTwo) {
		// TODO print out a matrix comparing two sequences;
		/*********
		 * input ATTGCGG and AGGCGT
		 * output the following matrix:
		 * Example:
		  	 TGCATA
		  	 ATCTGAT
		 *     T G C A T A
		 *   A 0 0 0 1 0 1
		 *   T 1 0 0 0 1 0
		 *   C 0 0 1 0 0 0
		 *   T 1 0 0 0 1 0
		 *   G 0 1 0 0 0 0
		 *   A 0 0 0 1 0 1
		 *   T 1 0 0 0 1 0
		 *   Thus, we get the original 'match' matrix;
		 *   to get score matrix, we have to calculate 
		 *   Score{ S[i][j] = max(S[i-1][j], S[i][j-1], S[i-1][j-1]+1 }
		 */
		
		int Row = seqTwo.length();
		int Col = seqOne.length();
		int[][] MatchMatrix = new int[Row][Col];
		for(int i=0; i<Col; i++){
			MatchMatrix[0][i] =0;
		} // the first row of the matrix assigned '0';
		
		for(int j=0; j<Row; j++){
			MatchMatrix[j][0] =0;
		} // the first col of the matrix assigned '0';
		
		System.out.println("The matrix is a " + Row + "*" + Col + " matrix.");
		
		/*******
		 * printout the first Row, seqOne;
		 */
		System.out.print(" ");
		for(int i=0; i<Col; i++){
			System.out.print(" " + seqOne.charAt(i));
		}
		System.out.println();
		
		for(int i=0; i<Row; i++){
			System.out.print(seqTwo.charAt(i)); // print out the ith char of seqTwo;
			
			for(int j=0; j<Col; j++){
				/**
				 * compare seqOne[j] and seqTwo[i];
				 * if they are the same, print '1'
				 * else print '0'
				 * then we got the matrix;
				 */
				if(seqOne.charAt(j) == seqTwo.charAt(i)){
					System.out.print(" 1");
					MatchMatrix[i][j] = 1;
				} else {
					System.out.print(" 0");
					MatchMatrix[i][j] = 0;
				} // end if-else loop;
				
			} // end for j<Col loop;
			System.out.println();
			
		} // end i<Row loop; finish printing matrix;
		
		System.out.println("Matrix and two Sequences as two boundries printed.");
		
		return MatchMatrix;
	} // end buildMatchMatrix() method;
	
	
} // end of everything;
