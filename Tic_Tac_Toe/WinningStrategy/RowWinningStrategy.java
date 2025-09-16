package WinningStrategy;

import entities.Board;
import entities.Player;

public class RowWinningStrategy implements WinningStrategy{

    @Override
    public boolean checkWinner(Player player, Board board) {
        int size=board.getSize();
        for(int i=0;i<size;++i){
            boolean rowFound=true;
            for(int j=0;j<size;++j){
                if(board.getCell(i, j).getSymbol()!=player.getSymbol()){
                    rowFound=false;
                    break;
                }
            }
            if(rowFound==true){
                return true;
            }
        }
        return false;
    }

}
