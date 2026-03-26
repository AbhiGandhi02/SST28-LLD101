import java.util.LinkedList;
import java.util.Queue;

public class Main{
    public static void main(String[] args) {

        Board board = new Board(100);

        // Add snakes
        board.addSnakes(99, 54);
        board.addSnakes(70, 55);

        // Add ladders
        board.addLadders(3, 22);
        board.addLadders(5, 8);

        Queue<Player> players = new LinkedList<>();
        players.add(new Player("A"));
        players.add(new Player("B"));

        GameMode mode = new ContinueOnSixMode();
        // OR
        // GameMode mode = new ThreeSixTerminateMode();

        Game game = new Game(board, players, mode);

        game.startGame();
    }
}