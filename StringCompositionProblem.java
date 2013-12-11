package assignments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class StringCompositionProblem {
	
	public static void main(String[] args) throws IOException{
		
		String OriStr="ATTCGGACCTAAGGCC";
		
		int subLen = 5;
		int oriLen = OriStr.length();
		String[] subStrings = new String[oriLen-subLen+1];
		
		File f=new File("StrComp.txt");
		FileWriter out=new FileWriter(f);
		
		
		for(int i=0; i<=oriLen-subLen; i++){
			subStrings[i] = OriStr.substring(i, i+subLen);

		}
		
		Arrays.sort(subStrings);
		
		for(int i=0; i<=oriLen-subLen; i++){
			System.out.println(subStrings[i]);
			
			out.write(subStrings[i] + "\n");
			
			
		}
		
		
		
		
		out.close();
		
	
	} // end main();

}
