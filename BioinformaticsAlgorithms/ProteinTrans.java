package assignments;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;



public class ProteinTrans {
	
	
	public static void main(String[] args) throws IOException {
	
	String[] dictionary = new String[100];
	int N=0;
	
	Scanner s = new Scanner(new FileReader("RNA_codon_table_1.txt"));
	while (s.hasNext()) {
	
		dictionary[N] = (s.nextLine());
		N++;
	}

	s.close();
	for(int i=0; i<N; i++){
		System.out.println("["+dictionary[i]+"]  length: " + dictionary[i].length() +".");
	}
	
	RNAtoPro[] RnaProtein = new RNAtoPro[N];
	for(int i=0; i<N; i++){
		RnaProtein[i] = new RNAtoPro();
		System.out.print("\n  Assign tem-String to RnaProtein: "+dictionary[i] +";  " + dictionary[i].substring(0, 3));
		
		if(dictionary[i].length() >=5){
			RnaProtein[i].RNA = dictionary[i].substring(0, 3);
			RnaProtein[i].Pro = dictionary[i].charAt(4);
		} else {
			RnaProtein[i].RNA = dictionary[i].substring(0, 3);
		}
	}
	
	System.out.println("\n ..*_*..");
	for(int i=0; i<N; i++){
		System.out.println(" " + RnaProtein[i].RNA +"    " + RnaProtein[i].Pro);
	}
	
	String NewStr = "AUGACAUACUCCGUCGAUGGUCGCGUUAAGAUAUUUGCGACCAUCUCCUGGCUUCGGAGAAAAGGGAUAGUUCGCAGUUGG"
			+ "ACUCCUGACCAUCGUGGAUUUAGCCGCCUAUCAAGCCAUCUGAAAGUGCACUUCUACAGUAAAUCGAAUGACCGGGAGACUUUUGAC"
			+ "GACCGCGUCGAUCAUCACUUUAUUGCAGUACCAAAGUCUCCACUCGUCUCUCCGCAGCAUAAUGGUACUUGUGAGUACCGACCACGA"
			+ "CGGGCAGCCUUAAACAGAGUGUGGGGGUAUUUAAGCCUGAGAUCAAGAGGCGCGAUCCGCGCAGCUAUCCUUCCAGAGGCCCUACAC"
			+ "ACGCACGGAGCAACUUUCGUCUCCCCCGCUGUUUUAAUAGUACCGCAGUCACAUCGCUGCACGGUAUACCGGGCGUAUAGGGUUUUUC"
			+ "CUUGUUCCCGUCAUUUAAGGCUCUACCAUAACUGUGCGUUUGUCGGACGGUCGCCCAUUAUCAUGCAAAUGAAUGAUAAUAUCUAUGA"
			+ "GCUCUACUGGUCAGUUAAACUCUUGUCGAAAGAUGGGUAUACUAGAACUCUGCGAAUCCAACCGCAGGACAGAUAUAGGCACAAGGAU"
			+ "AGUACGGUUCAACCACAGCGCCCUGUCUUGCUAGCCCGAAUUAAUAGCCUGUUACGCGGUCGUUCACAAGAGAUGACCUAUGGGAUUU"
			+ "GCUUUUAUAAUGGCUAUCAGGUUUUAGGGGGUAGCUCUUCCGCUUCGCCGAUAAUCGAAUUGUUUGCGCACUCCCUCAUAAUAAACAA"
			+ "AGGAACGCAGUUAUGGGCGGGUCGGAUAAGAUUGCGCGAUGUAAGUGCUGAUAUAUCGCUGCAGCGCGCAAGGUGUCAAUGGGGGAUC"
			+ "AUCUGUACGGGCCUUUGCGACCGAGGCGUUACUAUAAGCUAUUUCUCACAUCGUCGUAUAACGGCAGCUAGCUUUCAGAGAACACGCCG"
			+ "ACGUCAGGGAUUAUACUUCUAUAUACCCCUGAACACUUUUCCCGCCGUCUUCACGGGGUACAACCUGAGUUGGCGGCAGCCUUCCCGCC"
			+ "AAGAGGCUUCCAGCGUCGAGCGAAGUGCAUGGAACAUGCCAACGCACGCAUGUGCUACCUCUGACGUAAAAUGGACCGAUUUCGGCCAA"
			+ "GCUCCGCAUUGGCGGCUAACGCGCAACUUUCCUACAUGUUCUAGGCACAGUAGACACAAUUCCCAUUCGUUCAGUAUAGGAAAUAGCGG"
			+ "GUUCGCAUGGGAAGACUACAGGCGCUGUCUUUUCCCUGAGCUUCUCUUGCGUGUAAUAUGCUUGAUACUCCUAGAUUUUAUCACUGGAA"
			+ "CCGUAAGACUUUCGACUAUAGUUCUAUUAAACGUUUGGCACGUGUCAAUGGAACCUCUAUCACAUGCACAGAGACAAGCCCGAACGAGGA"
			+ "CGACCAAAAGUGCAGGUUAUUCAGCCCGCAUGUUUGAACUUUUAACGAUGAGGGAUCUCUCGCCAGGAUGUGUAAUGAUCACACCUCAAG"
			+ "GCAGUAACUUAGAGCACUUGGGCACGGAUCGCACUGGUUGGGUGAGAUAUGCACGGCGUUUUAUGCCAACUGAACCGGUUCUUCGCUUCC"
			+ "GCGUAGUUGCCAUCCCGUGUAACUGCGAGCUUAGUCGUUCGGACGACCCACGAUUGCCUUAUUUCAGAGAUCGGAGGUUCGUAUUAUCCG"
			+ "UUAUCUGUAGUGAUCCGUCAAUGCCCCGAGCGCUCCACGAGAUCUCUCGGUCAUUCGGACUUACUUUGUCAUUUUUGUCCCUGCGGCACG";
	String ProteinSeq ="";
	for(int i=0; i<NewStr.length()-3; i+=3){
		String temSubStr = NewStr.substring(i, i+3);
		System.out.println(" temSubStr: " +temSubStr);
		
		for(int j=0; j<N; j++){

			if (temSubStr.equals(RnaProtein[j].RNA)) {
				ProteinSeq += RnaProtein[j].Pro;
				System.out.print(" we got one match: " + RnaProtein[j].RNA +" and "+ RnaProtein[j].Pro +". " );
			}
		} // end inner for loop;
			
		} // end outter for loop;
	
	
	System.out.println("\nAfter checking RNA dictionary, the protein sequence is:\n " + ProteinSeq);

	
	} // end main();


}