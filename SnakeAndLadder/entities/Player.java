
import SnakeAndLadder.enums.Cell;
public class Player {

    private final String playerName;
    private Cell cell;
    private int currentMoves;

    public void setCurrentMoves(int currentMoves) {
        this.currentMoves = currentMoves;
    }

    public Player(String playerName,Cell cell){
        this.playerName=playerName;
        this.cell=cell;
        this.currentMoves=0;

    }

    public String getPlayerName() {
        return playerName;
    }
    public Cell getPlayerPosition()
    {
        return cell;
    }
    public void updateCellPosition(Cell cell){
        this.cell=cell;
    }
    public int getCurrentMoves() {
        return currentMoves;
    }

}
