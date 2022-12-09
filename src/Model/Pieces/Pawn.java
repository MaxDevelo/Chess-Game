package Model.Pieces;

import Model.Color;
import Model.Square;

public class Pawn  extends Piece {
    private Boolean _beginGame;
    public Pawn(Color color) {
        super(color);
        this._beginGame = true;
    }

    @Override
    public Boolean canMove(Square square, Square currentSquare) {
        if(up(square, currentSquare) || left(square, currentSquare) || right(square, currentSquare)){
            return true;
        }
        return false;
    }

    @Override
    public Type getName() {
        return Type.PAWN;
    }
    @Override
    public String getImage(){
        return  "img/Pieces/Pawn";
    }

    @Override
    public Boolean up(Square square, Square currentSquare) {
        if(this.getColor() == Model.Color.BLACK) {
            if(square.getRow() == currentSquare.getRow() + 1 && currentSquare.getColumn() == square.getColumn() && square.getPiece() == null){
                return true;
            }
            if(this._beginGame){
                if (square.getRow() == currentSquare.getRow() + 2 && currentSquare.getColumn() == square.getColumn() && square.getPiece() == null) {
                    this._beginGame = false;
                    return true;
                }
            }
        }else {
            if(square.getRow() == currentSquare.getRow() - 1 && currentSquare.getColumn() == square.getColumn() && square.getPiece() == null){
                return true;
            }
            if(this._beginGame) {
                if (square.getRow() == currentSquare.getRow() - 2 && currentSquare.getColumn() == square.getColumn() && square.getPiece() == null) {
                    this._beginGame = false;
                    return true;
                }
            }
        }

        return false;

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
    @Override
    public int getScore() {
        return 1;
    }
}