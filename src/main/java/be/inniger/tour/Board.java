package be.inniger.tour;

// Mutable and ugly
public class Board {

    private final int[][] board;

    public Board() {
        this.board = new int[Constants.NR_ROWS][Constants.NR_COLS];
    }

    public boolean canMark(int row, int col) {
        return row >= 0 && row < Constants.NR_ROWS &&
                col >= 0 && col < Constants.NR_COLS &&
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
        for (int row = 0; row < Constants.NR_ROWS; row++) {
            for (int col = 0; col < Constants.NR_COLS; col++) {
                if (board[row][col] == 0) {
                    return false;
                }
            }
        }

        return true;
    }
}
