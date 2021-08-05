package misc;

import utils.Utils;

public class Sudoku {
    public static void main(String[] args) {
        int[][] board = new int[9][9];
        board[0] = new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9};
        board[1] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[2] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[3] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[4] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[5] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[6] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[7] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        board[8] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        Utils.print2dIntArray(board);

//        System.out.println(isValidBoard(board));

        System.out.println(solve(board));

        Utils.print2dIntArray(board);
        System.out.println("done");
    }

    // if board is solvable, this function updates board with a solution and returns true
    // if board is not solvable, this function returns false
    public static boolean solve(int[][] board) {
        Utils.print2dIntArray(board);
        if (!isValidBoard(board)) return false;
        if (noneZero(board)) return true;  // solution found

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == 0) {
                    int[][] stateBefore = deepCopy(board);
                    for (int n = 1; n <= 9; n++) {
                        int[][] state = deepCopy(board);
                        board[r][c] = n;
                        if (solve(board))
                            return true;
                        // backtrack
                        restoreState(board, state);
                    }
                    if (equal(stateBefore, board))
                        return false;
                }
            }
        }
        return false;
    }

    private static boolean equal(int[][] board1, int[][] board2) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board1[r][c] != board2[r][c])
                    return false;
            }
        }
        return true;
    }

    private static void restoreState(int[][] board, int[][] state) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                board[r][c] = state[r][c];
            }
        }
    }


    private static int[][] deepCopy(int[][] board) {
        int[][] copy = new int[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                copy[r][c] = board[r][c];
            }
        }
        return copy;
    }


    // returns true if none of the elements is zero
    private static boolean noneZero(int[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == 0) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * returns true if board is valid
     * a valid board must satisfy the following 3 constraints:
     * 1. for each row, numbers 1 through 9 appear at most 1
     * 2. for each column, numbers 1 through 9 appear at most 1
     * 3. for each 3x3 square, numbers 1 through 9 appear at most 1
     * <p>
     * IMPORTANT: a valid board does not necessarily imply that the board is solvable,
     * for example, the following board is valid (satisfies above 3 constrains) but not solvable
     * because the only option for [0,0] is 1 (from row constraint). However, that would violate the column constraint.
     * Thus, although this partially filled board is valid Sudoku board, a solution is not possible, and hence
     * the board is not solvable.
     * <p>
     * (0 means blank)
     * <p>
     * 0 2 3 4 5 6 7 8 9
     * 1 0 0 0 0 0 0 0 0
     * 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 0 0 0 0 0
     * 0 0 0 0 0 0 0 0 00
     *
     * @param board
     * @return
     */
    private static boolean isValidBoard(int[][] board) {
        return checkRows(board) && checkCols(board) && checkSqs(board);
    }


    private static boolean checkRows(int[][] board) {
        for (int r = 0; r < 9; r++) {
            // to track if a number has been seen so far in current row
            // first index will be ignored, so we have length = 9 + 1 = 10
            boolean[] arr = new boolean[10];  // all false initially
            for (int c = 0; c < 9; c++) {
                int num = board[r][c];
                if (num != 0) {  // is cell is blank, no need to check
                    if (arr[num]) { // number already seen in current row
                        return false;
                    }
                    arr[num] = true;
                }
            }
        }
        return true;
    }

    private static boolean checkCols(int[][] board) {
        for (int c = 0; c < 9; c++) {
            // to track if a number has been seen so far in current col
            // first index will be ignored, so we have length = 9 + 1 = 10
            boolean[] arr = new boolean[10];  // all false initially
            for (int r = 0; r < 9; r++) {
                int num = board[r][c];
                if (num != 0) {  // is cell is blank, no need to check
                    if (arr[num]) { // number already seen in current row
                        return false;
                    }
                    arr[num] = true;
                }
            }
        }
        return true;
    }

    private static boolean checkSqs(int[][] board) {
        for (int rowStart = 0; rowStart < 7; rowStart += 3) {
            for (int colStart = 0; colStart < 7; colStart += 3) {
                boolean[] arr = new boolean[10];  // all false initially
                for (int r = rowStart; r < rowStart + 3; r++) {
                    for (int c = colStart; c < colStart + 3; c++) {
                        int num = board[r][c];
                        if (num != 0) {
                            if (arr[num]) {
                                return false;
                            }
                            arr[num] = true;
                        }
                    }
                }
            }
        }
        return true;
    }

}
