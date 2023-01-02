package View;

import Controler.ChessGameFacade;
import Model.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartGameView extends JFrame {
    private JTextField _txtFieldJoueur1, _txtFieldJoueur2;
    private JComboBox<String> jComboBox, jComboBox2;
    private ChessGameFacade _facade;
    public StartGameView(ChessGameFacade controller){
        // Ajout d'un logo au jeu
        setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
        setTitle("Jeu d'échec"); // Titre de la fenetre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Fermer l'application
        setSize(1000,400); // Taille de la fenetre
        // Centrer l'application
        Toolkit toolKit = getToolkit();
        Dimension size = toolKit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);


        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 50));;

        // Mise en place de "l'inscription des 2 joeurs"
        JLabel lblJoueur1 = new JLabel("Nom joueur 1: ", JLabel.TRAILING);
        _txtFieldJoueur1 = new JTextField(6);
        JLabel lblJoueur2 = new JLabel("Nom joueur 2: ", JLabel.TRAILING);
        _txtFieldJoueur2 = new JTextField(6);

        // Ajout du champs de texte dans le label du joueur 1
        lblJoueur1.setLabelFor(_txtFieldJoueur1);
        // Ajout d'une Font au Label
        lblJoueur1.setFont(new Font("Verdana", Font.PLAIN, 18));
        // Ajout d'une Font au champs de texte
        _txtFieldJoueur1.setFont(new Font("Verdana", Font.PLAIN, 18));

        // Ajout du champs de texte dans le label du joueur 2
        lblJoueur2.setLabelFor(_txtFieldJoueur1);
        // Ajout d'une Font au Label
        lblJoueur2.setFont(new Font("Verdana", Font.PLAIN, 18));
        // Ajout d'une Font au champs de texte
        _txtFieldJoueur2.setFont(new Font("Verdana", Font.PLAIN, 18));

        // Mise en place du bouton pour jouer
        JButton btnPlay = new JButton("Commencer la partie");
        // Event qui déclanche la partie quand on appui sur le bouton joueur
        btnPlay.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                playGame();
            }
        });


        // Choix de l'équipe
        String[] optionsToChoose = {"BLACK", "WHITE"};
        //Mise en place d'une ComboBox'
        jComboBox = new JComboBox<>(optionsToChoose);
        jComboBox.setBounds(80, 50, 140, 20);

        jComboBox2 = new JComboBox<>(optionsToChoose);
        jComboBox2.setBounds(80, 50, 140, 20);

        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(100, 100, 100, 100);

        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(lblJoueur1, constraints);
        constraints.gridx = 1;
        newPanel.add(_txtFieldJoueur1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        newPanel.add(lblJoueur2, constraints);
        constraints.gridx = 2;
        newPanel.add(_txtFieldJoueur2, constraints);
        constraints.gridwidth = 8;

        constraints.gridx = 3;
        newPanel.add(jComboBox, constraints);

        constraints.gridx = 3;
        newPanel.add(jComboBox2, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(btnPlay, constraints);


        newPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Jouer"));

        // Ajout des composants dans le newPanel
        newPanel.add(lblJoueur1);
        newPanel.add(_txtFieldJoueur1);
        newPanel.add(jComboBox);
        newPanel.add(lblJoueur2);
        newPanel.add(_txtFieldJoueur2);
        newPanel.add(jComboBox2);
        add(newPanel);

        pack();
        setLocationRelativeTo(null);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
        setResizable(false); // Enlever la redimenssion de la fenetre
        this._facade = controller;
    }

    /*
    * Lancement de la aprtie
    * */
    public void playGame(){
        // On vérifie si les nom sont au dessus de 4 caractères et en dessous de 10 caractères
        if(_txtFieldJoueur1.getText().length() <= 10 && _txtFieldJoueur2.getText().length() <= 10){
            // On vérifie que les 2 joueurs n'ont pas la meme couleur
            if(!jComboBox.getItemAt(jComboBox.getSelectedIndex()).equals(jComboBox2.getItemAt(jComboBox2.getSelectedIndex()))){
                Color colorTeam1 = (jComboBox.getItemAt(jComboBox.getSelectedIndex()).equals("BLACK")) ? Color.BLACK : Color.WHITE;
                Color colorTeam2 = (jComboBox2.getItemAt(jComboBox2.getSelectedIndex()).equals("BLACK")) ? Color.BLACK : Color.WHITE;
                // On met en place la partie
                this._facade.newGame(_txtFieldJoueur1.getText(), _txtFieldJoueur2.getText(), colorTeam1, colorTeam2);
                dispose();
            }
        }
    }
}
