package Players;

import java.util.ArrayList;

public interface Player {
    /**
     *
     * @param opponentMoves
     * @return 0 - swerve, 1 - straight
     */
    int makeDecision(ArrayList<Integer> opponentMoves);
}
