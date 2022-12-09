package Model.Pieces;

import Model.Board;
import Model.Color;
import Model.Square;

import java.util.ArrayList;
import java.util.List;

import static javax.swing.text.SimpleAttributeSet.EMPTY;

public class Rook extends Piece {
    private Square[][]  _boards;
    public Rook(Color color, Square[][]  boards) {
        super(color);
        _boards = boards;
    }

    @Override
    public Boolean canMove(Square square, Square currentSquare) {

        int i;
        // Si il est sur la meme case alors on renvoie faux
        if (currentSquare.getRow() == square.getRow() && currentSquare.getColumn() == square.getColumn())
            return false;

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
        return  "img/Pieces/Rook";
    }

    @Override
    public Boolean up(Square square, Square currentSquare) {
        int i=0;
        int dy = -1;

        for (i = currentSquare.getRow() + dy; i != square.getRow(); i += dy){
            if (_boards[i][currentSquare.getColumn()].getPiece() != null){
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
            if (_boards[i][currentSquare.getColumn()].getPiece() != null){
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
            if (_boards[currentSquare.getRow()][i].getPiece() != null){
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
            if (_boards[currentSquare.getRow()][i].getPiece() != null){
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