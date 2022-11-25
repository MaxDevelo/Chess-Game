package Model.Pieces;

import Model.Board;
import Model.Piece;
import Model.Square;

public class King  extends Piece {

    public King(Boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void moveAt(Square square, Board board, int row, int column) {

    }

    @Override
    public String getName() {
        return "K";
    }
}