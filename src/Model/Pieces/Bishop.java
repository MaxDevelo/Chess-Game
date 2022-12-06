package Model.Pieces;


import Model.Color;
import Model.Square;

public class Bishop extends Piece {

    private Square friendPieceScare;
    public Bishop(Color color) {
        super(color);
        this.friendPieceScare = null;
    }


    @Override
    public Boolean canMove(Square square, Square currentSquare) {
        return false;
    }


    @Override
    public String getName() {
        return "B";
    }
    @Override
    public String getImage(){
        return  "img/Pieces/Bishop";
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
