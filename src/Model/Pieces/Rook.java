package Model.Pieces;

import Model.Board;
import Model.Piece;
import Model.Square;

public class Rook extends Piece {

    public Rook(Boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void moveAt(Square square, Board board) {


    }



    @Override
    public String getName() {
        return "R";
    }
}