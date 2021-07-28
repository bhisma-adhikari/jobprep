package leetcode;

import utils.Utils;

public class RotateImage {
    public static void main(String[] args) {
        int[][] matrix = new int[3][];
        matrix[0] = new int[] {1,2,3};
        matrix[1] = new int[]{4,5,6};
        matrix[2] = new int[]{7,8,9};
        Utils.print2dIntArray(matrix);

        new Solution110().rotate(matrix);
        Utils.print2dIntArray(matrix);

    }

}


class Solution110 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int layer = 0; layer < n/2; layer++) {
            int first = layer;
            int last = n - 1 - first;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                // backup top
                int top = matrix[first][i];

                // top <- left
                matrix[first][i] = matrix[last-offset][first];

                // left <- bottom
                matrix[last-offset][first] = matrix[last][last - offset];

                // bottom <- right
                matrix[last][last-offset] = matrix[i][last];

                // right <- top
                matrix[i][last] = top;
            }
        }
    }
}
