package Model;

import Model.Pieces.*;

public class PieceFactory {
    public Pawn createPawn(Boolean isWhite){
        return new Pawn(isWhite);
    }
    public King createKing(Boolean isWhite){
        return new King(isWhite);
    }
    public Rook createRook(Boolean isWhite){
        return new Rook(isWhite);
    }
    public Bishop createBishop(Boolean isWhite){
        return new Bishop(isWhite);
    }
    public Queen createQueen(Boolean isWhite){
        return new Queen(isWhite);
    }
    public Knight createKnight(Boolean isWhite){
        return new Knight(isWhite);
    }
}
