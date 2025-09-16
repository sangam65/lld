package states;

import entities.Game;
import entities.Player;
import exception.InvalidMoveException;

public class WinningState implements GameState{

    @Override
    public void handleMove(Player player, Game game, int r, int c) {
        throw new InvalidMoveException("Game is already over. "+game.getWinner().getName() +" won the game");
        
    }

}
