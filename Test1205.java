package assignments;

public class Test1205 {
	
	public static void main(String[] args){
		
		String oriStr = "AGTCCGTCGGATTC";
		String oriStr1 = "AGTCTTTAGC";
		String test ="AGTC";
		int num = 0;
		num += SeqContainSub(oriStr, test, 1);
		num += SeqContainSub(oriStr1, test, 1);
		
		System.out.println("num=" + num);
		
		
		
	}	
	
	private static int SeqContainSub(String Sequence, String subSeq, int dNum) {
		// TO check whether or not the string sequence contain substring key with dNum vibrations;
		int LenOri = Sequence.length();
		int LenSub = subSeq.length();
		int count=0;
		for(int i=0; i<=LenOri-LenSub; i++){
			String temStr = Sequence.substring(i, i+LenSub);
		
			int diff = compareTwoStr(temStr, subSeq);
			System.out.println(" " + temStr+ " " + subSeq +" diff=" + diff);
			if (diff<=dNum)
				count=1;
		} // end for i<Lenori-LenSub loop;
		
		return count;
	}

	private static int compareTwoStr(String temStr, String subSeq) {
		// TO compare two strings of the same length; return # of different chars.
		//System.out.println(" " + temStr +" " + subSeq);
		
		int Len = temStr.length();
		int diff = 0;
		for(int i=0; i<Len; i++){
			if(temStr.charAt(i) != subSeq.charAt(i)){
				diff++; 
				
			}
		}
		System.out.print(" "+ diff);
		return diff;
	}

} // end of all;
