package Model.Pieces;

import Model.Color;
import Model.Square;

public abstract class Piece {
    private Color _color;
    public Piece(Color color){
        this._color = color;
    }
    public Color getColor() {
        return _color;
    }

    public abstract Boolean canMove(Square square, Square currentSquare);
    public abstract Type getType();
    public abstract String getImage();
    public abstract Boolean up(Square square, Square currentSquare);
    public abstract Boolean down(Square square, Square currentSquare);
    public abstract Boolean left(Square square, Square currentSquare);
    public abstract Boolean right(Square square, Square currentSquare);
    public abstract int getScore();
    public abstract Boolean isFirstMove();
    public abstract void setIsFirstMove(Boolean isFirstMove);


}
