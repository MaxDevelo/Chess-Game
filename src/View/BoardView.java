package View;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import Controler.Facade;
import Model.Square;
import Model.*;

public class BoardView extends JFrame{
    private Board _board;
    private Panel[][] _panels;
    public BoardView(){
        this._panels = new Panel[8][8];
        this._board = Board.getInstance();
        setTitle("Jeu d'échec");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout chessLayout = new GridLayout(8,8);
        setLayout(chessLayout);
        int posX = 0;
        int posY = 0;
        for(int c = 0; c<8; c++){
            for(int l=0; l<8; l++){
               // Square square = new Square();
                Panel panel = new Panel();
                Square square = new Square(l, c, null);
                _board.setSquare(square, l, c);
                panel.setSize(100, 100);
                _panels[c][l] = panel;
                if(c%2 == 0){
                    if(l%2 == 0){
                        panel.setBackground(Color.white);
                    }else{
                        panel.setBackground(Color.black);
                    }
                }else{
                    if(l%2 != 0){
                        panel.setBackground(Color.white);
                    }else{
                        panel.setBackground(Color.black);
                    }
                }

                panel.setLocation(posX, posY);
                posX += 100;
                add(panel);
                panel.setVisible(true);
            }
            posY += 100;
            posX = 0;
        }
        generatedPiece();
        setVisible(true);
    }

    public void generatedPiece(){
        Facade facade = new Facade();
        facade.generatePiece(_board.getBoard());
        for(int c = 0; c<8; c++) {
            for (int l = 0; l < 8; l++) {
                if(_board.getBoard()[c][l].getPiece() != null){
                    JButton btnPiece = new JButton(_board.getBoard()[c][l].getPiece().getName());
                    btnPiece.setSize(100, 100);
                    _panels[c][l].add(btnPiece);
                }
            }
        }

    }
}
