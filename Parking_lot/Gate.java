public class Gate {
    private int id;
    private int floor;
    private int x;
    private int y;

    public Gate(int id, int floor, int x, int y){
        this.id = id;
        this.floor = floor;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public int getFloor() {
        return floor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
