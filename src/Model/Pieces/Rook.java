package Model.Pieces;

import Model.Piece;

public class Rook extends Piece {

    public Rook(Boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void moveAt() {

    }
    @Override
    public String getName() {
        return "R";
    }
}