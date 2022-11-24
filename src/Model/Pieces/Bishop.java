package Model.Pieces;


import Model.Piece;

public class Bishop extends Piece {

    public Bishop(Boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void moveAt() {

    }

    @Override
    public String getName() {
        return "B";
    }

}
