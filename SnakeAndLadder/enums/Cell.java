package SnakeAndLadder.enums;

public class Cell
  {
      
    private  final int head;
    private int tail;

    public Cell(int head){
        this.head=head;

        this.tail=head;
    }
    public int getCell(int head) {
        return tail;
    }
    public int gethead() {
        return head;
    }
    public void setTailt(int tail){
        this.tail=tail;
    }
    
    
    
    public int gettail() {
        return tail;
    }

}
