package graph;

import java.util.Arrays;

//Creates adjacency matrix of size n x n -> n is number of nodes
//true at matrix[i][j] indicates i -> j
public class AdjacencyMatrix {
    int[][] matrix;

    public AdjacencyMatrix(int n) {
        matrix = new int[n][n];
    }

    public void DConnect(int a, int b) {
        matrix[a][b] = 1;
    }

    public void UDConnect(int a, int b) {
        DConnect(a, b);
        DConnect(b, a);
    }

    public String toString() {
        //return Arrays.deepToString(matrix);
        StringBuilder sb = new StringBuilder("[");
        for (int row = 0; row < matrix.length; row++) {
            sb.append("[");
            for (int col = 0; col < matrix[0].length; col++) {
                sb.append(matrix[row][col]);

                if (col < matrix[0].length - 1) //last elem
                    sb.append(", ");
            }
            sb.append("]");
            if (row < matrix.length - 1)
                sb.append(",\n");
        }
        sb.append("]");
        return sb.toString();
    }
}
