package arrays;

import java.util.Hashtable;

//Determine if two Strings are permutations of each other
public class CheckPermutation {
	public static void main(String[] args) {
		System.out.println(permutations("cAt", "tca"));
		System.out.println(permutations("", "tca"));
		System.out.println(permutations("", ""));
		System.out.println(permutations("cAtasdwad", "tca"));
	}
	
	/*	2 Strings can be considered permutations or anagrams of each other if they have the same amount of each character
	 	and correspondingly, the same length.
	 	
	 	Assume case not sensitive (i.e. cAt and tac are permutations of each other) so convert both to uppercase before checking
	 	
	 	BCR is O(N) where N is the length of both Strings. If unequal length -> return false immediately
	 	
	 	Brute force approach - generate permutations of the one string until it matches the other
	 	If no match, return false. O(n!) time where n is length of string b/c to gen permutations: n * n-1 * ... * 1
	 	
	 	Data Structure approach - Have 2 HashMaps or arrays that keep count of the number of each character
	 	[HM has Character : Integer pairing and array has 0-25 to map to characters]
	 	Go through each char in each String and add their counts to the HM. ex. if countered a, a.value++
	 	At the end, check if both HM are the same (matching KV pairs - .equals())
	 */
	
	public static boolean permutations(String one, String two) {
		if(one.length() != two.length())
			return false;
		
		one = one.toUpperCase();
		two = two.toUpperCase();
		
		Hashtable<Character, Integer> mapOne = new Hashtable<Character, Integer>();
		Hashtable<Character, Integer> mapTwo = new Hashtable<Character, Integer>();
		
		for(char c : one.toCharArray()) {
			if(mapOne.containsKey(c))
				mapOne.put(c, mapOne.get(c) + 1);
			else
				mapOne.put(c, 1);
		}
		
		for(char c : two.toCharArray()) {
			if(mapTwo.containsKey(c))
				mapTwo.put(c, mapTwo.get(c) + 1);
			else
				mapTwo.put(c, 1);
		}
		
		return mapOne.equals(mapTwo);
	}
}
