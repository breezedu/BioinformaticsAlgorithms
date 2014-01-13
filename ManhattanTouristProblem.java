package assignments;

import java.util.Scanner;

/*******************
 * Find the length of a longest path in the Manhattan Tourist Problem.
 * Input: Integers n and m, followed by an n ¡Á (m + 1) matrix down and 
 * an (n + 1) ¡Á m matrix right.
 * The two matrices are separated by the - symbol.
 * Output: The length of a longest path from source (0, 0) 
 * to sink (n, m) in the n ¡Á m rectangular grid whose
 * 
 * @author Frog Du
 * 
 * Sample Input:
 *    4
 *    4
 *    1 0 2 4 3
 *    4 6 5 2 1
 *    4 4 5 2 1
 *    5 6 8 5 3
 *    -
 *    3 2 4 0
 *    3 2 4 2
 *    0 7 3 3
 *    3 3 0 2
 *    1 3 2 2
 *
 * Sample Output:
 *    34
 */

public class ManhattanTouristProblem {
	
	public static void main(String[] args){
		
		System.out.println("Please input the Row and Colum of the map: M and N. Followed with the Down and Right matrices.");
		Scanner inputInts = new Scanner(System.in);
		
		int Row = inputInts.nextInt(); // the row of the map;
		int Col = inputInts.nextInt(); // the col of the map;
		int[][] MatrixDown = new int[Row][Col+1];   // the matrix of going down direction;
		int[][] MatrixRight = new int[Row+1][Col];  // the matrix of going right direction;
		
		for(int i=0; i<Row; i++){
			
			for(int j=0; j<=Col; j++){
				
				MatrixDown[i][j] = inputInts.nextInt();
				
			} // end for j<=Col loop;
		} // end for i<Row, finish input MatrixDown;
		
		String Sepe = inputInts.next(); // the '-' separates down and right matrices; 
		
		for(int i=0; i<=Row; i++){
			
			for(int j=0; j<Col; j++){
				
				MatrixRight[i][j] = inputInts.nextInt();
				
			} // end for j<Col loop;
		} // end for i<=Row loop, finish input MatrixRight;
		
		inputInts.close(); 
		
		/********
		 * finish input section;
		 * Below output the Row and Col, and the two matrices;
		 * to check if all information was inputed correctly;
		 */
		
		System.out.println("The Row and Column of the map are: " + Row +" " + Col);
		
		for(int i=0; i<Row; i++){
			for(int j=0; j<=Col; j++){
				
				System.out.print(" " + MatrixDown[i][j]);
				
			} // end for j<=Col loop;
			
			System.out.println();
			
		} // end for i<Row loop; finish output matrix one;
		
		System.out.println(" " + Sepe); // printout the '-' separate two matrices; 
		
		for(int i=0; i<=Row; i++){
			
			for(int j=0; j<Col; j++){
				
				System.out.print(" " + MatrixRight[i][j]);
				
			} // end for j<Col loop;
			
			System.out.println();
		} // end for i<=Row loop; finish output matrix two;
		
		/*************
		 * finish output section;
		 * two matrices were printed out;
		 * correct? we have to check by ourselves; 
		 */
		System.out.println("Finish input and output section.");
		
		int[][] Score = new int[Row+1][Col+1]; // Score is the longest distance from V[i][j] to V[0][0];
		
		/******
		 * calculating the longest routine in the matrix;
		 * 1st, set up the starting point Score[0,0] = 0;
		 * 2nd, the first Row, Score[0,i] = Score[0,i-1] + MatrixRight[0,i-1]
		 * 3rd, the first Col, Score[j,0] = Score[j-1,0] + MatrixDown[j-1,0]
		 * 4th, calculate all other points:
		 * 	for i=1 to Row
		 * 		for j=1 to Col
		 * 			Score[i,j] = Max{Score[i-1,j] + MatrixDown[i-1,j], Score[i,j-1]+MatrixRight[i,j-1]}
		 * END
		 */
		
		Score[0][0] = 0; // set up the starting point;
		
		for(int i=1; i<=Row; i++){
			
			Score[i][0] = Score[i-1][0] + MatrixDown[i-1][0];
			
		} // end calculating the first Row routine; 
		
		for(int j=1; j<=Col; j++){
			
			Score[0][j] = Score[0][j-1] + MatrixRight[0][j-1];
			
		} // end calculating the first Col routine;
		
		for(int i=1; i<=Row; i++){
			for(int j=1; j<=Col; j++){
				
				Score[i][j] = Score[i-1][j] + MatrixDown[i-1][j];
				if(Score[i][j-1] + MatrixRight[i][j-1] > Score[i][j]){
					Score[i][j] = Score[i][j-1] + MatrixRight[i][j-1];
				} // end if condition; 
				
				System.out.print(" " + Score[i][j]);
				
			} // end for j<Col loop
			
			System.out.println();
			
		} // end for i<Row loop; end calculating all Score[i,j] in the score matrix;
		
		/******
		 * output the matrix of score[][]
		 * compare with the sample, 
		 * to check if the score matrix is correct?
		 */
		System.out.println("Output the score matrix:");
		for(int i=0; i<=Row; i++){
			for(int j=0; j<=Col; j++){
				System.out.print(" " + Score[i][j]);
			} 
			System.out.println();
		} // end for i<Row; finish output score matrix;
		
		
		/********
		 * here we figured out the Manhattan Tourist Problem with 2 matrices;
		 * to accurately calculate the match subsequences between two Sequences
		 * we have to combine to matrices into one matrix;
		 * then track back the pointers of each score point;
		 * for detail, please see the LongestCommonSubsequence.java;
		 */
		
	} // end main();

} // end of everything;
