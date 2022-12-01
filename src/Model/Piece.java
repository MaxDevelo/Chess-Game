package Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private Color _color;
    public Piece(Color color){
        this._color = color;
    }
    public Color getColor() {
        return _color;
    }

    public abstract Boolean canMove(Square square, Square currentSquare);
    public abstract String getName();


}
