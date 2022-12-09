import Controler.ChessGameFacade;
import View.BoardView;

import Model.Color;



public class App {
    public static void main(String[] args) {
        // TESTE en mode HARDCODE
        ChessGameFacade controller = new ChessGameFacade();
        controller.newGame("Maxime", "Théo", Color.WHITE, Color.BLACK);
        new BoardView(controller);
        // Le jeu commence par l'inscription des 2 joueurs
        //new StartGameView(controller);
    }
}
