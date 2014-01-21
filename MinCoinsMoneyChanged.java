package assignments;

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
 *   output MinNumCoins(money)
 */

public class MinCoinsMoneyChanged {
	
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
		minCoins[0] = 0;
		int numOfCoins = coins.length;
		
		for(int i=1; i<=total; i++){
			
			minCoins[i] = total;
			
			for(int j=0; j<numOfCoins; j++){
				
				if(i>= coins[j]){
					
					if(minCoins[i - coins[j]] + 1 < minCoins[i]){
						minCoins[i] = minCoins[i-coins[j]] + 1;
						
					} // end inner if condition;
				} // end if i>=coins[j] condition;
				
			} // end for j<numOfCoins loop; a loop for every kind of coin;
			
			System.out.println("The minimum coins need for " + i +" are " + minCoins[i]);
		} // end for i<=total loop; a loop for every money;
		
		System.out.println(":) \nThe minimum coins need for " + total +" are " + minCoins[total]);
	} // end of changeMoney() method;

} // end of DPMoneyChange class;
