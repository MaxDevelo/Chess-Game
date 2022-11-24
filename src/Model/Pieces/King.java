package Model.Pieces;

import Model.Piece;

public class King  extends Piece {

    public King(Boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void moveAt() {

    }
    @Override
    public String getName() {
        return "K";
    }
}