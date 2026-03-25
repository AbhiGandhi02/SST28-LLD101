package Practice_Design.Pen_Design;

public class GelWrite implements WriteStrategy{
    @Override
    public void write(String text){
        System.out.println("Gel writing: " + text);
    }
}
