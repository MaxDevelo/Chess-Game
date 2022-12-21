import Controler.ChessGameFacade;
import View.BoardView;

import Model.Color;
import View.StartGameView;


public class App {
    public static void main(String[] args) {
        new StartGameView(new ChessGameFacade());
    }
}
