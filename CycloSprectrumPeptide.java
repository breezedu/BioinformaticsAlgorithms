package assignments;

<<<<<<< HEAD


=======
>>>>>>> 241499ba07e252d4f93e8233c8df32a84fc21ec5
public class CycloSprectrumPeptide {
	
	/*******************
	 * Peptide class to store the mol weight and peptide abs
	 * @author GoodDu
	 *
	 */
	
	public static void main(String[] args){
		/***********
		 *  1st, read in the peptides and their mol weight;
		 *  2nd, build cyclo peptides; each elementry will hold an sub-peptide;
		 *  as shown upper in the Peptide class;
		 *  for example, we build a cyclo peptide with 4 elements;
		 *  Peptide[0] ---- Peptide[3];
		 *  Peptide[0].next = 1;
		 *  Peptide[1].next = 2;
		 *  Peptide[2].next = 3;
		 *  Peptide[3].next = 0;
		 *  In this way we close the cycle with Peptide[3].next pointing to the index of Peptide[0];
		 */
		ProteinDic[] Proteins = buildProteinDictionary();
		
		/***********************
		*for(int i=0; i<20; i++){
		*	System.out.print(" " + Proteins[i].name + "  " + Proteins[i].MolWeight +"  ;  ");
		*	if ((i+1)%5 == 0)
		*		System.out.println();
		* }   //new ProteinDic[20] are built; Try to out put all proteins and their molecular weight;
		*/
		String inputProtein = "IVTSE";
		System.out.println("the protein sequence input is: " + inputProtein);
		
		/************
		 * build a inputprotein.lengh() elements cycloPeptide.
		 * assign each inputProtein element to cycloPeptide[i].peptide;
		 * make the cycloPeptide[i].next= i+1; thus each cyclopeptide[i].next will point to the next object
		 * make the last cycloPeptide[Len-1].next = 0; then the last object will point to the first one.
		 * close the cycle.
		 */
		int Len = inputProtein.length();
		
		cycloPeptide[] cyPep = new cycloPeptide[Len];
		
		for(int i=0; i<Len; i++){
			cyPep[i] = new cycloPeptide(); // build Len cycloPeptide() objects. each object means one peptide on the cycle;
			
			cyPep[i].peptide = inputProtein.charAt(i); // assign the Protein sequence to cycloPeptide objects;
			
			/***********************
			 * compare cyPep[i].peptide with proteins[k].name. (check the dictionary)
			 * if cyPep[i].peptide match proteins[k].name, assign the molecular weight to cyPep[i].Mass;
			 */
			for(int k=0; k<20; k++){
				if(cyPep[i].peptide == Proteins[k].name)
					cyPep[i].Mass = Proteins[k].MolWeight;
			} // end inner for loop; 
			
			cyPep[i].Next = i+1; // make each peptide on the cycle point to the next;
		} 
		cyPep[Len-1].Next = 0; // make the last peptide pointing to the first one.
		
		
		for(int i=0; i<Len; i++){
			System.out.println("cypep[" + i +"]=" + cyPep[i].peptide + " MW=" + cyPep[i].Mass + " . next=" + cyPep[i].Next);
		}
		
		/****
		 * try to output all Mers molWeights;
		 * first Mers =0; then Mers=1,,,, till Mers = Lens;
		 * count for each length of Mers, the MolWeight of that sequence;
		 */
			
		for(int Mers = 0; Mers<=Len; Mers++){
			for(int i=0; i<Len; i++){
					int MerWeight = 0;
					int index = i;
				for(int k=0; k<Mers; k++){
					MerWeight += cyPep[index].Mass;
					index = cyPep[index].Next;
					
				} // end of for loop; use this method to add all 3-Mers masses;

				System.out.print("" + MerWeight +" ");
			}

		}

		
	}  // end of main();

	private static ProteinDic[] buildProteinDictionary() {
		// TO build the protein dictionary; with protein ID and related MolWeight;
		ProteinDic[] ProteinID = new ProteinDic[20];
		for(int i=0; i<20; i++){
			ProteinID[i] = new ProteinDic();
		}
		
		ProteinID[0].name = 'G';
		ProteinID[0].MolWeight = 57;
		
		ProteinID[1].name = 'A';
		ProteinID[1].MolWeight = 71;
		
		ProteinID[2].name = 'S';
		ProteinID[2].MolWeight = 87;
		
		ProteinID[3].name = 'P';
		ProteinID[3].MolWeight = 97;
		
		ProteinID[4].name = 'V';
		ProteinID[4].MolWeight = 99;
		
		ProteinID[5].name = 'T';
		ProteinID[5].MolWeight = 101;
		
		ProteinID[6].name = 'C';
		ProteinID[6].MolWeight = 103;
		
		ProteinID[7].name = 'I';
		ProteinID[7].MolWeight = 113;
		
		ProteinID[8].name = 'L';
		ProteinID[8].MolWeight = 113;
		
		ProteinID[9].name = 'N';
		ProteinID[9].MolWeight = 114;
		
		ProteinID[10].name = 'D';
		ProteinID[10].MolWeight = 115;
		
		ProteinID[11].name = 'K';
		ProteinID[11].MolWeight = 128;
		
		ProteinID[12].name = 'Q';
		ProteinID[12].MolWeight = 128;
				
		ProteinID[13].name = 'E';
		ProteinID[13].MolWeight = 129;
		
		ProteinID[14].name = 'M';
		ProteinID[14].MolWeight = 131;
		
		ProteinID[15].name = 'H';
		ProteinID[15].MolWeight = 137;
		
		ProteinID[16].name = 'F';
		ProteinID[16].MolWeight = 147;
		
		ProteinID[17].name = 'R';
		ProteinID[17].MolWeight = 156;
		
		ProteinID[18].name = 'Y';
		ProteinID[18].MolWeight = 163;
		
		ProteinID[19].name = 'W';
		ProteinID[19].MolWeight = 186;
		
		return ProteinID;
	}

}
