package Practice_Design.Pen_Design;

public class CommonRefill implements RefillingStrategy{
    @Override
    public void refill(){
        System.out.println("Refilling Gel/Ball");
    }
}
