package Controler;

import Model.Board;
import Model.Game;
import Model.Square;

public class Controller {
    private Game _game;
    private Board _board;

    public void newGame(){
        _game = new Game();
        _board = new Board();
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
