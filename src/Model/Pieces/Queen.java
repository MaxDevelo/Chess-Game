package Model.Pieces;


import Model.Color;
import Model.Square;

public class Queen  extends Piece {

    private final Color _color;

    public Queen(Color color) {
        super(color);
        this._color = color;
    }

    @Override
    public Boolean canMove(Square square, Square currentSquare) {
        return null;
    }


    @Override
    public String getName() {
        return "Q";
    }
}