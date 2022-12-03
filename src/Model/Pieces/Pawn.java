package Model.Pieces;

import Model.Color;
import Model.Square;

public class Pawn  extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public Boolean canMove(Square square, Square currentSquare) {

        int currentRow = currentSquare.getRow();
        int currentColumn = currentSquare.getColumn();
        int row = square.getRow();
        int column = square.getColumn();
        if(this.getColor() == Model.Color.WHITE) {
            if((row == currentRow-1 && currentColumn == column) && square.getPiece() == null ||
                    ((row == currentRow-1) && (currentColumn-1 == column))  && square.getPiece() != null && square.getPiece().getColor() != this.getColor() ||
                    ((row == currentRow-1) && (currentColumn+1 == column))  && square.getPiece() != null  && square.getPiece().getColor() != this.getColor()){
                return true;
            }
        }else{
            if((row == currentRow+1 && currentColumn == column)&& square.getPiece() == null ||
                    ((row == currentRow+1) && (currentColumn-1 == column))  && square.getPiece() != null   && square.getPiece().getColor() != this.getColor()||
                    ((row == currentRow+1) && (currentColumn+1 == column))  && square.getPiece() != null  && square.getPiece().getColor() != this.getColor()){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return "P";
    }
}