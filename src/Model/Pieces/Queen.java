package Model.Pieces;


import Model.Board;
import Model.Piece;
import Model.Square;

public class Queen  extends Piece {

    private final Boolean isWhite;

    public Queen(Boolean isWhite) {
        super(isWhite);
        this.isWhite = isWhite;
    }

    @Override
    public void moveAt(Square square, Board board, int row, int column) {

    }


    @Override
    public String getName() {
        return "Q";
    }
}