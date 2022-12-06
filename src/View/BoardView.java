package View;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import Controler.Controller;
import Model.Board;
import Model.Player;
import Model.Square;

import static Model.Color.BLACK;
import static Model.Color.WHITE;

public class BoardView extends JFrame{
    private Board _board;
    private JPanel[][] _panels;
    private JPanel pnl_board;
    private JButton _currentButtonPiece;
    private Controller _controller;
    private JLabel _lbl_isTurn_white, _lbl_isTurn_black, _lbl_score_white, _lbl_score_black;
    public Boolean _attack;
    /*
        Création de l'interface du plateau avec les 2 joueurs
    */
    public BoardView(Controller controller){

        this._board = controller.getBoard();
        this._controller = controller;
        this._attack = false;
        setSize(1680,880);

        pnl_board = new JPanel();

        // Création de l'interface de jeu
        JPanel chessApp = new JPanel();
        chessApp.setSize(800,1200);
        
        setTitle("Jeu d'échec");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Génération du plateau de jeux
        generatedBoardUI(chessApp);

        // Génération des colones où on pourra voir le score et les pièces capturées
        piecesCapturedUI(chessApp);

        generatedPiece(); // Générer les pièces
        add(chessApp);
        setVisible(true);
        setResizable(false);
    }

    public void piecesCapturedUI(JPanel chessApp){
        //Pieces capturé par la team White
        JPanel pnlPieceCapturedWhite = new JPanel();
        JLabel lbl_title = new JLabel();
        if(this._controller.getGame().getPlayers().get(0).getColor() == WHITE){
            lbl_title.setText("TEAM WHITE: \n" + this._controller.getGame().getPlayers().get(0).getName());
        }else{
            lbl_title.setText("TEAM WHITE: \n" + this._controller.getGame().getPlayers().get(1).getName());
        }
        lbl_title.setLocation(pnlPieceCapturedWhite.getLocation());
        lbl_title.setFont(new Font("Serif", Font.BOLD, 30));

        // label qui permet de savoir qui joue.
        _lbl_isTurn_white = new JLabel();
        _lbl_isTurn_white.setText("C'est ton tour !");
        _lbl_isTurn_white.setLocation(pnlPieceCapturedWhite.getLocation());
        _lbl_isTurn_white.setFont(new Font("Serif", Font.BOLD, 40));

        // label qui permet de savoir le score.
        _lbl_score_white = new JLabel();
        _lbl_score_white.setText("Score: 0");
        _lbl_score_white.setLocation(pnlPieceCapturedWhite.getLocation());
        _lbl_score_white.setFont(new Font("Serif", Font.BOLD, 50));

        pnlPieceCapturedWhite.add(lbl_title);
        pnlPieceCapturedWhite.add(_lbl_score_white);
        pnlPieceCapturedWhite.add(_lbl_isTurn_white);

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

        // label qui permet de savoir qui joue.
        _lbl_isTurn_black = new JLabel();
        _lbl_isTurn_black.setText("C'est ton tour !");
        _lbl_isTurn_black.setLocation(pnlPieceCapturedBlack.getLocation());
        _lbl_isTurn_black.setFont(new Font("Serif", Font.BOLD, 40));

        // label qui permet de savoir le score.
        _lbl_score_black = new JLabel();
        _lbl_score_black.setText("Score: 0");
        _lbl_score_black.setLocation(pnlPieceCapturedBlack.getLocation());
        _lbl_score_black.setFont(new Font("Serif", Font.BOLD, 50));

        pnlPieceCapturedBlack.add(lbl_title);
        pnlPieceCapturedBlack.add(_lbl_score_black);
        pnlPieceCapturedBlack.add(_lbl_isTurn_black);

        if(_controller.getGame().getPlayers().get(0).getColor() == BLACK){
            _lbl_isTurn_white.setVisible(false);
            _lbl_isTurn_black.setVisible(true);
        }else{
            _lbl_isTurn_white.setVisible(true);
            _lbl_isTurn_black.setVisible(false);
        }

        pnlPieceCapturedBlack.setBackground(Color.GRAY);
        pnlPieceCapturedBlack.setPreferredSize(new Dimension(400, 800));
        chessApp.add(pnlPieceCapturedBlack, BorderLayout.LINE_START);
    }
    public void generatedBoardUI(JPanel chessApp){
        this._panels = new JPanel[8][8];
        GridLayout chessLayout = new GridLayout(8,8);
        pnl_board.setLayout(chessLayout);
        int posX = 0;
        int posY = 0;
        for(int l = 0; l<8; l++){
            for(int c=0; c<8; c++){
                JPanel panel = new JPanel();
                if(l%2 == 0){
                    panel.setBackground((c%2 == 0)? Color.white: Color.black);
                }else{
                    panel.setBackground((c%2 != 0)? Color.white: Color.black);
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
    }
    public void generatedPiece(){
        for(int l = 0; l<8; l++) {
            for (int c = 0; c < 8; c++) {
                if(_board.getBoard()[l][c].getPiece() != null){
                    ImageIcon imageIcon;
                    imageIcon = new ImageIcon(new ImageIcon(_board.getBoard()[l][c].getPiece().getImage()
                            + ((_board.getBoard()[l][c].getPiece().getColor() == BLACK) ? "_Black.png" : "_White.png")
                            ).getImage().getScaledInstance(60, 80, Image.SCALE_DEFAULT));
                    JButton btnPiece = new JButton();
                    btnPiece.setOpaque(false);
                    btnPiece.setContentAreaFilled(false);
                    btnPiece.setBorderPainted(false);
                    btnPiece.setIcon(imageIcon);
                    btnPiece.setName(_board.getBoard()[l][c].getPiece().getName());
                    btnPiece.setSize( 100, 100);
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
    /*
        Restart les couleurs
    */
    public void reloadSquareColor(){
        for(int l = 0; l<8; l++){
            for(int c=0; c<8; c++){
                if(l%2 == 0){
                    _panels[l][c].setBackground((c%2 == 0)? Color.white: Color.black);
                }else{
                    _panels[l][c].setBackground((c%2 != 0)? Color.white: Color.black);
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
        if(_attack){
            movePiece((JPanel)btnPiece.getParent());
        }
        this._attack = false;
        Player player;
        if(_controller.getGame().getPlayers().get(0).getCanPlay()){
            player = _controller.getGame().getPlayers().get(0);
        }else{
            player = _controller.getGame().getPlayers().get(1);
        }
        this._currentButtonPiece = btnPiece;
        Square square;
        if(btnPiece.getParent() != null){
            if(player.getColor() == _controller.getBoard().getBoard()[btnPiece.getParent().getLocation().y/100][btnPiece.getParent().getLocation().x/100].getPiece().getColor()){

                Boolean btnMove = false; // POUR EVITER QUE LES PIECES NON IMPLEMENTES PEUVENT BOUGER

                if(btnPiece.getName().equals("P")){
                    square = _board.getSquare(this._currentButtonPiece);
                    _controller.validMove(square);
                    btnMove = true; // A ENLEVER
                }else if(btnPiece.getName().equals("R")) {
                    square = _board.getSquare(this._currentButtonPiece);
                    _controller.validMove(square);
                    btnMove = true; // A ENLEVER
                }else if(btnPiece.getName().equals("Kn")) {
                    square = _board.getSquare(this._currentButtonPiece);
                    _controller.validMove(square);
                    btnMove = true; // A ENLEVER
                }
                if(btnMove){ // A ENLEVER
                    for(Square s : _board.getValidSquares()){
                        if(_board.getBoard()[s.getRow()][s.getColumn()].getPiece() != null){
                            _panels[s.getRow()][s.getColumn()].setBackground(Color.RED);
                            _panels[s.getRow()][s.getColumn()].repaint();
                            this._attack = true;
                        }else{
                            _panels[s.getRow()][s.getColumn()].setBackground(Color.GREEN);
                            _panels[s.getRow()][s.getColumn()].repaint();
                        }
                    }
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
               if(_currentButtonPiece.getParent() != null){
                   _controller.moveAt(_board.getBoard()[_currentButtonPiece.getParent().getLocation().y / 100][_currentButtonPiece.getParent().getLocation().x / 100], panel.getLocation().y / 100, panel.getLocation().x / 100);
                   _panels[panel.getLocation().y / 100][panel.getLocation().x / 100].add(_currentButtonPiece);
               }

                if(_controller.getGame().getPlayerPlay().getColor() == BLACK){
                    _lbl_isTurn_black.setVisible(false);
                    _lbl_isTurn_white.setVisible(true);
                    _lbl_score_black.setText("Score: " + _controller.getScorePlayer(_controller.getGame().getPlayerPlay()));
                }else{
                    _lbl_isTurn_black.setVisible(true);
                    _lbl_isTurn_white.setVisible(false);
                    _lbl_score_white.setText("Score: " + _controller.getScorePlayer(_controller.getGame().getPlayerPlay()));

                }
                _controller.turnGame(_controller.getGame().getPlayers().get(0), _controller.getGame().getPlayers().get(1));

            }
        }

    }



}
