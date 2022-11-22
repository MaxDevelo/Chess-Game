package Model;

public class Board {
    private Square[][] _board;

    public Board(){
        _board = new Square[8][8];
    }
    public void setSquare(Square square, int row, int column){
        this._board[row][column] = square;
    }

    public Square getSquare(int row, int column){
        return  this._board[row][column];
    }
}
