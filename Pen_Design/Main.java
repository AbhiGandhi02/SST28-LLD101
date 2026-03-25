package Practice_Design.Pen_Design;

public class Main {
    public static void main(String[] args) {
        // Gel Pen
        Pen pen = PenFactory.getPen("GEL", "Click", "Blue");
        pen.open();
        pen.refill();
        pen.write("Hello from Gel Pen");
        pen.close();

        System.out.println("---");

        // Ball Pen with Grip
        Pen gripPen = PenFactory.getPen("BALL", "Cap", "Black");
        gripPen.writeStrategy = new GripWriteDecorator(gripPen.writeStrategy);
        gripPen.open();
        gripPen.write("Hello with grip");
        gripPen.refill();
        gripPen.close();

        System.out.println("---");

        // Pencil
        Pencil pencil = new Pencil(new PencilWrite());
        pencil.write("Hello from Pencil");
    }
}
