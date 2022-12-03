package Model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private boolean _endGame;
    private List<Player> _players;
    public Game(){
        this._players = new ArrayList<>();
    }
    public void setPlayer(Player player){
        this._players.add(player);
    }
    public List<Player> getPlayers(){
        return this._players;
    }
    public void setEndGame(boolean endGame){
        this._endGame = endGame;
    }
    public boolean getEndGame(){
        return this._endGame;
    }
    public void displayScores(){

    }
    public void playGame(){

    }
}
