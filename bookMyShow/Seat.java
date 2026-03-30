package bookMyShow;

public class Seat {
    private String id;
    private int row;
    private int col;
    private SeatCategory category;

    public Seat(String id, int row, int col, SeatCategory category) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.category = category;
    }

    public SeatCategory getCategory() { return category; }
    public String getId() { return id; }
}
