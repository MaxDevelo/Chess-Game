package Model.Pieces;


import Model.Piece;

public class Queen  extends Piece {

    public Queen(Boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void moveAt() {

    }
    @Override
    public String getName() {
        return "Q";
    }
}