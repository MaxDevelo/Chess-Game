package Model;

public abstract class Piece {
    private boolean _isWhite;
    private boolean _canMove;
    public Piece(Boolean isWhite){
        this._isWhite = isWhite;
    }
    public Boolean getColor() {
        return _isWhite;
    }

    public abstract void moveAt();
    public abstract String getName();

}
