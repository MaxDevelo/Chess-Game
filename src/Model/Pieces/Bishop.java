package Model.Pieces;


import Model.Color;
import Model.Square;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);

    }


    @Override
    public Boolean canMove(Square square, Square currentSquare) {
        if(square.getPiece() == null || square.getPiece().getColor() != currentSquare.getPiece().getColor()){
            for(int i=0; i<8; i++){
                if(currentSquare.getRow()-i == square.getRow() && currentSquare.getColumn()+i == square.getColumn() ){
                    return true;
                }else if(currentSquare.getRow()-i == square.getRow() && currentSquare.getColumn()-i == square.getColumn()){
                    return true;
                }else if(currentSquare.getRow()+i == square.getRow() && currentSquare.getColumn()-i == square.getColumn()){
                    return true;
                }else  if(currentSquare.getRow()+i == square.getRow() && currentSquare.getColumn()+i == square.getColumn()){
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public Type getName() {
        return Type.BISHOP;
    }
    @Override
    public String getImage(){
        return  "img/Pieces/Bishop";
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
        return 3;
    }

}
