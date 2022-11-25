package Model.Pieces;

import Model.Board;
import Model.Piece;
import Model.Square;

public class Pawn  extends Piece {

    public Pawn(Boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void moveAt(Square square, Board board, int row, int column) {
        if(this.getColor()){
            int posX = square.getRow();
            int posY = square.getColumn();
            if(posY - 1 == row && column == posX){
                if(board.getSquare(posY - 1, posX).getPiece() != null){
                    System.out.println("Peut bouger");
                }else{
                    System.out.println("ENNEMI sur cette case");
                }
            }
            else if(posY - 1 == row && posX-1 == column){
                if(board.getSquare(posY - 1, posX-1).getPiece() != null){
                    System.out.println("Peut pas bouger");
                }else{
                    System.out.println("Piece sur cette case");
                }
            }else if(posY - 1 == row && posX+1 == column){
                if(board.getSquare(posY - 1, posX+1).getPiece() != null){
                    System.out.println("Peut pas bouger");
                }else{
                    System.out.println("Piece sur cette case");
                }
            }else {
                System.out.println("Peut pas bouger sur cette case");
            }
        }
    }

    @Override
    public String getName() {
        return "P";
    }
}