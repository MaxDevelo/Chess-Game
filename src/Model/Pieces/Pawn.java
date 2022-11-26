package Model.Pieces;

import Model.Board;
import Model.Piece;
import Model.Square;

public class Pawn  extends Piece {

    public Pawn(Boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void moveAt(Square square, Board board) {
        this.clearValidSquares();

        int row = square.getRow();
        int column = square.getColumn();

        if(this.getColor()){
            if(row - 1 >= 0 && row - 1 <=7 ){
                if(board.getSquare(row - 1, column).getPiece() == null){
                    this.setValidSquare(board.getSquare(row - 1, column));
                }else{

                }
            }
            if(row - 1 >= 0 && row - 1 <=7 && column-1 >=0 && column-1 <= 7){
                if(board.getSquare(row - 1, column-1).getPiece() == null || board.getSquare(row - 1, column-1).getPiece().getColor() == this.getColor()){
                }else{
                    this.setValidSquare(board.getSquare(row - 1, column-1));
                }
            }

            if(row - 1 >= 0 && row - 1 <= 7 && column + 1 <=7 && column + 1 >= 0){
                if(board.getSquare(row - 1, column + 1).getPiece() == null ||  board.getSquare(row - 1, column + 1).getPiece().getColor() == this.getColor()){
                }else{
                    this.setValidSquare(board.getSquare(row - 1, column + 1));
                }
            }
        }else{
            if(row + 1 >= 0 && row + 1 <= 7){
                if(board.getSquare(row + 1, column).getPiece() == null){
                    this.setValidSquare(board.getSquare(row + 1, column));
                }else{

                }
            }
            if(row + 1 >= 0 && row + 1 <= 7 && column + 1 <=7 && column + 1 >= 0){
                if(board.getSquare(row + 1, column + 1).getPiece() == null || board.getSquare(row + 1, column + 1).getPiece().getColor() == this.getColor()){
                }else{
                    this.setValidSquare(board.getSquare(row + 1, column + 1));
                }
            }
            if(row + 1 >= 0 && row + 1 <= 7 && column - 1 <=7 && column - 1 >= 0){
                if(board.getSquare(row + 1, column - 1).getPiece() == null || board.getSquare(row + 1, column - 1).getPiece().getColor() == this.getColor()){
                }else{
                    this.setValidSquare(board.getSquare(row + 1, column - 1));
                }
            }
        }
    }

    @Override
    public String getName() {
        return "P";
    }
}