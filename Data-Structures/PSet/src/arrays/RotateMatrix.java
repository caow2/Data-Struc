package arrays;

import java.util.Arrays;

public class RotateMatrix {
    public static void main(String[] args) {
        int[][] arr = new int[3][3];

        for (int row = 0; row < arr[0].length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                arr[row][col] = row + col;
            }
        }
        System.out.println(Arrays.deepToString(arr));
        System.out.println(Arrays.deepToString(rotateMatrix(arr)));
    }

    public static int[][] rotateMatrix(int[][] arr) {
        int endrow = arr[0].length - 1, endcol = endrow;
        int srow = 0, scol = 0;
        if (arr[0].length == 1)
            return arr;

        while (srow <= endrow) {
            for (int pos = scol; pos < endcol; pos++) {
                int temp = arr[srow][pos];
                arr[srow][pos] = arr[endrow - pos][scol];
                arr[endrow - pos][scol] = arr[endrow][endcol - pos];
                arr[endrow][endcol - pos] = arr[srow + pos][endcol];
                arr[srow + pos][endcol] = temp;
            }
            srow++;
            scol = srow;
            endrow--;
            endcol = endrow;
        }

        return arr;
    }
}
