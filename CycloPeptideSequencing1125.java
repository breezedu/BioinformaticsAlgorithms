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

public class CycloPeptideSequencing1125 {
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
		
		for(int i=0; i<numOfPep; i++){
			int count=0;
			System.out.println("i=" + i+ ". ");
			for(int j=0; j<numOfPep; j++){
				int temp = Pep2D[2][j] - cycloPeptide[i].value;
				for(int k=0; k<numOfPep; k++){
					if (temp == cycloPeptide[k].value && k!=i){
						count++;
						System.out.print("cycloPeptide[" +i+"]= "+ cycloPeptide[i].value +" + cyclpPeptide[" +k+"]= "+
											cycloPeptide[k].value +" = Pep2D[2]["+j+"] =" + Pep2D[2][j] +"\n");
					}	
						
				} // end for k loop;
				
			} // end of j loop;
			
			System.out.println("cycloPeptide["+i +"] has " + count +" neighbors. ");
			
			if(count==2){
				System.out.println("Two Neighbors!");
				findNeighbor(Pep2D, cycloPeptide, i, numOfPep);
				
				System.out.println("confirmed neighbor: left=" + cycloPeptide[i].left +". right=" + cycloPeptide[i].right);
				
			} // end if (count ==2);
			
		} // end for i loop;
		System.out.println("The neighbors of cycloPeptide[0] are: left=" + cycloPeptide[0].left + " and right=" + cycloPeptide[0].right);
		
		
		System.out.println();
		for(int i=0; i<numOfPep; i++){
			System.out.println("cycloPeptide[" + i +"].value=" + cycloPeptide[i].value +". left=" 
								+ cycloPeptide[i].left +". right=" + cycloPeptide[i].right);
		}
		
		/******************
		 * Here we figure out there are only two nodes with two neighbors;
		 * next we have to figure out what are another two nodes besides these 3-nodes in a row.
		 */

		for(int i=0; i<numOfPep; i++){
			if(cycloPeptide[i].left!=i && cycloPeptide[i].right!=i){
				int sum3 = 0;
				int left = cycloPeptide[i].left;
				int right = cycloPeptide[i].right;
				sum3 += cycloPeptide[i].value + cycloPeptide[left].value + cycloPeptide[right].value;
				System.out.println("sum3 = " + sum3);
				int count = 0;
				for(int j=0; j<numOfPep; j++){
					int temp = Pep2D[4][j] - sum3;
					for(int k=0; k<numOfPep; k++){
						if (temp == cycloPeptide[k].value){
							count ++;
							System.out.print("k= " + k +". temp= " + temp +" ");
							int sumR3 = cycloPeptide[i].value + cycloPeptide[right].value + cycloPeptide[k].value;
							for(int m=0; m<numOfPep; m++){
								if(Pep2D[3][m] == sumR3)
									System.out.println("cycloPeptide["+k+"] might be one the right side;");
							} // end for m loop;
							int sumL3 = cycloPeptide[i].value + cycloPeptide[left].value + cycloPeptide[k].value;
							for(int m=0; m<numOfPep; m++){
								if(Pep2D[3][m] == sumL3)
									System.out.println("cycloPeptide["+k+"] might be one the left side;");
							} // end for m loop;
						}
					}
				}
				System.out.println("there are " + count + " neighbors near cycloPeptide["+i+"] and " + left +" " + right);
				
			} // end if nodes have two neighbors.
		} // end for i loop;

	}// end of main();

	private static void findNeighbor(int[][] Pep2D, cycloPep[] cycloPeptide, int M, int numOfPep) {
		// TO confirm the two neightbors of cycloPeptide[i];
		
		for(int i=0; i<numOfPep; i++){
			int temp = Pep2D[2][i] - cycloPeptide[M].value;
			for(int j=0; j<numOfPep; j++){
				if (temp == cycloPeptide[j].value && cycloPeptide[M].left==M){
					System.out.println("cycloPeptide[" + j +"] and cycloPeptide[" +M +"]. ");
					cycloPeptide[M].left = j;
					//cycloPeptide[j].right = M;
				} else if(temp == cycloPeptide[j].value && cycloPeptide[M].left!=M){
					System.out.println("cycloPeptide[" + j +"] and cycloPeptide[" +M +"]. ");
					cycloPeptide[M].right = j;
					//cycloPeptide[j].left = M;
				}
					
			}
		}
		
		
	} // end findNeightbor() method.

}
