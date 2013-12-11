package assignments;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SkewOfGenome {
	
	public static void main(String[] args) throws IOException{
		
		@SuppressWarnings("resource")
		//String OriStr = new Scanner(new File("Thermotoga-petrophila.txt")).useDelimiter("\\A").next();
		String OriStr = new Scanner(new File("dataset_7_62.txt")).useDelimiter("\\A").next();
		//	String OriStr = " TAAAGACTGCCGAGAGGCCAACACGAGTGCTAGAACGAGGGGCGTAAACGCGGGTCCGAT";	
		
		String subStr1 = OriStr.substring(0, 10);				
		System.out.println("The number of ATGC in original sequence is: " + (OriStr.length()-2));
		System.out.println("The first 10 elements are :" + subStr1);
				
		// System.out.println(OriStr);
		int oriLeng = OriStr.length();
		int skew = 0;
		int Min =0;
		
		System.out.print(skew + " ");
		
		for(int i=0; i<oriLeng; i++){
			if (OriStr.charAt(i) == 'C'){
				skew +=-1;
			} else if (OriStr.charAt(i) == 'G'){
				skew +=1;
			} else skew += 0;
			
			// System.out.print(skew + " ");
			
			if (skew <= Min){
				Min = skew;
				System.out.println("Got one Min at: " + (i+1) + ". The Min value is: " + Min + " ");
			}

			
		}
		/***************************************************************************************
		 * input length of sequence to be checked; 
		 * normally the 9-element subsequence would be the k-mer
		 * for the ribosome to check and bind to, :) HERE we just input the number directly;
		 * In real program we have to use arg[0], or write a method to ask user to input the subSeqLen.
		 */
				
	} // end main(); ATGCTGAGT

}



