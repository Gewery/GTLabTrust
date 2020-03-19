package Players;

import java.util.ArrayList;

public class Copycat implements Player {
    @Override
    public int makeDecision(ArrayList<Integer> opponentMoves) {
        if (opponentMoves.size() == 0)
            return 0;
        return opponentMoves.get(opponentMoves.size() - 1);
    }
}
