package View;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Controler.Controller;
import Model.Board;
import Model.Game;
import Model.Square;

import static Model.Color.BLACK;
import static Model.Color.WHITE;

public class BoardView extends JFrame{
    private Board _board;
    private JPanel[][] _panels;
    private JPanel pnl_board;
    private JButton _currentButtonPiece;
    private Controller _controller;
    public BoardView(Controller controller){
        this._board = controller.getBoard();
        this._controller = controller;
        setSize(1680,880);
        // Barre d'outil
        pnl_board = new JPanel();
       JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        add(tools, BorderLayout.NORTH);
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
        // Création de l'interface de jeu
        JPanel chessApp = new JPanel();
        chessApp.setSize(800,1200);

        // Plateau de jeux
        this._panels = new JPanel[8][8];
        setTitle("Jeu d'échec");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout chessLayout = new GridLayout(8,8);
        pnl_board.setLayout(chessLayout);
        int posX = 0;
        int posY = 0;
        for(int l = 0; l<8; l++){
            for(int c=0; c<8; c++){
                JPanel panel = new JPanel();
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
        pnl_board.setPreferredSize(new Dimension(800, 800));
        chessApp.add(pnl_board, BorderLayout.CENTER);

        //Pieces capturé par la team White
        JPanel pnlPieceCapturedWhite = new JPanel();
        JLabel lbl_title = new JLabel();
        if(this._controller.getGame().getPlayers().get(0).getColor() == WHITE){
            System.out.println(this._controller.getGame().getPlayers().get(0).getColor());
            lbl_title.setText("TEAM WHITE: \n" + this._controller.getGame().getPlayers().get(0).getName());
        }else{
            lbl_title.setText("TEAM WHITE: \n" + this._controller.getGame().getPlayers().get(1).getName());
        }
        lbl_title.setLocation(pnlPieceCapturedWhite.getLocation());
        lbl_title.setFont(new Font("Serif", Font.BOLD, 30));
        pnlPieceCapturedWhite.add(lbl_title);
        pnlPieceCapturedWhite.setBackground(Color.GRAY);
        pnlPieceCapturedWhite.setPreferredSize(new Dimension(400, 800));
        chessApp.add(pnlPieceCapturedWhite, BorderLayout.LINE_END);


        chessApp.add(pnl_board);

        //Pieces capturé par la team Black
        JPanel pnlPieceCapturedBlack = new JPanel();
        lbl_title = new JLabel();
        if(this._controller.getGame().getPlayers().get(0).getColor() == BLACK){
            lbl_title.setText("TEAM BLACK: \n" + this._controller.getGame().getPlayers().get(0).getName());
        }else{
            lbl_title.setText("TEAM BLACK: \n" + this._controller.getGame().getPlayers().get(1).getName());
        }
        lbl_title.setLocation(pnlPieceCapturedBlack.getLocation());
        lbl_title.setFont(new Font("Serif", Font.BOLD, 30));
        pnlPieceCapturedBlack.add(lbl_title);
        pnlPieceCapturedBlack.setBackground(Color.GRAY);
        pnlPieceCapturedBlack.setPreferredSize(new Dimension(400, 800));
        chessApp.add(pnlPieceCapturedBlack, BorderLayout.LINE_START);




        generatedPiece(); // Générer les pièces
        add(chessApp);
        setVisible(true);
        setResizable(false);
    }

    public void generatedPiece(){
        for(int l = 0; l<8; l++) {
            for (int c = 0; c < 8; c++) {
                if(_board.getBoard()[l][c].getPiece() != null){
                    JButton btnPiece = new JButton(_board.getBoard()[l][c].getPiece().getName());
                    btnPiece.setName(_board.getBoard()[l][c].getPiece().getName());
                    btnPiece.setSize( 300, 100);
                   // _panels[l][c].setLayout(new GridLayout(1, 1));
                    _panels[l][c].add(btnPiece);
                    btnPiece.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent e) {
                            validMove(btnPiece);
                        }
                    });
                    _panels[l][c].add(btnPiece);
                }
            }
        }

    }

    public void reloadSquareColor(){
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
                if(_panels[l][c].getComponents().length == 1){
                    _panels[l][c].getComponent(0).setBackground(new Color(238, 238, 238));
                    _panels[l][c].getComponent(0).repaint();
                }
                _panels[l][c].repaint();
            }
        }
    }
    public void validMove(JButton btnPiece) {
        this._currentButtonPiece = btnPiece;
        Square square = _board.getSquare(this._currentButtonPiece);
        if(btnPiece.getName().equals("P")){
            _board.setCurrentPiece(square.getPiece());
            _board.setValidSquares(square);
            for(Square s : _board.getValidSquares()){
                if(_board.getBoard()[s.getRow()][s.getColumn()].getPiece() != null){
                    _panels[s.getRow()][s.getColumn()].getComponent(0).setBackground(Color.RED);
                    _panels[s.getRow()][s.getColumn()].repaint();
                }else{
                    _panels[s.getRow()][s.getColumn()].setBackground(Color.GREEN);
                    _panels[s.getRow()][s.getColumn()].repaint();
                }
            }
        }else if(btnPiece.getName().equals("R")){
            _board.setCurrentPiece(square.getPiece());
            _board.setValidSquares(square);
            for(Square s : _board.getValidSquares()){
                if(_board.getBoard()[s.getRow()][s.getColumn()].getPiece() != null){
                    _panels[s.getRow()][s.getColumn()].getComponent(0).setBackground(Color.RED);
                    _panels[s.getRow()][s.getColumn()].repaint();
                }else{
                    _panels[s.getRow()][s.getColumn()].setBackground(Color.GREEN);
                    _panels[s.getRow()][s.getColumn()].repaint();
                }
            }
        }
    }
    
    public void movePiece(JPanel panel){
        reloadSquareColor();
        for(Square s : _board.getValidSquares()) {
            if(s.getRow() == panel.getLocation().y/100 && s.getColumn() == panel.getLocation().x/100 ){
               if(_panels[panel.getLocation().y / 100][panel.getLocation().x / 100].getComponents().length == 1){
                    _panels[panel.getLocation().y / 100][panel.getLocation().x / 100].remove(_panels[panel.getLocation().y / 100][panel.getLocation().x / 100].getComponent(0));
                }
                _board.moveAt(_board.getBoard()[_currentButtonPiece.getParent().getLocation().y / 100][_currentButtonPiece.getParent().getLocation().x / 100], panel.getLocation().y / 100, panel.getLocation().x / 100);
                _panels[panel.getLocation().y / 100][panel.getLocation().x / 100].add(_currentButtonPiece);
            }
        }
        }

}
