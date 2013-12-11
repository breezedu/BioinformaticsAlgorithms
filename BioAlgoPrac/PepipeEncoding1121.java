package assignments;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class PepipeEncoding1121 {
	public static void main(String[] args) throws IOException {
		/*****************************************
		 * 		CODE CHALLENGE: Solve the Peptide Encoding Problem.
		 * 		Sample Input:
		 *      ATGGCCATGGCCCCCAGAACTGAGATCAATAGTACCCGTATTAACGGGTGA
		 *      
		 *      MA     (MA is the protein with two amino acids)
		 *      Sample Output:
		 *      ATGGCC (transfer into  RNA:  AUGGCC)
		 *      GGCCAT (Reverse to CCGGUA==> AUGGCC)
		 *      ATGGCC (repeat sequence, as  AUGGCC)
		 */
	
	String[] dictionary = new String[64]; // Question: do you know why I just arbitary use 64 here?
	int N=0; // use N to store how many elements will be saved to the dictionary.
	
	/***************************************************************************
	 * Read-in the RNA_codon_table, store each RNA-code and protein to a dictionary[i];
	 * these with string.length == 5 are RNA-colons which could product a sub amino acid;
	 * these with string.length == 3 are STOPs.
	 */
	Scanner s = new Scanner(new FileReader("RNA_codon_table_1.txt"));
	while (s.hasNext()) {
	
		dictionary[N] = (s.nextLine());
		N++;
	}
	// check out the value of N, then answer the question upper.
	System.out.println("N= " + N + ". Now you should know why I built 64 objects at the beginning. :)"); 
	

	s.close();
	
	/****************************************************************************************
	 * print out the dictionary string and protein they control as well as the string.length.
	 */
	System.out.println("The dictionary RNA colons and the protein they produces are put into dictionary.");
	
	RNAtoPro[] RnaProtein = new RNAtoPro[N];
	for(int i=0; i<N; i++){
		RnaProtein[i] = new RNAtoPro();
		// System.out.print("\n  Assign tem-String to RnaProtein: "+dictionary[i] +";  " + dictionary[i].substring(0, 3));
		
		if(dictionary[i].length() >=5){
			RnaProtein[i].RNA = dictionary[i].substring(0, 3);
			RnaProtein[i].Pro = dictionary[i].charAt(4);
		} else {
			RnaProtein[i].RNA = dictionary[i].substring(0, 3);
		}
	}
	
	System.out.println("RNA dictionary is ready.\n ..*_*..");

	/******************************
	 * readin peptide_encoding_data, store the string sequence into NewStr.
	 * Then input a sequence of protein to check.
	 * every loop, get a certain length of (protein sequence * 3) RNA string.
	 * transfer the RNA string into a protein String.
	 * if the protein sequence equals to the input protein sequence.
	 * Print out the original RNA strings.
	 */
	Scanner readStr = new Scanner(new FileReader("1122.txt"));
	String DNAStr = readStr.useDelimiter("\\A").next();
	readStr.close();
	
	// String NewStr = DNAtoRNA(DNAStr);
	
	String InputStr = "QSGQKVQY";
	System.out.println("input protein sequence is: " + InputStr);
	System.out.println("There are " + DNAStr.length() +" bases in the oringal DNA string." );
	System.out.println("the first 50 bases of original DNA string are: " + DNAStr.substring(0, 50) +"\n");
	
	
	// System.out.println("Below are the original DNA sub sequences which could product the input Protein:");
	int InputStrLen = InputStr.length();
	int newStrLen = DNAStr.length();
		
	for(int i=0; i<= newStrLen-3*InputStrLen; i++){
		String subStr = DNAStr.substring(i, i+3*InputStrLen);
		
		String RNAsubStr = DNAtoRNA(subStr);
		
		String ProteinSeq =transRNAtoProtein(RNAsubStr, RnaProtein, N);
		
		if(ProteinSeq.equals(InputStr)){
			
			System.out.println("original Sequence: " + subStr);
			
		} // end if
		
		String RevsubStr = reverseDNAtoRNA(subStr);
		String revProteinSeq = transRNAtoProtein(RevsubStr, RnaProtein, N);
		
		if(revProteinSeq.equals(InputStr)){
			System.out.println("reverse  Sequence: " + subStr);
		}

			
		} // end outter for loop;
	
	
	} // end main();

	private static String reverseDNAtoRNA(String subStr) {
		// TO transfer original RNA string into reversed String;
		String revStr = "";
		int Len = subStr.length();
		for(int i=1; i<=Len; i++){
			if(subStr.charAt(Len-i) == 'A'){
				revStr += "U";
			} else if(subStr.charAt(Len-i) == 'T'){
				revStr += "A";
			} else if(subStr.charAt(Len-i) == 'C'){
				revStr += "G";
			} else revStr += "C";
		}
		return revStr;
	}

	private static String DNAtoRNA(String dnaStr) {
		// TO transfer DNA string into RNA string;
		/***********
		 * transfer: ATG --> AUG
		 */
		int M = dnaStr.length();
		String rnaStr = "";
		for(int i=0; i<M; i++){
			if(dnaStr.charAt(i) == 'A'){
				rnaStr += "A";
			} else if(dnaStr.charAt(i) == 'G'){
				rnaStr += "G";
			} else if(dnaStr.charAt(i) == 'C'){
				rnaStr += "C";
			} else rnaStr += "U";
		}
		return rnaStr;
	}

	private static String transRNAtoProtein(String temSubStr, RNAtoPro[] RnaProtein, int N) {
		// TO transfer RNA strings into proteins;
		String ProteinSeq = "";
		// System.out.println("temSubStr= " + temSubStr +". temSubStr.length=" + temSubStr.length() + ". N= " + N);
		for(int i=0; i<temSubStr.length(); i+=3){
			String subStr = temSubStr.substring(i, i+3);
		//	 System.out.print(" " + subStr);
			
			for(int j=0; j<N; j++){
				if (subStr.equals(RnaProtein[j].RNA)) {
					ProteinSeq += RnaProtein[j].Pro;
				}
			} // end inner for loop;
		} 
		
		// System.out.println("  protein sequence is: " + ProteinSeq);
		
		return ProteinSeq;
		
	} // end of transRNAtoProtein() method; 

}
