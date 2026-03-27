import java.util.ArrayList;
import java.util.List;

public class Board {
    int size;
    List<Jump> jumps;

    Board(int size){
        this.size = size;
        this.jumps = new ArrayList<>();
    }

    public void addJump(Jump jump){
        jumps.add(jump);
    }

    public int getSize(){
        return this.size;
    }

    public int getFinalPosition(int position){
        for (Jump jump : jumps) {
            if (jump.getStart() == position) {
                System.out.println(position + " -> " + jump.getEnd());
                return jump.getEnd();
            }
        }
        return position;
    }
}
