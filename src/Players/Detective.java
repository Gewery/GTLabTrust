package Players;

import java.util.ArrayList;

public class Detective implements Player {
    @Override
    public int makeDecision(ArrayList<Integer> opponentMoves) {
        if (opponentMoves.size() == 0)
            return 0;
        else if (opponentMoves.size() == 1)
            return 1;
        else if (opponentMoves.size() <= 3)
            return 0;
        else {
            if (opponentMoves.contains(1))
                return opponentMoves.get(opponentMoves.size() - 1);
            else
                return 1;
        }
    }
}
