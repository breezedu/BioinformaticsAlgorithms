package assignments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FrequentSequence {
	
	public static void main(String[] args) throws IOException{
		
		@SuppressWarnings("resource")
		String OriStr = new Scanner(new File("Thermotoga-petrophila.txt")).useDelimiter("\\A").next();
		
		
		System.out.println("The number of ATGC in original sequence is: " + OriStr.length());
		// System.out.println(OriStr);
		
		//String inputStr = "CTTGATCAT";
		// the input length of sequence to be checked
		int length = 9;
		
		frequentCompare(OriStr, length);
		
	}

	private static void frequentCompare(String oriStr, int length) throws IOException {
		// TO reverse the input string, A to T, G to C, C to G, and T to A
		// Then back reversing: AAGGCT to TCGGAA
		// Final result: AGCTTTC ==> GAAAGCT
		int M = oriStr.length();
		int N = length;
		int Max = 0;
		int count =0;
		
		System.out.println("The length of input string is: " + N);
		
		File f=new File("freResultThermotoga.txt");
		FileWriter out=new FileWriter(f);
		
		for (int i=0; i<M-N-2; i++){
			
			String strTemp ="";
			
			for (int j=0; j<N; j++){
			strTemp += oriStr.charAt(i+j);
			
			}
			// System.out.print(" " + strTemp);
			
			for (int j=i+1; j<M-N-1; j++){
				String comStr = "";
				
				for (int k=0; k<N; k++){
					comStr += oriStr.charAt(j + k);
				}
				
				if (strTemp.equals(comStr)){
					count++;
				//	System.out.print(" " + j);
				}
				
			}
			
			if (count >= 100){
				Max = count;
				System.out.println("Now the sequence " + strTemp + " repeated " + count +" times.");


				out.write(strTemp + " ");
			}
			count=0;
			System.out.println("Running " + i + " circles.");
		}
			
	       System.out.println("The most frequent sequence repeated " + Max +" times; \n Done ..........");
	       out.close();
	//	System.out.print(inputStr);
		
	}			
}
