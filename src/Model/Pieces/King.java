package Model.Pieces;

import Model.Color;
import Model.Square;

public class King  extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public Boolean canMove(Square square, Square currentSquare) {
        if(square.getPiece() == null || square.getPiece().getColor() != currentSquare.getPiece().getColor()){
            if(up(square, currentSquare)){
                return true;
            }else if(down(square, currentSquare)){
                return true;
            }else if(left(square, currentSquare)){
                return true;
            }else if(right(square, currentSquare)){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    @Override
    public Type getName() {
        return Type.KING;
    }
    @Override
    public String getImage(){
        return  "/img/Pieces/King";
    }
    /**
     *  Retourne vrai si il peut avancer
     * @param square la case à vérifier
     * @param currentSquare case actuel où il y a notre pièce qu'on veut déplacer
     * @return Cela retourne si il peut avancer ou non
     */
    @Override
    public Boolean up(Square square, Square currentSquare) {
        if(square.getRow() == currentSquare.getRow()-1 && square.getColumn() == currentSquare.getColumn()){
            return true;
        }
        return false;
    }
    /**
     *  Retourne vrai si il peut aller vers le bas
     * @param square la case à vérifier
     * @param currentSquare case actuel où il y a notre pièce qu'on veut déplacer
     * @return Cela retourne si il peut aller vers le bas  non
     */
    @Override
    public Boolean down(Square square, Square currentSquare) {
        if(square.getRow() == currentSquare.getRow()+1 && square.getColumn() == currentSquare.getColumn()){ // DOWN
            return true;
        }
        return false;
    }
    /**
     *  Retourne vrai si il peut aller à gauche
     * @param square la case à vérifier
     * @param currentSquare case actuel où il y a notre pièce qu'on veut déplacer
     * @return Cela retourne si il peut aller à gauche ou non
     */
    @Override
    public Boolean left(Square square, Square currentSquare) {
        if(square.getColumn() == currentSquare.getColumn()-1){
            if(square.getRow() == currentSquare.getRow()){ // LEFT
                return true;
            }else if(square.getRow() == currentSquare.getRow()+1){
                return true;
            }else if(square.getRow() == currentSquare.getRow()-1){
                return true;
            }
        }
        return false;
    }
    /**
     *  Retourne vrai si il peut aller vers la droite
     * @param square la case à vérifier
     * @param currentSquare case actuel où il y a notre pièce qu'on veut déplacer
     * @return Cela retourne si il peut aller vers la droite ou non
     */
    @Override
    public Boolean right(Square square, Square currentSquare) {
        if(square.getColumn() == currentSquare.getColumn()+1){
            if(square.getRow() == currentSquare.getRow()){ // RIGHT
                return true;
            }else if(square.getRow() == currentSquare.getRow()-1){
                return true;
            }else if(square.getRow() == currentSquare.getRow()+1){
                return true;
            }
        }
        return false;
    }
    /**
     * Retourne combien vaut cette pièce. HORS. Le roi n'a pas de score
     * @return un entier, le score. Attention, le roi n'a pas de score
     */
    @Override
    public int getScore() {
        return 0;
    }
}