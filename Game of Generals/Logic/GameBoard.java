package Logic;

public class GameBoard {
    public Place[][] board = new Place[5][5];

    public GameBoard(){

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new Place(5-i , j+1 , null);
            }
        }

        board[3][0] = new Place(2,1,new Pawn(PiecesColor.white));
        board[4][0] = new Place(1,1,new King(PiecesColor.white));
        board[4][1] = new Place(1,2,new GoldGeneral(PiecesColor.white));
        board[4][2] = new Place(1,3,new SilverGeneral(PiecesColor.white));
        board[4][3] = new Place(1,4,new Bishop(PiecesColor.white));
        board[4][4] = new Place(1,5,new Lance(PiecesColor.white));


        board[1][4] = new Place(4,5,new Pawn(PiecesColor.black));
        board[0][0] = new Place(5,1,new Lance(PiecesColor.black));
        board[0][1] = new Place(5,2,new Bishop(PiecesColor.black));
        board[0][2] = new Place(5,3,new SilverGeneral(PiecesColor.black));
        board[0][3] = new Place(5,4,new GoldGeneral(PiecesColor.black));
        board[0][4] = new Place(5,5,new King(PiecesColor.black));

    }
}
