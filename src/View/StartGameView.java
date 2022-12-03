package View;

import Controler.Controller;
import Model.Game;
import Model.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.GroupLayout.Alignment.LEADING;

public class StartGameView extends JFrame {
    private JTextField _txtFieldJoueur1, _txtFieldJoueur2;
    private JComboBox<String> jComboBox, jComboBox2;
    private Controller _controller;
    public StartGameView(Controller controller){
        setTitle("Jeu d'échec");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,400);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 50));;
        // Créer automatiquement des espaces vide

        JLabel lblJoueur1 = new JLabel("Nom joueur 1: ", JLabel.TRAILING);
        JLabel lblJoueur2 = new JLabel("Nom joueur 2: ", JLabel.TRAILING);

        _txtFieldJoueur1 = new JTextField(6);
        _txtFieldJoueur2 = new JTextField(6);

        lblJoueur1.setLabelFor(_txtFieldJoueur1);
        lblJoueur1.setFont(new Font("Verdana", Font.PLAIN, 18));
        _txtFieldJoueur1.setFont(new Font("Verdana", Font.PLAIN, 18));



        lblJoueur2.setLabelFor(_txtFieldJoueur1);
        lblJoueur2.setFont(new Font("Verdana", Font.PLAIN, 18));
        _txtFieldJoueur2.setFont(new Font("Verdana", Font.PLAIN, 18));


        JButton btnPlay = new JButton("Commencer la partie");
        btnPlay.setSize(20, 100);

        btnPlay.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                playGame();
            }
        });



        String[] optionsToChoose = {"BLACK", "WHITE"};

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
        setResizable(false);
        this._controller = controller;
    }

    public void playGame(){
        if(_txtFieldJoueur1.getText() .length() >= 4 && _txtFieldJoueur2.getText() .length() >= 4){
            System.out.println(_txtFieldJoueur1.getText() + " " + jComboBox.getItemAt(jComboBox.getSelectedIndex()) + "\n"
                    + _txtFieldJoueur2.getText() + " " + jComboBox2.getItemAt(jComboBox2.getSelectedIndex()));
            if(!jComboBox.getItemAt(jComboBox.getSelectedIndex()).equals(jComboBox2.getItemAt(jComboBox2.getSelectedIndex()))){
                Color colorTeam1 = (jComboBox.getItemAt(jComboBox.getSelectedIndex()).equals("BLACK")) ? Color.BLACK : Color.WHITE;
                Color colorTeam2 = (jComboBox2.getItemAt(jComboBox2.getSelectedIndex()).equals("BLACK")) ? Color.BLACK : Color.WHITE;
                this._controller.newGame(_txtFieldJoueur1.getText(), _txtFieldJoueur2.getText(), colorTeam1, colorTeam2);
                this._controller.generatedBoard();
                new BoardView(this._controller);
            }
        }
    }
}
