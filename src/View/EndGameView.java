package View;

import Controler.ChessGameFacade;
import Model.Color;
import Model.Pieces.Piece;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Model.Color.WHITE;

public class EndGameView extends JFrame {
    private ChessGameFacade _facade;
    private JPanel  pnlPiecesCapturesWhite,  pnlPiecesCapturesBlack;
    public EndGameView(ChessGameFacade facade){
        setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
        this._facade = facade;
        setTitle("FIN DE LA PARTIE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,400);
        // Centrer l'applciation
        Toolkit toolKit = getToolkit();
        Dimension size = toolKit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);

        setVisible(true);
        setResizable(false);
        for(Player player : _facade.getGame().getPlayers()){
            if(player.getWin()){ // Si le joueur observé a gagné, on l'affiche
                JPanel p = new JPanel();
                JLabel name = new JLabel("Le gagnant est " + player.getName()); // Nom du gagnant
                name.setFont(new Font("Serif", Font.BOLD, 30)); // Ajout d'une Font
                name.setForeground(java.awt.Color.white); // Ajout d'une couleur au texte
                JLabel score = new JLabel(" avec un score de " + String.valueOf(player.getScore())); // Son score
                score.setFont(new Font("Serif", Font.BOLD, 30));
                score.setForeground(java.awt.Color.white);
                p.add(name, BorderLayout.CENTER); // Ajout du nom au centre
                p.add(score, BorderLayout.SOUTH);// Ajout du score vers le bas
                p.setBackground(new java.awt.Color(48, 66, 36));
                // Panel pour ajouter les pièces capturées
                JPanel pnlPiecesCapturesBlack = new JPanel();
                // Ajout des pièces capturées
                System.out.println(player.getPieceCaptured().size());
                for(Piece piece : player.getPieceCaptured()){

                    // Mise en place de l'image de la pièce
                    ImageIcon imageIcon;
                    if(piece.getColor() == Model.Color.BLACK){
                        imageIcon = new ImageIcon(new ImageIcon(getClass().getResource(piece.getImage() + "_Black.png")
                        ).getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT));
                    }else{

                        imageIcon = new ImageIcon(new ImageIcon(getClass().getResource(piece.getImage() + "_White.png")
                        ).getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT));
                    }
                    JButton btnPiece = new JButton();
                    btnPiece.setOpaque(false);
                    btnPiece.setContentAreaFilled(false);
                    btnPiece.setBorderPainted(false);
                    btnPiece.setIcon(imageIcon);
                    pnlPiecesCapturesBlack.add(btnPiece);
                }
                pnlPiecesCapturesBlack.setLayout(new GridLayout(2, 8));
                add(p, BorderLayout.NORTH);
                add(pnlPiecesCapturesBlack,  BorderLayout.CENTER);
            }

        }
        // outon pour rejoueur au jeu
        JButton btnPlay = new JButton("REJOUER");

        btnPlay.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                reload();
            }
        });
        btnPlay.setSize(245, 300);
        add(btnPlay, BorderLayout.SOUTH);
    }

    /**
     *  Rejouer au jeu d'échec
     */
    public void reload(){
        // TESTE
        ChessGameFacade controller = new ChessGameFacade();
        controller.newGame("[Nom Joueur1]", "[Nom Joueur2]", Color.WHITE, Color.BLACK);
        new BoardView(controller);
        dispose(); // Suppression du JFrame EndGameView
    }


}
