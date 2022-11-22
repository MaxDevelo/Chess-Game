package Model;

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

    public void setSquare(Piece piece){
        this._piece = piece;
    }

    public Piece getSquare(){
        return this._piece;
    }
}
