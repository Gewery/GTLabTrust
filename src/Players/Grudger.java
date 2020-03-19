package Players;

import java.util.ArrayList;

public class Grudger implements Player {
    @Override
    public int makeDecision(ArrayList<Integer> opponentMoves) {
        if (opponentMoves.size() == 0)
            return 0;
        if (opponentMoves.contains(1))
            return 1;
        else
            return 0;
    }
}
