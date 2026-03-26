public class ContinueOnSixMode implements GameMode{
    @Override
    public int getTotalMoves(Dice dice){
        int total = 0;

        while (true) {
            int roll = dice.roll();
            System.out.println("Rolled: " + roll);

            total += roll;

            if (roll != 6) {
                break;
            }
        }

        return total;
    }
}
