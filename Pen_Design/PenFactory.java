package Pen_Design;

public class PenFactory {
    public static Pen getPen(String type, String mechanismType, String color){
        WriteStrategy write;
        RefillingStrategy refill;
        PenMechanism mechanism;

        switch(type) {
            case "INK":
                write = new InkWrite();
                refill = new InkRefill();
                break;
            case "GEL":
                write = new GelWrite();
                refill = new CommonRefill();
                break;
            case "BALL":
                write = new BallWrite();
                refill = new CommonRefill();
                break;
            default:
                throw new RuntimeException("Invalid type");
        }

        switch(mechanismType){
            case "Click":
                mechanism = new ClickMechanism();
                break;
            case "Cap":
                mechanism = new CapMechanism();
                break;
            default: 
                throw new RuntimeException("Invalid type");
        }

        return new Pen(write, refill, mechanism, color);
    }
}
