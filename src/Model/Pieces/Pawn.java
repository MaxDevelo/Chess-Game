package Model.Pieces;

import Model.Color;
import Model.Square;

public class Pawn  extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public Boolean canMove(Square square, Square currentSquare) {

        if(up(square, currentSquare) || left(square, currentSquare) || right(square, currentSquare)){
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return "P";
    }
    @Override
    public String getImage(){
        return  "img/Pieces/Pawn";
    }

    @Override
    public Boolean up(Square square, Square currentSquare) {
        if(this.getColor() == Model.Color.BLACK) {
          return  (square.getRow() == currentSquare.getRow() + 1
                  && currentSquare.getColumn() == square.getColumn())
                  && square.getPiece() == null;
        }else {
            return (square.getRow() == currentSquare.getRow() - 1
                    && currentSquare.getColumn() == square.getColumn())
                    && square.getPiece() == null;
        }
    }

    @Override
    public Boolean down(Square square, Square currentSquare) {
        return  false;
    }

    @Override
    public Boolean left(Square square, Square currentSquare) {
        if(this.getColor() == Model.Color.BLACK) {
            return   ((square.getRow() == currentSquare.getRow()+1)
                    && (currentSquare.getColumn()-1 == square.getColumn()))
                    && square.getPiece() != null
                    && square.getPiece().getColor() != this.getColor();
        }else {
            return ((square.getRow() == currentSquare.getRow()-1)
                    && (currentSquare.getColumn()-1 == square.getColumn()))
                    && square.getPiece() != null
                    && square.getPiece().getColor() != this.getColor();
        }
    }

    @Override
    public Boolean right(Square square, Square currentSquare) {
        if(this.getColor() == Model.Color.BLACK) {
            return   ((square.getRow() == currentSquare.getRow()+1)
                    && (currentSquare.getColumn()+1 == square.getColumn()))
                    && square.getPiece() != null
                    && square.getPiece().getColor() != this.getColor();
        }else {
            return ((square.getRow() == currentSquare.getRow()-1)
                    && (currentSquare.getColumn()+1 == square.getColumn()))
                    && square.getPiece() != null
                    && square.getPiece().getColor() != this.getColor();
        }
    }
}