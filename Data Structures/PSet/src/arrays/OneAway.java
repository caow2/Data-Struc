package arrays;

import java.util.Hashtable;

// Given 2 Strings, check if they are 1 or 0 away:
// insert char, replace char, or remove a char
public class OneAway {
    public static void main(String[] arg) {
        System.out.println(oneAway("bales", "pale"));
    }
	
	/*	BCR is O(N) - have to at go through at least one String
 	 	
 	  	Brute Force - check if same length or if lens are 1 apart
 	  	Separate to 2 cases: equal length and not equal length
 	  	Go through each word and check if there is more than 1 differences - track number of differences
 	  	O(N^2) b/c of brute force checking, which is a bottleneck here and duplicated work 
 	  	O(1) space
 	  	
 	  	Alternate Approach - same idea as above, but instead of repeatedly checking,
 	  	go through the second word and add it to HashMap with <Character, Int count>
 	  	go thru first word and check each char while decreasing count. Track the differences.
 	  	Working with longer string is optimal when seeking to see if the other String contains a char so you
 	  	hit all the characters between both Strings.
 	  	O(N) time and O(N) space
 	  	
 	  	Optimal Approach - do the same checking, but since you know all the characters 
	 */

    public static boolean oneAway(String first, String sec) {
        //working with longer String is much easier
        if (sec.length() > first.length()) {
            String temp = first;
            first = sec;
            sec = temp;
        }

        first = first.toLowerCase();
        sec = sec.toLowerCase();

        if (Math.abs(first.length() - sec.length()) > 1)
            return false;

        Hashtable<Character, Integer> table = new Hashtable<Character, Integer>();

        for (char c : sec.toCharArray()) {
            if (table.containsKey(c))
                table.put(c, table.get(c) + 1);
            else
                table.put(c, 1);
        }

        int diff = 0;

        for (char c : first.toCharArray()) {
            if (table.get(c) == null || table.get(c) == 0)
                diff++;
            else
                table.put(c, table.get(c) - 1);

            if (diff > 1)
                return false;
        }

        return true;
    }
}
