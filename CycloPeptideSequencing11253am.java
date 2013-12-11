package assignments;

import java.util.Scanner;

public class CycloPeptideSequencing11253am {
	public static void main(String[] args){
		int MM = 92;
		int numOfPep = (int) Math.sqrt(MM) +1; // num of peptes are n*(n-1), here we got MM = (n-1) first;

		int[] nums = new int[MM]; 
		Scanner sc = new Scanner(System.in); 
		System.out.print("Enter 100 numbers on one line with spaces: \n"); 
		sc.useDelimiter(" "); 
		for(int i = 0; i<nums.length; i++){ 
		nums[i] = sc.nextInt(); 
		} 
		
		sc.close();
		
		
		System.out.println("There are " + numOfPep + " peptides on the cyclopeptide ring.");
		for(int i=0; i<MM; i++){
			System.out.print(" " + nums[i]);
			if (i%10 == 0)
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
			//cycloPeptide[i].name += Integer.toString(i);
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
		String[] StrSequences = new String[10]; 
		for(int i=0; i<numOfPep; i++){
			StrSequences[i] = "";
			StrSequences[0] += cycloPeptide[i].name +"*";
		}
		System.out.println("StrSequences[0]=" + StrSequences[0]);
		
		int Len = 1;
		while(Len<10){
			
			System.out.println("Len= " + Len);
			
			for(int stringIndex=0; stringIndex<StrSequences[Len-1].length(); stringIndex += (Len+1)){
				String subStr = StrSequences[Len-1].substring(stringIndex, stringIndex+Len);
				System.out.println("subStr= " + subStr +". ");
				
				
				
				for(int i=0; i<numOfPep; i++){
					
					/*****************
					 * have to make sure seqStr has no duplicate characters in it; 
					 * 
					 */
			//		for(int m=0; m<subStr.length(); m++){
			//			if(subStr.substring(m, m+1).equals(cycloPeptide[i].name)) {
			//				i++;
			//			} else {
							
							String seqStr = subStr + cycloPeptide[i].name;
							
							int Weight = getWeight(seqStr, cycloPeptide);
							
							if(Len<9){
								for(int j=0; j<numOfPep; j++){
									if (Pep2D[Len+1][j] == Weight){
										StrSequences[Len] += (seqStr + "*");

									} // end of if condition; 
								} // end of for j loop;
							} else if(Len>=9){
								if(Weight == nums[MM-1]){
									StrSequences[Len] += (seqStr + "*");
								}
							}


						} // end of for i loop;
							
	//					} // end else;
						
	//				} // end for m loop;
	//				String seqStr = subStr + cycloPeptide[i].name;
					

				
			} // end of for strings in the strSequences[Len-1];
			
			Len ++;
		} // end of while;
		
		System.out.println("StrSequences[10]= " + StrSequences[9]);
		

	}// end of main();

	private static int getWeight(String subStr, cycloPep1125[] cycloPeptide) {
		// TO get the molecular weight of a certain protein sequences;
		int MolWeight=0;
		
		for(int i=0; i<subStr.length(); i++){
		
			MolWeight += cycloPeptide[Character.getNumericValue(subStr.charAt(i))].value;
		//	System.out.println("subStr.charAt=" + subStr.charAt(i) + ". subStr.numic=" + Character.getNumericValue(subStr.charAt(i)) +". MolWei=" + MolWeight +". ");
		}
		
		return MolWeight;
	}

}
