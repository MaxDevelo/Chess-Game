package Model.Pieces;

import Model.Color;
import Model.Square;

public class Rook extends Piece {
    private Square lastSquareColumnUp, lastSquareColumnDown, lastSquareRowRight , lastSquareRowLeft;

    public Rook(Color color) {
        super(color);
        lastSquareColumnUp = null;
        lastSquareColumnDown = null;
        lastSquareRowRight = null;
        lastSquareRowLeft = null;
    }

    @Override
    public Boolean canMove(Square square, Square currentSquare) {
        if (currentSquare.getRow() == square.getRow()) { // Bouger Horizontalement
                if (currentSquare.getColumn() <  square.getColumn()) { // Vers la DROITE
                    return right(square, currentSquare);
              }else{ // Vers la GAUCHE
                    return left(square, currentSquare);
                }
        } else if (currentSquare.getColumn() ==  square.getColumn()) { // Bouger verticalement
                if (currentSquare.getRow() < square.getRow()) { // Vers le bas
                    return down(square, currentSquare);
                } else { // Vers le haut
                    return up(square, currentSquare);
                }
        }
        return false;
    }

    @Override
    public String getName() {
        return "R";
    }
    @Override
    public String getImage(){
        return  "img/Pieces/Rook";
    }

    @Override
    public Boolean up(Square square, Square currentSquare) {
        if (square.getPiece() == null || square.getPiece().getColor() != this.getColor()){
            if(this.lastSquareColumnUp != null && lastSquareColumnUp.getRow() < square.getRow()){
                return false;
            }else{
                this.lastSquareColumnUp = null;
                return true;
            }
        }else{
            this.lastSquareColumnUp = square;
            return false;
        }
    }

    @Override
    public Boolean down(Square square, Square currentSquare) {
            if (square.getPiece() == null || square.getPiece().getColor() != this.getColor()) {
                if (this.lastSquareColumnDown != null && lastSquareColumnDown.getRow() < square.getRow()) {
                    return false;
                } else {
                    this.lastSquareColumnDown = null;
                    return true;
                }
            } else {
                this.lastSquareColumnDown = square;
                return false;
            }
    }

    @Override
    public Boolean left(Square square, Square currentSquare) {
        if (square.getPiece() == null || square.getPiece().getColor() != this.getColor()){
            if(this.lastSquareRowLeft != null && lastSquareRowLeft.getColumn() > square.getColumn()){
                return false;
            }else{
                this.lastSquareRowLeft = null;
                return true;
            }
        }else{
            this.lastSquareRowLeft = square;
            return false;
        }
    }

    @Override
    public Boolean right(Square square, Square currentSquare) {
        if (square.getPiece() == null || square.getPiece().getColor() != this.getColor()){
            if(this.lastSquareRowRight != null && lastSquareRowRight.getColumn() < square.getColumn()){
                return false;
            }else{
                this.lastSquareRowRight = null;
                return true;
            }
        }else{
            this.lastSquareRowRight = square;
            return false;
        }
    }
}