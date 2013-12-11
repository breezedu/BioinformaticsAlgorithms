package assignments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ClumpFind {
	
	public static void main(String[] args) throws IOException{
		
		@SuppressWarnings("resource")
		String OriStr = new Scanner(new File("dataset_4_4.txt")).useDelimiter("\\A").next();
	
		
		
		System.out.println("The number of ATGC in original sequence is: " + OriStr.length());
		// System.out.println(OriStr);
		
		//String inputStr = "CTTGATCAT";
		// the input length of sequence to be checked
		int length = 12;
		
		frequentCompare(OriStr, length);
		
	}

	private static void frequentCompare(String oriStr, int length) throws IOException {
		// TO reverse the input string, A to T, G to C, C to G, and T to A
		// Then back reversing: AAGGCT to TCGGAA
		// Final result: AGCTTTC ==> GAAAGCT
		int M = oriStr.length();
		int N = length;
		int count =0;
		
		System.out.println("The length of input string is: " + N);
		
		File f=new File("dataset_4_4res.txt");
		FileWriter out=new FileWriter(f);
		
		for (int i=0; i<M-N-2; i++){
			
			String strTemp ="";
			
			for (int j=0; j<N; j++){
			strTemp += oriStr.charAt(i+j);
			
			}
			// System.out.print(" " + strTemp);
			
			for (int j=i+1; j<i+524-N && j<M-N; j++){
				String comStr = "";
				
				for (int k=0; k<N; k++){
					comStr += oriStr.charAt(j + k);
				}
				
				if (strTemp.equals(comStr)){
					count++;
				}
				
			}
			
			if (count == 16){
				//System.out.println("Now the sequence " + strTemp + " repeated " + count +" times.");
				System.out.print(strTemp + " ");
				out.write(strTemp + " ");
			}
			count=0;
		}
			
	       System.out.println("\n Done ..........");
	       out.close();
	//	System.out.print(inputStr);
		
	}			
}
