public class Ladder implements Jump{
    private int start;
    private int end;

    Ladder(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int getStart(){
        return start;
    }

    @Override
    public int getEnd(){
        return end;
    }
}
