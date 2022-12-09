package Model;

import Model.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String _name;
    private boolean _canPlay;
    private Color _colorTeam;
    private int _score;
    private Boolean _isWin;
    private List<Piece> _piecesCaptured;

    public Player(){
        this._piecesCaptured = new ArrayList<>();
        this._score = 0;
        this._isWin = false;
    }
    public int getScore(){
        return _score;
    }
    // Défini son score
    public void setScore(int score){
        this._score += score;
    }
    // Initialise le nom du joueur
    public void setName(String name){
        this._name = name;
    }
    // Récupération du nom du joueur
    public String getName(){
        return this._name;
    }
    // Récupération de la couleur (de sa TEAM)
    public Color getColor(){
        return this._colorTeam;
    }

    /**
     *  Stocker les pièces capturés
     * @param piece pièce qui est capturée
     */
    public void addPieceCaptured(Piece piece){
        this._piecesCaptured.add(piece);
    }

    /**
     *  Permet de tourner la partie
     * @param canPlay changement d ejoueur
     */
    public void isTurn(Boolean canPlay){
        this._canPlay = canPlay;
    }
    public Boolean getCanPlay(){
        return  this._canPlay;
    }
    public void setColor(Color color){
        this._colorTeam = color;
    }
    public  List<Piece> getPieceCaptured(){
        return _piecesCaptured;
    }
    public void setWin(Boolean isWin){
        this._isWin = isWin;
    }
    public Boolean getWin(){
        return this._isWin;
    }

}
