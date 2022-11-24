package Model.Pieces;


import Model.Piece;

public class Knight  extends Piece  {

    public Knight(Boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void moveAt() {

    }

    @Override
    public String getName() {
        return "Kn";
    }
}