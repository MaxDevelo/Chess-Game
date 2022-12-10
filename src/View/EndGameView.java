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
        this._facade = facade;
        setTitle("FIN DE LA PARTIE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1680,840);
        setVisible(true);
        setResizable(false);

        for(Player player : _facade.getGame().getPlayers()){
            if(player.getWin()){
                JPanel p = new JPanel();
                JLabel name = new JLabel("Bien jouer, " + player.getName() + " !");
                name.setFont(new Font("Serif", Font.BOLD, 50));
                JLabel score = new JLabel(String.valueOf(player.getScore()));
                score.setFont(new Font("Serif", Font.BOLD, 50));
                p.setPreferredSize(new Dimension(840, 840));;
                p.add(name, BorderLayout.CENTER);
                //  p.add(score, BorderLayout.SOUTH);
                add(p, BorderLayout.CENTER);
            }

        }
        JButton btnPlay = new JButton("REJOUER");

        btnPlay.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                reload();
            }
        });
        btnPlay.setSize(245, 130);
        add(btnPlay, BorderLayout.SOUTH);
    }

    public void reload(){
        // TESTE
        ChessGameFacade controller = new ChessGameFacade();
        controller.newGame("[Nom Joueur1]", "[Nom Joueur2]", Color.WHITE, Color.BLACK);
        new BoardView(controller);
        dispose();
    }


}
