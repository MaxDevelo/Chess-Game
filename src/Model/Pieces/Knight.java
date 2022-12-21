package Model.Pieces;


import Model.Color;
import Model.Square;

public class Knight  extends Piece  {

    public Knight(Color color) {
        super(color);
    }
    /**
     *  Retourne vrai si la pièce peut avancer
     * @param square la case à vérifier
     * @param currentSquare case actuel où il y a notre pièce qu'on veut déplacer
     * @return retourne vrai si la pièce peut aller sur cette case
     */
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
            }
        }
        return false;
    }


    @Override
    public Type getType() {
        return Type.KNIGHT;
    }
    @Override
    public String getImage(){
        return  "/img/Pieces/Knight";
    }
    /**
     *  Retourne vrai si il peut avancer
     * @param square la case à vérifier
     * @param currentSquare case actuel où il y a notre pièce qu'on veut déplacer
     * @return Cela retourne si il peut avancer ou non
     */
    @Override
    public Boolean up(Square square, Square currentSquare) {
        if(square.getRow() == currentSquare.getRow()+2 && square.getColumn() == currentSquare.getColumn()+1){
            return true;
        }else if(square.getRow() == currentSquare.getRow()+2 && square.getColumn() == currentSquare.getColumn()-1){
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
        if(square.getRow() == currentSquare.getRow()-2 && square.getColumn() == currentSquare.getColumn()+1){
            return true;
        }else if(square.getRow() == currentSquare.getRow()-2 && square.getColumn() == currentSquare.getColumn()-1){
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
        if(square.getRow() == currentSquare.getRow()-1 && square.getColumn() == currentSquare.getColumn()-2){
            return true;
        }else if(square.getRow() == currentSquare.getRow()+1 && square.getColumn() == currentSquare.getColumn()-2){
            return true;
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
        if(square.getRow() == currentSquare.getRow()-1 && square.getColumn() == currentSquare.getColumn()+2){
            return true;
        }else if(square.getRow() == currentSquare.getRow()+1 && square.getColumn() == currentSquare.getColumn()+2){
            return true;
        }
        return false;
    }
    /**
     * Retourne combien vaut cette pièce si on l'attrape
     * @return un entier, le score
     */
    @Override
    public int getScore() {
        return 3;
    }

    @Override
    public Boolean isFirstMove() {
        return null;
    }

    @Override
    public void setIsFirstMove(Boolean isFirstMove) {

    }
}