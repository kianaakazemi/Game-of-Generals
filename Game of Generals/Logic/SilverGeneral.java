package Logic;

public class SilverGeneral extends Pieces {
    public SilverGeneral(PiecesColor color){
        super(color);
    }


    @Override
    public boolean move(Place start,Place end) {
        int delRow = end.getRow() - start.getRow();
        int delCol = Math.abs(end.getColumn() - start.getColumn());
        if (start.getPiece().getColor() == PiecesColor.white) {
            return (Math.abs(delRow) == 1 && delCol == 1) || (delRow == 1 && delCol == 0);
        }else{
            return (Math.abs(delRow) == 1 && delCol == 1) || (delRow == -1 && delCol == 0);
        }
    }

    @Override
    public boolean promotedMove(Place start,Place end) {
        int delRow = Math.abs(end.getRow() - start.getRow());
        int delCol = Math.abs(end.getColumn() - start.getColumn());
        return ((delRow == 1 && delCol == 1) || (delRow == 1 && delCol == 0) || (delRow == 0 && delCol == 1)
                || (delRow == 2 && delCol == 2) || (delRow == 2 && delCol == 0) || (delRow == 0 && delCol == 2))
                && clearPathPro(Game.gameBoard,start,end);
    }

    @Override
    public boolean clearPathPro(GameBoard board,Place start,Place end){
        boolean flag = true;
        int delRow = end.getRow() - start.getRow();
        int delCol = end.getColumn() - start.getColumn();
        if (delRow == 2 && delCol == 0) {
            if (board.board[5-start.getRow()-1][start.getColumn()-1].getPiece() != null) {
                flag = false;
            }
        }else if (delRow == -2 && delCol == 0) {
            if (board.board[5-start.getRow()+1][start.getColumn()-1].getPiece() != null) {
                flag = false;
            }
        }else if (delCol == 2 && delRow == 0) {
            if (board.board[5-start.getRow()][start.getColumn()-1+1].getPiece() != null) {
                flag = false;
            }
        }else if (delCol == -2 && delRow == 0) {
            if (board.board[5-start.getRow()][start.getColumn()-1-1].getPiece() != null) {
                flag = false;
            }
        }else if (delCol == -2 && delRow == -2) {
            if (board.board[5-start.getRow()+1][start.getColumn()-1-1].getPiece() != null) {
                flag = false;
            }
        }else if (delCol == 2 && delRow == 2) {
            if (board.board[5-start.getRow()-1][start.getColumn()-1+1].getPiece() != null) {
                flag = false;
            }
        }else if (delCol == 2 && delRow == -2) {
            if (board.board[5-start.getRow()+1][start.getColumn()-1+1].getPiece() != null) {
                flag = false;
            }
        }else if (delCol == -2 && delRow == 2) {
            if (board.board[5-start.getRow()-1][start.getColumn()-1-1].getPiece() != null) {
                flag = false;
            }
        }
        return flag;
    }
}
