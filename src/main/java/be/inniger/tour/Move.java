package be.inniger.tour;

public class Move {

    private final int rowOffset;
    private final int colOffset;

    private Move(int rowOffset, int colOffset) {
        this.rowOffset = rowOffset;
        this.colOffset = colOffset;
    }

    public static Move move(VerticalMove verticalMove, HorizontalMove horizontalMove) {
        return new Move(verticalMove.getOffset(), horizontalMove.getOffset());
    }

    public int getRowOffset() {
        return rowOffset;
    }

    public int getColOffset() {
        return colOffset;
    }
    
    private static abstract class AbstractMove {
        
        private final int offset;

        protected AbstractMove(int offset) {
            this.offset = offset;
        }

        public int getOffset() {
            return offset;
        }
    }
    
    public static class VerticalMove extends AbstractMove {

        private VerticalMove(int offset) {
            super(offset);
        }

        public static VerticalMove up(int nrSteps) {
            return new VerticalMove(nrSteps);
        }

        public static VerticalMove down(int nrSteps) {
            return new VerticalMove(-nrSteps);
        }
    }

    public static class HorizontalMove extends AbstractMove {

        public HorizontalMove(int offset) {
            super(offset);
        }

        public static HorizontalMove right(int nrSteps) {
            return new HorizontalMove(nrSteps);
        }

        public static HorizontalMove left(int nrSteps) {
            return new HorizontalMove(-nrSteps);
        }
    }
}
