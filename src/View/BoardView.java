package View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import Controler.ChessGameFacade;
import Model.Board;
import Model.Pieces.Piece;
import Model.Player;
import Model.Square;

import static Model.Color.WHITE;
import static Model.Pieces.Type.PAWN;


public class BoardView extends JFrame{


    private Board _board;
    private JPanel[][] _panels;
    private JPanel pnl_board,  pnlPiecesCapturesWhite,  pnlPiecesCapturesBlack;
    private JButton _currentButtonPiece;
    private ChessGameFacade _facade;
    private JLabel  _lbl_score_white, _lbl_score_black,  _lblTurnBlack, _lblTurnWhite;
    /*
        Création de l'interface du plateau avec les 2 joueurs
    */
    public BoardView(ChessGameFacade controller){
      setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());;
        this._board = controller.getBoard();
        this._facade = controller;
        setSize(1680,840);
        // Centrer l'applciation
        Toolkit toolKit = getToolkit();
        Dimension size = toolKit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);

        pnl_board = new JPanel();

        // Création de l'interface de jeu
        JPanel chessApp = new JPanel();
        chessApp.setSize(800,1200);
        chessApp.setBackground(new Color(48, 66, 36));
        setTitle("Jeu d'échec");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Génération du plateau de jeux
        generatedBoardGUI(chessApp);

        // Génération des colonnes où on pourra voir le score et les pièces capturées
        chessPlayersGUI(chessApp);

        generatedPieceGUI(); // Générer les pièces

        add(chessApp);
        setVisible(true);
        setResizable(false);
        turnGameGUI();
    }
/*
* procédure qui génère les 2 endroit où l'on va stocker les pièces capturés des 2 joueurs
* */
    public void chessPlayersGUI(JPanel chessApp){
        //Pieces capturé par la team White
        JPanel pnlPieceCapturedWhite = new JPanel();
        JLabel lbl_title = new JLabel(); // TITRE
        // On récupère le nom du joueur
        if(this._facade.getGame().getPlayers().get(0).getColor() == WHITE){
            lbl_title.setText("WHITE: \n" + this._facade.getGame().getPlayers().get(0).getName());
        }else{
            lbl_title.setText("WHITE: \n" + this._facade.getGame().getPlayers().get(1).getName());
        }

        lbl_title.setLocation(pnlPieceCapturedWhite.getLocation());
        // Ajout d'une FONT
        lbl_title.setFont(new Font("Serif", Font.BOLD, 30));


        // label qui permet de savoir le score du joueur
        JPanel stat = new JPanel();
        _lbl_score_white = new JLabel();
        _lbl_score_white.setText("Score: 0");
        _lbl_score_white.setLocation(pnlPieceCapturedWhite.getLocation());
        _lbl_score_white.setFont(new Font("Serif", Font.BOLD, 50));
        stat.setPreferredSize(new Dimension(400, 120));
        stat.add(lbl_title, BorderLayout.SOUTH);
        stat.add(_lbl_score_white, BorderLayout.NORTH);
        // LE TOUR
        _lblTurnWhite = new JLabel();
        _lblTurnWhite.setText("TON TOUR");
        _lblTurnWhite.setFont(new Font("Arial", Font.BOLD, 30));
        pnlPieceCapturedWhite.add(_lblTurnWhite, BorderLayout.NORTH);
        pnlPieceCapturedWhite.add(stat, BorderLayout.CENTER);
        pnlPieceCapturedWhite.add(stat, BorderLayout.NORTH);


        pnlPieceCapturedWhite.setPreferredSize(new Dimension(420, 300));

        // Panel pour ajouter les pièces capturées
        pnlPiecesCapturesWhite = new JPanel();
        pnlPieceCapturedWhite.add(pnlPiecesCapturesWhite,  BorderLayout.SOUTH);
        chessApp.add(pnlPieceCapturedWhite, BorderLayout.LINE_START);

        chessApp.add(pnl_board, BorderLayout.CENTER); // Ajout du board

        JPanel pnlPieceCapturedBlack= new JPanel();

         lbl_title = new JLabel(); // TITRE
        // On récupère le nom du joueur
        if(this._facade.getGame().getPlayers().get(0).getColor() == Model.Color.BLACK){
            lbl_title.setText("BLACK: \n" + this._facade.getGame().getPlayers().get(0).getName());
        }else{
            lbl_title.setText("BLACK: \n" + this._facade.getGame().getPlayers().get(1).getName());
        }

        lbl_title.setLocation(pnlPieceCapturedBlack.getLocation());
        // Ajout d'une FONT
        lbl_title.setFont(new Font("Serif", Font.BOLD, 30));


        // label qui permet de savoir le score du joueur
        stat = new JPanel();
        _lbl_score_black = new JLabel();
        _lbl_score_black.setText("Score: 0");
        _lbl_score_black.setLocation(pnlPieceCapturedBlack.getLocation());
        _lbl_score_black.setFont(new Font("Serif", Font.BOLD, 50));

        stat.setPreferredSize(new Dimension(400, 120));
        stat.add(lbl_title, BorderLayout.NORTH);
        stat.add(_lbl_score_black, BorderLayout.SOUTH);
        // Label TOUR
        _lblTurnBlack = new JLabel();
        _lblTurnBlack.setText("TON TOUR");
        _lblTurnBlack.setFont(new Font("Arial", Font.BOLD, 30));
        pnlPieceCapturedBlack.add(_lblTurnBlack, BorderLayout.NORTH);
        pnlPieceCapturedBlack.add(stat, BorderLayout.CENTER);

        pnlPieceCapturedBlack.setPreferredSize(new Dimension(420, 300));
        chessApp.add(pnlPieceCapturedBlack, BorderLayout.LINE_END);

        // Panel pour ajouter les pièces capturées
        pnlPiecesCapturesBlack = new JPanel();
        pnlPieceCapturedBlack.add(pnlPiecesCapturesBlack,  BorderLayout.SOUTH);


        chessApp.add(pnlPieceCapturedBlack, BorderLayout.LINE_END);
    }

    /*
    * Procédure qui génère le plateau de jeu
    * */
    public void generatedBoardGUI(JPanel chessApp){
        this._panels = new JPanel[8][8];
        GridLayout chessLayout = new GridLayout(8,8);
        pnl_board.setLayout(chessLayout);
        int posX = 0;
        int posY = 0;
        for(int l = 0; l<8; l++){
            for(int c=0; c<8; c++){
                JPanel panel = new JPanel();
                if(l%2 == 0){
                    panel.setBackground((c%2 == 0)? new Color( 	235, 236, 208): new Color( 	119, 148, 85));
                }else{
                    panel.setBackground((c%2 != 0)?  new Color( 	235, 236, 208): new Color( 	119, 148, 85));
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
    /*
     * Génération des pièces du plateau
     * */
    public void generatedPieceGUI(){
        for(int l = 0; l<8; l++) {
            for (int c = 0; c < 8; c++) {
                if(_board.getBoard()[l][c].getPiece() != null){
                    createPieceGUI(l, c);
                }
            }
        }
    }
    /*
     * Génération de la pièce
     * */
    public void createPieceGUI(int l, int c){
        ImageIcon imageIcon;
        imageIcon = new ImageIcon(new ImageIcon(getClass().getResource(_board.getBoard()[l][c].getPiece().getImage()
                + ((_board.getBoard()[l][c].getPiece().getColor() == Model.Color.BLACK) ? "_Black.png" : "_White.png"))
        ).getImage().getScaledInstance(60, 80, Image.SCALE_DEFAULT));
        JButton btnPiece = new JButton();
        // Fond du bouton transparent
        btnPiece.setOpaque(false);
        btnPiece.setContentAreaFilled(false);
        btnPiece.setBorderPainted(false);
        btnPiece.setIcon(imageIcon);
        btnPiece.setName(_board.getBoard()[l][c].getPiece().getName().name());
        btnPiece.setSize( 100, 100);
        btnPiece.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                reloadSquareColor();
                turnGameGUI();
                validMove(btnPiece);

            }
        });
        _panels[l][c].add(btnPiece);
    }
    /*
        Restart les couleurs
    */
    public void reloadSquareColor(){
        for(int l = 0; l<8; l++){
            for(int c=0; c<8; c++){
                if(l%2 == 0){
                    _panels[l][c].setBackground((c%2 == 0)? new Color( 	235, 236, 208): new Color( 	119, 148, 85));
                }else{
                    _panels[l][c].setBackground((c%2 != 0)? new Color( 	235, 236, 208): new Color( 	119, 148, 85));
                }
                if(_panels[l][c].getComponents().length == 1){
                    _panels[l][c].getComponent(0).setBackground(new Color(238, 238, 238));
                    _panels[l][c].getComponent(0).repaint();
                }
                _panels[l][c].repaint();
            }
        }
    }
    /*
     * Validation en montrant où la pièce peut aller
     */
    public void validMove(JButton btnPiece) {
        movePiece((JPanel) btnPiece.getParent());

            Player player;
            if (_facade.getGame().getPlayers().get(0).getCanPlay()) {
                player = _facade.getGame().getPlayers().get(0);
            } else {
                player = _facade.getGame().getPlayers().get(1);
            }
            this._currentButtonPiece = btnPiece;
            Square square;
            if (btnPiece.getParent() != null) {
                if (_facade.getBoard().getBoard()[btnPiece.getParent().getLocation().y / 100][btnPiece.getParent().getLocation().x / 100].getPiece() != null && player.getColor() == _facade.getBoard().getBoard()[btnPiece.getParent().getLocation().y / 100][btnPiece.getParent().getLocation().x / 100].getPiece().getColor()) {

                    if (btnPiece.getName().equals("PAWN") || btnPiece.getName().equals("ROOK") || btnPiece.getName().equals("KNIGHT") || btnPiece.getName().equals("QUEEN") || btnPiece.getName().equals("KING") || btnPiece.getName().equals("BISHOP")) {
                        square = _board.getSquare(this._currentButtonPiece.getParent().getLocation().y / 100, this._currentButtonPiece.getParent().getLocation().x / 100);
                        if(_facade.verifyIfCheckKing()){
                            List<Square> squareBordidden = _board.squareForbidden();
                            _facade.validMove(square);
                            for (Square s : squareBordidden) {
                                if(_board.getValidSquares().contains(s)){
                                    _board.getValidSquares().remove(s);
                                }
                            }
                        }else{
                            _facade.validMove(square);
                        }
                    }
                    // Boucle qui permetd e récupérer et afficher les cases où le joueur
                    // peut se déplacer avec la pèce
                    for (Square s : _board.getValidSquares()) {

                        if (_board.getBoard()[s.getRow()][s.getColumn()].getPiece() != null) {
                            _panels[s.getRow()][s.getColumn()].setBackground(new Color(241, 139, 129));
                            _panels[s.getRow()][s.getColumn()].repaint();
                        } else {
                            // Mise en place de la couleur rouge pour indiquer que le pion peut aller de coté
                            // (Quand il y a une prise en passant)
                            if(s.getColumn() != btnPiece.getParent().getLocation().x / 100 && _facade.getBoard().getBoard()[btnPiece.getParent().getLocation().y / 100][btnPiece.getParent().getLocation().x / 100].getPiece().getName().equals(PAWN)){
                                _panels[s.getRow()][s.getColumn()].setBackground(new Color(241, 139, 129));
                                _panels[s.getRow()][s.getColumn()].repaint();
                            } else{
                                _panels[s.getRow()][s.getColumn()].setBackground(new Color(129, 241, 139));
                                _panels[s.getRow()][s.getColumn()].repaint();
                            }
                        }
                    }
                }
            }
    }

    /*
    * Déclanche le choix de selection de 4 pièces lors de la promotion
    * du pion.
    * */
    public String selectPiecesPromotion(){
        String[] pieces = {"Tour", "Cavalier", "Fou", "Reine"};
        String input = "";
        while(input == ""){
             input = (String) JOptionPane.showInputDialog(null, "Choisissez une pièce à promouvoir :",
                    "Promotion", JOptionPane.QUESTION_MESSAGE, null, pieces, pieces[0]);
        }
        return input;
    }

    /*
     * Procédure qui gère le déplacement de la pièce
     * */
    public void movePiece(JPanel panel){
        reloadSquareColor();

        for(Square s : _board.getValidSquares()) {
            if(s.getRow() == panel.getLocation().y/100 && s.getColumn() == panel.getLocation().x/100 ){
                if(_panels[panel.getLocation().y / 100][panel.getLocation().x / 100].getComponents().length == 1){
                    _panels[panel.getLocation().y / 100][panel.getLocation().x / 100].remove(_panels[panel.getLocation().y / 100][panel.getLocation().x / 100].getComponent(0));
                }
               if(_currentButtonPiece.getParent() != null){
                   _facade.moveAt(_board.getBoard()[_currentButtonPiece.getParent().getLocation().y / 100][_currentButtonPiece.getParent().getLocation().x / 100], panel.getLocation().y / 100, panel.getLocation().x / 100);
                   piecesCapturesGUI();
                   _panels[panel.getLocation().y / 100][panel.getLocation().x / 100].add(_currentButtonPiece);
                   // Promotion du PION
                   if(_board.isPromoted(_board.getBoard()[panel.getLocation().y / 100][panel.getLocation().x / 100])){
                       String namePiece = selectPiecesPromotion();
                       _board.promotion(_board.getBoard()[panel.getLocation().y / 100][panel.getLocation().x / 100], namePiece);
                       _panels[panel.getLocation().y/100][panel.getLocation().x/100].remove(0); // On supprime la pièce pour promevoir
                       createPieceGUI(panel.getLocation().y/100, panel.getLocation().x/100);
                   }
               }
               // Gère la prise en passant du Pion
                if(_board.getBoard()[s.getRow()+1][s.getColumn()].getPiece() == null && _panels[s.getRow()+1][s.getColumn()].getComponents().length == 1){
                    _panels[s.getRow()+1][s.getColumn()].remove(_panels[s.getRow()+1][s.getColumn()].getComponent(0));
                }
                if(_board.getBoard()[s.getRow()-1][s.getColumn()].getPiece() == null && _panels[s.getRow()-1][s.getColumn()].getComponents().length == 1){
                    _panels[s.getRow()-1][s.getColumn()].remove(_panels[s.getRow()-1][s.getColumn()].getComponent(0));
                }
                reloadScoreGame();
               break;
            }
        }
        turnGameGUI(); // On change de joueur
        // Vérificatione échec du roi
        if(_facade.verifyIfCheckKing()){
            // Affiche une fenêtre de confirmation avec des boutons Oui / Non
            JOptionPane.showConfirmDialog(null, "Echec du Roi, Attention", "ok", JOptionPane.CLOSED_OPTION);
        }
        // Vérificatione échec et MATE du roi
        if(_facade.verifyIfCheckMateKing()){
            // Affiche une fenêtre de confirmation avec des boutons Oui / Non
            int result = JOptionPane.showConfirmDialog(null, "Il y a échec du ROI !", "REJOUER", JOptionPane.CLOSED_OPTION);
            // Si l'utilisateur a cliqué sur Oui, exécute la fonction
            if (result == JOptionPane.OK_OPTION) {
                new EndGameView(_facade);
                dispose();
            }
        }
    }



    /*
     * Indique qui doit jouer
     * */
    public void turnGameGUI(){
       for(Player player : _facade.getGame().getPlayers()){
           if(player.getCanPlay()) {
               if(player.getColor().equals(Model.Color.BLACK)){
                   _lblTurnBlack.setVisible(true);
                   _lblTurnWhite.setVisible(false);
               }else{
                   _lblTurnBlack.setVisible(false);
                   _lblTurnWhite.setVisible(true);
               }
           }
       }
    }
    /*
     * Procédure qui recharge le score et tourne la partie
     * */
    public void reloadScoreGame(){
        if(_facade.getGame().getPlayerPlay().getColor() == Model.Color.BLACK){
            _lbl_score_black.setText("Score: " + _facade.getScorePlayer(_facade.getGame().getPlayerPlay()));
        }else{
            _lbl_score_white.setText("Score: " + _facade.getScorePlayer(_facade.getGame().getPlayerPlay()));

        }
        _facade.turnGame(_facade.getGame().getPlayers().get(0), _facade.getGame().getPlayers().get(1));
    }
    /*
     * Procédure qui permet de visualiser les pièces capturées du joueur
     * */
    public void piecesCapturesGUI(){
        for(Component component : pnlPiecesCapturesWhite.getComponents()){
            pnlPiecesCapturesWhite.remove(component);
        }
        for(Component component : pnlPiecesCapturesBlack.getComponents()){
            pnlPiecesCapturesBlack.remove(component);
        }

        for(Player player : _facade.getGame().getPlayers()){
            for(Piece piece : player.getPieceCaptured()){

                // Mise en palce de l'image de la pièce
                ImageIcon imageIcon;
                if(piece.getColor() == Model.Color.BLACK){
                    imageIcon = new ImageIcon(new ImageIcon(getClass().getResource(piece.getImage() + "_Black.png")
                    ).getImage().getScaledInstance(30, 35, Image.SCALE_DEFAULT));
                }else{
                    imageIcon = new ImageIcon(new ImageIcon(getClass().getResource(piece.getImage() + "_White.png")
                    ).getImage().getScaledInstance(30, 35, Image.SCALE_DEFAULT));
                }
                JButton btnPiece = new JButton();
                btnPiece.setOpaque(false);
                btnPiece.setContentAreaFilled(false);
                btnPiece.setBorderPainted(false);
                btnPiece.setIcon(imageIcon);
                if( piece.getColor() == Model.Color.BLACK){
                    pnlPiecesCapturesWhite.add(btnPiece);
                }else{
                    pnlPiecesCapturesBlack.add(btnPiece);
                }
            }
        }
        pnlPiecesCapturesWhite.setLayout(new GridLayout(2, 8));
        pnlPiecesCapturesBlack.setLayout(new GridLayout(2, 8));
    }

}
