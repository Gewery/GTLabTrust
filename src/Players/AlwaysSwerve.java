package Players;

import java.util.ArrayList;

public class AlwaysSwerve implements Player {
    @Override
    public int makeDecision(ArrayList<Integer> opponentMoves) {
        return 1;
    }
}
