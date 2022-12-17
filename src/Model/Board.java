package Model;

import Model.Pieces.*;
import Model.Pieces.Type;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Square[][] _board;
    private Piece _currentPiece;
    private Square _lastPieceMove;
    private List<Square> _validSquares;
    public Board(){
        _board = new Square[8][8];
        this._validSquares = new ArrayList<>();
        this._lastPieceMove = null;
    }


    /**
     *  Génération des pièces dans le tableau de case
     * @param board tableau de case
     * @return le tableau de case avec els pièces
     *  et dont le fils droit et le fils gauche sont vides.
     */
    public Square[][]  generatePiece(Square[][] board){

        PieceFactory createPiece = new PieceFactory();

        // Pièces NOIR
        board[0][0].setPiece(createPiece.createRook(Model.Color.BLACK));
        board[0][1].setPiece(createPiece.createKnight(Model.Color.BLACK));
        board[0][2].setPiece(createPiece.createBishop(Model.Color.BLACK));
        board[0][3].setPiece(createPiece.createKing(Model.Color.BLACK));
        board[0][4].setPiece(createPiece.createQueen(Model.Color.BLACK));
        board[0][5].setPiece(createPiece.createBishop(Model.Color.BLACK));
        board[0][6].setPiece(createPiece.createKnight(Model.Color.BLACK));
        board[0][7].setPiece(createPiece.createRook(Model.Color.BLACK));

        board[1][0].setPiece(createPiece.createPawn(Model.Color.BLACK));
        board[1][1].setPiece(createPiece.createPawn(Model.Color.BLACK));
        board[1][2].setPiece(createPiece.createPawn(Model.Color.BLACK));
        board[1][3].setPiece(createPiece.createPawn(Model.Color.BLACK));
        board[1][4].setPiece(createPiece.createPawn(Model.Color.BLACK));
        board[1][5].setPiece(createPiece.createPawn(Model.Color.BLACK));
        board[1][6].setPiece(createPiece.createPawn(Model.Color.BLACK));
        board[1][7].setPiece(createPiece.createPawn(Model.Color.BLACK));


        // Pièces BLANCHES
        board[6][0].setPiece(createPiece.createPawn(Model.Color.WHITE));
        board[6][1].setPiece(createPiece.createPawn(Model.Color.WHITE));
        board[6][2].setPiece(createPiece.createPawn(Model.Color.WHITE));
        board[6][3].setPiece(createPiece.createPawn(Model.Color.WHITE));
        board[6][4].setPiece(createPiece.createPawn(Model.Color.WHITE));
        board[6][5].setPiece(createPiece.createPawn(Model.Color.WHITE));
        board[6][6].setPiece(createPiece.createPawn(Model.Color.WHITE));
        board[6][7].setPiece(createPiece.createPawn(Model.Color.WHITE));

        board[7][0].setPiece(createPiece.createRook(Model.Color.WHITE));
        board[7][1].setPiece(createPiece.createKnight(Model.Color.WHITE));
        board[7][2].setPiece(createPiece.createBishop(Model.Color.WHITE));
        board[7][3].setPiece(createPiece.createKing(Model.Color.WHITE));
        board[7][4].setPiece(createPiece.createQueen(Model.Color.WHITE));
        board[7][5].setPiece(createPiece.createBishop(Model.Color.WHITE));
        board[7][6].setPiece(createPiece.createKnight(Model.Color.WHITE));
        board[7][7].setPiece(createPiece.createRook(Model.Color.WHITE));

        return board;
    }


    /**
     *  Mettre la case dans le tableau de case
     * @param square Il s'agit de la case à initialiser
     * @param row correspond à la ligne de la case
     * @param column correspond à la colonne de la case
     */
    public void setSquare(Square square, int row, int column){
        this._board[row][column] = square;
    }


    /**
     *  Récupère la case
     * @param row correspond à la ligne de la case
     * @param column correspond à la colonne de la case
     * @return retourne la case
     */
    public Square getSquare(int row, int column){
        return  this._board[row][column];
    }

    /**
     *  Retourner le tableau de case
     * @return retourne le tableau
     */
    public Square[][] getBoards(){
        return this._board;
    }


    /**
     *  La pièces où l'on joue actuellement
     * @param piece pièce actuel
     */
    public void setCurrentPiece(Piece piece){
        this._currentPiece = piece;
    }
    public Piece getCurrentPiece(){
        return this._currentPiece;
    }
    public List<Square> getValidSquares(){
        return this._validSquares;
    }

    /**
     *  Vérifier sur quelles cases on peut aller et il le met dans une liste de case
     * @param square case à vérifier
     */
    public void setValidSquares(Square square){
        clearValidSquare();
        for(int l = 0; l<8; l++) {
            for (int c = 0; c < 8; c++) {
                if(isTakenInPassing(square)){
                    if(_lastPieceMove.getPiece().getColor().equals(Color.BLACK)){
                        if(_board[l][c].getRow() == 2 && _board[l][c].getColumn() == _lastPieceMove.getColumn()){
                            this._validSquares.add(_board[l][c]);
                        }
                    }else{
                        if(_board[l][c].getRow() == 5 && _board[l][c].getColumn() == _lastPieceMove.getColumn()){
                            this._validSquares.add(_board[l][c]);
                        }
                    }
                }
                if(square.getPiece().canMove(_board[l][c], square)){ // == true
                    this._validSquares.add(_board[l][c]);
                }
            }
        }
    }

    /**
     *  Clear la liste des cases valide (qui permet de savoir où la Pièce peut aller
     */
    public void clearValidSquare(){ // On rénitialise la liste des cases validés
        this._validSquares.clear();
    }
    // Prise en apssant
    public boolean isTakenInPassing(Square currentSquare){
        if(_lastPieceMove != null && _lastPieceMove.getPiece() != null && _lastPieceMove.getPiece().getColor() != currentSquare.getPiece().getColor()){
            if(_lastPieceMove.getPiece().getColor().equals(Color.BLACK)){
                    if(_lastPieceMove.getRow()-1 == 2 && currentSquare.getRow()-1 == 2){
                        return true;
                    }
            }else{
                if(_lastPieceMove.getRow()+1 == 5 && currentSquare.getRow()+1 == 5){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     *  Gestion de la promotion du Pion
     * @param square case à vérifier
     * @return retourne vrai si il y a promotion
     */
    public Boolean isPromoted(Square square){
        if(square.getPiece() != null){
            if(square.getPiece().getName() == Type.PAWN){
                if(square.getPiece().getColor() == Color.WHITE){
                    if(square.getRow() == 0){
                        return true;
                    }
                }else{
                    if(square.getRow() == 7){
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }


    /**
     *  Changement de la pièce (Attention, c'est un test. NON TERMINE
     * @param square case où l'on a la promotion
     * @param namePiece nom de la pièce choisi
     */
    public void promotion(Square square, String namePiece){
        Square[][] board = _board;
        PieceFactory changePiece = new PieceFactory();
        if(namePiece.equals("Tour")){
            board[square.getRow()][square.getColumn()].setPiece(changePiece.createRook(square.getPiece().getColor()));
        }else if(namePiece.equals("Cavalier")){
            board[square.getRow()][square.getColumn()].setPiece(changePiece.createKnight(square.getPiece().getColor()));
        }
        else if(namePiece.equals("Fou")){
            board[square.getRow()][square.getColumn()].setPiece(changePiece.createBishop(square.getPiece().getColor()));
        }
        else{
            board[square.getRow()][square.getColumn()].setPiece(changePiece.createQueen(square.getPiece().getColor()));
        }

        setBoard(board);
    }
    public void setBoard(Square[][] board){
        this._board = board;
    }

    /**
     *  On supprime la pièce de la case attaqué
     * @param square case où l'on supprime la pièce
     */
    public void attack(Square square){
        square.setPiece(null); // Pièce supprimé
    }

    /**
     *  Vérification si le Roi est en échec
     * @return retourne vrai s il y a échec
     */
    public Boolean isCheck(){
        for(int l = 0; l<8; l++) {
            for (int c = 0; c < 8; c++) {
                for(Square square : this._validSquares){
                    if(square.getPiece() != null && square.getPiece().getName().equals(Type.KING)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *  Retourne la liste des cases où la pièce (qui met en échec le Roi)
     *  peut aller et en prenant également le Roi afin de supprimer les cases où
     *  le roi peut aller.
     * @return retourne la liste des cases
     */
    public List<Square> squareForbidden(){
        List<Square> squareForbidden = new ArrayList<>();
        for (Square s : getValidSquares()) {
            squareForbidden.add(s);
        }
        return squareForbidden;
    }

    /**
     *  Permet de stocker la dernière case de la pièce déplacé
     * @param lastPieceMove la case de la dernière pièce déplacé
     */
    public void setLastPieceMove(Square lastPieceMove){
        this._lastPieceMove = lastPieceMove;
    }
}
