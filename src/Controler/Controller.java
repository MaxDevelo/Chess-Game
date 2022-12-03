package Controler;

import Model.*;

public class Controller {
    private Game _game;
    private Board _board;

    public void newGame(String namePlayer1, String namePlayer2, Color teamJoueur1, Color teamJoueur2){
        _game = new Game();
        _board = new Board();
        Player player1 = new Player();
        player1.setName(namePlayer1);
        player1.setColor(teamJoueur1);
        _game.setPlayer(player1);
        Player player2 = new Player();
        player2.setName(namePlayer2);
        player2.setColor(teamJoueur2);
        _game.setPlayer(player2);
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

    public Game getGame(){
        return this._game;
    }
    public Board getBoard(){
        return this._board;
    }

}