package Logic;

public class Lance extends Pieces {
    public Lance(PiecesColor color){
        super(color);
    }

    @Override
    public boolean move(Place start,Place end) {
        int delRow = end.getRow() - start.getRow();
        int delCol = end.getColumn() - start.getColumn();
        if (start.getPiece().getColor() == PiecesColor.white) {
            return (delRow > 0 && delCol == 0) && clearPath(Game.gameBoard,start,end);
        }else{
            return (delRow < 0 && delCol == 0) && clearPath(Game.gameBoard,start,end);
        }
    }

    @Override
    public boolean promotedMove(Place start,Place end) {
        int delRow = end.getRow() - start.getRow();
        int delCol = end.getColumn() - start.getColumn();
        return ((delRow != 0 && delCol == 0) || (delRow == 0 && delCol != 0)) && clearPathPro(Game.gameBoard,start,end);
    }

    @Override
    public boolean clearPath(GameBoard board,Place start,Place end){
        boolean flag = true;
        int delRow = end.getRow() - start.getRow();
        for (int i = 1; i < Math.abs(delRow); i++) {
            Place place;
            if (start.getPiece().getColor() == PiecesColor.white) {
                 place = board.board[5-start.getRow()-i][start.getColumn()-1];
            }else{
                 place = board.board[5-start.getRow()+i][start.getColumn()-1];
            }
            if (place.getPiece() != null) {
                flag = false;
                break;
            }
        }
        return flag;
    }


    @Override
    public boolean clearPathPro(GameBoard board,Place start,Place end){
        boolean flag = true;
        int delRow = end.getRow() - start.getRow();
        int delCol = end.getColumn() - start.getColumn();
        if (delRow > 0) {
            for (int i = 1; i < delRow; i++) {
                if (board.board[5-start.getRow()-i][start.getColumn()-1].getPiece() != null) {
                    flag = false;
                    break;
                }
            }
        } else if (delRow < 0) {
            for (int i = 1; i < Math.abs(delRow); i++) {
                if (board.board[5-start.getRow()+i][start.getColumn()-1].getPiece() != null) {
                    flag = false;
                    break;
                }
            }

        } else if (delCol > 0) {
            for (int i = 1; i < delCol; i++) {
                if (board.board[5-start.getRow()][start.getColumn()-1+i].getPiece() != null) {
                    flag = false;
                    break;
                }
            }

        }else if(delCol < 0){
            for (int i = 1; i < Math.abs(delCol); i++) {
                if (board.board[5-start.getRow()][start.getColumn()-1-i].getPiece() != null) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
}
