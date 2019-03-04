package be.inniger.tour;

class Move {

    private final int rowOffset;
    private final int colOffset;

    private Move(int rowOffset, int colOffset) {
        this.rowOffset = rowOffset;
        this.colOffset = colOffset;
    }

    public static Move of(int rowOffset, int colOffset) {
        return new Move(rowOffset, colOffset);
    }

    public int getRowOffset() {
        return rowOffset;
    }

    public int getColOffset() {
        return colOffset;
    }
    
    // TODO -- move DSL
}
