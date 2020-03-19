package Players;

import java.util.ArrayList;

public class Copykitten implements Player {
    @Override
    public int makeDecision(ArrayList<Integer> opponentMoves) {
        if (opponentMoves.size() <= 1)
            return 0;

        if (opponentMoves.get(opponentMoves.size() - 1) == 1 && opponentMoves.get(opponentMoves.size() - 2) == 1)
            return 1;
        else
            return 0;
    }
}
