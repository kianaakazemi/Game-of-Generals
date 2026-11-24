package Logic;

public class Place {
    private int row;
    private int column;
    private Pieces piece;

    public Place(int row, int column, Pieces piece) {
        this.row = row;
        this.column = column;
        this.piece = piece;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Pieces getPiece() {
        return piece;
    }

    public void setPiece(Pieces piece) {
        this.piece = piece;
    }
}
