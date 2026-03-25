package Practice_Design.Pen_Design;

public class GripWriteDecorator implements WriteStrategy {
    private WriteStrategy wrappedStrategy;

    public GripWriteDecorator(WriteStrategy writeStrategy) {
        this.wrappedStrategy = writeStrategy;
    }

    @Override
    public void write(String text) {
        System.out.println("Grip is active — adjusting writing pressure...");
        wrappedStrategy.write(text);
    }
}
