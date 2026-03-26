public class Snake implements Jump{
    private int head;
    private int tail;

    Snake(int head, int tail){
        this.head = head;
        this.tail = tail;
    }

    @Override
    public int getStart(){
        return head;
    }

    @Override
    public int getEnd(){
        return tail;
    }
}
