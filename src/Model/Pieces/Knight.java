package Model.Pieces;


import Model.Color;
import Model.Piece;
import Model.Square;

public class Knight  extends Piece  {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public Boolean canMove(Square square, Square currentSquare) {
        return null;
    }


    @Override
    public String getName() {
        return "Kn";
    }
}