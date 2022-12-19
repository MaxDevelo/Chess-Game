import Controler.ChessGameFacade;
import View.BoardView;

import Model.Color;
import View.StartGameView;


public class App {
    public static void main(String[] args) {
        // TESTE
        ChessGameFacade controller = new ChessGameFacade();
        //controller.newGame("[Nom Joueur1]", "[Nom Joueur2]", Color.WHITE, Color.BLACK);
        //new BoardView(controller);

        // Le jeu commence par l'inscription des 2 joueurs
        new StartGameView(controller);
    }
}
