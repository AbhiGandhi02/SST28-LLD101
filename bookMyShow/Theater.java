package bookMyShow;

import java.util.ArrayList;
import java.util.List;

public class Theater {
    private String id;
    private String name;
    private String city;
    private List<Screen> screens;

    public Theater(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.screens = new ArrayList<>();
    }

    public void addScreen(Screen screen) { screens.add(screen); }
    public String getCity() { return city; }
    public String getName() { return name; }
}
