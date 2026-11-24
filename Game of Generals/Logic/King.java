package Logic;

public class King extends Pieces {
    public King(PiecesColor color){
        super(color);
    }

    @Override
    public boolean move(Place start,Place end) {
            int delRow = Math.abs(end.getRow() - start.getRow());
            int delCol = Math.abs(end.getColumn() - start.getColumn());
            return (delRow == 1 && delCol == 1) || (delRow == 1 && delCol == 0) || (delRow == 0 && delCol == 1);

    }

}
