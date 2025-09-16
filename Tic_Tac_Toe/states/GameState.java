package states;

import entities.Game;
import entities.Player;

public interface GameState {
     void handleMove(Player player,Game game,int r,int c);
}
