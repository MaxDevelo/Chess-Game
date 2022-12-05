package Model.Pieces;


import Model.Color;
import Model.Square;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public Boolean canMove(Square square, Square currentSquare) {
        return null;
    }


    @Override
    public String getName() {
        return "B";
    }
    @Override
    public String getImage(){
        return  "img/Pieces/Bishop";
    }

}
