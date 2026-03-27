package Pen_Design;

public class CapMechanism implements PenMechanism{
    @Override
    public void open(){
        System.out.println("Remove Cap");
    }
    @Override
    public void close(){
        System.out.println("Close Cap");
    }
}
