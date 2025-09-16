import SnakeAndLadder.enums.Cell;

public class Board {
    private final int size;
    private Dice dice;
    private final Cell cell[];


    
    public Board(Dice dice,int size){
        this.dice=dice;
        this.size=size;
        this.cell=new Cell[size];
        initializeBoard();
    }
    private void initializeBoard(){
        for(int i=0;i<size;i++){
            cell[i]=new Cell(i);
        }
    }
    public int getSize() {
        return size;
    }
    public Dice getDice() {
        return dice;
    }
    public Cell[] getCell() {
        return cell;
    }
    public void validCell(int x) throws InvalidCellException{
        if(x<0||x>=size)
        {
            throw new InvalidCellException("Out of bounds , try again");
        }
    }

    public Cell getCell(int x) throws InvalidCellException{
       
            validCell(x);
       
        return cell[x];
    }
    // public Cell playerMove(Player p,int x){
    //     validCell(x);
    //     return cell[x];
    // }
    public void setSnakeCell(int head,int tail){
        
        getCell(head);
        
        cell[head].setTailt(tail);
    }
    public void setLadderCell(int head,int tail ){
        
        getCell(head);
      
        cell[head].setTailt(tail);
    }
    public Cell nextMove(Player player){
        int number= rollDice();

        Cell cell=player.getPlayerPosition();
        if(number+cell.gethead()>=size){
            throw new InvalidCellException("Out of bounds");

        }
        int newPosition=number+cell.gethead();
        player.setCurrentMoves(1+player.getCurrentMoves());
        Cell newCell=getCell(newPosition);
        
        



        return newCell;

    }
    public int rollDice(){
        int number=dice.getNumber();
        int curNumber=(int)(Math.random()*number)+1;

        curNumber=curNumber%number+1;
        return curNumber;
    }
}
