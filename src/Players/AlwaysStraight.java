package Players;

import java.util.ArrayList;

public class AlwaysStraight implements Player {
    @Override
    public int makeDecision(ArrayList<Integer> opponentMoves) {
        return 1;
    }
}
