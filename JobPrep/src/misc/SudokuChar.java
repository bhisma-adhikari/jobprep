package misc;

import java.util.HashMap;
import java.util.Map;
import utils.*;

public class SudokuChar {
    public static void main(String[] args) {
        char[][] board = new char[9][9];
        board[0] = new char[]{'.', '2', '3', '4', '5', '6', '7', '8', '9'};
        board[1] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.', '.'};
        board[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.', '.'};
        board[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.', '.'};
        board[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.', '.'};
        board[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.', '.'};
        board[6] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.', '.'};
        board[7] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.', '.'};
        board[8] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.', '.'};

        System.out.println(solveSudoku(board));
        Utils.print2dCharArray(board);


    }

    // if board is solvable, this function updates board with a solution and returns true
    // if board is not solvable, this function returns false
    public static boolean solveSudoku(char[][] board) {
        Utils.print2dCharArray(board);
        if (!isValidBoard(board)) return false;
        if (allFilled(board)) return true;  // solution found

        char[] nums = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    char[][] stateBefore = deepCopy(board);
                    for (char n : nums) {
                        char[][] state = deepCopy(board);
                        board[r][c] = n;
                        if (solveSudoku(board))
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

    private static boolean equal(char[][] board1, char[][] board2) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board1[r][c] != board2[r][c])
                    return false;
            }
        }
        return true;
    }

    private static void restoreState(char[][] board, char[][] state) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                board[r][c] = state[r][c];
            }
        }
    }


    private static char[][] deepCopy(char[][] board) {
        char[][] copy = new char[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                copy[r][c] = board[r][c];
            }
        }
        return copy;
    }


    // returns true if none of the elements is zero
    private static boolean allFilled(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
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
    private static boolean isValidBoard(char[][] board) {
        return checkRows(board) && checkCols(board) && checkSqs(board);
    }


    private static Map<Character, Boolean> getMap() {
        Map<Character, Boolean> map = new HashMap<>();
        // '1' char has ASCII value of 49
        for (int i = 49; i <= 57; i++) {
            Character c = (char) i;  // 49 -> '1'
            map.put(c, false);
        }
        return map;
    }

    private static boolean checkRows(char[][] board) {
        for (int r = 0; r < 9; r++) {
            Map<Character, Boolean> map = getMap();
            for (int c = 0; c < 9; c++) {
                char num = board[r][c];
                if (num != '.') {  // if cell is blank, no need to check
                    if (map.get(num)) {  // number already seen in current row
                        return false;
                    }
                    map.put(num, true);
                }
            }
        }
        return true;
    }

    private static boolean checkCols(char[][] board) {
        for (int c = 0; c < 9; c++) {
            Map<Character, Boolean> map = getMap();
            for (int r = 0; r < 9; r++) {
                char num = board[r][c];
                if (num != '.') {  // if cell is blank, no need to check
                    if (map.get(num)) {  // number already seen in current column
                        return false;
                    }
                    map.put(num, true);
                }
            }
        }
        return true;
    }


    private static boolean checkSqs(char[][] board) {
        for (int rowStart = 0; rowStart < 7; rowStart += 3) {
            for (int colStart = 0; colStart < 7; colStart += 3) {
                Map<Character, Boolean> map = getMap();
                for (int r = rowStart; r < rowStart + 3; r++) {
                    for (int c = colStart; c < colStart + 3; c++) {
                        char num = board[r][c];
                        if (num != '.') {
                            if (map.get(num)) {  // number already seen in current 3x3 square
                                return false;
                            }
                            map.put(num, true);
                        }
                    }
                }
            }
        }
        return true;
    }
}
