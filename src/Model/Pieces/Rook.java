package Model.Pieces;

import Model.Color;
import Model.Square;

public class Rook extends Piece {
    private Square sq, sqRow;
    public Rook(Color color) {
        super(color);
        sq = null;
        sqRow = null;
    }

    @Override
    public Boolean canMove(Square square, Square currentSquare) {

        int currentRow = currentSquare.getRow();
        int currentColumn = currentSquare.getColumn();
        int row = square.getRow();
        int column = square.getColumn();

        if(this.getColor() == Model.Color.WHITE) {
            if (currentRow == row) { // Bouger Horizontalement
                if (currentColumn < column) { // Vers la DROITE
                    if (square.getPiece() == null || square.getPiece().getColor() != this.getColor()){
                        if(this.sqRow != null && sqRow.getColumn() < column){
                            return false;
                        }else{
                            return true;
                        }
                    }else{
                        this.sqRow = square;
                        return false;
                    }
                }else{ // Vers la GAUCHE
                    if (square.getPiece() == null || square.getPiece().getColor() != this.getColor()){
                        if(this.sqRow != null && sqRow.getColumn() < column){
                            return false;
                        }else{
                            return true;
                        }
                    }else{
                        return false;
                    }
                }
            } else if (currentColumn == column) { // Bouger verticalement
                if (currentRow < row) { // Vers le bas
                    if (square.getPiece() == null || square.getPiece().getColor() != this.getColor()){
                        if(this.sq != null && sq.getRow() < row){
                            return false;
                        }else{
                            this.sq = null;
                            return true;
                        }
                    }else{
                        this.sq = square;
                        return false;
                    }
                }else{ // Vers le haut
                    if (square.getPiece() == null || square.getPiece().getColor() != this.getColor()){
                        if(this.sq != null && sq.getRow() < row){
                            return false;
                        }else{
                            this.sq = null;
                            return true;
                        }
                    }else{
                        return false;
                    }
                }
            } else {
                return false;
            }

        }else{
            if (currentRow == row) { // Bouger Horizontalement
                if (currentColumn < column) { // Vers la DROITE
                    if (square.getPiece() == null || square.getPiece().getColor() != this.getColor()){
                        if(this.sqRow != null && sqRow.getColumn() < column){
                            return false;
                        }else{
                            return true;
                        }
                    }else{
                        this.sqRow = square;
                        return false;
                    }
                }else{ // Vers la GAUCHE
                   if (square.getPiece() == null || square.getPiece().getColor() != this.getColor()){
                        if(this.sqRow != null && sqRow.getColumn() < column){
                            return false;
                        }else{
                            return true;
                        }
                    }else{
                        return false;
                    }
                }
            } else if (currentColumn == column) { // Bouger verticalement
                if (currentRow < row) { // Vers le bas
                    if (square.getPiece() == null || square.getPiece().getColor() != this.getColor()){
                        if(this.sq != null && sq.getRow() < row){
                            return false;
                        }else{
                            this.sq = null;
                            return true;
                        }
                    }else{
                        this.sq = square;
                        return false;
                    }
                }else{ // Vers le haut
                    if (square.getPiece() == null || square.getPiece().getColor() != this.getColor()){
                        if(this.sq != null && sq.getRow() < row){
                            return false;
                        }else{
                            this.sq = null;
                            return true;
                        }
                    }else{
                        return false;
                    }
                }
            } else {
                return false;
            }

        }

    }

    @Override
    public String getName() {
        return "R";
    }
}