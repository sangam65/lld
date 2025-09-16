
package WinningStrategy;

import entities.Board;
import entities.Player;

public class ColumnWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWinner(Player player, Board board) {
        int size=board.getSize();
        for(int i=0;i<size;++i){
            boolean colFound=true;
            for(int j=0;j<size;++j){
                if(board.getCell(j, i).getSymbol()!=player.getSymbol()){
                    colFound=false;
                    break;
                }
            }
            if(colFound==true){
                return true;
            }
        }
        return false;
    }

}
