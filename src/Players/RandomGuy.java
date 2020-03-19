package Players;

import java.util.ArrayList;
import java.util.Random;

public class RandomGuy implements Player {
    static Random rn = new Random();
    @Override
    public int makeDecision(ArrayList<Integer> opponentMoves) {
        return rn.nextInt(2);
    }
}
