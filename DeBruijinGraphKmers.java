package assignments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*****************
 * 
 * @author Frog;
 * DeBruijn Graph from k-mers Problem: Construct the de Bruijn graph from a set of k-mers.
 * Input: A collection of k-mers Patterns.
 * Output: The adjacency list of the de Bruijn graph DeBruijn(Patterns).
 * 
 * 
 * CODE CHALLENGE: Solve the de Bruijn Graph from k-mers Problem.
 * 
 * Sample Input:
 *     GAGG
 *     GGGG
 *     GGGA
 *     CAGG
 *     AGGG
 *     GGAG
 *     
 *     Sample Output:
 *     
 *     AGG -> GGG
 *     CAG -> AGG
 *     GAG -> AGG
 *     GGA -> GAG
 *     GGG -> GGA,GGG
 *
 */

public class DeBruijinGraphKmers {
	
	public static void main(String[] args) throws IOException{
		
		Scanner ScanLines = new Scanner(new File("dataset_54_7.txt"));
		ArrayList<String> lines = new ArrayList<String>();
		
		while(ScanLines.hasNextLine()){
			lines.add(ScanLines.nextLine());
		}
		
		ScanLines.close();
		
		String[] oriStr = lines.toArray(new String[0]);

		ScanLines.close();
		
		int Num = oriStr.length;
		
		int Len = oriStr[0].length();
				
		System.out.println("There are " + Num + " Sequences in the original " +Len+ " bases graphs.");

		
		File f=new File("BruijinGraphKmers.txt");
		FileWriter out=new FileWriter(f);
		
		String[] subSeqs = new String[Num];
		
		for(int i=0; i<Num; i++){
			String frontHalf = oriStr[i].substring(0, Len-1);
			String backHalf = oriStr[i].substring(1);
			
			subSeqs[i] = frontHalf +" -> " + backHalf;
			
		}
	
		Arrays.sort(subSeqs);
		
		subSeqs = combineSequences(subSeqs, Len-1);
		
		for(int i=0; i<Num; i++){
			System.out.println( subSeqs[i]);
			
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
					subSeqs[i] ="    XXXXXXXXXX"+subSeqs[i];
					
				} // end if 
				
			} // end j<i loop
		} // end for i<Len loop
		
		return subSeqs;
		
	} // end combineSequences() method; 

}
