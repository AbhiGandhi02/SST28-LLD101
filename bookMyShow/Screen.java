package bookMyShow;

public class Screen {
    private String id;
    private String name;
    private Theater theater;

    public Screen(String id, String name, Theater theater) {
        this.id = id;
        this.name = name;
        this.theater = theater;
    }

    public String getId() { return id; }
}