package be.inniger.tour;

import java.util.Arrays;
import java.util.stream.Stream;

// Mutable and ugly
public class Board {

    private final int nrRows;
    private final int nrCols;
    private final int[][] board;

    public Board(int nrRows, int nrCols) {
        this.nrRows = nrRows;
        this.nrCols = nrCols;
        this.board = new int[nrRows][nrCols];
    }

    public void print() {
        Stream.of(board)
                .map(Arrays::toString)
                .forEach(System.out::println);
        System.out.println();
    }

    public boolean canMark(int row, int col) {
        return row < nrRows && row >= 0 &&
                col < nrCols && col >= 0 &&
                board[row][col] == 0;
    }

    public void mark(int row, int col) {
        board[row][col] = 1;
    }

    public void unMark(int row, int col) {
        board[row][col] = 0;
    }

    public boolean isSolved() {
//        return Stream.of(board)
//                .flatMapToInt(Arrays::stream)
//                .noneMatch(i -> i == 0);
        for (int row = 0; row < nrRows; row++) {
            for (int col = 0; col < nrCols; col++) {
                if (board[row][col] == 0) {
                    return false;
                }
            }
        }

        return true;
    }
}
