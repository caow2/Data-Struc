package arrays;

//Replace all strings with %20. Assume string has enough space to hold extra chars and 
//you're given the true length of the string
public class URLify {
	public static void main(String[] args) {
		System.out.println(URLify("Apple Pie"));
		System.out.println(URLifyInPlace("Apple Pie  ", 9));
		System.out.println(URLifyInPlace("", 0));
		System.out.println(URLifyInPlace2("   ", 1));
	}
	
	/* 
	 	BCR is O(N) - have to at least go through the string 
	 	
	 	Brute Force - Have a string or stringbuilder. Go through each character of the String and append/concat
	 	to the string/sb. If white space append %20 instead. O(N^2) time and O(N) space b/c
	 	Amortized time for append N chars (with resizing) will be O(N) and you have to go through each char.
	 	For String, Time will be O(N^3) due to N^2 for concatenating over and over.
	 	
	 	Another Approach - Go through the string once and sum up the amount of WS.
	 	Calculate final array length by String.length() - WS + (WS * 3) or length + (WS * 2)
	 	Instatiate Array
	 	Go through the string again and add characters accordingly.
	 	O(N) time. Worst case full string is full of WS - O(3N + N) ~ O(N). O(N) space too for the same logic.
	 	
	 	In place - Get the character array. Go through it. Each time you encounter a white space, perform a shifting of
	 	all characters after that white space (+3 forward). Replace white space with '%20' and keep going.
	 	Shifting will O(N^2). Total runtime O(N^3). Shifting is bottleneck
	 	
	 	In place modified shifting - start from end of the string and since we have the true length, just check chars from there
	 	and put them towards the end of the string.
	 	O(N) time
	 	*/
	
	public static String URLify(String str) {
		int ws = 0;
		
		for (char c : str.toCharArray()) {
			if(c == ' ')
				ws++;
		}
		
		int len = str.length() + (2 * ws);
		char[] result = new char[len];
		int index = 0;
		
		for(int pos = 0; pos < str.length(); pos++) {
			if(str.charAt(pos) == ' ') {
				result[index] = '%';
				result[index + 1] = '2';
				result[index + 2] = '0';
				index += 3;
			}
			else {
				result[index] = str.charAt(pos);
				index++;
			}
		}
		
		return new String(result);
	}
	
	public static String URLifyInPlace2(String str, int len) {
		if(str.length() == 0)
			return "";
		
		char[] c = str.toCharArray();
		
		int ws = (str.length() - len) / 2;
		int index = str.length() - 1;
		for(int pos = len - 1; pos >= 0; pos--) {
			if(str.charAt(pos) == ' ') {
				c[index] = '0';
				c[index - 1] = '2';
				c[index - 2] = '%';
				index -= 3;
			}
			else {
				c[index] = str.charAt(pos);
				index--;
			}
		}
		
		return new String(c);
		
	}
	
	
	public static String URLifyInPlace(String str, int len) {
		if(str.length() == 0)
			return "";
		
		//given string and true length, modify String in place
		char[] arr = str.toCharArray();
		int index = 0;
		for(int pos = 0; pos < len; pos++) {
			if(str.charAt(pos) == ' ') {
				//shifting is a bottleneck and duplicated work
				for(int indx = str.length() - 1; indx > index + 3; indx--) {
					arr[indx] = arr[indx - 3];
				}
				
				arr[index] = '%';
				arr[index + 1] = '2';
				arr[index + 2] = '0';
				index += 3;
			}
			else {
				arr[index] = str.charAt(pos);
				index++;
			}		
		}
		return new String(arr);
	}
	
	
}
