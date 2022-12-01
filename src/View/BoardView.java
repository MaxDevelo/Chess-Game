package View;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.Board;
import Model.Square;

public class BoardView extends JFrame{
    private Board _board;
    private JPanel[][] _panels;
    private JPanel pnl_board;
    private JButton _currentButtonPiece;

    public BoardView(){
        _board = new Board();
        setSize(1920, 1080);
        // Barre d'outil
        pnl_board = new JPanel();
        JToolBar tools = new JToolBar();
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
        tools.addSeparator();

        JPanel chessApp = new JPanel();

        JPanel pnlPieceCaptured = new JPanel();
        JLabel lbl_title = new JLabel();
        lbl_title.setText("Pions capturés:");
        lbl_title.setLocation(pnlPieceCaptured.getLocation());
        lbl_title.setFont(new Font("Serif", Font.BOLD, 40));
        pnlPieceCaptured.add(lbl_title);
        pnlPieceCaptured.setBackground(Color.GRAY);
        pnlPieceCaptured.setPreferredSize(new Dimension(398, 895));
        chessApp.add(pnlPieceCaptured, BorderLayout.LINE_START);

        
        // Plateau de jeux
        this._panels = new JPanel[8][8];
        setTitle("Jeu d'échec");
       // pnl_board.setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout chessLayout = new GridLayout(8,8);
        pnl_board.setLayout(chessLayout);
        int posX = 0;
        int posY = 0;
        for(int l = 0; l<8; l++){
            for(int c=0; c<8; c++){
                JPanel panel = new JPanel();
                Square square = new Square(l, c, null);
                _board.setSquare(square, l,  c);
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
                panel.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        super.mouseClicked(e);
                        movePiece(panel);
                    }
                });
                _panels[l][c] = panel;
                posX += 100;
                pnl_board.add(panel);
                panel.setVisible(true);
            }
            posY += 100;
            posX = 0;
        }
        generatedPiece(); // Générer les pièces
        pnl_board.setPreferredSize(new Dimension(800, 800));
        chessApp.add(pnl_board, BorderLayout.CENTER);


        add(chessApp);
        setVisible(true);
        setResizable(false);
    }

    public void generatedPiece(){
        _board.setBoard(_board.generatePiece(_board.getBoard()));
        for(int l = 0; l<8; l++) {
            for (int c = 0; c < 8; c++) {
                if(_board.getBoard()[l][c].getPiece() != null){
                    JButton btnPiece = new JButton(_board.getBoard()[l][c].getPiece().getName());
                    btnPiece.setName(_board.getBoard()[l][c].getPiece().getName());
                    //btnPiece.setSize( 100, 100);
                    btnPiece.setLocation(_panels[l][c].getLocation());
                    btnPiece.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent e) {
                            super.mouseClicked(e);
                            validMove(btnPiece);
                        }
                    });
                    _panels[l][c].add(btnPiece);
                }
            }
        }

    }

    public void reloadSquareColor(){
        int posX = 0;
        int posY = 0;
        for(int l = 0; l<8; l++){
            for(int c=0; c<8; c++){
                if(l%2 == 0){
                    if(c%2 == 0){
                        _panels[l][c].setBackground(Color.WHITE);
                    }else{
                        _panels[l][c].setBackground(Color.black);
                    }
                }else{
                    if(c%2 != 0){
                        _panels[l][c].setBackground(Color.white);
                    }else{
                        _panels[l][c].setBackground(Color.black);
                    }
                }
                _panels[l][c].repaint();
            }
        }
    }
    public void validMove(JButton btnPiece) {
        reloadSquareColor();
        this._currentButtonPiece = btnPiece;
        Square square = _board.getSquare(this._currentButtonPiece);
        if(btnPiece.getName().equals("P")){
            _board.setCurrentPiece(square.getPiece());
            _board.setValidSquares(square);
            for(Square s : _board.getValidSquares()){
                _panels[s.getRow()][s.getColumn()].setBackground(Color.GREEN);
                _panels[s.getRow()][s.getColumn()].repaint();
            }
        }else if(btnPiece.getName().equals("R")){
            _board.setCurrentPiece(square.getPiece());
            _board.setValidSquares(square);
            for(Square s : _board.getValidSquares()){
                _panels[s.getRow()][s.getColumn()].setBackground(Color.GREEN);
                _panels[s.getRow()][s.getColumn()].repaint();
            }
        }
    }
    
    public void movePiece(JPanel panel){
        reloadSquareColor();
        _board.moveAt(_board.getBoard()[_currentButtonPiece.getParent().getLocation().y/100][_currentButtonPiece.getParent().getLocation().x/100] ,panel.getLocation().y/100, panel.getLocation().x/100);
        _panels[panel.getLocation().y/100][panel.getLocation().x/100].add(_currentButtonPiece);
    }

}
