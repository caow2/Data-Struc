package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

//Given a String, check if its a permutation of a palindrome
public class PalindromePermutation {
    public static void main(String[] args) {
        System.out.println(palindromePermutation("raec ra c"));
    }

    /*
        BCR: O(N) - have to process the String at least once

        Brute Force approach - generate every permutation of the str and see if its a palindrome
        O(N! * N) - N! for generating, N for checking. O(N! * N) space. N! possible permutations, each of size N.

        Consider a palindrome -> if it has more than one character that occurs only once, it is impossible for it to be a palindrome.
        aaa is one. For something like abca, its impossible no matter how its arranged.
        Another approach - check if the string is a palindrome - only one unique character.
        O(N) time and O(N space)
     */
    public static boolean palindromePermutation(String str) {
        str = str.toUpperCase();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        for (char c : str.toCharArray()) {
            if (c == ' ')
                continue;
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }

        boolean uniqueFound = false;
        for (Character key : map.keySet()) {
            if (map.get(key) == 1) {
                if (uniqueFound)
                    return false;
                uniqueFound = true;
            }
        }

        return uniqueFound;
    }

}
