
import SnakeAndLadder.enums.Cell;
public class Main {
    public static void main(String[] args) {
        Game game=new Game(6,100);
        Player p1=new Player("sangam", new Cell(0));
        Player p2=new Player("rahul", new Cell(0));
        Player p3=new Player("anjali", new Cell(0));
        game.addPlayer(p3);
        game.addPlayer(p2);
        game.addPlayer(p1);
        game.makeSnakeCell(17, 9);
        game.makeLadderCell(3,16);


        game.start();

    }
}
