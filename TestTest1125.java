package assignments;

import java.util.Hashtable;
import java.util.Set;



public class TestTest1125 {
	
	private class proteinUnit{
		char name;
		int molWeight;
	}

	
	
	public static void main(String[] args){
		
		TestTest1125 Test = new TestTest1125();
		
		proteinUnit[] newProUnits = new proteinUnit[10];
		for(int i=0; i<10; i++){
			newProUnits[i] = Test.new proteinUnit();
			newProUnits[i].molWeight = i+100;
			newProUnits[i].name = 'A';	
		}
		
		for(int i=0; i<10; i++){
			System.out.println("newProUnits[" + i +"].name= " + newProUnits[i].name +". molWeight=" + newProUnits[i].molWeight);
			
		}
		
		String string = "0817456239*0817546239*0864571239*0864572139*0865471239*0865472139*";
		String temp = "10817456239";
		
		
		
		if(!string.contains(temp)){
			System.out.println("String does not contain temp;");
		}
		for(int i=0; i<string.length(); i+=11){

			String subStr = string.substring(i,i+10);
			System.out.println(" " + subStr);
			
		}
		
 Hashtable<String, String> hm = new Hashtable<String, String>();
 //add key-value pair to Hashtable
 		hm.put("first", "FIRST INSERTED");
 		hm.put("second", "SECOND INSERTED");
 		hm.put("third","THIRD INSERTED");
 		
 		System.out.println(hm);
 		
 		Set<String> keys = hm.keySet();
 			System.out.println(keys);
 			
 		for(String key: keys){
 			
 			System.out.println(key);
 		}
 			

	}

}
