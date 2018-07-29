package arrays;

//Compress string if length of returned string < original length
public class StringCompression {
    public static void main(String[] args) {
        System.out.println(compress("aabcccccaaa"));
        System.out.println(compress("acsdaasdqwd"));
    }

    public static String compress(String str) {
        char[] chars = new char[str.length()];
        int[] count = new int[str.length()];
        int uniqueCount = 0, index = 0;
        ;

        for (int pos = 0; pos < str.length(); pos++) {
            char current = str.charAt(pos);
            chars[index] = current;
            count[index] = 1;
            uniqueCount++;
            //check for contigous same chars
            while (pos + 1 != str.length() && str.charAt(pos + 1) == current) {
                count[index]++;
                pos++;
            }
            index++;
        }

        if (uniqueCount * 2 >= str.length())
            return str;

        StringBuilder builder = new StringBuilder();

        for (int pos = 0; pos < uniqueCount; pos++) {
            builder.append(chars[pos]);
            builder.append(count[pos]);
        }

        return builder.toString();
    }
}
