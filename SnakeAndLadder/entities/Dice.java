public class Dice {
    private final int number;
    public Dice(int number){
        this.number=number;
    }
    public int getNumber(){
        return number;
    }   
    public int rollDice(){
        return (int)(Math.random()*number)+1;
    }
   
}
