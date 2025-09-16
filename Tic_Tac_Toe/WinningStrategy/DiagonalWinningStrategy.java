package WinningStrategy;

import entities.Board;
import entities.Player;

public class DiagonalWinningStrategy implements WinningStrategy{

    @Override
    public boolean checkWinner(Player player, Board board) {
        int size=board.getSize();
        for(int i=0;i<size;++i){
            boolean diagonalFound=true;
   
            if(board.getCell(i, i).getSymbol()!=player.getSymbol()){
                diagonalFound=false;
                break;
            }
            
            if(diagonalFound==true){
                return true;
            }
        }
        int i=0,j=size-1;
        while(i<size&&j>=0){
            boolean diagonalFound=true;
   
            if(board.getCell(i, j).getSymbol()!=player.getSymbol()){
                diagonalFound=false;
                break;
            }
            
            if(diagonalFound==true){
                return true;
            }
            i++;j--;

        }
    
        return false;
    }

}
