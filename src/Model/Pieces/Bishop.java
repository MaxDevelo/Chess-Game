package Model.Pieces;


import Model.Color;
import Model.Square;

public class Bishop extends Piece {
    // Stocke les cases connu après vérification de la case
    private Square[][] _knownSquare;
    public Bishop(Color color) {
        super(color);
        _knownSquare = new Square[8][8];
    }

    @Override
    public Boolean canMove(Square square, Square currentSquare) {   int i;
        // Si il est sur la meme case alors on renvoie faux
        if (currentSquare.getRow() == square.getRow() && currentSquare.getColumn() == square.getColumn())
            return false;
        // On rempli le tableau des case connu de la pièce
        for(int l=0; l<8; l++){
            for(int c=0; c<8; c++){
                if(l == square.getRow() && c == square.getColumn()){
                    _knownSquare[l][c] = square;
                }
            }
        }
        // Vérifie si le fou reste sur la même diagonale
        if (Math.abs(currentSquare.getRow() - square.getRow()) == Math.abs(currentSquare.getColumn() - square.getColumn())) {
            // Vérifie si il y a des obstacles sur la diagonale
            int rowStep = (square.getRow() - currentSquare.getRow()) / Math.abs(square.getRow() - currentSquare.getRow());
            int colStep = (square.getColumn() - currentSquare.getColumn()) / Math.abs(square.getColumn() - currentSquare.getColumn());
            int row = currentSquare.getRow() + rowStep;
            int col = currentSquare.getColumn() + colStep;
            while (row != square.getRow() && col != square.getColumn()) {
                if (_knownSquare[row][col] != null && _knownSquare[row][col].getPiece() != null) {
                    return false;
                }
                row += rowStep;
                col += colStep;
            }
            if(square.getPiece() != null && square.getPiece().getColor() == currentSquare.getPiece().getColor()){
                return false;
            }
            return true;
        }

        return false;
    }


    @Override
    public Type getName() {
        return Type.BISHOP;
    }
    @Override
    public String getImage(){
        return  "/img/Pieces/Bishop";
    }

    @Override
    public Boolean up(Square square, Square currentSquare) {
        return false;
    }

    @Override
    public Boolean down(Square square, Square currentSquare) {
        return false;
    }

    @Override
    public Boolean left(Square square, Square currentSquare) {
        return false;
    }

    @Override
    public Boolean right(Square square, Square currentSquare) {
        return false;
    }

    @Override
    public int getScore() {
        return 3;
    }

}
