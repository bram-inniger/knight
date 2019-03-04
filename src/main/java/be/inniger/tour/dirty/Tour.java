package be.inniger.tour.dirty;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class Tour {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final int NR_ROWS = 8;
    private static final int NR_COLS = 8;

    private static final Collection<Move> MOVES = List.of(
            Move.of(2, 1),
            Move.of(1, 2),
            Move.of(-1, 2),
            Move.of(-2, 1),
            Move.of(-2, -1),
            Move.of(-1, -2),
            Move.of(1, -2),
            Move.of(2, -1));

    private final int[][] tour;

    public Tour() {
        this.tour = calculateTour();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        new Tour().print();
        System.out.println("Took ms: " + (System.currentTimeMillis() - start));
    }

    private int[][] calculateTour() {
        Board board = new Board(NR_ROWS, NR_COLS);
        Deque<Position> positionLog = new ArrayDeque<>();

        board.mark(0, 0);
        positionLog.push(Position.of(0, 0));

        solveRecursively(board, positionLog);

        int[][] endPos = new int[NR_ROWS][NR_COLS];
        int size = positionLog.size();
        for (int i = 0; i < size; i++) {
            Position position = positionLog.removeLast();
            endPos[position.row][position.col] = i;
        }

        return endPos;
    }

    private boolean solveRecursively(Board board, Deque<Position> positionLog) {
        if (board.isSolved()) {
            return true;
        }

        Position current = requireNonNull(positionLog.peek());

        for (Move move : MOVES) {
            int nextRow = current.row + move.rowOffset;
            int nextCol = current.col + move.colOffset;

            if (board.canMark(nextRow, nextCol)) {
                board.mark(nextRow, nextCol);
                positionLog.push(Position.of(nextRow, nextCol));

                boolean success = solveRecursively(board, positionLog);

                if (success) {
                    return true;
                }

                board.unMark(nextRow, nextCol);
                positionLog.pop();
            }
        }

        return false;
    }

    private void print() {
        Stream.of(tour)
                .map(intArr -> Arrays.stream(intArr)
                        .mapToObj(i -> String.format("%s%02d%s", getColour(i), i, ANSI_RESET))
                        .collect(Collectors.joining(" ")))
                .forEach(System.out::println);
    }

    private String getColour(int i) {
        int index = i * 8 / (NR_ROWS * NR_COLS);

        switch (index) {
            case 0:
                return ANSI_BLACK;
            case 1:
                return ANSI_BLUE;
            case 2:
                return ANSI_CYAN;
            case 3:
                return ANSI_GREEN;
            case 4:
                return ANSI_PURPLE;
            case 5:
                return ANSI_RED;
            case 6:
                return ANSI_WHITE;
            default:
                return ANSI_YELLOW;
        }
    }

    private static class Move {

        private final int rowOffset;
        private final int colOffset;

        private Move(int rowOffset, int colOffset) {
            this.rowOffset = rowOffset;
            this.colOffset = colOffset;
        }

        private static Move of(int rowOffset, int colOffset) {
            return new Move(rowOffset, colOffset);
        }
    }

    private static class Position {

        private final int row;
        private final int col;

        private static final Map<Integer, Position> POSITIONS;

        static {
            POSITIONS = new HashMap<>();
            for (int row = 0; row < NR_ROWS; row++) {
                for (int col = 0; col < NR_COLS; col++) {
                    POSITIONS.put(row * NR_COLS + col, new Position(row, col));
                }
            }
        }

        private Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        private static Position of(int row, int col) {
            return POSITIONS.get(row * NR_COLS + col);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return row == position.row &&
                    col == position.col;
        }

        @Override
        public int hashCode() {
            return row * NR_COLS + col;
        }
    }
}
