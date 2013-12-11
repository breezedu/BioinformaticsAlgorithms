package assignments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DeBruijinGraph {
	
	public static void main(String[] args) throws IOException{
		
		Scanner ScanLines = new Scanner(new File("dataset_53_6.txt"));
		String OriSeq = ScanLines.nextLine();
		
		ScanLines.close();
		
		int Len = OriSeq.length();
		int subLen = 12;
				
		System.out.println("There are " + Len + " Bases in the original sequence graphs.");
		System.out.println("Please see BruijinGraph.txt for detailed answer.");

		
		File f=new File("BruijinGraph.txt");
		FileWriter out=new FileWriter(f);
		String[] subSeqs = new String[Len-subLen+1];
		
		for(int i=0; i<=Len-subLen; i++){
			String frontHalf = OriSeq.substring(i, i+subLen-1);
			String backHalf = OriSeq.substring(i+1, i+subLen);
			
			subSeqs[i] = frontHalf +" -> " + backHalf;
			
		}
	
		Arrays.sort(subSeqs);
		
		subSeqs = combineSequences(subSeqs, subLen-1);
		
		for(int i=0; i<=Len-subLen; i++){
			if(subSeqs[i].charAt(0) != ' '){
				out.write( subSeqs[i] +"\n");	
			}
		}
		
		out.close();
		
	
	} // end main();

	private static String[] combineSequences(String[] subSeqs, int subLen) {
		// TO combine duplicates 9-mers;
		int Len = subSeqs.length;
		for(int i=1; i<Len; i++){
			String aftStr = subSeqs[i].substring(0, subLen);
			
			for(int j=0; j<i; j++){
				String befStr = subSeqs[j].substring(0, subLen);
				
				if(befStr.equals(aftStr)){
					subSeqs[j] += "," + subSeqs[i].substring(subLen+4);
					subSeqs[i] ="    "+subSeqs[i];
				}
			}
		}
		
		return subSeqs;
		
	} // end combineSequences() method; 

}
