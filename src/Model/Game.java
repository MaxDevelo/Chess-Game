package Model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private boolean _endGame;
    private List<Player> _players;
    private Player _currentPlayerPlay;
    public Game(){
        this._players = new ArrayList<>();
    }
    /*
        stocker le joueur
    */
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
    /*
    Stocker le joueur qui est entrain de joueur
    */
    public void setPlayerPlay(Player currentPlayerPlay){
        this._currentPlayerPlay = currentPlayerPlay;
    }
    public Player getPlayerPlay(){
        return _currentPlayerPlay;
    }
}
