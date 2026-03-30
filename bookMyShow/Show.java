package bookMyShow;

import java.util.List;

public class Show {
    private String id;
    private Movie movie;
    private Screen screen;
    private long startTime;
    private List<ShowSeat> seats;

    public Show(String id, Movie movie, Screen screen, long startTime, List<ShowSeat> seats) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.seats = seats;
    }

    public String getId() { return id; }
    public Movie getMovie() { return movie; }
    public List<ShowSeat> getSeats() { return seats; }
}
