package Controler;

import Model.*;
import Model.Pieces.Piece;
import Model.Pieces.Type;

import static Model.Color.BLACK;
import static Model.Color.WHITE;

public class ChessGameFacade {
    private Game _game;
    private Board _board;

    public void newGame(String namePlayer1, String namePlayer2, Color teamJoueur1, Color teamJoueur2){
        _game = new Game();
        _board = new Board();
        Player player1 = new Player();
        player1.setName(namePlayer1);
        player1.setColor(teamJoueur1);
        player1.isTurn(true);
        player1.isTurn((teamJoueur1== WHITE)? true: false);
        _game.setPlayer(player1);

        Player player2 = new Player();
        player2.setName(namePlayer2);
        player2.setColor(teamJoueur2);
        player2.isTurn(false);
        player2.isTurn((teamJoueur2== WHITE)? true: false);
        _game.setPlayer(player2);

        // le joueur TEAM WHITE commence en premier
        _game.setPlayerPlay((player1.getColor() == WHITE)? player1: player2);
        generatedBoard();

    }

    public void generatedBoard(){
        // Génération des cases
        for(int l = 0; l<8; l++){
            for(int c=0; c<8; c++) {
                Square square = new Square(l, c, null);
                _board.setSquare(square, l,  c);
            }
        }
        // Génération des pièces pièces
        _board.setBoard(_board.generatePiece(_board.getBoard()));
    }
    public void validMove(Square square){
        _board.setCurrentPiece(square.getPiece());
        _board.setValidSquares(square);
    }
    public Game getGame(){
        return this._game;
    }
    public Board getBoard(){
        return this._board;
    }
    public void turnGame(Player player1, Player player2){
        isStalemate();
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
    /*
    * Fonction qui met à jour le tableau 2d de cases suite au mouvement de la pièce
    * */
    public void moveAt(Square square, int pnlRow, int pnlColumn){
        Square[][] board = _board.getBoard();
        if(square.getPiece().getColor().equals(Color.BLACK)){
            if(board[pnlRow-1][pnlColumn].getPiece() != null && board[pnlRow-1][pnlColumn].getPiece().getName().equals(Type.PAWN) && pnlRow-1 == 4){
                _game.getPlayers().get((_game.getPlayers().get(0).getCanPlay()) ? 0 : 1).addPieceCaptured(board[pnlRow-1][pnlColumn].getPiece());
                updateScore(1);
                _board.attack(board[pnlRow-1][pnlColumn]);
            }
        }else{
            if(board[pnlRow+1][pnlColumn].getPiece() != null && board[pnlRow+1][pnlColumn].getPiece().getName().equals(Type.PAWN) && pnlRow+1 == 3){
                _game.getPlayers().get((_game.getPlayers().get(0).getCanPlay()) ? 0 : 1).addPieceCaptured(board[pnlRow+1][pnlColumn].getPiece());
                updateScore(1);
                _board.attack(board[pnlRow+1][pnlColumn]);
            }
        }
        if(board[pnlRow][pnlColumn].getPiece() != null && board[pnlRow][pnlColumn].getPiece().getColor() != square.getPiece().getColor()){
            _game.getPlayers().get((_game.getPlayers().get(0).getCanPlay()) ? 0 : 1).addPieceCaptured(board[pnlRow][pnlColumn].getPiece());
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
                // ROI CAPTURE.
            }
        }
        board[square.getRow()][square.getColumn()].setPiece(null);
        board[pnlRow][pnlColumn].setPiece(_board.getCurrentPiece());
        // Onr écupère le prmeier mouvement du Pion pour la prise en passant
        if(board[pnlRow][pnlColumn].getPiece().getName().equals(Type.PAWN)){
            if((pnlRow-1 == 2 || pnlRow+1 == 5) && board[pnlRow][pnlColumn].getPiece().isFirstMove()){
                _board.setLastPieceMove(board[pnlRow][pnlColumn]);
            }
            board[pnlRow][pnlColumn].getPiece().setIsFirstMove(false);
        }
        _board.setBoard(board);
    }
    /*
    * On vérifie si il y a échec et mate
    **/
    public Boolean verifyIfCheckMateKing(){
        for(int l = 0; l<8; l++){
            for(int c=0; c<8; c++) {
                    if(_board.getBoard()[l][c].getPiece() != null && _board.getBoard()[l][c].getPiece().getColor() ==  _game.getPlayerPlay().getColor()){
                        _board.setValidSquares(_board.getBoard()[l][c]);
                        // On vérifie si le roi est en échec
                        if(_board.isCheck()){
                            System.out.println("Adieux le ROI !");
                            _game.setEndGame(true);
                            _board.clearValidSquare();
                            int n=0;
                            for(Player player: _game.getPlayers()){
                                if(player == _game.getPlayerPlay()){
                                    Player updatePlayer = _game.getPlayers().get(n);
                                    updatePlayer.setWin(true);
                                    _game.getPlayers().set(n, updatePlayer);
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

    /*
     * On vérifie si le Roi est en échec
     */
    public Boolean verifyIfCheckKing(){
        for(int l = 0; l<8; l++){
            for(int c=0; c<8; c++) {
                if(_board.getBoard()[l][c].getPiece() != null && _board.getBoard()[l][c].getPiece().getColor() !=  _game.getPlayerPlay().getColor()){
                    _board.setValidSquares(_board.getBoard()[l][c]);
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

    /*
     * Fonction qui met à jour le score du joueur
     * */
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

    public int getScorePlayer(Player player){
        return player.getScore();
    }



    // Détection de patte
    public boolean isStalemate() {
        for (int i = 0; i < _board.getBoard().length; i++) {
            for (int j = 0; j <  _board.getBoard()[i].length; j++) {
                Piece piece =  _board.getBoard()[i][j].getPiece();
                Player player = _game.getPlayers().get(0).getCanPlay() ? _game.getPlayers().get(0): _game.getPlayers().get(1);
                if (piece != null && player.getColor() == piece.getColor()) {
                    for (int k = 0; k <  _board.getBoard().length; k++) {
                        for (int l = 0; l <  _board.getBoard()[k].length; l++) {
                            if(_board.getBoard()[i][j].getPiece().getName() != Type.KING){
                                if (piece.canMove(_board.getBoard()[k][l], _board.getBoard()[i][j]) && !_board.isCheck()) { // Regarde si la pièce peut bouger et qu'il n'y a pas echec
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
