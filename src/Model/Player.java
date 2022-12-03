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
    public void setScore(int score){
        this._score = score;
    }
    public void setName(String name){
        this._name = name;
    }
    public String getName(){
        return this._name;
    }
    public Color getColor(){
        return this._colorTeam;
    }
    public void addPieceCaptured(Piece piece){
        this._piecesCaptured.add(piece);
    }
    public void isTurn(Boolean canplay){
        this._canPlay = canplay;
    }
    public Boolean getCanPlay(){
        return  this._canPlay;
    }
    public void setColor(Color color){
        this._colorTeam = color;
    }

}
