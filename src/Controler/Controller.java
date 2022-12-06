package Controler;

import Model.*;

import static Model.Color.WHITE;

public class Controller {
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
        // gGénération des pièces pièces
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
    public void moveAt(Square square, int pnlRow, int pnlColumn){
        Square[][] board = _board.getBoard();
        if(board[pnlRow][pnlColumn].getPiece() != null && board[pnlRow][pnlColumn].getPiece().getColor() != square.getPiece().getColor()){
            // On donne des points.
            if(board[pnlRow][pnlColumn].getPiece().getName().equals("P")){
                updateScore(1); // 1 POINT
            }else if(board[pnlRow][pnlColumn].getPiece().getName().equals("B")){
                updateScore(3); // 3 POINTS
            }else if(board[pnlRow][pnlColumn].getPiece().getName().equals("R")){
                updateScore(5); // 5 POINTS
            }else if(board[pnlRow][pnlColumn].getPiece().getName().equals("kn")){
                updateScore(3); // 3 POINTS
            }else if(board[pnlRow][pnlColumn].getPiece().getName().equals("Q")){
                updateScore(9); // 9 POINTS
            }else{
                // ROI CAPTURE.
            }
            _board.attack(board[square.getRow()][square.getColumn()]);
        }
        board[square.getRow()][square.getColumn()].setPiece(null);
        board[pnlRow][pnlColumn].setPiece(_board.getCurrentPiece());
        _board.setBoard(board);
    }


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

}
