package assignments;

/************************************8
 * CODE CHALLENGE: Implement CYCLOPEPTIDESEQUENCING (pseudocode reproduced below).
 * Note: After the failure of the first brute-force algorithm we considered, 
 * you may be hesitant to implement this algorithm for fear that its runtime will be prohibitive. The potential problem with CYCLOPEPTIDESEQUENCING is that it may generate incorrect k-mers at intermediate stages (i.e., k-mers that are not subpeptides of a correct solution). You may wish to wait to implement CYCLOPEPTIDESEQUENCING until after the next section, where we will analyze this algorithm.
 *     CYCLOPEPTIDESEQUENCING(Spectrum)
 *     List ¡û {0-peptide}
 *     while List is nonempty
 *     	List ¡û Expand(List)
 *     		for each peptide Peptide in List
 *           if Cyclospectrum(Peptide) = Spectrum
 *                        output Peptide
 *                               remove Peptide from List
 *              else if Peptide is not consistent with Spectrum
 *           	       remove Peptide from List
 *           
 *   Sample Input:
 *   0 113 128 186 241 299 314 427
 *   
 *   Sample Output:
 *   186-128-113 186-113-128 128-186-113 128-113-186 113-186-128 113-128-186
 *   
 */
import java.util.Scanner;

public class CycloPeptideSequencing112510am {
	public static void main(String[] args){
		int MM = 92;
		int numOfPep = (int) Math.sqrt(MM) +1; // num of peptes are n*(n-1), here we got MM = (n-1) first;

		int[] nums = new int[MM]; 
		Scanner sc = new Scanner(System.in); 
		System.out.print("Enter *** numbers on one line with spaces: \n"); 
		sc.useDelimiter(" "); 
		for(int i = 0; i<nums.length; i++){ 
		nums[i] = sc.nextInt(); 
		} 
		
		sc.close();
		
		
		System.out.println("There are " + numOfPep + " peptides on the cyclopeptide ring.");
		for(int i=0; i<MM; i++){
			System.out.print(" " + nums[i]);
			if (i% (numOfPep-1) == 0)
				System.out.println();
		} // end of for i loop;
		System.out.println();
		
		
		int[][] Pep2D = new int[numOfPep][numOfPep];
		for(int i=1; i<numOfPep; i++){
				System.out.print(" i= " + i + " ");	
				
				for (int j=0; j< numOfPep; j++){
					Pep2D[i][j] = nums[(i-1)*numOfPep + j +1];
				
					System.out.print(Pep2D[i][j] + "  ");
				}
				System.out.println();
		} // end for i loop, build a 2D array Pep2D to store all a[1][0--9] till a[9][0--9]; 
		for(int i=1; i<numOfPep; i++){
			Pep2D[numOfPep-1][i] = nums[MM-1];
		}
		
		/*******************************
		 * build a new CIRCLE of cycloPeptide[] objects, 
		 * to store the MolWeight of each peptide on the cycloPeptide;
		 * cycloPeptide[i].right and cycloPeptide[i].left both pointing to itself; 
		 */
		cycloPep1125[] cycloPeptide = new cycloPep1125[numOfPep]; // define a new array to story the cycloPeptide; 
		for(int i=0; i< numOfPep; i++){
			cycloPeptide[i] = new cycloPep1125();
			cycloPeptide[i].value = Pep2D[1][i];
		//	cycloPeptide[i].name += Integer.toString(i);
			cycloPeptide[i].next = i;
			
			System.out.println("cycloPeptide[" + i+"]. value=" + cycloPeptide[i].value +" name=" + cycloPeptide[i].name +" next=" + cycloPeptide[i].next);
		} 


		/************
		 * initial an array of strings from StrSequences[0] to StrSequences[9];
		 * StrSequences[0] will store all protein sequences in the mass spectrum with only 1 protein unit;
		 * StrSequences[1] will store all protein sequences in the mass spectrum with two protein units;
		 * etc, etc, etc, etc, etc;
		 * StrSequences[9] will store one chain of numOfPep protein sequence with all protein unites in the spectrum;
		 */
		String[] StrSequences = new String[numOfPep]; 
		for(int i=0; i<numOfPep; i++){
			StrSequences[i] = "";
	
		}
		StrSequences[0] += cycloPeptide[0].name +"*";
		
		System.out.println("StrSequences[0]=" + StrSequences[0]);
		
		int Len = 1;
		while(Len<numOfPep){
			
			System.out.print("(Length of the sequence) Len= " + Len);
			System.out.println(" Strseq[Len-1]= " + StrSequences[Len-1]);
			
			for(int stringIndex=0; stringIndex<StrSequences[Len-1].length(); stringIndex += (Len+1)){
				String subStr = StrSequences[Len-1].substring(stringIndex, stringIndex+Len);
			//	System.out.println("subStr= " + subStr +". ");

				for(int i=0; i<numOfPep; i++){
					
					/*****************
					 * have to make sure seqStr has no duplicate characters in it; 
					 * 
					 */
					String temp = "" + cycloPeptide[i].name;
					for(int k=0; k<subStr.length(); k++){
	
						if(subStr.substring(k, k+1).equals(temp))
							temp="";
					}
					
					String seqStr = subStr + temp;
					/**********
					 * hide substring here; 
					 */
					// System.out.println("subStr=" + subStr +" ");
					
					int Weight = getWeight(seqStr, cycloPeptide);
							
					if(Len<numOfPep-1){
						for(int j=0; j<numOfPep; j++){
							if (Pep2D[Len+1][j] == Weight && !StrSequences[Len].contains(seqStr)){
								
								// Only when there's no duplicate string in the StrSequences[Len], will we save the seqStr into StrSequences[Len];
								StrSequences[Len] += (seqStr + "*"); 

								} // end of if condition; 
							} // end of for j loop;
						
						} else if(Len>=(numOfPep-1)){
							if(Weight == nums[MM-1]){
								StrSequences[Len] += (seqStr + "*");
							}
						} // end if (Len<9); 

				} // end of for i loop;
											
			} // end of for strings in the strSequences[Len-1];
			
			Len ++;
		} // end of while;
		
		System.out.println("nums[MM-1] = " + nums[MM-1]);
		
		System.out.println("StrSequences["+(numOfPep-1)+"]=" + StrSequences[numOfPep-1] +". "
							+ "\n There are only few sequences left. "
							+ "the last step is the check whether each sequence makes sense;");
		
		/***********
		 * check each sub sequence at the last StrSequences[9].
		 * To see which one meet all the Mass spectrum requirment;
		 */
		for(int i=0; i<StrSequences[numOfPep-1].length(); i+=(numOfPep+1)){
			String subStr = StrSequences[numOfPep-1].substring(i, i+numOfPep);
			int count = checkValid(subStr, Pep2D, numOfPep, cycloPeptide);
			if (count>=(numOfPep*2-3)){ // like when numOfPep = 10, check count>=17)
				System.out.println("final subStr=" + subStr +". ");
				
				int last = 0;
				for(int m=0; m<subStr.length()-1; m++){
					int index = Character.getNumericValue(subStr.charAt(m));
					int next = Character.getNumericValue(subStr.charAt(m+1));
					cycloPeptide[index].next = next;
					last = next;
					
					System.out.println("cycloPeptide[" + index +"].next = " + cycloPeptide[index].next +". ");
				}
				cycloPeptide[last].next = Character.getNumericValue(subStr.charAt(0));
				System.out.println("cycloPeptide["+last +"].next = " + cycloPeptide[last].next +". ");
				
				System.out.println("OK, now the cycloPeptide is closed with full elements!");
				//break; // we just need one sequence here in this step;
				
			} // end if count>=17;

		} // end for i loop; end printing 10 sequence cycloPeptides;
		
		
		/*****
		 * now try to out put all possible sequences:
		 */
		for(int i=0; i<numOfPep; i++){
			int circle =0;
			int index = i;
			int last = i;
			while(circle<(numOfPep-1)){
				
				System.out.print(cycloPeptide[index].value +"-");
				last = index = cycloPeptide[index].next;
				circle++;
			} // end while;
			System.out.print(cycloPeptide[last].value +" ");

		} // end for loop;

	}// end of main();

	private static int checkValid(String finalStr, int[][] pep2d, int numOfPep, cycloPep1125[] cycloPeptide) {
		// TO check if the final sequence fit the mass spectrum requirement;
		int count2 = 0;
		int count3 = 0;
		for(int i=0; i<finalStr.length()-1; i++){

			String sub2 = finalStr.substring(i, i+2);
			// System.out.print(" " + sub2);
			int subWei = getWeight(sub2, cycloPeptide);
			for(int j=0; j<numOfPep; j++){
				if(subWei == pep2d[2][j])
					count2++;
			}
			
		} // end for i loop;
		
		for(int i=0; i<finalStr.length()-2; i++){

			String sub3 = finalStr.substring(i, i+3);
			//System.out.print(" " + sub3);
			int subWei = getWeight(sub3, cycloPeptide);
			for(int j=0; j<numOfPep; j++){
				if(subWei == pep2d[3][j])
					count3++;
			}
			
		} // end for i loop;

		return count2+count3;
	}

	private static int getWeight(String subStr, cycloPep1125[] cycloPeptide) {
		// TO get the molecular weight of a certain protein sequences;
		int MolWeight=0;
		
		for(int i=0; i<subStr.length(); i++){
			// System.out.println("subStr.charAt=" + subStr.charAt(i) + ". subStr.numic=" + Character.getNumericValue(subStr.charAt(i)) +". MolWei=" + MolWeight +". ");
			MolWeight += cycloPeptide[Character.getNumericValue(subStr.charAt(i))].value;
		
		}
		
		return MolWeight;
	}

}
