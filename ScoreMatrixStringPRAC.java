package assignments;

/**********************
 * calculate the score of a DNA sequences matrix;
 * input row, following by DNA strings;
 */

import java.util.Scanner;

public class ScoreMatrixStringPRAC {
	
	public static void main(String[] args){
		
		Scanner inputStr = new Scanner(System.in);
		
		int Row = inputStr.nextInt();
		String[] Matrix = new String[Row];
		
		for(int i=0; i<Row; i++){
			Matrix[i] = inputStr.next();
		} // end for i<Row input loop;
		inputStr.close();
		
		System.out.println("There are +" + Row +" rows in the matrix. ");
		for(int i=0; i<Row; i++){
			System.out.println("row " + i +", " + Matrix[i]);
		} // end for i<Row output loop;
		
		double score = scoreMatrix(Matrix, Row);
		System.out.println("The score of input matrix is: " + score);
		
	} // end main();

	private static double scoreMatrix(String[] matrix, int row) {
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
				System.out.print(" " + ACGT[k] + " ");
				if (ACGT[k] > Max)
					Max = ACGT[k];
			} // end for k<4 loop;
			
			score *= (double) Max/row;
			System.out.println("i=" + i+ "; Max= " + Max +"; row=" + row +". score=" + score*10000);
			
		} // end for i<Len; 
		
		return score;
	} // end scoreMatrix() method;

} // end of everything;
