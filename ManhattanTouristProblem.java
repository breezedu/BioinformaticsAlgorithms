package assignments;

import java.util.Scanner;

/*******************
 * Find the length of a longest path in the Manhattan Tourist Problem.
 * Input: Integers n and m, followed by an n × (m + 1) matrix down and 
 * an (n + 1) × m matrix right.
 * The two matrices are separated by the - symbol.
 * Output: The length of a longest path from source (0, 0) 
 * to sink (n, m) in the n × m rectangular grid whose
 * 
 * @author GJ Du
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
		
		System.out.println("Please input the Row and Colum of the map: M and N.");
		Scanner inputInts = new Scanner(System.in);
		int Row = inputInts.nextInt();
		int Col = inputInts.nextInt();
		int[][] MatrixDown = new int[Row][Col+1];
		int[][] MatrixRight = new int[Row+1][Col];
		for(int i=0; i<Row; i++){
			for(int j=0; j<=Col; j++){
				MatrixDown[i][j] = inputInts.nextInt();
				
			} // end for j<=Col loop;
		} // end for i<Row, finish input MatrixOne;
		
		String Sepe = inputInts.next();
		
		for(int i=0; i<=Row; i++){
			for(int j=0; j<Col; j++){
				MatrixRight[i][j] = inputInts.nextInt();
			}
		} // end for i<=Row, finish input MatrixTwo;
		
		inputInts.close();
		/********
		 * finish input section;
		 * Below output the Row and Col, and the two matrices;
		 */
		
		System.out.println("The Row and Column of the map are: " + Row +" " + Col);
		
		for(int i=0; i<Row; i++){
			for(int j=0; j<=Col; j++){
				System.out.print(" " + MatrixDown[i][j]);
			}
			System.out.println();
		} // end for i<Row loop; finish output matrix one;
		
		System.out.println(" " + Sepe);
		
		for(int i=0; i<=Row; i++){
			for(int j=0; j<Col; j++){
				System.out.print(" " + MatrixRight[i][j]);
			}
			System.out.println();
		} // end for i<=Row loop; finish output matrix two;
		
		/*************
		 * finish output section;
		 * two matrices were printed out;
		 */
		System.out.println("Finish input and output section.");
		
		int[][] Score = new int[Row+1][Col+1]; // Score is the longest distance from V[i][j] to V[0][0];
		Score[0][0] = 0;
		
		for(int i=1; i<=Row; i++){
			Score[i][0] = Score[i-1][0] + MatrixDown[i-1][0];
		} // end the first Row routine; 
		
		for(int j=1; j<=Col; j++){
			Score[0][j] = Score[0][j-1] + MatrixRight[0][j-1];
		} // end the first Col routine;
		
		
		for(int i=1; i<=Row; i++){
			for(int j=1; j<=Col; j++){
				
				Score[i][j] = Score[i-1][j] + MatrixDown[i-1][j];
				if(Score[i][j-1] + MatrixRight[i][j-1] > Score[i][j]){
					Score[i][j] = Score[i][j-1] + MatrixRight[i][j-1];
				}
				System.out.print(" " + Score[i][j]);
			} // end for j<Col loop
			System.out.println();
		} // end for i<Row loop;
		
		/******
		 * output the matrix of score[][]
		 */
		System.out.println("Output the score matrix:");
		for(int i=0; i<=Row; i++){
			for(int j=0; j<=Col; j++){
				System.out.print(" " + Score[i][j]);
			} 
			System.out.println();
		} // end for i<Row; finish output score matrix;
		
	} // end main();

} // end of everything;
