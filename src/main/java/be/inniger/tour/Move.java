package be.inniger.tour;

import java.util.Objects;

public class Move {

    private final int verticalOffset;
    private final int horizontalOffset;

    private Move(int verticalOffset, int horizontalOffset) {
        this.verticalOffset = verticalOffset;
        this.horizontalOffset = horizontalOffset;
    }

    public static Move of(VerticalOffset verticalOffset, HorizontalOffset horizontalOffset) {
        return new Move(verticalOffset.getNumericValue(), horizontalOffset.getNumericValue());
    }

    public int getVerticalOffset() {
        return verticalOffset;
    }

    public int getHorizontalOffset() {
        return horizontalOffset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return getVerticalOffset() == move.getVerticalOffset() &&
                getHorizontalOffset() == move.getHorizontalOffset();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVerticalOffset(), getHorizontalOffset());
    }

    @Override
    public String toString() {
        return "Move{" +
                "verticalOffset=" + verticalOffset +
                ", horizontalOffset=" + horizontalOffset +
                '}';
    }

    public interface Offset {

        int getNumericValue();
    }

    public static class HorizontalOffset implements Offset {

        private final int numericValue;

        private HorizontalOffset(int numericValue) {
            this.numericValue = numericValue;
        }

        public static HorizontalOffset right(int steps) {
            return new HorizontalOffset(steps);
        }


        public static HorizontalOffset left(int steps) {
            return new HorizontalOffset(-steps);
        }

        public int getNumericValue() {
            return numericValue;
        }
    }

    public static class VerticalOffset implements Offset {

        private final int numericValue;

        private VerticalOffset(int numericValue) {
            this.numericValue = numericValue;
        }

        public static VerticalOffset up(int steps) {
            return new VerticalOffset(steps);
        }


        public static VerticalOffset down(int steps) {
            return new VerticalOffset(-steps);
        }

        public int getNumericValue() {
            return numericValue;
        }
    }
}
