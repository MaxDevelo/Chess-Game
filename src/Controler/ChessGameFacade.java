package Controler;

import Model.*;
import Model.Pieces.Piece;
import Model.Pieces.Type;

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
        _board.setValidSquares(square);
    }
    /**
     *  Retourne le jeu
     */
    public Game getGame(){
        return this._game;
    }
    /**
     *  Retourne le plateau
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
        for (Player p :  _game.getPlayers()) {
            if(p.getName().equals(player1.getName())){
                _game.getPlayers().set(cursor, player1);
            }else if(p.getName().equals(player2.getName())){
                _game.getPlayers().set(cursor, player2);
            }
            cursor++;
        }
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
        // On vérifie si la  pièce est noir et qu'elle est de type PION pour gérer la prise en apssant
        if(square.getPiece().getColor().equals(Color.BLACK) && square.getPiece().getName().equals(Type.PAWN)){
            // On vérifie si on peut effectuer la prise en passant
            if(board[pnlRow-1][pnlColumn].getPiece() != null && board[pnlRow-1][pnlColumn].getPiece().getName().equals(Type.PAWN) && pnlRow-1 == 4){
                _game.getPlayers().get((_game.getPlayers().get(0).getCanPlay()) ? 0 : 1).addPieceCaptured(board[pnlRow-1][pnlColumn].getPiece());
                updateScore(1);
                _board.attack(board[pnlRow-1][pnlColumn]);
            }
            // On vérifie si on peut effectuer la prise en passant
        }else if(square.getPiece().getColor().equals(Color.WHITE) && square.getPiece().getName().equals(Type.PAWN)){
            if(board[pnlRow+1][pnlColumn].getPiece() != null && board[pnlRow+1][pnlColumn].getPiece().getName().equals(Type.PAWN) && pnlRow+1 == 3){
                _game.getPlayers().get((_game.getPlayers().get(0).getCanPlay()) ? 0 : 1).addPieceCaptured(board[pnlRow+1][pnlColumn].getPiece());
                updateScore(1);
                _board.attack(board[pnlRow+1][pnlColumn]);
            }
        }
        // Si la case que l'on veut aller a une pièce ennemi, alors on l'attaque
        if(board[pnlRow][pnlColumn].getPiece() != null && board[pnlRow][pnlColumn].getPiece().getColor() != square.getPiece().getColor()){
            // Ajout de la case capturée dans la liste des pièces capturées chez le joueur
            _game.getPlayers().get((_game.getPlayers().get(0).getCanPlay()) ? 0 : 1).addPieceCaptured(board[pnlRow][pnlColumn].getPiece());
            // Attaquer la pièce (permet de mettre cette case à NULL)
            _board.attack(board[square.getRow()][square.getColumn()]);
            // On donne des points.
            if(board[pnlRow][pnlColumn].getPiece().getName().equals(Type.PAWN)){
                updateScore(1); // 1 POINT
            }else if(board[pnlRow][pnlColumn].getPiece().getName().equals(Type.BISHOP)){
                updateScore(3); // 3 POINTS
            }else if(board[pnlRow][pnlColumn].getPiece().getName().equals(Type.ROOK)){
                updateScore(5); // 5 POINTS
            }else if(board[pnlRow][pnlColumn].getPiece().getName().equals(Type.KNIGHT)){
                updateScore(3); // 3 POINTS
            }else if(board[pnlRow][pnlColumn].getPiece().getName().equals(Type.QUEEN)){
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
        if(board[pnlRow][pnlColumn].getPiece().getName().equals(Type.PAWN)){
            if((pnlRow-1 == 2 || pnlRow+1 == 5) && board[pnlRow][pnlColumn].getPiece().isFirstMove()){
                _board.setLastPieceMove(board[pnlRow][pnlColumn]);
            }
            board[pnlRow][pnlColumn].getPiece().setIsFirstMove(false);
        }
        _board.setBoard(board);
    }


    /**
     * On vérifie si il y a échec et mate du Roi
     */
    public Boolean verifyIfCheckMateKing(){
        for(int l = 0; l<8; l++){
            for(int c=0; c<8; c++) {
                // On regarde que la case à une pièce et que cette pièce est de la meme couleur que le joueur
                    if(_board.getBoards()[l][c].getPiece() != null && _board.getBoards()[l][c].getPiece().getColor() ==  _game.getPlayerPlay().getColor()){
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
    }

}
