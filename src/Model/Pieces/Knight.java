package Model.Pieces;


import Model.Color;
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
    @Override
    public String getImage(){
        return  "img/Pieces/Knight";
    }
    @Override
    public Boolean up(Square square, Square currentSquare) {
        return false;
    }

    @Override
    public Boolean down(Square square, Square currentSquare) {
        return false;
    }

    @Override
    public Boolean left(Square square, Square currentSquare) {
        return false;
    }

    @Override
    public Boolean right(Square square, Square currentSquare) {
        return false;
    }
}