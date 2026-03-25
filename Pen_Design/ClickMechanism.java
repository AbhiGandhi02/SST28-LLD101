package Practice_Design.Pen_Design;

public class ClickMechanism implements PenMechanism{
    @Override
    public void open(){
        System.out.println("Click Open");
    }
    @Override
    public void close(){
        System.out.println("Click Close");
    }
}
