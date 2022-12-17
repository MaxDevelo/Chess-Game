package Model.Pieces;


import Model.Color;
import Model.Square;

public class Queen  extends Piece {

    // Stocke les cases connu après vérification de la case
    // afin de pouvoir anticiper les obstacles.
    private Square[][] _knownSquare;
    public Queen(Color color) {
        super(color);
        _knownSquare = new Square[8][8];
    }

    @Override
    public Boolean canMove(Square square, Square currentSquare) {
        int i;
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
        if(square.getPiece() != null && square.getPiece().getColor() == currentSquare.getPiece().getColor()){
            return false;
        }
        return true;

    }


    @Override
    public Type getName() {
        return Type.QUEEN;
    }
    @Override
    public String getImage(){
        return  "/img/Pieces/Queen";
    }
    /**
     *  Retourne vrai si il peut avancer
     * @param square la case à vérifier
     * @param currentSquare case actuel où il y a notre pièce qu'on veut déplacer
     * @return Cela retourne si il peut avancer ou non
     */
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
    /**
     *  Retourne vrai si il peut aller vers le bas
     * @param square la case à vérifier
     * @param currentSquare case actuel où il y a notre pièce qu'on veut déplacer
     * @return Cela retourne si il peut aller vers le bas  non
     */
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
    /**
     *  Retourne vrai si il peut aller à gauche
     * @param square la case à vérifier
     * @param currentSquare case actuel où il y a notre pièce qu'on veut déplacer
     * @return Cela retourne si il peut aller à gauche ou non
     */
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
    /**
     *  Retourne vrai si il peut aller vers la droite
     * @param square la case à vérifier
     * @param currentSquare case actuel où il y a notre pièce qu'on veut déplacer
     * @return Cela retourne si il peut aller vers la droite ou non
     */
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
    /**
     * Retourne combien vaut cette pièce si on l'attrape
     * @return un entier, le score
     */

    @Override
    public int getScore() {
        return 9;
    }

    @Override
    public Boolean isFirstMove() {
        return null;
    }

    @Override
    public void setIsFirstMove(Boolean isFirstMove) {

    }
}