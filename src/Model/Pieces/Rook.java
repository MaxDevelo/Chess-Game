package Model.Pieces;

import Model.Color;
import Model.Square;

import static javax.swing.text.SimpleAttributeSet.EMPTY;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color);
    }

    @Override
    public Boolean canMove(Square square, Square currentSquare) {
        int fromRow = currentSquare.getRow();
        int fromCol = currentSquare.getColumn();
        int toRow = square.getRow();
        int toCol = square.getColumn();
        int i;

        if (fromRow == toRow && fromCol == toCol)
            return false;

        if (fromRow == toRow) {
            // Horizontal move
            if (fromCol < toCol) {
                // Move right
                for (i = fromCol + 1; i <= toCol; ++i){
                    if(square.getPiece() != null){
                        if (currentSquare.getRow() == square.getRow() &&  currentSquare.getColumn() == square.getColumn()+i && square.getPiece().getColor() == currentSquare.getPiece().getColor()){
                            return false;
                        }
                    }
                }
            } else {
                // Move left
                for (i = fromCol - 1; i >= toCol; --i){
                    if(square.getPiece() != null){
                        if (currentSquare.getRow() == square.getRow() &&  currentSquare.getColumn() == square.getColumn()+i && square.getPiece().getColor() == currentSquare.getPiece().getColor()){
                            return false;
                        }
                    }
                }
            }
        } else if (fromCol == toCol) {
            // Vertical move
            if (fromRow < toRow) {
                // Move down
                for (i = fromRow + 1; i <= toRow; ++i){
                    if(square.getPiece() != null) {
                        if (currentSquare.getRow() == square.getRow()+i &&  currentSquare.getColumn() == square.getColumn() && square.getPiece().getColor() == currentSquare.getPiece().getColor())
                            return false;
                    }
                }
            } else {
                // Move up
                for (i = fromRow - 1; i >= toRow; --i){
                    if(square.getPiece() != null) {
                        if (currentSquare.getRow() == square.getRow()+i &&  currentSquare.getColumn() == square.getColumn() && square.getPiece().getColor() == currentSquare.getPiece().getColor())
                            return false;
                    }
                }
            }
        } else {
            return false;
        }
        if(square.getPiece() != null){
            if(square.getPiece().getColor() == currentSquare.getPiece().getColor()){
                return false;
            }
        }
        return true;

    }

    @Override
    public Type getName() {
        return Type.ROOK;
    }
    @Override
    public String getImage(){
        return  "img/Pieces/Rook";
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
    @Override
    public int getScore() {
        return 5;
    }
}