package Practice_Design.Pen_Design;

public class BallWrite implements WriteStrategy{
    @Override
    public void write(String text){
        System.out.println("Ball writing: " + text);
    }
}
