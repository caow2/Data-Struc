package arrays;

public class StringRotation {
    public static void main(String[] args) {
        System.out.println(stringRotation("waterbottle", "erbottlewat"));
        System.out.println(stringRotation("water", "erwat"));
    }

    public static boolean stringRotation(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        String s3 = s2 + s2;

        return s3.indexOf(s1) > -1;

    }
}
