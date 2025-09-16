package states;

import entities.Game;
import entities.Player;
import exception.InvalidMoveException;

public class DrawState implements GameState {

    @Override
    public void handleMove(Player player, Game game, int r, int c) {
        throw new InvalidMoveException("Game is already over. It was a draw");
        
    }

}
