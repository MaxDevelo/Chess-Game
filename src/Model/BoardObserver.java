package Model;

public interface BoardObserver {
    void onUpdateScore(int scorePlayer1, int scorePlayer2);
    void onUpdateTurnGame();
}
