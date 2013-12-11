package assignments;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PepipeEncoding {
	public static void main(String[] args) throws IOException {
		/*****************************************
		 * CODE CHALLENGE: Solve the Peptide Encoding Problem.
		 * Sample Input:
		 *      ATGGCCATGGCCCCCAGAACTGAGATCAATAGTACCCGTATTAACGGGTGA
		 *      MA
		 *      Sample Output:
		 *      ATGGCC
		 *      GGCCAT
		 *      ATGGCC
		 */
	
	String[] dictionary = new String[100];
	int N=0;
	
	/***************************************************************************
	 * Readin the RNA_codon_table, store each rna-code and protein to a dictionary[i];
	 * these with string.length == 5 are rna-codons which could product a sub amino acid;
	 * these with string.length == 3 are STOPs.
	 */
	Scanner s = new Scanner(new FileReader("RNA_codon_table_1.txt"));
	while (s.hasNext()) {
	
		dictionary[N] = (s.nextLine());
		N++;
	}

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
	
	System.out.println("\n RNA dictionary is ready.\n ..*_*..");

	/******************************
	 * readin peptide_encoding_data, store the string sequence into NewStr.
	 * Then input a sequence of protein to check.
	 * every loop, get a certain length of (protein sequence * 3) RNA string.
	 * transfer the RNA string into a protein String.
	 * if the protein sequence equals to the input protein sequence.
	 * Print out the original RNA strings.
	 */
	Scanner readStr = new Scanner(new FileReader("peptide_encoding_data.txt"));
	String DNAStr = readStr.useDelimiter("\\A").next();
	readStr.close();
	
	// String NewStr = DNAtoRNA(DNAStr);
	
	String InputStr = "KEVFEPHYY";
	System.out.println("input protein sequence is: " + InputStr);
	System.out.println("the first 20 bases of new String are: " + DNAStr.substring(0, 20) +"\n");
	
	int InputStrLen = InputStr.length();
	int newStrLen = DNAStr.length();
		
	for(int i=0; i<= newStrLen-3*InputStrLen; i++){
		String subStr = DNAStr.substring(i, i+3*InputStrLen);
		
		String RNAsubStr = DNAtoRNA(subStr);
		
		String ProteinSeq =transRNAtoProtein(RNAsubStr, RnaProtein, N);
		
		if(ProteinSeq.equals(InputStr)){
			
			System.out.println("OneMatch:  " + subStr);
			
		} // end if
		
		String RevsubStr = reverseDNAtoRNA(subStr);
		String revProteinSeq = transRNAtoProtein(RevsubStr, RnaProtein, N);
		
		if(revProteinSeq.equals(InputStr)){
			System.out.println("One ReverseRNA match: " + subStr);
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
