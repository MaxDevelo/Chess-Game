package Model.Pieces;

import Model.Color;
import Model.Square;

public class King  extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public Boolean canMove(Square square, Square currentSquare) {
        return null;
    }

    @Override
    public String getName() {
        return "K";
    }
    @Override
    public String getImage(){
        return  "img/Pieces/King";
    }
}