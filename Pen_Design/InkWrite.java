package Practice_Design.Pen_Design;

public class InkWrite implements WriteStrategy{
    @Override
    public void write(String text){
        System.out.println("Ink writing: " + text);
    }
}
