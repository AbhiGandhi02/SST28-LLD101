import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Game {
    private Board board;
    private Dice dice;
    private Queue<Player> players;
    private GameMode gameMode;
    private List<Player> winners;

    Game(Board board, Queue<Player> players, GameMode gameMode){
        this.board = board;
        this.players = players;
        this.dice = new Dice();
        this.gameMode = gameMode;
        this.winners = new ArrayList<>();
    }

    public void startGame(){
        while (players.size() > 1) {

            Player player = players.poll();

            System.out.println("\n" + player.getName() + "'s turn:");

            int moves = gameMode.getTotalMoves(dice);

            int currentPosition = player.getPosition();
            int newPosition = currentPosition + moves;

            // Boundary check
            if (newPosition > board.getSize()) {
                newPosition = currentPosition;
            } else {
                newPosition = board.getFinalPosition(newPosition);
            }

            player.setPosition(newPosition);

            System.out.println(player.getName() + " moved to " + newPosition);

            // Check winner — remove from game, continue
            if (newPosition == board.getSize()) {
                winners.add(player);
                System.out.println(player.getName() + " finishes at rank " + winners.size() + "!");
            } else {
                players.offer(player);
            }
        }

        // Last player remaining
        Player last = players.poll();
        winners.add(last);
        System.out.println("\n" + last.getName() + " is the last one remaining.");

        // Print final rankings
        System.out.println("\n=== Final Rankings ===");
        for (int i = 0; i < winners.size(); i++) {
            System.out.println((i + 1) + ". " + winners.get(i).getName());
        }
    }
}
