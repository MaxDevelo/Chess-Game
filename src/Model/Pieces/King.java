package Model.Pieces;

import Model.Color;
import Model.Square;

public class King  extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public Boolean canMove(Square square, Square currentSquare) {
        if(square.getPiece() == null || square.getPiece().getColor() != currentSquare.getPiece().getColor()){
            if(up(square, currentSquare)){
                return true;
            }else if(down(square, currentSquare)){
                return true;
            }else if(left(square, currentSquare)){
                return true;
            }else if(right(square, currentSquare)){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return "K";
    }
    @Override
    public String getImage(){
        return  "img/Pieces/King";
    }
    @Override
    public Boolean up(Square square, Square currentSquare) {
        if(square.getRow() == currentSquare.getRow()-1 && square.getColumn() == currentSquare.getColumn()){
            return true;
        }
        return false;
    }

    @Override
    public Boolean down(Square square, Square currentSquare) {
        if(square.getRow() == currentSquare.getRow()+1 && square.getColumn() == currentSquare.getColumn()){ // DOWN
            return true;
        }
        return false;
    }

    @Override
    public Boolean left(Square square, Square currentSquare) {
        if(square.getRow() == currentSquare.getRow() && square.getColumn() == currentSquare.getColumn()-1){ // LEFT
            return true;
        }else if(square.getRow() == currentSquare.getRow()+1 && square.getColumn() == currentSquare.getColumn()-1){
            return true;
        }else if(square.getRow() == currentSquare.getRow()-1 && square.getColumn() == currentSquare.getColumn()-1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean right(Square square, Square currentSquare) {
        if(square.getRow() == currentSquare.getRow() && square.getColumn() == currentSquare.getColumn()+1){ // RIGHT
            return true;
        }else if(square.getRow() == currentSquare.getRow()-1 && square.getColumn() == currentSquare.getColumn()+1){
            return true;
        }else if(square.getRow() == currentSquare.getRow()+1 && square.getColumn() == currentSquare.getColumn()+1){
            return true;
        }
        return false;
    }
}