package Model.Pieces;

import Model.Color;
import Model.Square;

public class Rook extends Piece {
    // Stocke les cases connu après vérification de la case
    private Square[][] _knownSquare;
    public Rook(Color color) {
        super(color);
        _knownSquare = new Square[8][8];
    }

    @Override
    public Boolean canMove(Square square, Square currentSquare) {

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

        // Bouger horizontalement
        if (currentSquare.getRow() == square.getRow()) {
            if(currentSquare.getColumn() < square.getColumn()){
                if(!right(square, currentSquare)){
                    return false;
                }
            }else{
                if(!left(square, currentSquare)){
                    return false;
                }
            }
        } else if (currentSquare.getColumn() == square.getColumn()) { // Bouger verticalement
            if(currentSquare.getRow() < square.getRow()){
                if(!down(square, currentSquare)){
                    return false;
                }
            }else{
                if(!up(square, currentSquare)){
                    return false;
                }
            }
        } else {
            return false;
        }
        // On vérifie que la pièce n'est pas de la meme couleur que la pièce qu'on vérifie
        if(square.getPiece() != null && square.getPiece().getColor() == currentSquare.getPiece().getColor()){
            return false;
        }

        return true;

    }

    @Override
    public Type getName() {
        return Type.ROOK;
    }
    @Override
    public String getImage(){
        return  "src/img/Pieces/Rook";
    }

    @Override
    public Boolean up(Square square, Square currentSquare) {
        int i=0;
        int dy = -1;

        for (i = currentSquare.getRow() + dy; i != square.getRow(); i += dy){
            if (_knownSquare[i][currentSquare.getColumn()] != null && _knownSquare[i][currentSquare.getColumn()].getPiece() != null){
                return false;
            }
        }

        return true;
    }

    @Override
    public Boolean down(Square square, Square currentSquare) {
        int i=0;
        int dy = 1;

        for (i = currentSquare.getRow() + dy; i != square.getRow(); i += dy){
            if (_knownSquare[i][currentSquare.getColumn()] != null &&  _knownSquare[i][currentSquare.getColumn()].getPiece() != null){
                return false;
            }
        }

        return true;
    }

    @Override
    public Boolean left(Square square, Square currentSquare) {
        int i = 0;
        int dx = -1;

        for (i = currentSquare.getColumn() + dx; i != square.getColumn(); i += dx){
            if (_knownSquare[currentSquare.getRow()][i] != null &&  _knownSquare[currentSquare.getRow()][i].getPiece() != null){
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean right(Square square, Square currentSquare) {
        int i = 0;
        int dx = 1;

        for (i = currentSquare.getColumn() + dx; i != square.getColumn(); i += dx){
            if (_knownSquare[currentSquare.getRow()][i] != null && _knownSquare[currentSquare.getRow()][i].getPiece() != null){
                return false;
            }
        }
        return true;
    }
    @Override
    public int getScore() {
        return 5;
    }
}