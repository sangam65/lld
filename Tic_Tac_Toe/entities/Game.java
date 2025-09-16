package entities;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import WinningStrategy.ColumnWinningStrategy;
import WinningStrategy.DiagonalWinningStrategy;
import WinningStrategy.RowWinningStrategy;
import WinningStrategy.WinningStrategy;
import enums.GameStatus;
import states.GameState;
import states.InProgressState;

public class Game {
    private final Board board;
    private final Deque<Player>playerList;
    private  Player winner;
    private GameStatus gameStatus;
    private GameState gameState;
    
    private final List<WinningStrategy> winningStrategies;
    public Game(Player p1,Player p2){
        this.board=new Board(4);
        this.playerList=new ArrayDeque<>();
        this.playerList.add(p1);
        this.playerList.add(p2);

        this.gameState=new InProgressState();
        this.gameStatus=GameStatus.IN_PROGRESS;
        this.winningStrategies=List.of(
            new RowWinningStrategy(),
            new ColumnWinningStrategy(),
            new DiagonalWinningStrategy()
        );



    }


    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    public GameStatus getGameStatus() {
        return gameStatus;
    }


    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
    public boolean checkWinner(Player player){
        for(WinningStrategy winningStrategy:winningStrategies){
            if(winningStrategy.checkWinner(player, board)){
                this.winner=player;
                return true;
            }
        }
        return false;
    }
    public void makeMove(int r,int c){
        Player player=playerList.getFirst();
        playerList.removeFirst();
        gameState.handleMove(player, this, r, c);
        playerList.add(player);
    }

    public GameState getGameState() {
        return gameState;
    }


    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public Board getBoard(){
        return board;
    }
   

    

}
