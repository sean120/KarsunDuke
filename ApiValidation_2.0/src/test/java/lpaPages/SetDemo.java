package lpaPages;

import java.util.HashSet;
import java.util.Set;

public class SetDemo {
	
	public static void main(String[] args) {
		
		
		
	
String s = "hello World";

	char[] charArray =s.toCharArray();
	int ctr =0;
	for (char i=0; i<charArray.length; i++){
		ctr++;
		System.out.println(ctr + "  "+ charArray);
		
	}
	
//	Set<Character> set = new HashSet<Character>();
//	int ctr = 0;
//	
//	for( int i=0; i<charArray.length; i++){
//		if(!set.add(charArray[i])){
//			System.out.println(i);
//			ctr++;
//			
//		}
//		System.out.println("total number of characters"+ ctr);
//		
//		
//	}
	
}
}

