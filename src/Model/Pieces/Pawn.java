package Model.Pieces;

import Model.Board;
import Model.Piece;
import Model.Square;

public class Pawn  extends Piece {

    public Pawn(Boolean isWhite) {
        super(isWhite);
    }

    @Override
    public Boolean moveAt(Square square, Square currentSquare) {
     //   this.clearValidSquares();

        int currentRow = currentSquare.getRow();
        int currentColumn = currentSquare.getColumn();
        int row = square.getRow();
        int column = square.getColumn();
        if(this.getColor()) {
            if (row - 1 >= 0 && row - 1 <= 7) {

            }
            if (row - 1 >= 0 && row - 1 <= 7 && column - 1 >= 0 && column - 1 <= 7) {

            }
            if (row - 1 >= 0 && row - 1 <= 7 && column + 1 <= 7 && column + 1 >= 0) {

            }
        }else{
            if(row + 1 >= 0 && row + 1 <= 7){

            }
            if(row + 1 >= 0 && row + 1 <= 7 && column + 1 <=7 && column + 1 >= 0){

            }
            if(row + 1 >= 0 && row + 1 <= 7 && column - 1 <=7 && column - 1 >= 0){

            }
        }

        return false;


     /*   if(this.getColor()){
            if(row - 1 >= 0 && row - 1 <=7 ){
                if(this.North(1, square).getPiece() == null){
                    this.setValidSquare(board.getSquare(row - 1, column));
                }
            }
            if(row - 1 >= 0 && row - 1 <=7 && column-1 >=0 && column-1 <= 7){
                if(board.getSquare(this.North(1, square).getRow(), this.West(1, square).getColumn()).getPiece() != null && board.getSquare(this.North(1, square).getRow(), this.West(1, square).getColumn()).getPiece().getColor() != this.getColor()){
                    this.setValidSquare(board.getSquare(this.North(1, square).getRow(), this.West(1, square).getColumn()));
                }
            }

            if(row - 1 >= 0 && row - 1 <= 7 && column + 1 <=7 && column + 1 >= 0){
                if(board.getSquare(this.North(1, square).getRow(), this.East(1, square).getColumn()).getPiece() != null &&  board.getSquare(this.North(1, square).getRow(), this.East(1, square).getColumn()).getPiece().getColor() != this.getColor()){
                    this.setValidSquare(board.getSquare(this.North(1, square).getRow(), this.East(1, square).getColumn()));
                }
            }
        }else{
            if(row + 1 >= 0 && row + 1 <= 7){
                if(this.South(1, square).getPiece() == null){
                    this.setValidSquare(board.getSquare(this.South(1, square).getRow(), column));
                }
            }
            if(row + 1 >= 0 && row + 1 <= 7 && column + 1 <=7 && column + 1 >= 0){
                if(board.getSquare(this.South(1, square).getRow(), this.East(1, square).getColumn()).getPiece() != null && board.getSquare(this.South(1, square).getRow(), this.East(1, square).getColumn()).getPiece().getColor() != this.getColor()){
                    this.setValidSquare(board.getSquare(row + 1, column + 1));
                }
            }
            if(row + 1 >= 0 && row + 1 <= 7 && column - 1 <=7 && column - 1 >= 0){
                if(board.getSquare(this.South(1, square).getRow(), this.West(1, square).getColumn()).getPiece() != null && board.getSquare(this.South(1, square).getRow(), this.West(1, square).getColumn()).getPiece().getColor() != this.getColor()){
                    this.setValidSquare(board.getSquare(this.South(1, square).getRow(), this.West(1, square).getColumn()));
                }
            }
        }*/


    }

    @Override
    public String getName() {
        return "P";
    }
}