package Model;

import Model.Pieces.*;

public class PieceFactory {
    private Square[][] _board;
    public PieceFactory(Square[][] board){
        this._board = board;
    }
    public Pawn createPawn(Color color){
        return new Pawn(color);
    }
    public King createKing(Color color){
        return new King(color);
    }
    public Rook createRook(Color color){
        return new Rook(color, _board);
    }
    public Bishop createBishop(Color color){
        return new Bishop(color);
    }
    public Queen createQueen(Color color){
        return new Queen(color);
    }
    public Knight createKnight(Color color){
        return new Knight(color);
    }
}
