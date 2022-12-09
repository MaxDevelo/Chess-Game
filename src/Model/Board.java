package Model;

import Model.Pieces.Piece;
import Model.Pieces.Type;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private Square[][] _board;
    private Piece _currentPiece;
    private List<Square> _validSquares;
    public Board(){
        _board = new Square[8][8];
        this._validSquares = new ArrayList<>();
    }

    /*
        Génération des pièces
    */

    public Square[][]  generatePiece(Square[][] board){

        PieceFactory createPiece = new PieceFactory();

        // NOIR
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


        // BLANC
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

    /*
        stocker une case
    */
    public void setSquare(Square square, int row, int column){
        this._board[row][column] = square;
    }

    /*
    récupérer une case
    */
    public Square getSquare(JButton btnPiece){
        return  this._board[btnPiece.getParent().getLocation().y / 100][btnPiece.getParent().getLocation().x / 100];
    }
    public Square[][] getBoard(){
        return this._board;
    }


    public void setCurrentPiece(Piece piece){
        this._currentPiece = piece;
    }
    public Piece getCurrentPiece(){
        return this._currentPiece;
    }
    public List<Square> getValidSquares(){
        return this._validSquares;
    }
    /*
        Vérifier sur quelles cases la pièce peut aller.
    */
    public void setValidSquares(Square square){
        clearValidSquare();
        for(int l = 0; l<8; l++) {
            for (int c = 0; c < 8; c++) {
                if(square.getPiece().canMove(_board[l][c], square)){ // == true
                    this._validSquares.add(_board[l][c]);
                }
            }
        }
    }
    public void clearValidSquare(){ // On rénitialise la liste des cases validés
        this._validSquares.clear();
    }
    /*
    * On vérifie si il y a promotion de la pièce
    * */
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
    /*
    * Changement de pièce (Attention, cela est harcodé)
    * */
    public void promotion(Square square){
        Square[][] board = _board;
        PieceFactory changePiece = new PieceFactory();
        if(square.getPiece().getColor() == Color.BLACK){
            board[square.getRow()][square.getColumn()].setPiece(changePiece.createKnight(Model.Color.BLACK)); // CREATION CAVALIER
        }else{
            board[square.getRow()][square.getColumn()].setPiece(changePiece.createKnight(Model.Color.WHITE)); // CREATION CAVALIER
        }
        setBoard(board);
    }
    public void setBoard(Square[][] board){
        this._board = board;
    }
    /*
        On attaque la pièce en la mettant à null
     */
    public void attack(Square square){
        square.setPiece(null); // Pièce supprimé
    }
    /*
    * Fonction qui vérifie si le roi est en échec
    * */
    public Boolean isCheck(){
        for(int l = 0; l<8; l++) {
            for (int c = 0; c < 8; c++) {
                for(Square square : this._validSquares){
                    if(square.getPiece() != null && square.getPiece().getName() == Type.KING){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /*
     * Fonction qui vérifie si il y a échec et mate
     * */
    public Boolean isCheckMate(){
        for(int l = 0; l<8; l++) {
            for (int c = 0; c < 8; c++) {
                // A COMPLETER
            }
        }
        return false;
    }
}
