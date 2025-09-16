package states;

import entities.Game;
import entities.Player;
import enums.GameStatus;

public class InProgressState implements GameState{

    @Override
    public void handleMove(Player player, Game game, int r, int c) {
        game.getBoard().placeSymbol(player, r, c);
        if(game.checkWinner(player)==true){
            game.setGameState(new WinningState());
            game.setGameStatus(GameStatus.WINNER);

        }
        else if(game.getBoard().isFull()){
            game.setGameState(new DrawState());
            game.setGameStatus(GameStatus.DRAW);
        }
    }

}
