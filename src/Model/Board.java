package Model;

public class Board {
    private Square[][] _board;
    private static Board _instance;
    public Board(){
        _board = new Square[8][8];
    }
    public static Board getInstance(){
        if(_instance == null){
            _instance = new Board();
        }
        return _instance;
    }
    public void setSquare(Square square, int row, int column){
        this._board[row][column] = square;
    }

    public Square getSquare(int row, int column){
        return  this._board[row][column];
    }
    public Square[][] getBoard(){
        return this._board;
    }
}
