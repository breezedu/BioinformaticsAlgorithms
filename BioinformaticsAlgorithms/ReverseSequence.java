package assignments;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class ReverseSequence {
	
	public static void main(String[] args) throws IOException{
		
		@SuppressWarnings("resource")
		String OriStr = new Scanner(new File("dataset_3_3.txt")).useDelimiter("\\A").next();
		
		
		System.out.println("The number of ATGC in original sequence is: " + OriStr.length());
		System.out.println(OriStr);
		
		reverseStr( OriStr);
		
	}

	private static String reverseStr(String Str) throws IOException {
		// TO reverse the input string, A to T, G to C, C to G, and T to A
		// Then back reversing: AAGGCT to TCGGAA
		// Final result: AGCTTTC ==> GAAAGCT
		int M = Str.length();
		char[] reStr = new char[M];
		
		for (int i=M-1; i>=0; i--){
			
			if(Str.charAt(i) == 'A')
				reStr[M-i-1] = 'T';
			else if(Str.charAt(i) == 'G')
				reStr[M-i-1] = 'C';
			else if(Str.charAt(i) == 'T')
				reStr[M-i-1] = 'A';
			else if(Str.charAt(i) == 'C')
				reStr[M-i-1] = 'G';
		}

		System.out.print(reStr);
		
		File f=new File("reversedString3_3.txt");
		FileWriter out=new FileWriter(f);
	       out.write(reStr);
	       
	       System.out.println("\n Done ..........");
	       out.close();
		
		return Str;
	}			
}