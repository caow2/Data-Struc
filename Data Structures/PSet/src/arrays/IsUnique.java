package arrays;

import java.util.Arrays;
import java.util.HashSet;

//Determine if String is all unique chars
//Best conceivable runtime is O(n) because you have to check all characters
//and string doesn't come sorted
public class IsUnique {
    public static void main(String[] args) {
        System.out.println(uniqueChar("Apple") + " " + uniqueCharNoDS("ApPle"));
        System.out.println(uniqueChar("") + " " + uniqueCharNoDS(""));
        System.out.println(uniqueChar("Bpple") + " " + uniqueCharNoDS("Bpple"));
    }

    /* Brute Force Approach - for each character, check if that character exists in the String
     * by checking it linearly - N^2
     *
     * In place checking approaching ( no additional data structure ) - Sort the string, then go through it once
     * and see if the current character matches the previous/next character -> N + NlogN ~ NlogN
     *
     * Another Approach - Go through string once and have a HashSet to track the characters already seen.
     * For each character in string, check if its in the set (not unique), otherwise add it to HS and check next one
     * O(N) time and O(N) additional space
     *
     * Optimal - Use boolean array[128] to represent 128 ascii values and use that instead of HashTable/ HS.
     */

    public static boolean uniqueChar(String str) {
        str = str.toUpperCase();
        HashSet<Character> set = new HashSet<Character>();
        for (char c : str.toCharArray()) {
            if (set.contains(c))
                return false;
            set.add(c);
        }
        return true;
    }

    //In place checking
    public static boolean uniqueCharNoDS(String str) {
        str = str.toUpperCase();
        Arrays.sort(str.toCharArray());

        for (int pos = 0; pos < str.length() - 1; pos++) {
            if (str.charAt(pos) == str.charAt(pos + 1))
                return false;
        }
        return true;
    }
}
