package bookMyShow;

public class Movie {
    private String id;
    private String title;
    private String language;

    public Movie(String id, String title, String language) {
        this.id = id;
        this.title = title;
        this.language = language;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
}
