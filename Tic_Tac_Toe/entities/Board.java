package entities;

import enums.Symbol;
import exception.InvalidMoveException;

public class Board {
    private int size;
    private Cell board[][];
    private int totalMoves;

    public Board(int size) {
        this.totalMoves = 0;
        this.size = size;
        initializeBoard();

    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell();

            }
        }
    }

    public boolean isFull() {
        return size * size == totalMoves;
    }

    public boolean placeSymbol(Player p1, int x, int y) throws InvalidMoveException{
        validCell(x, y);
        
        if(board[x][y].getSymbol()!=Symbol.EMPTY){
             throw new InvalidMoveException("Invalid position: cell is already occupied.");
        }
        board[x][y].setSymbol( p1.getSymbol());
        totalMoves++;
        return true;

    }

    public boolean validCell(int x, int y) {
        return (x < 0 && x >= size && y < 0 && y >= size);

    }

    public Cell getCell(int x, int y) throws InvalidMoveException{

        if (validCell(x, y))
            return board[x][y];
        throw new InvalidMoveException("Invalid position: out of bounds");
    }

    public int getSize() {
        return size;
    }

}
