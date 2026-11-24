package Logic;


public class Bishop extends Pieces {
    public Bishop(PiecesColor color){
        super(color);
    }

    @Override
    public boolean move(Place start, Place end) {
            int delRow = end.getRow() - start.getRow();
            int delCol = end.getColumn() - start.getColumn();
            if (start.getPiece().getColor() == PiecesColor.white) {
                return (Math.abs(delRow) == Math.abs(delCol) && delRow > 0) && clearPath(Game.gameBoard,start,end);
            }else{
                return (Math.abs(delRow) == Math.abs(delCol)  && delRow < 0) && clearPath(Game.gameBoard,start,end);
            }
    }


    @Override
    public boolean promotedMove(Place start, Place end) {
            int delRow = Math.abs(end.getRow() - start.getRow());
            int delCol = Math.abs(end.getColumn() - start.getColumn());
            return (delRow == delCol && delCol != 0) || (delRow == 1 && delCol == 0) || (delRow == 0 && delCol == 1)
                    && clearPathPro(Game.gameBoard,start,end);
    }

    @Override
    public boolean clearPath(GameBoard board, Place start, Place end){
        boolean flag = true;
        int delCol = end.getColumn() - start.getColumn();
        if (start.getPiece().getColor() == PiecesColor.white) {
            if (delCol > 0) {
                for (int i = 1; i < delCol; i++) {
                    if (board.board[5-start.getRow()-i][start.getColumn()-1+i].getPiece() != null) {
                        flag = false;
                        break;
                    }
                }
            } else if (delCol < 0) {
                for (int i = 1; i < Math.abs(delCol); i++) {
                    if ( board.board[5-start.getRow()-i][start.getColumn()-1-i].getPiece() != null) {
                        flag = false;
                        break;
                    }
                }
            }
        }else{
            if (delCol > 0) {
                for (int i = 1; i < delCol; i++) {
                    if (board.board[5-start.getRow()+i][start.getColumn()-1+i].getPiece() != null) {
                        flag = false;
                        break;
                    }
                }
            } else if (delCol < 0) {
                for (int i = 1; i < Math.abs(delCol); i++) {
                    if (board.board[5-start.getRow()+i][start.getColumn()-1-i].getPiece() != null) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    @Override
    public boolean clearPathPro(GameBoard board, Place start, Place end){
        boolean flag = true;
        int delRow = end.getRow() - start.getRow();
        int delCol = end.getColumn() - start.getColumn();
        if (delCol > 0 && delRow > 0) {
            for (int i = 1; i < delCol; i++) {
                if ( board.board[5-start.getRow()-i][start.getColumn()-1+i].getPiece() != null) {
                    flag = false;
                    break;
                }
            }
        } else if (delCol < 0 && delRow >0) {
            for (int i = 1; i < Math.abs(delCol); i++) {
                if (board.board[5-start.getRow()-i][start.getColumn()-1-i].getPiece() != null) {
                    flag = false;
                    break;
                }
            }
        } else if (delCol > 0 && delRow < 0) {
            for (int i = 1; i < delCol; i++) {
                if ( board.board[5-start.getRow()+i][start.getColumn()-1+i].getPiece() != null) {
                    flag = false;
                    break;
                }
            }
        } else if (delCol < 0 && delRow < 0) {
            for (int i = 1; i < Math.abs(delCol); i++) {
                if (board.board[5-start.getRow()+i][start.getColumn()-1-i].getPiece() != null) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
}
