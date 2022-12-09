package Model;

import Model.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String _name;
    private boolean _canPlay;
    private Color _colorTeam;
    private int _score;
    private List<Piece> _piecesCaptured;

    public Player(){
        this._piecesCaptured = new ArrayList<>();
        this._score = 0;
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
    // Stocke les pièces capturé
    public void addPieceCaptured(Piece piece){
        this._piecesCaptured.add(piece);
    }
    // Permet de savoir qui doit joueur
    public void isTurn(Boolean canplay){
        this._canPlay = canplay;
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

}
