package Controler;

import Model.*;
import Model.Pieces.Type;
import View.BoardView;

import static Model.Color.WHITE;

public class ChessGameFacade {
    private Game _game;
    private Board _board;

    /**
     *  Création de la partie
     * @param namePlayer1 Nom du joueur 1
     * @param namePlayer2 Nom du joueur 2
     * @param teamJoueur1 Couleur de la pièce du joueur 1
     * @param teamJoueur2 Couleur de la pièce du joueur 2
     */
    public void newGame(String namePlayer1, String namePlayer2, Color teamJoueur1, Color teamJoueur2){
        // Création du jeu
        _game = new Game();
        // gréation de l'échequier
        _board = new Board();
        // Mise en place du joueur 1
        Player player1 = new Player();
        player1.setName(namePlayer1);
        player1.setColor(teamJoueur1);
        player1.isTurn((teamJoueur1== WHITE)? true: false); // Les pièces blanches commencent en premier
        _game.setPlayer(player1);

        Player player2 = new Player();
        player2.setName(namePlayer2);
        player2.setColor(teamJoueur2);
        player2.isTurn((teamJoueur2== WHITE)? true: false); // Les pièces blanches commencent en premier
        _game.setPlayer(player2);

        // Joueur qui va jouer en premier (Pièces blanches)
        // On stocke le joueur en mémoire le temps qu'il joue
        _game.setPlayerPlay((player1.getColor() == WHITE)? player1: player2);
        generatedBoard();
        // On génère le plateau
        generatedBoard();
        // Création de l'observer
        ChessgameObserver o = new BoardView(this);
        _board.addObserver(o);
        _board.notifyObserversTurnGame();
    }


    /**
     *  Génération de l'échequier qui stocke des cases
     */
    public void generatedBoard(){
        // Génération des cases
        for(int l = 0; l<8; l++){
            for(int c=0; c<8; c++) {
                Square square = new Square(l, c, null);
                _board.setSquare(square, l,  c);
            }
        }
        // Génération des pièces pièces
        _board.setBoard(_board.generatePiece(_board.getBoards()));
    }
    /**
     *  Vérifier sur quelle case la pièce peut aller
     * @param square Case de la pièce actuelle
     */
    public void validMove(Square square){
        _board.setCurrentPiece(square.getPiece());
        _board.clearValidSquare();
        if(!verifyIfCheckKing()){
            if (square.getPiece().getType().equals(Model.Pieces.Type.KING) && square.getPiece().isFirstMove()) {
                _board.canCastle(square);
            }
        }
        // Vérifie si il y a un Roque
        _board.setValidSquares(square);
    }
    /**
     *  Fonction qui permet de récuperer le Jeu
     * @return retourne le jeu
     */
    public Game getGame(){
        return this._game;
    }
    /**
     *  Fonction qui permet de récupérer le board
     *  @return retourne le board
     */
    public Board getBoard(){
        return this._board;
    }
    /**
     *  Permer de changer de joueur.
     * @param player1 Joueur 1
     * @param player2 Joueur 2
     */
    public void turnGame(Player player1, Player player2){
        // On regarde si c'est le joueur 1 qui a deja joué
        if(_game.getPlayers().get(0).getCanPlay()){
            player1.isTurn(false);
            player2.isTurn(true);
            _game.setPlayerPlay(player2);
        }else{
            player1.isTurn(true);
            player2.isTurn(false);
            _game.setPlayerPlay(player1);
        }
        int cursor = 0;
        // On met à jour la liste des joueurs afin de changer le tour de
        // celui qui doit jouer
        for (Player p :  _game.getPlayers()) {
            if(p.getName().equals(player1.getName())){
                _game.getPlayers().set(cursor, player1);
            }else if(p.getName().equals(player2.getName())){
                _game.getPlayers().set(cursor, player2);
            }
            cursor++;
        }
        if(isPat()){
            _game.setNullGame(true);
        }
        // On met a jour le tour du joueur
        _board.notifyObserversTurnGame();
    }

    /**
     * Fonction qui met à jour le tableau 2d de cases suite au mouvement de la pièce
     * @param square Case où la pièce que l'on veut bouger est
     * @param pnlRow Ligne de la case où l'on souhaite la mettre
     * @param pnlColumn Colonne de la case où l'on souhaite la mettre
     */
    public void moveAt(Square square, int pnlRow, int pnlColumn){
        // On récupère le plateau
        Square[][] board = _board.getBoards();
        // On vérifie si la  pièce est noir et qu'elle est de type PION pour gérer la prise en passant
        if(square.getPiece().getColor().equals(Color.BLACK) && square.getPiece().getType().equals(Type.PAWN)){
            // On vérifie si on peut effectuer la prise en passant.
            if(square.getColumn() != pnlColumn && board[pnlRow-1][pnlColumn].getPiece() != null && board[pnlRow-1][pnlColumn].getPiece().getType().equals(Type.PAWN) && pnlRow-1 == 4 ){
                _game.getPlayers().get((_game.getPlayers().get(0).getCanPlay()) ? 0 : 1).addPieceCaptured(board[pnlRow-1][pnlColumn].getPiece());
                updateScore(1);
                _board.attack(board[pnlRow-1][pnlColumn]);
            }
            // On vérifie si on peut effectuer la prise en passant
        }else if(square.getPiece().getColor().equals(Color.WHITE) && square.getPiece().getType().equals(Type.PAWN)){
            if(_board.verifyLimitBoard(pnlRow+1) && square.getColumn() != pnlColumn && board[pnlRow+1][pnlColumn].getPiece() != null && board[pnlRow+1][pnlColumn].getPiece().getType().equals(Type.PAWN) && pnlRow+1 == 3){
                _game.getPlayers().get((_game.getPlayers().get(0).getCanPlay()) ? 0 : 1).addPieceCaptured(board[pnlRow+1][pnlColumn].getPiece());
                updateScore(1);
                _board.attack(board[pnlRow+1][pnlColumn]);
            }
            // On vérifie si il y a un Roque possible
        } else if (_board.canCastle(square)) {
            // Vérifier si le déplacement du Roi est vers la Droite et que sur 2 cases après, il y a une Tour
            if(_board.verifyLimitBoard(pnlColumn+2) && _board.getBoards()[pnlRow][pnlColumn+2].getPiece() != null && square.getColumn() < pnlColumn && _board.getBoards()[pnlRow][pnlColumn+2].getPiece().getType().equals(Type.ROOK)){
                board[pnlRow][pnlColumn-1].setPiece(_board.getBoards()[pnlRow][pnlColumn+2].getPiece());
                board[pnlRow][pnlColumn+2].setPiece(null);
            }
            // Vérifier si le déplacement du Roi est vers la Gauche et que sur la case précédente il y a une Tour
            if(_board.verifyLimitBoard(pnlColumn-1) && _board.getBoards()[pnlRow][pnlColumn-1].getPiece() != null && square.getColumn() > pnlColumn && _board.getBoards()[pnlRow][pnlColumn-1].getPiece().getType().equals(Type.ROOK)){
                board[pnlRow][pnlColumn + 1].setPiece(_board.getBoards()[pnlRow][pnlColumn - 1].getPiece());
                board[pnlRow][pnlColumn - 1].setPiece(null);
            }
            _board.setBoard(board);
        }
        // Si la case que l'on veut aller a une pièce ennemi, alors on l'attaque
        if(board[pnlRow][pnlColumn].getPiece() != null && !board[pnlRow][pnlColumn].getPiece().getColor().equals(square.getPiece().getColor())){
            // Ajout de la case capturée dans la liste des pièces capturées chez le joueur
            _game.getPlayers().get((_game.getPlayers().get(0).getCanPlay()) ? 0 : 1).addPieceCaptured(board[pnlRow][pnlColumn].getPiece());
            // Attaquer la pièce (permet de mettre cette case à NULL)
            _board.attack(board[square.getRow()][square.getColumn()]);
            // On donne des points.
            if(board[pnlRow][pnlColumn].getPiece().getType().equals(Type.PAWN)){
                updateScore(1); // 1 POINT
            }else if(board[pnlRow][pnlColumn].getPiece().getType().equals(Type.BISHOP)){
                updateScore(3); // 3 POINTS
            }else if(board[pnlRow][pnlColumn].getPiece().getType().equals(Type.ROOK)){
                updateScore(5); // 5 POINTS
            }else if(board[pnlRow][pnlColumn].getPiece().getType().equals(Type.KNIGHT)){
                updateScore(3); // 3 POINTS
            }else if(board[pnlRow][pnlColumn].getPiece().getType().equals(Type.QUEEN)){
                updateScore(9); // 9 POINTS
            }else{
                // RIEN POUR LE ROI car sa met fin à la partie
            }
        }
        // On met la case où la pièce était avant à NULL.
        board[square.getRow()][square.getColumn()].setPiece(null);
        // On ajout la pièce dans sa nouvelle case.
        board[pnlRow][pnlColumn].setPiece(_board.getCurrentPiece());
        // Onr écupère le premier mouvement du Pion pour la prise en passant
        if(board[pnlRow][pnlColumn].getPiece().getType().equals(Type.PAWN)){
            if((pnlRow-1 == 2 || pnlRow+1 == 5) && board[pnlRow][pnlColumn].getPiece().isFirstMove()){
                _board.setLastPieceMove(board[pnlRow][pnlColumn]);
            }
            board[pnlRow][pnlColumn].getPiece().setIsFirstMove(false);
        }
        _board.setBoard(board);
        // Le Roi termine son premier mouvement
        if(board[pnlRow][pnlColumn].getPiece().getType().equals(Type.KING) &&  board[pnlRow][pnlColumn].getPiece().isFirstMove() != null){
            board[pnlRow][pnlColumn].getPiece().setIsFirstMove(false);
        }
        // La Tour termine son premier mouvement
        if(board[pnlRow][pnlColumn].getPiece().getType().equals(Type.ROOK) &&  board[pnlRow][pnlColumn].getPiece().isFirstMove() != null){
            board[pnlRow][pnlColumn].getPiece().setIsFirstMove(false);
        }
    }


    /**
     * On vérifie si il y a échec et mate du Roi
     */
    public Boolean verifyIfCheckMateKing(){
        for(int l = 0; l<8; l++){
            for(int c=0; c<8; c++) {
                // On regarde que la case à une pièce et que cette pièce est de la meme couleur que le joueur
                    if(_board.getBoards()[l][c].getPiece() != null && _board.getBoards()[l][c].getPiece().getColor() ==  _game.getPlayerPlay().getColor()){
                        _board.clearValidSquare();
                        _board.setValidSquares(_board.getBoards()[l][c]);
                        // On vérifie si le roi est en échec
                        if(_board.isCheck()){
                            // FIN de la partie
                            _game.setEndGame(true);
                            // Clear la liste des cases valides
                            _board.clearValidSquare();
                            int n=0;
                            // On change le setWin au joueur gagant
                            for(Player player: _game.getPlayers()){
                                if(player == _game.getPlayerPlay()){
                                    Player updatePlayer = _game.getPlayers().get(n);
                                    updatePlayer.setWin(true);
                                    _game.getPlayers().set(n, updatePlayer); // Update du joueur
                                }
                                n++;
                            }
                            return true;
                        }
                    }
            }
        }
        return false;
    }

    /**
     * On vérifie si il y a échec du Roi
     */
    public Boolean verifyIfCheckKing(){
        for(int l = 0; l<8; l++){
            for(int c=0; c<8; c++) {
                // On regarde que la case à une pièce et que cette pièce est de la meme couleur que le joueur
                if(_board.getBoards()[l][c].getPiece() != null && _board.getBoards()[l][c].getPiece().getColor() !=  _game.getPlayerPlay().getColor()){
                    _board.clearValidSquare();
                    _board.setValidSquares(_board.getBoards()[l][c]);
                    // On vérifie si le roi est en échec
                    if(_board.isCheck()){
                        return true;
                    }
                    _board.clearValidSquare();
                }
            }
        }
        return false;
    }


    /**
     * Fonction qui met à jour le score du joueur
     * @param score score du joueur
     */
    public void updateScore(int score){

        int cursor = 0;
        _game.getPlayerPlay().setScore(score);
        for (Player p :  _game.getPlayers()) {
            if(p.getName().equals(_game.getPlayerPlay().getName())){
                _game.getPlayers().set(cursor, _game.getPlayerPlay());
            }
            cursor++;
        }
        int scoreBlack = _game.getPlayers().get(0).getColor().equals(Model.Color.BLACK) ? _game.getPlayers().get(0).getScore() : _game.getPlayers().get(1).getScore();
        int scoreWhite = _game.getPlayers().get(0).getColor().equals(Model.Color.WHITE) ? _game.getPlayers().get(0).getScore() : _game.getPlayers().get(1).getScore();
        _board.notifyObserversScore(scoreBlack, scoreWhite);
    }
    /**
     * Fonction qui vérifie si le Joueur peut déplacer une pièce
     * Si il peut pas, alors on a partie nulle
     * return vrai si il y a partie nulle
     */
    public Boolean isPat(){
        return _board.noMovePossible(_game.getPlayerPlay().getColor());
    }

}
