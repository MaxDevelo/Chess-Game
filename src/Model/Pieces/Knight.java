package Model.Pieces;


import Model.Board;
import Model.Piece;
import Model.Square;

public class Knight  extends Piece  {

    public Knight(Boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void moveAt(Square square, Board board) {

    }

    @Override
    public String getName() {
        return "Kn";
    }
}