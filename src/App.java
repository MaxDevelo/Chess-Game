import Controler.Controller;
import View.BoardView;
import View.StartGameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Color;



public class App {
    public static void main(String[] args) {
        // TESTE en mode HARDCODE
        Controller controller = new Controller();
        controller.newGame("Maxime", "Théo", Color.WHITE, Color.BLACK);
        new BoardView(controller);
        // Le jeu commence par l'inscription des 2 joueurs
        //new StartGameView(controller);
    }
}
