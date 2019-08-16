package lpaPages;

import java.util.HashMap;
import java.util.Map;

public abstract class HashDemo {

	public static void main(String[] args) {
		
		String s ="hello World";
		
		char[] charArray = s.toCharArray();
		HashMap<Character, Integer> map =  new HashMap<Character, Integer>();
		for( char c: charArray){
			if(!map.containsKey(c)){
				map.put(c, 1);}
		
		else{map.put(c, map.get(c)+1);}
		
		
	}
		
		for(Map.Entry entry :map.entrySet()){
			
			System.out.println(entry.getKey() + "   "+ entry.getValue());
		}

}
}
