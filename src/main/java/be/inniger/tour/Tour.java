package be.inniger.tour;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class Tour {

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
        Board board = new Board();
        Deque<Position> positionLog = new ArrayDeque<>();

        board.mark(0, 0);
        positionLog.push(Position.of(0, 0));

        solveRecursively(board, positionLog);

        int[][] endPos = new int[Constants.NR_ROWS][Constants.NR_COLS];
        int size = positionLog.size();
        for (int i = 0; i < size; i++) {
            Position position = positionLog.removeLast();
            endPos[position.getRow()][position.getCol()] = i;
        }

        return endPos;
    }

    private boolean solveRecursively(Board board, Deque<Position> positionLog) {
        if (board.isSolved()) {
            return true;
        }

        Position current = requireNonNull(positionLog.peek());

        for (Move move : MOVES) {
            int nextRow = current.getRow() + move.getRowOffset();
            int nextCol = current.getCol() + move.getColOffset();

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
        ColourFormatter colourFormatter = ColourFormatter.with(Constants.NR_ROWS * Constants.NR_COLS);
        
        Stream.of(tour)
                .map(intArr -> Arrays.stream(intArr)
                        .mapToObj(i -> colourFormatter.format("%s%02d%s", i))
                        .collect(Collectors.joining(" ")))
                .forEach(System.out::println);
    }
}
