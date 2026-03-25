package Practice_Design.Pen_Design;

public class PencilWrite implements WriteStrategy{
    @Override
    public void write(String text){
        System.out.println("Pencil writing: " + text);
    }
}
