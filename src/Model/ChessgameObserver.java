package Model;

public interface ChessgameObserver {
    void onUpdateScore(int scorePlayer1, int scorePlayer2);
    void onUpdateTurnGame();
}
