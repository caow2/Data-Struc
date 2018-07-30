package list;

/*	In terms of another approach, utilize a char array instead of an
 *	ArrayList. The char array would have to be dynamically resized (and hence
 *	acting as an ArrayList) but when calling toString(), it would be O(1)
 *	as opposed to O(n). There would be no conversion from Character to c.
 */
public class StringBuilder {
    ArrayList<Character> list = new ArrayList<Character>();

    public StringBuilder(String s) {
        for (char c : s.toCharArray()) {
            list.add(c);
        }
    }

    public StringBuilder() {
        // TODO Auto-generated constructor stub
    }

    public void append(String str) {
        for (char c : str.toCharArray()) {
            list.add(c);
        }
    }

    public String toString() {
        //Char
        return new String(toCharArray());
    }

    //O(n) where n is size of the ArrayList
    private char[] toCharArray() {
        char[] arr = new char[list.size()];

        for (int pos = 0; pos < list.size(); pos++) {
            arr[pos] = list.get(pos);
        }

        return arr;
    }
}
