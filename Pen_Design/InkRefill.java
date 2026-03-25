package Practice_Design.Pen_Design;

public class InkRefill implements RefillingStrategy{
    @Override
    public void refill(){
        System.out.println("Refilling Ink");
    }
}
