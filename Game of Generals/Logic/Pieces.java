package Logic;

public class Pieces {
private PiecesColor color;
private boolean promoted;

    public Pieces(PiecesColor color) {
        this.color = color;
        promoted = false;
    }

    public PiecesColor getColor() {
        return color;
    }

    public void setColor(PiecesColor color) {
        this.color = color;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public boolean move(Place start, Place end){
        return false;
    }

    public boolean promotedMove(Place start,Place end){
        return false;
    }

    public boolean clearPath(GameBoard board,Place start,Place end){
        return false;
    }

    public boolean clearPathPro(GameBoard board,Place start,Place end){
        return false;
    }

    public String findIcon(){
        if (color == PiecesColor.white) {
            if (this instanceof King) {
                return "src/icons/0-K.png";
            }else if (this instanceof Pawn) {
                return "src/icons/0-P.png";
            }else if (this instanceof SilverGeneral) {
                return "src/icons/0-SG.png";
            }else if (this instanceof GoldGeneral) {
                return "src/icons/0-GG.png";
            }else if (this instanceof Bishop) {
                return "src/icons/0-B.png";
            }else if (this instanceof Lance) {
                return "src/icons/0-L.png";
            }

        }else{
            if (this instanceof King) {
                return "src/icons/1-K.png";
            }else if (this instanceof Pawn) {
                return "src/icons/1-P.png";
            }else if (this instanceof SilverGeneral) {
                return "src/icons/1-SG.png";
            }else if (this instanceof GoldGeneral) {
                return "src/icons/1-GG.png";
            }else if (this instanceof Bishop) {
                return "src/icons/1-B.png";
            }else if (this instanceof Lance) {
                return "src/icons/1-L.png";
            }
        }
        return "";

    }

}
