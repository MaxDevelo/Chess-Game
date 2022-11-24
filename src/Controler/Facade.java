package Controler;

import Model.Board;
import Model.PieceFactory;
import Model.Square;

public class Facade {

    public void generatePiece(Square[][] board){
        Boolean isWhite = true;
        int l = 0;
        int c = 0;
        PieceFactory createPiece = new PieceFactory();
        for(int i = 0; i<2; i++){
            if(i == 1){
                isWhite = false;
                l = 7;
                c = 0;
            }
            board[l][c].setPiece(createPiece.createRook(isWhite));
            board[l][c++].setPiece(createPiece.createBishop(isWhite));
            board[l][c++].setPiece(createPiece.createKnight(isWhite));
            board[l][c++].setPiece(createPiece.createBishop(isWhite));
            board[l][c++].setPiece(createPiece.createKing(isWhite));
            board[l][c++].setPiece(createPiece.createQueen(isWhite));
            board[l][c++].setPiece(createPiece.createBishop(isWhite));
            board[l][c++].setPiece(createPiece.createKnight(isWhite));
            board[l][c++].setPiece(createPiece.createRook(isWhite));
            if(i == 1) {
                l--;
            }else{
                l++;
            }
            c = 0;
            for(int pawn = 0; pawn<8; pawn++){
                board[l][c].setPiece(createPiece.createPawn(isWhite));
                c++;
            }
        }
    }
}
