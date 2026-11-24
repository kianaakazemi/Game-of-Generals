package Logic;

import java.util.ArrayList;
import java.util.List;


public class Game {
    public static GameBoard gameBoard = new GameBoard();
    public static List<Pieces> outB = new ArrayList<>();
    public static List<Pieces> outW = new ArrayList<>();
    public static King WKing = (King) gameBoard.board[4][0].getPiece();
    public static King BKing = (King)gameBoard.board[0][4].getPiece();
    public static int turn = 1;
    public static boolean endGame = false;


    public void move(char ch,int startR,int startC,int endR,int endC){
        Place start = gameBoard.board[5-startR][startC-1];
        Place end = gameBoard.board[5-endR][endC-1];
        Pieces p = start.getPiece();
        if (start.getPiece() != null && Character.isLowerCase(ch) && start.getPiece().getColor() == PiecesColor.black && turn%2 == 1 ) {
            if (Character.toLowerCase(ch) == Character.toLowerCase(p.getClass().getSimpleName().charAt(0))) {

                if (start.getPiece().isPromoted()) {
                    if (start.getPiece().promotedMove(start, end)) {
                        if (end.getPiece() != null && end.getPiece().getColor() == PiecesColor.white) {
                            end.getPiece().setPromoted(false);
                            end.getPiece().setColor(PiecesColor.black);
                            outB.add(end.getPiece());
                            end.setPiece(null);
                        }
                        if (end.getPiece() == null) {
                            end.setPiece(start.getPiece());
                            start.setPiece(null);
                            if (end.getPiece().getColor() == PiecesColor.black && (endR == 1 || endR == 2)) {
                                if (end.getPiece() instanceof Pawn || end.getPiece() instanceof Lance || end.getPiece() instanceof Bishop || end.getPiece() instanceof SilverGeneral) {
                                    end.getPiece().setPromoted(true);
                                }
                            }
                            turn++;
                        }
                    }

                } else {
                    if (start.getPiece().move(start, end)) {
                        if (end.getPiece() != null && end.getPiece().getColor() == PiecesColor.white) {
                            end.getPiece().setPromoted(false);
                            end.getPiece().setColor(PiecesColor.black);
                            outB.add(end.getPiece());
                            end.setPiece(null);
                        }
                        if (end.getPiece() == null) {
                            end.setPiece(start.getPiece());
                            start.setPiece(null);
                            if (end.getPiece().getColor() == PiecesColor.black && (endR == 1 || endR == 2)) {
                                if (end.getPiece() instanceof Pawn || end.getPiece() instanceof Lance || end.getPiece() instanceof Bishop || end.getPiece() instanceof SilverGeneral) {
                                    end.getPiece().setPromoted(true);
                                }
                            }
                            turn++;
                        }
                    }
                }
            }


        }else if (start.getPiece() != null && Character.isUpperCase(ch) && start.getPiece().getColor() == PiecesColor.white && turn%2 == 0) {
            if (Character.toLowerCase(ch) == Character.toLowerCase(p.getClass().getSimpleName().charAt(0))) {
                if (start.getPiece().isPromoted()) {
                    if (start.getPiece().promotedMove(start,end)) {
                        if (end.getPiece() != null && end.getPiece().getColor() == PiecesColor.black) {
                            end.getPiece().setPromoted(false);
                            end.getPiece().setColor(PiecesColor.white);
                            outW.add(end.getPiece());
                            end.setPiece(null);
                        }
                        if (end.getPiece() == null) {
                            end.setPiece(start.getPiece());
                            start.setPiece(null);
                            if (end.getPiece().getColor() == PiecesColor.white && (endR == 4 || endR == 5)) {
                                if (end.getPiece() instanceof Pawn || end.getPiece() instanceof Lance || end.getPiece() instanceof Bishop || end.getPiece() instanceof SilverGeneral) {
                                    end.getPiece().setPromoted(true);
                                }
                            }
                            turn++;
                        }
                    }

                }else{
                    if (start.getPiece().move(start, end)) {
                        if (end.getPiece() != null && end.getPiece().getColor() == PiecesColor.black) {
                            end.getPiece().setPromoted(false);
                            end.getPiece().setColor(PiecesColor.white);
                            outW.add(end.getPiece());
                            end.setPiece(null);
                        }
                        if (end.getPiece() == null) {
                            end.setPiece(start.getPiece());
                            start.setPiece(null);
                            if (end.getPiece().getColor() == PiecesColor.white && (endR == 4 || endR == 5)) {
                                if (end.getPiece() instanceof Pawn || end.getPiece() instanceof Lance || end.getPiece() instanceof Bishop || end.getPiece() instanceof SilverGeneral) {
                                    end.getPiece().setPromoted(true);
                                }
                            }
                            turn++;
                        }
                    }
                }
            }
        }
        System.out.println(printState());

        if (outB.contains(WKing)) {
            System.out.println("black wins!");
            endGame = true;
        } else if (outW.contains(BKing)) {
            System.out.println("white wins!");
            endGame = true;
        }
    }

    public void backPiece(char ch,int R,int C){
        Pieces piece;
        if (turn % 2 == 0) {
            for (Pieces p : outW) {
                if (p.getClass().getSimpleName().charAt(0) == ch) {
                    piece = p;
                    if (gameBoard.board[5 - R][C - 1].getPiece() == null) {
                       // piece.setColor(PiecesColor.white);
                        gameBoard.board[5 - R][C - 1].setPiece(piece);
                        outW.remove(piece);
                        turn++;
                        break;
                    }
                }
            }
        } else if (turn % 2 == 1) {
            for (Pieces p : outB) {
                if (p.getClass().getSimpleName().toLowerCase().charAt(0) == ch) {
                    piece = p;
                    if (gameBoard.board[5 - R][C - 1].getPiece() == null) {
                       // piece.setColor(PiecesColor.black);
                        gameBoard.board[5 - R][C - 1].setPiece(piece);
                        outB.remove(piece);
                        turn++;
                        break;
                    }
                }
            }
        }
        System.out.println(printState());
    }

    public String printState(){
        String boardStr = "";
        String bOut = "";
        String wOut = "";
        String finalStr;

        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < 5; j++) {
                if(gameBoard.board[i][j].getPiece() == null){
                    boardStr += "-";
                }else{
                    if (gameBoard.board[i][j].getPiece().getColor() == PiecesColor.black) {
                        boardStr += Character.toLowerCase(gameBoard.board[i][j].getPiece().getClass().getSimpleName().charAt(0));
                    }else{
                        boardStr += gameBoard.board[i][j].getPiece().getClass().getSimpleName().charAt(0);
                    }

                }
            }
        }
        for (Pieces p : outB) {
            bOut += p.getClass().getSimpleName().toLowerCase().charAt(0);
        }
        for (Pieces p : outW) {
            wOut += p.getClass().getSimpleName().charAt(0);
        }

        finalStr = boardStr + "\n" + bOut + "\n" + wOut;
        return finalStr;
    }

    public void playGame(String letter, int start,int end){
            if (letter.equals("0")) {
                return;
            }
            if (start == 0 && end < 60 && end % 10 > 0 && end % 10 < 6 && end / 10 > 0) {
                backPiece(letter.charAt(0) ,end%10,end/10);
            }else if (start < 60 && end < 60 && start % 10 > 0 && start % 10 < 6 && end % 10 > 0 && end % 10 < 6 && start / 10 > 0 && end / 10 > 0)  {
                move(letter.charAt(0) ,start%10,start/10,end%10,end/10);
            }else{
                System.out.println(printState());
            }
    }
}
