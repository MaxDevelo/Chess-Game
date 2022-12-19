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

    public Board() {
        _board = new Square[8][8];
        this._validSquares = new ArrayList<>();
        this._lastPieceMove = null;
    }


    /**
     * Génération des pièces dans le tableau de case
     *
     * @param board tableau de case
     * @return le tableau de case avec els pièces
     * et dont le fils droit et le fils gauche sont vides.
     */
    public Square[][] generatePiece(Square[][] board) {

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
     * Mettre la case dans le tableau de case
     *
     * @param square Il s'agit de la case à initialiser
     * @param row    correspond à la ligne de la case
     * @param column correspond à la colonne de la case
     */
    public void setSquare(Square square, int row, int column) {
        this._board[row][column] = square;
    }


    /**
     * Récupère la case
     *
     * @param row    correspond à la ligne de la case
     * @param column correspond à la colonne de la case
     * @return retourne la case
     */
    public Square getSquare(int row, int column) {
        return this._board[row][column];
    }

    /**
     * Retourner le tableau de case
     *
     * @return retourne le tableau
     */
    public Square[][] getBoards() {
        return this._board;
    }


    /**
     * La pièces où l'on joue actuellement
     *
     * @param piece pièce actuel
     */
    public void setCurrentPiece(Piece piece) {
        this._currentPiece = piece;
    }

    public Piece getCurrentPiece() {
        return this._currentPiece;
    }

    public List<Square> getValidSquares() {
        return this._validSquares;
    }

    /**
     * Vérifier sur quelles cases on peut aller et il le met dans une liste de case
     * @param square case à vérifier
     */
    public void setValidSquares(Square square) {
        for (int l = 0; l < 8; l++) {
            for (int c = 0; c < 8; c++) {
                if (isTakenInPassing(square)) {
                    if (_lastPieceMove.getPiece().getColor().equals(Color.BLACK)) {
                        if (_board[l][c].getRow() == 2 && _board[l][c].getColumn() == _lastPieceMove.getColumn()) {
                            this._validSquares.add(_board[l][c]);
                        }
                    } else {
                        if (_board[l][c].getRow() == 5 && _board[l][c].getColumn() == _lastPieceMove.getColumn()) {
                            this._validSquares.add(_board[l][c]);
                        }
                    }
                }
                if (square.getPiece().canMove(_board[l][c], square)) { // == true
                    this._validSquares.add(_board[l][c]);
                }
            }
        }
    }


    /**
     * Clear la liste des cases valide (qui permet de savoir où la Pièce peut aller
     */
    public void clearValidSquare() { // On rénitialise la liste des cases validés
        this._validSquares.clear();
    }

    /**
     * Vérifie si il y a prise en passant pour le Pion
     * @param currentSquare case actuelle où est la pièce
     */
    public boolean isTakenInPassing(Square currentSquare) {
        // On vérifie que la dernière pièce qui a bougé n'est pas null (On sait jamais)
        // On regarde si la pièce que l'on veut bouger n'est pas de la meme couleur
        // que la dernière pièce qui a bougé
        if (_lastPieceMove != null && _lastPieceMove.getPiece() != null && _lastPieceMove.getPiece().getColor() != currentSquare.getPiece().getColor()) {
            if (_lastPieceMove.getPiece().getColor().equals(Color.BLACK)) {
                // Si la dernière pièce qui a bougé est à gauche de la pièce actuelle
                if ((_lastPieceMove.getRow() - 1 == 2 && currentSquare.getRow() - 1 == 2) && (_lastPieceMove.getColumn() ==  currentSquare.getColumn()-1 || _lastPieceMove.getColumn() ==  currentSquare.getColumn()+1) ) {
                    return true;
                }
            } else {
                // Si la dernière pièce qui a bougé est à droite de la pièce actuelle
                if ((_lastPieceMove.getRow() + 1 == 5 && currentSquare.getRow() + 1 == 5) && (_lastPieceMove.getColumn() ==  currentSquare.getColumn()-1 || _lastPieceMove.getColumn() ==  currentSquare.getColumn()+1)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gestion de la promotion du Pion
     *
     * @param square case à vérifier
     * @return retourne vrai si il y a promotion
     */
    public Boolean isPromoted(Square square) {
        if (square.getPiece() != null) {
            // On vérifie que la pièce est bien un Pion
            if (square.getPiece().getType().equals(Type.PAWN)) {
                if (square.getPiece().getColor().equals(Color.WHITE)) {
                    // On regarde si le Pion blanc est sur la première ligne du plateau
                    if (square.getRow() == 0) {
                        return true;
                    }
                } else {
                    // On regarde si le Pion noir est sur la dernière  ligne du plateau
                    if (square.getRow() == 7) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * Changement de la pièce (Attention, c'est un test. NON TERMINE
     *
     * @param square    case où l'on a la promotion
     * @param namePiece nom de la pièce choisi
     */
    public void promotion(Square square, String namePiece) {
        Square[][] board = _board;
        // Créaton de la factory pour créer la nouvelle Pièce
        PieceFactory changePiece = new PieceFactory();
        if (namePiece.equals("Tour")) {
            board[square.getRow()][square.getColumn()].setPiece(changePiece.createRook(square.getPiece().getColor()));
        } else if (namePiece.equals("Cavalier")) {
            board[square.getRow()][square.getColumn()].setPiece(changePiece.createKnight(square.getPiece().getColor()));
        } else if (namePiece.equals("Fou")) {
            board[square.getRow()][square.getColumn()].setPiece(changePiece.createBishop(square.getPiece().getColor()));
        } else {
            board[square.getRow()][square.getColumn()].setPiece(changePiece.createQueen(square.getPiece().getColor()));
        }
        setBoard(board);
    }

    public void setBoard(Square[][] board) {
        this._board = board;
    }

    /**
     * On supprime la pièce de la case attaqué
     * @param square case où l'on supprime la pièce
     */
    public void attack(Square square) {
        square.setPiece(null); // Pièce supprimé
    }

    /**
     * Vérification si le Roi est en échec
     * @return retourne vrai si il y a échec
     */
    public Boolean isCheck() {
        for (int l = 0; l < 8; l++) {
            for (int c = 0; c < 8; c++) {
                for (Square square : this._validSquares) {
                    // Vérifie si parmi les cases valide de la pièce, il y a le Roi.
                    // Si c'ets le cas, cela veut dire que le Roi est en échec
                    if (square.getPiece() != null && square.getPiece().getType().equals(Type.KING)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Vérification si la position est dans la limite de l'échéquier
     * @param pos Position à vérifier
     * @return retourne vrai si la position ne sort pat de l'échequier
     */
    public boolean verifyLimitBoard(int pos){
        if(pos >= 0 && pos <=7){
            return true;
        }
        return false;
    }
    /**
     * Retourne la liste des cases où la pièce (qui met en échec le Roi)
     * peut aller et en prenant également le Roi afin de supprimer les cases où
     * le roi peut aller.
     *
     * @return retourne la liste des cases
     */
    public List<Square> squareForbidden() {
        List<Square> squareForbidden = new ArrayList<>();
        for (Square s : getValidSquares()) {
            squareForbidden.add(s);
        }
        return squareForbidden;
    }

    /**
     * Permet de stocker la dernière case de la pièce déplacé
     *
     * @param lastPieceMove la case de la dernière pièce déplacé
     */
    public void setLastPieceMove(Square lastPieceMove) {
        this._lastPieceMove = lastPieceMove;
    }
    /**
     * Vérifie si il y a uyn Roque possible
     * @param currentSquare case actuelle où est la pièce (King)
     */
    public boolean canCastle(Square currentSquare) {
        int rowCurrentSquare = currentSquare.getRow();
        int cursorRow = 0; // Permet de savoir quelle ligne il faut prendre
        if(currentSquare.getPiece().getColor().equals(Color.BLACK)) { // Si la pièce actuelle est Noire
            cursorRow = 0; // On prend la ligne 0 (Dans le tableau de cases. C'est la première ligne de l'échequier)
        }else {
            cursorRow = 7;// On prend la ligne 7 (Dans le tableau de cases. C'est la 8ième  ligne de l'échequier)
        }
        // On vérifie si il y a possible de Roque en vérifiant si la première tour n'a pas encore bougé
        if(_board[cursorRow][0].getPiece() != null && (_board[cursorRow][0].getPiece().getType().equals(Type.ROOK)) && rowCurrentSquare == _board[cursorRow][0].getRow() && _board[cursorRow][0].getPiece().isFirstMove()){
            // On vérifie qu'il y a pas de pièce entre le Roi et la Tour.
            if(_board[cursorRow][1].getPiece() == null &&  _board[cursorRow][2].getPiece() == null ){
                // On ajoute les cases à la liste des cases valides pour montrer que le Roi peut aller
                _validSquares.add(_board[cursorRow][1]);
                return true;
            }
            // On vérifie si il y a possible de Roque en vérifiant si la 2ième Tour n'a pas encore bougé
        }
        if(_board[cursorRow][7].getPiece() != null && (_board[cursorRow][7].getPiece().getType().equals(Type.ROOK))  && rowCurrentSquare == _board[cursorRow][7].getRow() && _board[cursorRow][7].getPiece().isFirstMove()){
            // On vérifie qu'il y a pas de pièce entre le Roi et la Tour.
            if(_board[cursorRow][4].getPiece() == null &&  _board[cursorRow][5].getPiece() == null && _board[cursorRow][6].getPiece() == null){
                // On ajoute les cases à la liste des cases valides pour montrer que le Roi peut aller
                _validSquares.add(_board[cursorRow][5]);
                return true;
            }
        }
        return false;
    }
    /**
     * Vérifie si il n'y a plus de mouvement possible pour le joueur
     * (En prenant pas en compte le Roi) afind e savoir si il y a
     * partie nulle
     * @param color Couleur des pièces du joueur
     */
    public Boolean noMovePossible(Color color){
        clearValidSquare();
        for (int l = 0; l < 8; l++) {
            for (int c = 0; c < 8; c++) {
                if(_board[l][c].getPiece() != null && _board[l][c].getPiece().getColor() == color && !_board[l][c].getPiece().getType().equals(Type.KING)){
                    setValidSquares(_board[l][c]);
                    if(_validSquares.size() > 0){
                        return false;
                    }
                    clearValidSquare();
                }
            }
        }
        return true;
    }
}

