package Practice_Design.Pen_Design;

class Pen implements WritingInstrument {
    public WriteStrategy writeStrategy;
    public RefillingStrategy refillingStrategy;
    public PenMechanism penMechanism;
    public String color;

    Pen(WriteStrategy writeStrategy, RefillingStrategy refillingStrategy, PenMechanism penMechanism, String color){
        this.writeStrategy = writeStrategy;
        this.refillingStrategy = refillingStrategy;
        this.penMechanism = penMechanism;
        this.color = color;
    }

    @Override
    public void write(String text){
        writeStrategy.write(text);
    }

    public void refill(){
        refillingStrategy.refill();
    }

    public void open(){
        penMechanism.open();
    }

    public void close(){
        penMechanism.close();
    }
}
