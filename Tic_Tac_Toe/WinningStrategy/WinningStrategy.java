package WinningStrategy;

import entities.Board;
import entities.Player;

public interface WinningStrategy {
    boolean checkWinner(Player player,Board board);
}
