package View;

import javax.swing.*;
import java.awt.*;
import Model.Square;
import Model.*;

public class BoardView extends JFrame{

    public BoardView(){
        Board board = new Board();
        setTitle("Jeu d'échec");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        GridLayout chessLayout = new GridLayout(8,8);
        setLayout(chessLayout);
        int posX = 0;
        int posY = 0;
        for(int c = 0; c<8; c++){
            for(int l=0; l<8; l++){
               // Square square = new Square();
                Panel panel = new Panel();
                Square square = new Square(l, c, null);
                board.setSquare(square, l, c);
                panel.setSize(100, 100);
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
                panel.setVisible(true);
                posX += 100;
                add(panel);
            }
            posY += 100;
            posX = 0;
        }
        for(int c = 0; c<8; c++) {
            for (int l = 0; l < 8; l++) {
                System.out.print(board.getSquare(l, c).getRow() + "," + board.getSquare(l, c).getColumn()  + " ");
            }
            System.out.print("\n");
        }

    }
}
