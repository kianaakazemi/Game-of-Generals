import java.util.Scanner;
import Logic.*;

public class Main {
    static  Scanner myScanner = new Scanner(System.in);
    static Game game = new Game();
    public static void main(String[] args) {
      playGame();
    }

    public static void playGame(){
        while (!Game.endGame) {
            String letter = myScanner.next();
            if (letter.equals("0")) {
                return;
            }
            int start = myScanner.nextInt();
            int end = myScanner.nextInt();
            if (start == 0 && end < 60 && end % 10 > 0 && end % 10 < 6 && end / 10 > 0) {
                game.backPiece(letter.charAt(0) ,end%10,end/10);
            }else if (start < 60 && end < 60 && start % 10 > 0 && start % 10 < 6 && end % 10 > 0 && end % 10 < 6 && start / 10 > 0 && end / 10 > 0)  {
                game.move(letter.charAt(0) ,start%10,start/10,end%10,end/10);
            }else{
                System.out.println(game.printState());
            }
        }
    }
}
