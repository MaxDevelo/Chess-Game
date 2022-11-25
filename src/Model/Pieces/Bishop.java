package Model.Pieces;


import Model.Board;
import Model.Piece;
import Model.Square;

public class Bishop extends Piece {

    public Bishop(Boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void moveAt(Square square, Board board, int row, int column) {

    }



    @Override
    public String getName() {
        return "B";
    }

}
