package GUI;

import Logic.Game;
import Logic.Pieces;
import Logic.PiecesColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame {
    Game game = new Game();
    Button[][] board = new Button[5][5];
    Button[][] whiteOut = new Button[5][2];
    Button[][] blackOut = new Button[5][2];
    JPanel panel = new JPanel();


    public GameFrame(){
        this.setSize(new Dimension(1440 , 800));
        this.setTitle("<< Game Of Generals >>");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(panel);
        panel.setLayout(new GridLayout(5,9));
        setButtons();
        this.setVisible(true);
        setIcons();

    }

    public void setButtons() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                whiteOut[i][j] = new Button(5 - i,-j);
                whiteOut[i][j].setOpaque(true);
                whiteOut[i][j].setBackground(Color.gray);
                panel.add(whiteOut[i][j]);
                whiteOut[i][j].addMouseListener(click);
            }
            for (int j = 0; j < 5; j++) {
                board[i][j] = new Button(5 - i, j + 1);
                board[i][j].setOpaque(true);
                if ((i + j) % 2 == 0) {
                    board[i][j].setBackground(Color.black);
                } else {
                    board[i][j].setBackground(Color.gray);
                }
                panel.add(board[i][j]);
                board[i][j].addMouseListener(click);
            }
            for (int j = 0; j < 2; j++) {
                blackOut[i][j] = new Button(5 - i,  6 + j);
                blackOut[i][j].setOpaque(true);
                blackOut[i][j].setBackground(Color.gray);
                panel.add(blackOut[i][j]);
                blackOut[i][j].addMouseListener(click);
            }
        }
    }
    MouseListener click = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Button button = (Button) e.getComponent();
            Pieces pieceEnd;
            if (isOut(button)) {
                if (Game.turn % 2 == 0) {
                    pieceEnd = whatPieceWhite(button);
                }else{
                    pieceEnd = whatPieceBlack(button);
                }
            }else{
                pieceEnd = Game.gameBoard.board[5 - button.row][button.column - 1].getPiece();
            }
            if (Button.first == null) {
                if ((Game.turn % 2 == 0 && pieceEnd.getColor() == PiecesColor.white) || (Game.turn % 2 == 1 && pieceEnd.getColor() == PiecesColor.black)) {
                    Button.first = button;
                }
            } else {
                Pieces pieceStart;
                if (isOut(Button.first)) {
                    if (Game.turn % 2 == 0) {
                        pieceStart = whatPieceWhite(Button.first);
                    }else{
                        pieceStart = whatPieceBlack(Button.first);
                    }

                }else{
                    pieceStart = Game.gameBoard.board[5 - Button.first.row][Button.first.column - 1].getPiece();
                }

                String letter;
                if (pieceStart != null) {
                    if (pieceStart.getColor() == PiecesColor.black) {
                        letter = pieceStart.getClass().getSimpleName().substring(0, 1).toLowerCase();
                    } else {
                        letter = pieceStart.getClass().getSimpleName().substring(0, 1);
                    }

                    if (isOut(Button.first)) {
                        game.playGame(letter, 0, button.column * 10 + button.row);
                    }else{
                        game.playGame(letter, Button.first.column * 10 + Button.first.row, button.column * 10 + button.row);
                    }
                    Button.first = null;
                }
            }
            setIcons();
            String popUp = "";
            if (Game.outB.contains(Game.WKing)) {
                Game.endGame = true;
                popUp = "Black wins!";
            } else if (Game.outW.contains(Game.BKing)) {
                Game.endGame = true;
                popUp = "White wins!";
            }
            if (Game.endGame) {
                JOptionPane.showMessageDialog(board[2][2], popUp);
                System.exit(0);
            }

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    public Pieces whatPieceWhite(Button button){
        if (button.column == 0) {
            return Game.outW.get(((5 - button.row)*2 )+ 1);
        }else{
            return Game.outW.get((5 - button.row)*2);
        }
    }

    public Pieces whatPieceBlack(Button button){
        if (button.column == 6) {
            return Game.outB.get(((5 - button.row)*2 )+ 1);
        }else{
            return Game.outB.get((5 - button.row)*2);
        }
    }

    public boolean isOut(Button button){
        boolean flag = false;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                if (blackOut[i][j]==(button) || whiteOut[i][j]==(button)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
    public void setIcons(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (Game.gameBoard.board[i][j].getPiece() != null) {
                    board[i][j].setIcon(new ImageIcon(Game.gameBoard.board[i][j].getPiece().findIcon()));
                }else{
                    board[i][j].setIcon(null);
                }
            }
        }
        int c = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 1 ;j >= 0 ; j--) {
                if (Game.outW.size() > c && Game.outW.get(c) != null) {
                    whiteOut[i][j].setIcon(new ImageIcon(Game.outW.get(c).findIcon()));
                }else{
                    whiteOut[i][j].setIcon(null);
                }

                if (Game.outB.size() > c && Game.outB.get(c) != null) {
                    blackOut[i][j].setIcon(new ImageIcon(Game.outB.get(c).findIcon()));
                }else{
                    blackOut[i][j].setIcon(null);
                }
                c++;
            }
        }
    }
}
