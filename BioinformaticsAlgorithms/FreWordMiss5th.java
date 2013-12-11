package assignments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FreWordMiss5th {
	
	public static void main(String[] args) throws IOException{
		
		//String OriStr = new Scanner(new File("dataset_sample.txt")).useDelimiter("\\A").next();
		String OriStr = "CACAGTAGGCGCCGGCACACACAGCCCCGGGCCCCGGGCCGCCCCGGGCCGGCGGCCGCCGGCGCCGGCACACCGGCACAGCCGTACCGGCACAGTAGTACCGGCCGGCCGGCACACCGGCACACCGGGTACACACCGGGGCGCACACACAGGCGGGCGCCGGGCCCCGGGCCGTACCGGGCCGCCGGCGGCCCACAGGCGCCGGCACAGTACCGGCACACACAGTAGCCCACACACAGGCGGGCGGTAGCCGGCGCACACACACACAGTAGGCGCACAGCCGCCCACACACACCGGCCGGCCGGCACAGGCGGGCGGGCGCACACACACCGGCACAGTAGTAGGCGGCCGGCGCACAGCC";
		System.out.println("There are " + OriStr.length() +" base in the original sequence.");
	
		/***************************************************************************************
		 * input length of sequence to be checked; 
		 * normally the subSeqLen-element subsequence would be the k-mer
		 * for the ribosome to check and bind to, :) HERE we just input the number directly;
		 * In real program we have to use arg[0], or write a method to ask user to input the subSeqLen.
		 */
		int subSeqLen = 10;
		int Miss = 2;

		// frequentCompare() method to get the k-mers;
		// output the k-mers in the kmers.txt document; there might be a lot of duplications in the kmers.txt.
		frequentCompare(OriStr, subSeqLen);

		@SuppressWarnings("resource")
		String kmerStr = new Scanner(new File("kmers.txt")).useDelimiter("\\A").next();
		System.out.println("There are " + kmerStr.length() +" base in the kmer String");
		
		// remove the duplicated sub strings;
		kmerStr = removeDuplicates(kmerStr, subSeqLen);
		System.out.println("After duplicates deleted, the kmerStr is:" + kmerStr);
		
		// get the one-miss-String from k-mer String;
		// save the new string to OneMissStr.txt document;
		OneMissFre(kmerStr, subSeqLen);
		
		
		@SuppressWarnings("resource")
		String oneMissStr = new Scanner(new File("OneMissStr.txt")).useDelimiter("\\A").next();
		System.out.println("There are " + oneMissStr.length() + " bases in the one miss String");
		// String RoneMissStr = removeDuplicates(oneMissStr, subSeqLen);
		// System.out.println("ONEMISS STRING: " + oneMissStr);
		
		// use compFreWord() to compare the most frequently repeated subsequences in oneMissStr, 
		// comparing to oriStr. with d=Miss missed chars.
		 compFreWord(oneMissStr, OriStr, subSeqLen, Miss);
		
		@SuppressWarnings("resource")
		String freOneMiss = new Scanner(new File("freRepStr.txt")).useDelimiter("\\A").next();
		
		TwoMissFre(freOneMiss, subSeqLen);
		
		@SuppressWarnings("resource")
		String twoMissStr = new Scanner(new File("TwoMissStr.txt")).useDelimiter("\\A").next();
		
		//String RtwoMissStr = removeDuplicates(twoMissStr, subSeqLen);
		//System.out.println("The reduced twomiss String is: " + twoMissStr);
		compFreWord(twoMissStr, OriStr, subSeqLen, Miss);
		
		
	} // end main(); ATGCTGAGT

	private static void compFreWord(String MissStr, String oriStr, int subSeqLen, int miss) throws IOException {
		// TO compare which str has the most repeated chances with d=miss missed;
		int M = oriStr.length();
		int N = subSeqLen;
		int O = MissStr.length();
		int Max=0;
		String freStr = ""; // creat a new string to store frequently repeated subsequences;
		
		for(int i=0; i<O; i+=N){
			int count=0;
			String sub2Miss = MissStr.substring(i, i+N);
			for(int k=0; k<M-N; k++){
				String subOriStr = oriStr.substring(k, k+N);
				if (matchStrings(subOriStr, sub2Miss, subSeqLen) >= N-miss ){
					count++;
				}
			} // enf for k loop;
			if (count >= 15){
				
				freStr += sub2Miss;
			}
			
			if(count>=Max){
				Max=count;
				System.out.println("(Max) count= " + count + ". the subString is: " + sub2Miss);
			}
			
		} // end for i loop;
		
		File f=new File("freRepStr.txt");
		FileWriter out=new FileWriter(f);
		out.write(freStr);
		out.close();
		
	}

	private static int matchStrings(String str1, String str2, int length) {
		// To count how many chars match between two strings.
		int match =0;
		for(int i=0; i<length; i++){
			if (str1.charAt(i) == str2.charAt(i))
				match++;
		}
		
		return match;
	}

	private static void TwoMissFre(String inputStr, int subSeqLen) throws IOException {
		// TO generate a new string with two character missed to the original string.
		
		String twoMissStr = "";
		int M = inputStr.length();
		int N = subSeqLen;
		
		char[] mid = new char[4];
		mid[0] = 'A';
		mid[1] = 'G';
		mid[2] = 'T';
		mid[3] = 'C';
		
		for (int k=0; k<M; k+=N){
			for (int i=0; i<N; i++){
				for (int j=0; j<4; j++){
					
					String tempStr = inputStr.substring(k+0, k+i) + mid[j] + inputStr.substring(k+i+1, k+N);
					twoMissStr += tempStr;
					}
				} // end for i loop;

		}	// end for k loop;
		
		System.out.println("\nThe Two miss String is ready.");
		
		//oneMissStr = removeDuplicates(oneMissStr, subSeqLen);
		twoMissStr = removeDuplicates(twoMissStr, subSeqLen);
		
		File f=new File("TwoMissStr.txt");
		FileWriter out=new FileWriter(f);
		out.write(twoMissStr);
		out.close();
	}

	private static void OneMissFre(String kmerStr, int subSeqLen) throws IOException {
		// TO generate a new string with one character missed to the original string.
		String oneMissStr = "";
		int M = kmerStr.length();
		int N = subSeqLen;
		
		char[] mid = new char[4];
		mid[0] = 'A';
		mid[1] = 'G';
		mid[2] = 'T';
		mid[3] = 'C';
		
		for (int k=0; k<M; k+=N){
			for (int i=0; i<N; i++){
				for (int j=0; j<4; j++){
					
					String tempStr = kmerStr.substring(k, k+i) + mid[j] + kmerStr.substring(k+i+1, k+N);
					oneMissStr += (tempStr);
					}
				} // end for i loop;

		}	// end for k loop;
		System.out.println("\nThe One miss String is ready." + oneMissStr);
		
		String RoneMissStr = removeDuplicates(oneMissStr, subSeqLen);
		
		File f=new File("OneMissStr.txt");
		FileWriter out=new FileWriter(f);
		out.write(RoneMissStr);
		out.close();
	}

	private static String removeDuplicates(String inputStr, int subSeqLen) {
		// TO remove the duplications of sub strings.
		String newStr = "" +inputStr.substring(0, subSeqLen);
		
		for (int i=subSeqLen; i<inputStr.length(); i+=subSeqLen){
			int count =0;
			String inputStrSub = inputStr.substring(i, i+subSeqLen);
			
			for(int k=0; k<newStr.length(); k+=subSeqLen){	
				String newStrSub = newStr.substring(k, k+subSeqLen);

				if(newStrSub.equals(inputStrSub)){
					count++;
				}// end if, if there's no substring in newStr equals to kmerStrsub, count==0;
			} // enf for k loop;
			
			// check the count value, if count<1, which means there's no duplicate substrings in newStr;
			// so add the inputStrSub to newStr.
			if (count < 1){
				newStr += inputStrSub;
			}
		} // end for i loop;
		
		System.out.println("the duplicates removed two miss String is ready.");
		return newStr;
	}

	private static void frequentCompare(String oriStr, int subLength) throws IOException {
		// TO check how many times a subsequence in the DNA sequence repeated
		// to begin with the very first 9 (length) lone-base-pair as the begin sequence
		// check all subsequence in the whole sequence and compare it the the begin sequence
		
		int M = oriStr.length();
		int N = subLength;
		String kmerStr = "";
		System.out.println("Out k-mers; \nThe length of sub string is: " + N);
		
		// print out the total lone-base-pair in the original sequence.
		
		File f=new File("kmers.txt");
		FileWriter out=new FileWriter(f);
		
		// use the fileWriter to output all subsequence repeated over 100 times.
			for(int i=0; i<M-N; i++){
				
				String tempStr = oriStr.substring(i, i+N);
				kmerStr += tempStr;
			} // end for i loop
			
			System.out.println("Write the kmers-String into kmers.txt.");
	       
			out.write(kmerStr);
			out.close();
	
	} // end frequent compare method;

}  // END the whole program.