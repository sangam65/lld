import java.util.ArrayDeque;

import java.util.Deque;

import SnakeAndLadder.enums.Cell;
import SnakeAndLadder.enums.GameStatus;


public class Game {

    private  Deque<Player>players;
    private final Board board;
    private Player winner;
    private GameStatus status;
    
    public Game(int diceNumber,int boardSize){ 
        this.players = new ArrayDeque<>();
        
        this.board = new Board(new Dice(diceNumber), boardSize);
        this.winner = null;
        this.status=GameStatus.IN_PROGRESS;
    }
    public Board getBoard(){
        return board;
    }
    public void addPlayer(Player p){
        players.addLast(p);
    }
    public Player getWinner(){
        return winner;
    }
    public void nextMove(){
        if(!this.status.equals(GameStatus.IN_PROGRESS)){
            throw new InvalidCellException("Game already won by "+winner.getPlayerName());
        }
        Player currentPlayer=players.peekFirst();
        
        
        Cell cell=board.nextMove(currentPlayer);
        if(checkWinner(cell)){
            this.winner=currentPlayer;
            return;
        }
        players.removeFirst();
        players.addLast(currentPlayer);

    }
    public boolean checkWinner(Cell cell){
        if(cell.gethead()==board.getSize()-1){
            return true;
        }
        return false;
    }
    public void makeSnakeCell(int x,int y){
        board.setSnakeCell(x, y);

    }
    public void makeLadderCell(int x,int y){
        board.setLadderCell(x, y);
    }

    public void start(){
        while(winner==null){
            try {
                nextMove();
            } catch (Exception e) {
                // TODO: handle exception
            }
            
        }

        this.status=GameStatus.COMPLETED;
    }

}
