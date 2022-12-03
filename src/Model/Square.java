package Model;

import Model.Pieces.Piece;

public class Square {
    private int _row;
    private int _column;
    private Piece _piece;
    public Square(int row, int column, Piece piece){
        this._row = row;
        this._column = column;
        if(piece != null){
            this._piece = piece;
        }else{
            this._piece = null;
        }
    }

    public int getRow(){

        return this._row;
    }
    
    public int getColumn(){
        
        return this._column;
    }

    public void setPiece(Piece piece){
        this._piece = piece;
    }

    public Piece getPiece(){
        return this._piece;
    }
}
