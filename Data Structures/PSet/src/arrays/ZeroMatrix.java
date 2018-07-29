package arrays;

import java.util.Arrays;
import java.util.HashSet;

public class ZeroMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {0, 1, 2}, {2, 3, 0}};
        System.out.println(Arrays.deepToString(matrix));
        //System.out.println(Arrays.deepToString(zeroMatrix(matrix)));
        System.out.println(Arrays.deepToString(zeroMatrixImproved(matrix)));
    }

    public static int[][] zeroMatrix(int[][] matrix) {
        HashSet<Integer> rows = new HashSet<Integer>();
        HashSet<Integer> cols = new HashSet<Integer>();

        for (int row = 0; row < matrix[0].length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                if (matrix[row][col] == 0) {
                    rows.add(row);
                    cols.add(col);
                }
            }
        }

        for (int row : rows) {
            for (int pos = 0; pos < matrix[0].length; pos++) {
                matrix[row][pos] = 0;
            }
        }

        for (int col : cols) {
            for (int pos = 0; pos < matrix.length; pos++) {
                matrix[pos][col] = 0;
            }
        }

        return matrix;
    }

    public static int[][] zeroMatrixImproved(int[][] matrix) {
        boolean firstRowZero = false, firstColZero = false;

        //check if first row has 0
        for (int col = 0; col < matrix[0].length; col++) {
            if (matrix[0][col] == 0)
                firstRowZero = true;
        }
        //check if first col has 0
        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row][0] == 0)
                firstColZero = true;
        }


        for (int row = 1; row < matrix[0].length; row++) {
            for (int col = 1; col < matrix.length; col++) {
                //use the first row and column as placeholders b/c
                //we know if theres a zero at row, col, then the whole row and col has to be 0

                if (matrix[row][col] == 0) {
                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                }
            }
        }

        //check first column
        if (matrix.length > 1) {
            for (int row = 1; row < matrix.length; row++) {
                if (matrix[row][0] == 0)
                    nullifyRow(matrix, row);
            }
        }

        //check first row
        if (matrix[0].length > 1) {
            for (int col = 1; col < matrix[0].length; col++) {
                if (matrix[0][col] == 0)
                    nullifyCol(matrix, col);
            }
        }

        if (firstRowZero)
            nullifyRow(matrix, 0);

        if (firstColZero)
            nullifyCol(matrix, 0);

        return matrix;
    }

    public static void nullifyRow(int[][] matrix, int row) {
        for (int pos = 0; pos < matrix[0].length; pos++) {
            matrix[row][pos] = 0;
        }
    }

    public static void nullifyCol(int[][] matrix, int col) {
        for (int pos = 0; pos < matrix.length; pos++) {
            matrix[pos][col] = 0;
        }
    }
}
