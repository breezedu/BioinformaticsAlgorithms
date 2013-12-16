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

public class CycloPeptideSequencing1124 {
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
		
		
		/*******************************
		 * build a new CIRCLE of cycloPeptide[] objects, 
		 * to store the MolWeight of each peptide on the cycloPeptide;
		 * cycloPeptide[i].right and cycloPeptide[i].left both pointing to itself; 
		 */
		cycloPep[] cycloPeptide = new cycloPep[numOfPep]; // define a new array to story the cycloPeptide; 
		for(int i=0; i< numOfPep; i++){
			cycloPeptide[i] = new cycloPep();
			cycloPeptide[i].value = Pep2D[1][i];
			cycloPeptide[i].left = i;
			cycloPeptide[i].right = i;
		}
		
		// Pep2D[1][0] = -1;
		int count =0; // use count to calculate how many matches we got;
		int index = 0;
		for(int j=0; j<numOfPep; j++){
			int temp = Pep2D[2][j] - cycloPeptide[index].value;
			
			for(int k=0; k<numOfPep; k++){
				if(temp == cycloPeptide[k].value){
					//Pep2D[2][j] = 0;

					if(cycloPeptide[index].right==index){
						cycloPeptide[index].right = k;
						cycloPeptide[k].left = index;
					} else if (cycloPeptide[index].left == index){
						cycloPeptide[index].left = k;
						cycloPeptide[k].right = index;
					}
				
				} // end if;
			} // end for k loop				
		} // end for j loop;
		System.out.print("\n index = " + index);
		index = cycloPeptide[index].right;
		
		while(count <numOfPep){
			System.out.print("\n index = " + index);

			
			for(int i=0; i<numOfPep; i++){
				int temp = Pep2D[2][i] - cycloPeptide[index].value;
				for(int k=0; k<numOfPep; k++){
					if(temp == cycloPeptide[k].value && cycloPeptide[k].left==k){ 
						System.out.print(". Pep2D[2][" +i+"]=" + Pep2D[2][i]);
					//	cycloPeptide[index].value = 10000;
						cycloPeptide[index].right = k;
						cycloPeptide[k].left = index;
					}
					
					// end if;
				} // end for k loop;
				
			} // end for i loop;
			count++;
			index = cycloPeptide[index].right;

		} // end while loop;
		
		System.out.println();
		for(int i=0; i<numOfPep; i++){
			System.out.println("cycloPeptide[" + i +"].value=" + cycloPeptide[i].value +". left=" 
								+ cycloPeptide[i].left +". right=" + cycloPeptide[i].right);
		}
		
		index = 0;
		count =0;
		while(count<numOfPep){
			System.out.print(cycloPeptide[index].value + "-");
			index = cycloPeptide[index].right;
			count++;
		}

	}// end of main();

}
