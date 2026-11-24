package Logic;

public class GoldGeneral extends Pieces {
    public GoldGeneral(PiecesColor color){
        super(color);
    }

    @Override
    public boolean move(Place start,Place end) {
        int delRow = end.getRow() - start.getRow();
        int delCol = Math.abs(end.getColumn() - start.getColumn());
        if (start.getPiece().getColor() == PiecesColor.white) {
            return (delRow == 1 && delCol == 1) || (delRow == 1 && delCol == 0) || (delRow == 0 && delCol == 1) || (delRow == -1 && delCol == 0);
        }else{
            return (delRow == -1 && delCol == 1) || (delRow == 1 && delCol == 0) || (delRow == 0 && delCol == 1) || (delRow == -1 && delCol == 0);
        }
    }
}
