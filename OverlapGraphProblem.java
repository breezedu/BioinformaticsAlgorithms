package assignments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class OverlapGraphProblem {
	
	public static void main(String[] args) throws IOException{
		
		Scanner ScanLines = new Scanner(new File("dataset_52_7.txt"));
		ArrayList<String> lines = new ArrayList<String>();
		
		while(ScanLines.hasNextLine()){
			lines.add(ScanLines.nextLine());
		}
		
		ScanLines.close();
		
		String[] oriStr = lines.toArray(new String[0]);
		int Num = oriStr.length;
		int Len = oriStr[0].length();
		
		Arrays.sort(oriStr);
		
		System.out.println("There are " + Num + " lines in the original sequence graphs.");
		System.out.println("The first string[] is " + oriStr[0]);
		System.out.println("The last string[] is " + oriStr[Num-1]);
		
		
		File f=new File("OvrelapGraph.txt");
		FileWriter out=new FileWriter(f);
	
		for(int i=0; i<Num; i++){
			String oriSubStr = oriStr[i].substring(1);
			
			for(int j=0; j<Num; j++){

				String tempStr = oriStr[j].substring(0, Len-1);
				if(oriSubStr.equals(tempStr) && i!=j){
					out.write(oriStr[i] + " -> " + oriStr[j] +"\n");			
					System.out.println(oriStr[i] + " -> " + oriStr[j]);
				}
			} // end for j<Num loop

		} // end for i<Num-1 loop;
		
		out.close();
		
	
	} // end main();

}
