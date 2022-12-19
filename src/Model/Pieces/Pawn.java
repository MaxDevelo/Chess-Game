package Model.Pieces;

import Model.Color;
import Model.Square;

public class Pawn  extends Piece {
    private Boolean _beginGame;
    private Boolean _isFirstMove;
    public Pawn(Color color) {
        super(color);
        this._beginGame = true;
        this._isFirstMove = true;
    }
    /**
     *  Retourne vrai si la pièce peut avancer
     * @param square la case à vérifier
     * @param currentSquare case actuel où il y a notre pièce qu'on veut déplacer
     * @return retourne vrai si la pièce peut aller sur cette case
     */
    @Override
    public Boolean canMove(Square square, Square currentSquare) {
        if(up(square, currentSquare) || left(square, currentSquare) || right(square, currentSquare)){
            return true;
        }
        return false;
    }

    @Override
    public Type getType() {
        return Type.PAWN;
    }
    @Override
    public String getImage(){
        return  "/img/Pieces/Pawn";
    }

    /**
     *  Retourne vrai si il peut avancer
     * @param square la case à vérifier
     * @param currentSquare case actuel où il y a notre pièce qu'on veut déplacer
     * @return Cela retourne si il peut avancer ou non
     */
    @Override
    public Boolean up(Square square, Square currentSquare) {
        if(currentSquare.getRow() == 1 ||currentSquare.getRow() == 6){
            this._beginGame = true;
        }
        if(this.getColor() == Model.Color.BLACK) {
            if(square.getRow() == currentSquare.getRow() + 1 && currentSquare.getColumn() == square.getColumn() && square.getPiece() == null){
                return true;
            }
            if(this._beginGame){
                if (square.getRow() == currentSquare.getRow() + 2 && currentSquare.getColumn() == square.getColumn() && square.getPiece() == null) {
                    this._beginGame = false;
                    return true;
                }
            }
        }else {
            if(square.getRow() == currentSquare.getRow() - 1 && currentSquare.getColumn() == square.getColumn() && square.getPiece() == null){
                return true;
            }
            if(this._beginGame) {
                if(currentSquare.getRow() == 6){
                    this._beginGame = false;
                }
                if (square.getRow() == currentSquare.getRow() - 2 && currentSquare.getColumn() == square.getColumn() && square.getPiece() == null) {
                    return true;
                }
            }
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
        return  false;
    }
    /**
     *  Retourne vrai si il peut aller à gauche
     * @param square la case à vérifier
     * @param currentSquare case actuel où il y a notre pièce qu'on veut déplacer
     * @return Cela retourne si il peut aller à gauche ou non
     */
    @Override
    public Boolean left(Square square, Square currentSquare) {
        if(this.getColor().equals(Model.Color.BLACK)) {
            return   ((square.getRow() == currentSquare.getRow()+1)
                    && (currentSquare.getColumn()-1 == square.getColumn()))
                    && square.getPiece() != null
                    && square.getPiece().getColor() != this.getColor();
        }else {
            return ((square.getRow() == currentSquare.getRow()-1)
                    && (currentSquare.getColumn()-1 == square.getColumn()))
                    && square.getPiece() != null
                    && square.getPiece().getColor() != this.getColor();
        }
    }
    /**
     *  Retourne vrai si il peut aller vers la droite
     * @param square la case à vérifier
     * @param currentSquare case actuel où il y a notre pièce qu'on veut déplacer
     * @return Cela retourne si il peut aller vers la droite ou non
     */
    @Override
    public Boolean right(Square square, Square currentSquare) {
        if(this.getColor().equals(Model.Color.BLACK)) {
            return   ((square.getRow() == currentSquare.getRow()+1)
                    && (currentSquare.getColumn()+1 == square.getColumn()))
                    && square.getPiece() != null
                    && square.getPiece().getColor() != this.getColor();
        }else {
            return ((square.getRow() == currentSquare.getRow()-1)
                    && (currentSquare.getColumn()+1 == square.getColumn()))
                    && square.getPiece() != null
                    && square.getPiece().getColor() != this.getColor();
        }
    }
    /**
     * Retourne combien vaut cette pièce si on l'attrape
     * @return un entier, le score
     */
    @Override
    public int getScore() {
        return 1;
    }

    @Override
    public Boolean isFirstMove() {
        return _isFirstMove;
    }

    @Override
    public void setIsFirstMove(Boolean isFirstMove) {
        this._isFirstMove = isFirstMove;
    }
}