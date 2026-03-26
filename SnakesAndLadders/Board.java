import java.util.HashMap;

public class Board {
    int size;
    HashMap<Integer, Integer> snakes;
    HashMap<Integer, Integer> ladders;

    Board(int size){
        this.size = size;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
    }

    public void addSnakes(int head, int tail){
        snakes.put(head, tail);
    }

    public void addLadders(int start, int end){
        ladders.put(start, end);
    }

    public int getSize(){
        return this.size;
    }

    public int getFinalPosition(int position){
        if (snakes.containsKey(position)) {
        System.out.println("Bitten by snake! " + position + " -> " + snakes.get(position));
        return snakes.get(position);
    }

    if (ladders.containsKey(position)) {
        System.out.println("Climbed ladder! " + position + " -> " + ladders.get(position));
        return ladders.get(position);
    }

    return position;
    }

}
