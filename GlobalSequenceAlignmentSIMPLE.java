package assignments;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**********
 * CODE CHALLENGE: Solve the Global Alignment Problem.
 * 
 * Input: Two protein strings written in the single-letter amino acid alphabet.
 * Output: The maximum alignment score of these strings 
 * followed by an alignment achieving this maximum score. 
 * Use the BLOSUM62 scoring matrix and indel penalty �� = 5.
 * 
 * @_@ author Frog Du
 *
 * Sample Input:
 * MAPEEYHIDIFKPPKPKRRGDLDCIPRAADIPCKGELLI
 * MAPEEYHIDMIGERAFVPPKPKRRGDLDCIPRAACKGELLI
 * 
 *  Sample Output:
 *  Score is: 152 
 *  MAPEEYHID-I----FKPPKPKRRGDLDCIPRAADIPCKGELLI
 *  MAPEEYHIDMIGERAFVPPKPKRRGDLDCIPRAA---CKGELLI
 *  
 */

public class GlobalSequenceAlignmentSIMPLE {
	
	public static void main(String[] args) throws IOException{
		/********
		 * first, readIn the score matrix:
		 * The score of char*char make a 20*20 matrix,
		 * plus the first line and first row of characters;
		 * we got a 21*21 matrix;
		 * from the first char of each line, we got the index of the Character list: indexChar[]
		 * from the score integers we got the score matrix:  Score[][]
		 * 
		 */
		
		/***********
		 * Declare and create the Row through scanMatrixFile();
		 */
		int Row = scanMatrixFile(); // use scanMatrixFile() to get the Row and Col of the Matrix;
		//int Col = Row;
		
		
		/***********
		 * Declare and create and build the indexChar[] array through readChar() method;
		 * Printout the array to see if the readChar() works correctly;
		 */
		char[] indexChar = new char[Row-1];
		indexChar = readChar(Row);
		System.out.println("Printout the indexchar[] array:");
		for(int i=0; i<Row-1; i++){
			System.out.print(" " + indexChar[i]);
		}   System.out.println();
		
		/**********
		 * Declare and create the Score[][] matrix through readMatrix() method;
		 * Printout the matrix to see if the readMatrix() works correctly;
		 */
		int[][] matchScore = new int[Row-1][Row-1];
		matchScore = buildMatchMatrix(Row);
		System.out.println("L65 Printout the match matrix:");
		//printMatrix(matchScore, Row-1, Row-1);
		
		
		/************
		 * ReadIn two sequences to alignment:
		 * use Scanner(System.in) method; 
		 * 
		 */
		Scanner inputSeq = new Scanner(System.in);
		String SeqOne = inputSeq.next();
		String SeqTwo = inputSeq.next();
		
		System.out.println("Sequence one is: " + SeqOne);
		System.out.println("Sequence two is: " + SeqTwo);
		inputSeq.close();
		
		/*********
		 * base on the two sequences inputed;
		 * declare and build the string-Match-Matrix;
		 * 
		 * INPUT:
		 *  PLEASANTLY
		 *  MEANLY
		 *  
		 *  Sequence one is: PLEASANTLY
		 *  Sequence two is: MEANLY
		 *  Printout the strScore matrix:
		 *  -2  2 -2 -1 -1 -1
		 *  -1 -3  5 -1  0 -1
		 *  -1 -1 -1  4  1  4
		 *  -2 -3  0 -2  1 -2
		 *  -3  4 -3 -1 -2 -1
		 *  -3 -1 -2 -2 -2 -2
		 **/
		int matchRow = SeqTwo.length();
		int matchCol = SeqOne.length();
		int[][] strMatchScore = new int[matchRow][matchCol];
		strMatchScore = buildStrMatchScoreMatrix(SeqOne, SeqTwo, matchScore, indexChar);
		System.out.println("L104 Printout the strScore matrix:");
		
		//printMatrix(strMatchScore, matchRow, matchCol);

		/***************
		 * from the strMatchScore matrix, build a new scoreMatrix[matchRow+1][matchCol+1],
		 * through the buildScoreMatrix() method;
		 * 
		 * the first row and first col elements are '0';
		 * the other scoreMatrix[i,j] = max{ 
		 * scoreMatrix[i-1,j] -Sigma, scoreMatrix[i,j-1] -Sigma, scoreMatrix[i-1,j-1]+strMatchScore[i,j]};
		 * the last element of the scoreMatrix is the finial score of the two sequences inputed;
		 * follow the printlCS recursion method, we can printout the global-alignment sequence;
		 */
		int scoreRow = matchRow+1;
		int scoreCol = matchCol+1;
		int[][] strScoreMatrix = new int[scoreRow][scoreCol];
		strScoreMatrix = buildScoreMatrix(strMatchScore, scoreRow, scoreCol);
		System.out.println("\n L122 Printout the strScoreMatrix:");
		//printMatrix(strScoreMatrix, scoreRow, scoreCol);  
		
		/***********
		 * build the back-tract matrix;
		 * based on the strScoreMatrix;
		 * Sigma = 5, as usual;
		 */
		int backRow = scoreRow;
		int backCol = scoreCol;
		char[][] backTractMatrix = new char[backRow][backCol];
		backTractMatrix = buildBackTrackMatrix(strScoreMatrix, strMatchScore, backRow, backCol);
		System.out.println("L134 Printout the backTractMatrix:");
		//printMatrix(backTractMatrix, scoreRow, scoreCol);
		
		/***************
		 * Use recursion method printLCS();
		 * to printout the routine from source to sink point;
		 * printUpAlignSeq() is used to printout the 1st modified sequence;
		 * printLeftAlignSeq() is used to printout the 2nd modified sequence;
		 */
		
		printUpAlignSeq(backTractMatrix, SeqOne, backRow-1, backCol-1);
		
		printLeftAlignSeq(backTractMatrix, SeqTwo, backRow-1, backCol-1);
		
	} // end of main();


	
	/**********************************************
	 ** Below the methods used in this program; ***
	 *********************************************/
	
	/********
	 * printLCS is a recursion method, to printout the routine
	 * from source to sink point, with the maximum length;
	 * 
	 * the algorithm move UpLeft to the next point:
	 * 
	 * 		if Back[i,j] == 'O'
	 * 			printLCS(Back, SeqTwo, Row-1, Col-1) // move up left, with printing;
	 * 			PrintOut SeqTwo.charAt(Row) // here is a little different from the real code;
	 * 		else if Back[i,j] == 'U'
	 * 			printLCS(Back, SeqTwo, Row-1, Col) // just move up, no printing;
	 * 		else if Back[i,j] == 'L'
	 * 			printLCS(Back, SeqTwo, Row, Col-1) // just move left, no printing;
	 * END
	 * 
	 * @param backTractMatrix
	 * @param seqTwo
	 * @param Row
	 * @param Col
	 */
	private static void printLeftAlignSeq(char[][] backTractMatrix, String seqTwo, int Row, int Col) {
		// TODO To printout the longest routine from source to sink;
		if(Row==0 && Col==0){
			System.out.println();
			return;
		} // end if condition;
		
		if(backTractMatrix[Row][Col] == 'O'){
			printLeftAlignSeq(backTractMatrix, seqTwo, Row-1, Col-1);
			System.out.print(seqTwo.charAt(Row-1));
			
		} else if(backTractMatrix[Row][Col] == 'L'){
			printLeftAlignSeq(backTractMatrix, seqTwo, Row, Col-1);
			System.out.print("-");
			
		} else if(backTractMatrix[Row][Col] == 'U'){
			printLeftAlignSeq(backTractMatrix, seqTwo, Row-1, Col);
			System.out.print(seqTwo.charAt(Row-1)); // here it is very important !!!
			
		} // end if-else condition;
		
	} // end printLeftLCS() method;

	private static void printUpAlignSeq(char[][] backTractMatrix, String seqOne, int Row, int Col) {
		// TODO To printout the longest routine from source to sink;
		if(Row==0 && Col==0){
			System.out.println();
			return;
		} // end if condition;
		
		if(backTractMatrix[Row][Col] == 'O'){
			printUpAlignSeq(backTractMatrix, seqOne, Row-1, Col-1);
			System.out.print(seqOne.charAt(Col-1));
			
		} else if(backTractMatrix[Row][Col] == 'L'){
			printUpAlignSeq(backTractMatrix, seqOne, Row, Col-1);
			System.out.print(seqOne.charAt(Col-1));    // here, it is very important;
			
		} else if(backTractMatrix[Row][Col] == 'U'){
			printUpAlignSeq(backTractMatrix, seqOne, Row-1, Col);
			System.out.print("-");
			
		} // end if-else condition;
		
	} // end printLeftLCS() method;

	/***********
	 * build the backTrackMatrix[][] based on scoreMatrix;
	 * if Score[i,j] = Score[i-1,j]+Sigma, Back[i,j] = 'U'; U for Up;
	 * else if Score[i,j] = Score[i,j-1] +Sigma, Back[i,j] = 'L'; L for Left;
	 * else if Score[i,j] = Score[i-1,j-1]+ matchMatrix[i,j], Back[i,j] = 'O'; O for UpLeft;
	 *
	 * @param strScoreMatrix
	 * @param strScoreMatrix2
	 * @param backRow
	 * @param backCol
	 * @return
	 */
	private static char[][] buildBackTrackMatrix(int[][] scoreMatrix, int[][] matchMatrix, int Row, int Col) {
		// TODO To build the back-tract matrix
		int Sigma = 5; 
		//System.out.print("Row= " + Row +". Col= " + Col);
		
		char[][] directMatrix = new char[Row][Col];
		
		for(int i=0; i<Row; i++){
			
			directMatrix[i][0] = 'U';
		} // end for i<Row loop; the first col of driectMatrix created;
		
		for(int j=0; j<Col; j++){
			
			directMatrix[0][j] = 'L';
		} // end for j<Col loop; the first row of directMatrix created;
		
		for(int i=1; i<Row; i++){
		
			for(int j=1; j<Col; j++){
				
				if(scoreMatrix[i][j] == scoreMatrix[i-1][j]-Sigma){
					directMatrix[i][j] = 'U';
					
				} else if(scoreMatrix[i][j] == scoreMatrix[i][j-1]-Sigma){
					directMatrix[i][j] = 'L';
					
				} else { 
					//if(scoreMatrix[i][j] == scoreMatrix[i-1][j-1] + matchMatrix[i][j])
					directMatrix[i][j] = 'O';
					
				} // end if-else condition;
				
			} // end for j<Col inner loop;
			
		} // end for i<Row outer loop;
		
		
		return directMatrix;
		
	} // end buildBackTrackMatrix() method;
	
	
	private static int[][] buildScoreMatrix(int[][] strMatchScore, int Row, int Col) {
		// TODO To build the strScoreMatrix base on the strMatchScore matrix;
		
		int Sigma = 5; // here we just declare and create Sigma = 5;
		int[][] newMatrix = new int[Row][Col];
		newMatrix[0][0] = 0;
		
		for(int i=1; i<Row; i++){
			newMatrix[i][0] = newMatrix[i-1][0] - Sigma;
		} // end for i<Row loop; the first element of each line assigned as '0, 5, 10, 15....';
		
		for(int j=1; j<Col; j++){
			newMatrix[0][j] = newMatrix[0][j-1] - Sigma;
		} // end for j<Col loop; each element of the first line assigned as '0';
		
		for(int i=1; i<Row; i++){
			for(int j=1; j<Col; j++){
				
				newMatrix[i][j] = strMatchScore[i-1][j-1];
				
			} // end inner for j<Col loop;
		} // end outer for i<Row loop;
		
		System.out.println("\nPrintout the temp newmatrix:");
		//printMatrix(newMatrix, Row, Col);
		
		System.out.println("Please input the indel penalty SIGMA here: \n***Sigma = 5. ***");
		
		
		
		/********
		 * so far, the newMatrix is still the 'match matrix' of two sequences;
		 * we have to transfer the match into score with the 
		 * max{s[i-1,j]-Sigma, s[i,j-1]-Sigma, s[i-1,j-1]+newMatrix[i,j];
		 * 
		 */
		
		for(int i=1; i<Row; i++){
			for(int j=1; j<Col; j++){
				
				newMatrix[i][j] = maxOfThree(newMatrix[i-1][j]-Sigma, newMatrix[i][j-1]-Sigma, newMatrix[i-1][j-1]+newMatrix[i][j]);
			
			} // end for j<Col inner loop;
			
		} // end for i<Row outer loop;
		//printMatrix(newMatrix, Row, Col);
			
		System.out.println("\nThe finial score of two sequence is: " + newMatrix[Row-1][Col-1]);
		
		/***********
		 * assign the scoreMatrix
		 * scoreMatrix[i,j] = newMatrix[i+1][j+1];
		 */
		System.out.println("Printout the scoreMatrix after calculating.");
		int[][] scoreMatrix = new int[Row-1][Col-1];
		for(int i=0; i<Row-1; i++){
			for(int j=0; j<Col-1; j++){
				
				scoreMatrix[i][j] = newMatrix[i+1][j+1];
				
			} // end inner for j<Col-1 loop;
		} // end for i<Row-1 outer loop; the scoreMatrix[][] created!
		/****
		 * Will use the scoreMatrix when calling printLCS()
		 */
		
		return newMatrix;
	} // end buldScoreMatrix() method;
	

	/**********
	 * Return the max of three integers;
	 * @param i
	 * @param j
	 * @param k
	 * @return
	 */
	private static int maxOfThree(int i, int j, int k) {
		// TODO To return the max value of three integers;
		int Max = i;
		
		if(j>Max){
			Max = j;
		} 
		
		if (k>Max){
			Max = k;
		}
		
		return Max;
	} // end of maxOfThree() method;
	

	/*********************
	 * buildStrScoreMatrix() method, to build the real score matrix of two strings;
	 * @param seqOne
	 * @param seqTwo
	 * @param matchScore
	 * @param indexChar
	 * @return
	 */
	private static int[][] buildStrMatchScoreMatrix(String seqOne, String seqTwo, int[][] matchScore, char[] indexChar) {
		// TODO To build the real score matrix of two strings;
		/************
		 * take the string seqOne as the upper boundary; Col = seqOne.length;
		 * take the string seqTwo as the left boundary; Row = seqTwo.length;
		 * for StrScore[i,j], check the charPair of seqTwo.charAt(i) and seqOne.charAt(j);
		 * check the charPair to the matchScore matrix;
		 * get the score from the matchScore, use getPairScore() method;
		 */
		int Col = seqOne.length();
		int Row = seqTwo.length();
		int[][] strScore = new int[Row][Col];
		
		for(int i=0; i<Row; i++){
			for(int j=0; j<Col; j++){
				
				/*******
				 * get the characters pair from seqTwo.charAt(i) and seqOne.charAt(j);
				 * get the score of pair through getPairScore() method;
				 */
				strScore[i][j] = getPairScore(seqTwo.charAt(i), seqOne.charAt(j), matchScore, indexChar);			
				
			} // end inner for j<Col loop;
			
		} // end outer for i<Row loop;
		
		return strScore;
	} // end buildStrScoreMatrix() method;


	/***********
	 * the getPairScore method;
	 * pass by a pair of two characters charOne and charTwo;
	 * get the score of the pair from a matchScore matrix;
	 * 
	 * input: AT
	 * 
	 * matchScore:
	 *   A T G C
	 * A 5 4 3 2
	 * T 4 6 1 2
	 * G 2 1 7 4
	 * C 2 2 4 8
	 * 
	 *  score: 4
	 *  input GC: score 4....
	 * @param charOne
	 * @param charTwo
	 * @param matchScore
	 * @param indexChar
	 * @return
	 */
	private static int getPairScore(char charOne, char charTwo, int[][] matchScore, char[] indexChar) {
		// TODO Auto-generated method stub
		int score = 0;
		int indexLen = indexChar.length;
		
		int row = 0;
		int col = 0;
		for(int i=0; i<indexLen; i++){
			if (charOne == indexChar[i])
				row = i;
		} // end for i<indexLen loop; get the row of the pair;
		for(int j=0; j<indexLen; j++){
			if (charTwo == indexChar[j])
				col = j;
		} // end for j<indexLen loop; get the col of the pair;
		
		score = matchScore[row][col];
		
		return score;
	} // end getPairScore() method;


	/****************************************************
	 * Below, the printMatrix method used in this program;
	 * @param Score
	 * @param Row
	 */
	
	private static void printMatrix(int[][] Score, int Row, int Col) {
		// TODO To printout the matrix;
		
		for(int i=0; i<Row; i++){
			
			for(int j=0; j<Col; j++){
			
				if(Score[i][j]>9){
					System.out.print(" " + Score[i][j]);
					
				} else if(Score[i][j]>=0){
					System.out.print("  " + Score[i][j]);
					
				} else {
					System.out.print(" " + Score[i][j]);
					
				} // end if-else condition;
				
			} // end for j<Row-1 inner loop;
			
			System.out.println(); // print Enter for each Score[][] row;
		} // end for i<Row-1 outer loop;

		
	} // end printMatrix( int[][]) method;
	
	private static void printMatrix(char[][] Direct, int Row, int Col) {
		// TODO To printout the matrix;
		
		for(int i=0; i<Row; i++){
			
			for(int j=0; j<Col; j++){
				
				System.out.print(" " + Direct[i][j]);
				
			} // end for j<Row-1 inner loop;
			
			System.out.println(); // print Enter for each Direct[][] row;
		} // end for i<Row-1 outer loop;

		
	} // end printMatrix(char[][]) method;
	
	
	/********************************
	 * Below, the scanMatrixFile() used to check how many lines
	 * in the score matrix;
	 * @return
	 * @throws IOException
	 */
	private static int scanMatrixFile() throws IOException {
		// TODO To check the Row and Col of the matrix;
		Scanner readIn = new Scanner(new File("BLOSUM62.TXT"));
		int Row = 0;
		
		while(readIn.hasNextLine()){
			readIn.nextLine();
			Row ++;
		} // end while loop;
		
		readIn.close();
		
		System.out.println("There are " + Row + " rows.");
		return Row;
	}

	private static char[] readChar(int row) throws IOException {
		// TODO To return the index of characters should appear in the sequences;
		
		Scanner readIn = new Scanner(new File("BLOSUM62.txt"));
		char[] characters = new char[row-1];
		readIn.nextLine(); // the first char of first line is Blank;
		
		for(int i=0; i<row-1; i++){
			characters[i] = readIn.nextLine().charAt(0);
			
		} // end for i<row-1 loop; finish reading in the first character of each line;
		
		readIn.close(); // close the readIn scanner;
		
		return characters;
	} // end readChar() method;
	

	/******************************************
	 * Below, the buildMatchMatrix() method to build matchScore matrix;
	 * @param row
	 * @return
	 * @throws IOException
	 */
	private static int[][] buildMatchMatrix(int row) throws IOException {
		// TODO To build the score matrix; here row = 21;
		/*********
		 * readIn the score matrix from BLOSUM62.txt;
		 * it was a 21*21 matrix;
		 */
		
		Scanner readIn = new Scanner(new File("BLOSUM62.txt"));
		String[] readStr = new String[row];
		for(int i=0; i<row; i++){
			
			readStr[i] = readIn.nextLine();
		} // end for i<21 loop, finish readIn matrix;
		
		readIn.close(); // close the readIn scanner
		
		System.out.println("ReadIn the original score matrix from BLOSUM62.txt document.");
		/*
		for(int i=0; i<row; i++){
			System.out.println(" " + readStr[i]);
		} // end for i<21 loop, finish output matrix;
		*/
		
		int[][] intMatrix = new int[row-1][row-1];
		char[] scanChar = new char[row-1];
		int Row = row-1;
		int Col = row-1;
		for(int i=0; i<Row; i++){
			
			Scanner scanStr = new Scanner(readStr[i+1].substring(2));
			scanChar[i] = readStr[i+1].charAt(0);

			for(int j=0; j<Col; j++){
				intMatrix[i][j] = scanStr.nextInt(); //temStr[j+1];
			}		
			
			scanStr.close(); // close the scanStr scanner;
	
		} // end for i<27 loop;
		

		/*****
		 * Try printing out the intMatrix[][];
		 */
		System.out.println("Printout the integers score matrix:");
		/*
		for(int i=0; i<Row; i++){
			
			System.out.print(scanChar[i] + " ");
			for(int j=0; j<Col; j++){
				if(intMatrix[i][j]<0){
					System.out.print(" " + intMatrix[i][j]);
					
				} else if(intMatrix[i][j] > 9){
					System.out.print(" " + intMatrix[i][j]);
					
				} else {
					System.out.print("  " + intMatrix[i][j]);
					
				} // end if-else condition;
					
			} // end inner for j<Col loop;
			
			System.out.println();
		} // end for i<Row loop; finish output the intMatrix[][];
		*/
		System.out.println("Return the score matrix:");
		return intMatrix;
	} // end readMatrix() method;
	
	

} // end of GlobalSequencealignmentProblem class;
