import java.util.LinkedList;
import java.util.Queue;

public class Main{
    public static void main(String[] args) {

        Board board = new Board(100);

        // Add snakes
        board.addJump(new Snake(99, 54));
        board.addJump(new Snake(70, 55));

        // Add ladders
        board.addJump(new Ladder(3, 22));
        board.addJump(new Ladder(5, 8));

        Queue<Player> players = new LinkedList<>();
        players.add(new Player("A"));
        players.add(new Player("B"));
        players.add(new Player("C"));
        players.add(new Player("D"));
        players.add(new Player("E"));

        GameMode mode = new ContinueOnSixMode();
        // OR
        // GameMode mode = new ThreeSixTerminateMode();

        Game game = new Game(board, players, mode);

        game.startGame();
    }
}