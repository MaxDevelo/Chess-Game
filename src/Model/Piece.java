package Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private boolean _isWhite;
    private boolean _canMove;
    private List<Square> _validSquares;
    public Piece(Boolean isWhite){
        this._isWhite = isWhite;
        this._validSquares = new ArrayList<>();
    }
    public Boolean getColor() {
        return _isWhite;
    }

    public abstract void moveAt(Square square, Board board);
    public abstract String getName();
    public List<Square> getValidSquares(){
        return this._validSquares;
    }
    public void setValidSquare(Square square){
        this._validSquares.add(square);
    }
    public void clearValidSquares(){
        this._validSquares.clear();
    }

}
