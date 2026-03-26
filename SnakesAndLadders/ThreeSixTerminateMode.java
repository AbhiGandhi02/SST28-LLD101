public class ThreeSixTerminateMode implements GameMode{
    @Override
    public int getTotalMoves(Dice dice){
        int total = 0;
        int sixCount = 0;

        while (true) {

            int roll = dice.roll();
            System.out.println("Rolled: " + roll);

            total += roll;

            if (roll == 6) {
                sixCount++;

                if (sixCount == 3) {
                    System.out.println("3 consecutive 6s! Turn ends.");
                    break;
                }
            } else {
                break;
            }
        }

        return total;
    }
}
