package Model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private boolean _endGame;
    private boolean _nullGame;
    private List<Player> _players;
    private Player _currentPlayerPlay;
    public Game(){
        this._players = new ArrayList<>();
        this._nullGame = false;
    }
    public void setPlayer(Player player){
        this._players.add(player);
    }
    public List<Player> getPlayers(){
        return this._players;
    }
    public void setNullGame(boolean nullGame){
        this._nullGame = nullGame;
    }
    public boolean getNullGame(){
        return this._nullGame;
    }

    /**
     *  On update à vrai si le jeu est fini
     *  @param endGame Boolean
     */
    public void setEndGame(boolean endGame){
        this._endGame = endGame;
    }

    public void setPlayerPlay(Player currentPlayerPlay){
        this._currentPlayerPlay = currentPlayerPlay;
    }

    /**
     *  Retourne le joueur qui est entrain de jouer
     *  @return retourne le joueur entrain de jouer
     */
    public Player getPlayerPlay(){
        return _currentPlayerPlay;
    }
}
