package assignments;

public class StringQueueOpe {
	public static void main(String[] args){
		
		int iSequence = 1823443;
		int jFinal = 0;
		
		String str = "ADFGSDFDSTRQREWQRDFDSFDYREWRF";
		String temp = "";
		for (int i=0; i<str.length(); i++){
			temp += str.charAt(i);
			System.out.println(temp +"  ");
		}
		
		for (int j=0; j<str.length()-3; j++){
			//temp = temp - temp.charAt(0);
			System.out.println(temp.substring(j));
		}
		for (int j=0; j<iSequence; j+=1000){
			System.out.println(j);
			jFinal = j;
		}
		System.out.println("The end, j overflow value is: " + jFinal);
	}
}
