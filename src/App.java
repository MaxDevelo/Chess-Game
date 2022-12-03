import Controler.Controller;
import View.BoardView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class App {
    public static void main(String[] args) {
        // TESTE
        Controller controller = new Controller();
        controller.newGame();
        new BoardView(controller);
    }
}
