package assignments;

import java.util.Hashtable;
import java.util.Scanner;

/***********
 * Use Dynamic Programming method to solve the Change problem
 * 
 * DPCHANGE(money, coins)
 * MinNumCoins(0) ¡û 0
 * for m ¡û 1 to money
 *       MinNumCoins(m) ¡û ¡Þ
 *       for i ¡û 1 to |coins|
 *           if m ¡Ý coini
 *               if MinNumCoins(m - coini) + 1 < MinNumCoins(m)
 *                   MinNumCoins(m) ¡û MinNumCoins(m - coini) + 1
 *   output MinNumCoins(money) and the amount for each coins need;
 *   
 *   input:
 *   40
 *   50,25,20,10,5,1
 *   
 *   Output:
 *   The minimum coins need for 40 are 2
 *   The coins needed for each value are:
 *   $50 0;  $25 0;  $20 2;  $10 0;  $5 0;  $1 0; 
 */

public class MinCoinsChangedSteps {
	
	public static void main(String[] args){
		/******
		 * Section I;
		 * 1st, readIn the total money, and the coins we have;
		 */
		System.out.println("DPMoneyChange Problem, Please input the total money and coins you have:");
		Scanner input = new Scanner(System.in);
		int Total = input.nextInt();
		
		String CoinsStrInput = input.next();
		input.close();
		
		String[] CoinsStr = CoinsStrInput.split(",");
		
		int Length = CoinsStr.length;
		int[] Coins = new int[Length];
		
		for(int i=0; i<Length; i++){
			
			Coins[i] = Integer.parseInt(CoinsStr[i]);
		} // end for i<Length; all coinsStr are parsed into integers;
		
		System.out.println("The total money is:" + Total);
		System.out.println("We have " + Length +" different coins.");
		for(int i=0; i<Length; i++){
			
			System.out.print(" " + Coins[i]);
		} // end for i<Length loop; value of different coins we have were printed;
		
		System.out.println("\nFinished input section.");
		
		/********
		 * use a iteration method changeMoney()
		 * to get the minimum coins for every amount of money;
		 */
		changeMoney(Total, Coins);
		
		
	} // end main();

	private static void changeMoney(int total, int[] coins) {
		// TODO A recursion method to calculate min coins needed;
		
		int[] minCoins = new int[total+1];
			int numOfCoins = coins.length;
		
		@SuppressWarnings("unchecked")
		Hashtable<Integer, Integer>[] listCoins = new Hashtable[total+1];
		for(int i=0; i<=total; i++){
			listCoins[i] = new Hashtable<Integer, Integer>();
			for(int j=0; j<numOfCoins; j++){
				
				listCoins[i].put(coins[j], 0);
			}
		} // end outer for i<=total loop;
		
		minCoins[0] = 0;
		//listCoins[0].put(0, 0);
		
	
		
		for(int i=1; i<=total; i++){
			
			minCoins[i] = i; // this is the worst case; change with penny only;
			listCoins[i].put(1, i); 
			
			for(int j=0; j<numOfCoins; j++){
				if(i>= coins[j]){ // ? 
					
					if(minCoins[i - coins[j]] + 1 < minCoins[i]){
						
						minCoins[i] = minCoins[i-coins[j]] + 1;
						
						// add another coin, which should be coins[j] to the listCoins[i-coins[j]] hashtable;
					//	System.out.print(" put 1 more coins["+j+"]=" +coins[j]+" to hashtable listCoins["+i+"]." );

						for(int c=0; c<numOfCoins; c++){

							listCoins[i].put(coins[c], listCoins[i-coins[j]].get(coins[c]));
						}
						
						listCoins[i].put(coins[j], listCoins[i-coins[j]].get(coins[j])+1);
					
					} // end inner if condition;
									

				} // end if i>=coins[j] condition;
				

			} // end for j<numOfCoins loop; a loop for every kind of coin;
			
			System.out.print("\nThe minimum coins need for " + i +" are " + minCoins[i] +". ");
		//	printHashTable(listCoins[i], coins, numOfCoins);
			
		} // end for i<=total loop; a loop for every money;
		
		System.out.println(":) \nThe minimum coins need for " + total +" are " + minCoins[total]);
		
		System.out.println("The coins needed for each value are:");
		printHashTable(listCoins[total], coins, numOfCoins);
		
	} // end of changeMoney() method;

	private static void printHashTable(Hashtable<Integer, Integer> listCoins, int[] coins, int numOfCoins) {
		// TODO Auto-generated method stub
		
		for(int m=0; m<numOfCoins; m++){
			System.out.print(" $" + coins[m] +" " + listCoins.get(coins[m]) +"; ");
		}
		
	} // end of printHashTable(coins); 
	

} // end of DPMoneyChange class;
