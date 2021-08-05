package main;

import utils.Utils;

import java.util.*;
import java.util.stream.Collectors;

class Test {
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


        int[][] board1 = Arrays.copyOf(board, board.length);

         Arrays.stream(board).map(el -> el.clone()).toArray($ -> board.clone());


        board[8][8] = 88;
        Utils.print2dIntArray(board1);
    }


}