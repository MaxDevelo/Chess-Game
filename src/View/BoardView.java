package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.IntStream;

import Controler.Facade;
import Model.Square;
import Model.*;

public class BoardView extends JFrame{
    private Square[][] _board;
    private Board _instanceBoard;
    private JPanel[][] _panels;
    private JPanel pnl_board;
    public BoardView(){
        setSize(898,895);
        pnl_board = new JPanel();
        /*JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        add(tools, BorderLayout.PAGE_START);
        Action newGameAction = new AbstractAction("Nouvelle Partie") {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Nouvelle Partie");
            }
        };
        tools.add(newGameAction);
        tools.addSeparator();
        newGameAction = new AbstractAction("Reset Partie") {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Reset Partie");
            }
        };
        tools.add(newGameAction);
        tools.addSeparator();*/

        this._panels = new JPanel[8][8];
        _instanceBoard = Board.getInstance();
        setTitle("Jeu d'échec");
       // pnl_board.setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout chessLayout = new GridLayout(8,8);
        setLayout(chessLayout);
        int posX = 0;
        int posY = 0;
        for(int l = 0; l<8; l++){
            for(int c=0; c<8; c++){
                JPanel panel = new JPanel();
                Square square = new Square(l, c, null);
                _instanceBoard.setSquare(square, l,  c);
                if(l%2 == 0){
                    if(c%2 == 0){
                        panel.setBackground(Color.white);
                    }else{
                        panel.setBackground(Color.black);
                    }
                }else{
                    if(c%2 != 0){
                        panel.setBackground(Color.white);
                    }else{
                        panel.setBackground(Color.black);
                    }
                }
                panel.setSize(100, 100);
                panel.setLocation(posX, posY);
                _panels[l][c] = panel;
                posX += 100;
                add(panel);
                panel.setVisible(true);
            }
            posY += 100;
            posX = 0;
        }
        generatedPiece(); // Générer les pièces
       // add(pnl_board);
       // pnl_board.setVisible(true);
        this.setResizable(false);
        setVisible(true);
    }

    public void generatedPiece(){
        Facade facade = new Facade();
        this._board = facade.generatePiece(_instanceBoard.getBoard());
        for(int l = 0; l<8; l++) {
            for (int c = 0; c < 8; c++) {
                if(_board[l][c].getPiece() != null){
                    JButton btnPiece = new JButton(_board[l][c].getPiece().getName());
                    btnPiece.setName(_board[l][c].getPiece().getName());
                    //btnPiece.setSize( 100, 100);
                    btnPiece.setLocation(_panels[l][c].getLocation());
                    btnPiece.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent e) {
                            super.mouseClicked(e);
                            movePiece(btnPiece);
                        }
                    });
                    _panels[l][c].add(btnPiece);
                }
            }
        }

    }
    
    public void movePiece(JButton btnPiece) {
        if(_panels[btnPiece.getParent().getLocation().y / 98][btnPiece.getParent().getLocation().x/ 95].getComponents().length == 1) {
             if (_panels[btnPiece.getParent().getLocation().y / 98 ][btnPiece.getParent().getLocation().x/ 95].getComponent(0) == btnPiece) {
                 Piece piece = _board[btnPiece.getParent().getLocation().y / 98][btnPiece.getParent().getLocation().x / 95].getPiece();
                 //System.out.println(piece.getName());
                 if (piece.getColor()) {
                     if (_board[(btnPiece.getParent().getLocation().y / 98 ) - 1][btnPiece.getParent().getLocation().x / 95].getPiece() == null) {
                         _board[(btnPiece.getParent().getLocation().y / 98 )- 1][btnPiece.getParent().getLocation().x / 95].setPiece(piece);
                         _panels[(btnPiece.getParent().getLocation().y / 98 )- 1][btnPiece.getParent().getLocation().x / 95].add(btnPiece);
                         //Supprimer la pièce de la case
                         _panels[(btnPiece.getParent().getLocation().y / 98)+1][btnPiece.getParent().getLocation().x / 95].remove(btnPiece);
                         _panels[(btnPiece.getParent().getLocation().y / 98)+1][btnPiece.getParent().getLocation().x / 95].repaint();
                     }
                 }else {
                    if (_board[(btnPiece.getParent().getLocation().y / 98 )+1][btnPiece.getParent().getLocation().x / 95].getPiece() == null) {
                        _board[(btnPiece.getParent().getLocation().y / 98 )][btnPiece.getParent().getLocation().x / 95].setPiece(null);
                        _board[(btnPiece.getParent().getLocation().y / 98 ) + 1][btnPiece.getParent().getLocation().x / 95].setPiece(piece);
                        _panels[(btnPiece.getParent().getLocation().y / 98 ) + 1][btnPiece.getParent().getLocation().x / 95].add(btnPiece);
                        //Supprimer la pièce de la case
                        _panels[(btnPiece.getParent().getLocation().y / 98 )-1][btnPiece.getParent().getLocation().x / 95].remove(btnPiece);
                        _panels[(btnPiece.getParent().getLocation().y / 98 )-1][btnPiece.getParent().getLocation().x / 95].repaint();

                    }
                }
             }

        }
    }

}
