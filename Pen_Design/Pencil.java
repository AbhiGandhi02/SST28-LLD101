package Pen_Design;

public class Pencil implements WritingInstrument {
    public WriteStrategy writeStrategy;

    Pencil(WriteStrategy writeStrategy){
        this.writeStrategy = writeStrategy;
    }

    @Override
    public void write(String text){
        writeStrategy.write(text);
    }
}
