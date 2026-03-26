import java.util.Queue;

public class Game {
    private Board board;
    private Dice dice;
    private Queue<Player> players;
    private GameMode gameMode;

    Game(Board board, Queue<Player> players, GameMode gameMode){
        this.board = board;
        this.players = players;
        this.dice = new Dice();
        this.gameMode = gameMode;
    }

    public void startGame(){
        while (true) {

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

            // Check winner
            if (newPosition == board.getSize()) {
                System.out.println(player.getName() + " wins!");
                break;
            }

            players.offer(player);
        }
    }
}
