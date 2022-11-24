package Model.Pieces;

import Model.Piece;

public class Pawn  extends Piece {

    public Pawn(Boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void moveAt() {

    }
    @Override
    public String getName() {
        return "P";
    }
}