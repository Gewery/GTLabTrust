import Players.*;
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    final static int NUMBER_OF_COPYCATS = 2;
    final static int NUMBER_OF_ALWAYSSWERVE = 2;
    final static int NUMBER_OF_ALWAYSSTRAIGHT = 2;
    final static int NUMBER_OF_GRUDGERS = 2;
    final static int NUMBER_OF_DETECTIVES = 2;
    final static int NUMBER_OF_RANDOMGUYS = 2;
    final static int NUMBER_OF_COPYKITTENS = 2;

    final static int NUMBER_OF_GENERATIONS = 10;
    final static int NUMBER_OF_ROUNDS = 10;
    final static double PERCENT_OF_EXCLUSION_CLONING = 0.2;

    final static int[][][] CHICKEN_GAME_MATRIX = {{{0, 0}, {-1, +1}},
                                                {{+1, -1}, {-1000, -1000}}};

    static int NUMBER_OF_PLAYERS = NUMBER_OF_COPYCATS + NUMBER_OF_GRUDGERS + NUMBER_OF_DETECTIVES + NUMBER_OF_ALWAYSSTRAIGHT + NUMBER_OF_ALWAYSSWERVE + NUMBER_OF_COPYKITTENS + NUMBER_OF_RANDOMGUYS;

    static ArrayList<Player> players = new ArrayList<>();

    /**
     * Play one game between two players
     * @param a first player
     * @param b second player
     * @return Pair - score of player a, score of player b
     */
    private static Pair<Integer, Integer> playGame(Player a, Player b) {
        int scoreA = 0, scoreB = 0;
        ArrayList<Integer> movesA = new ArrayList<>();
        ArrayList<Integer> movesB = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_ROUNDS; i++) {
            int moveA = a.makeDecision(movesB);
            int moveB = b.makeDecision(movesA);
            movesA.add(moveA);
            movesB.add(moveB);
            scoreA += CHICKEN_GAME_MATRIX[moveA][moveB][0];
            scoreB += CHICKEN_GAME_MATRIX[moveA][moveB][1];
        }

        return new Pair<>(scoreA, scoreB);
    }

    /**
     * Play tournament between each other
     * @return ArrayList of pairs (Player, it's score)
     */
    private static ArrayList<Pair<Player, Integer>> playTournament() {
        ArrayList<Pair<Player, Integer>> tournamentResults = new ArrayList<>();

        int[] results = new int[players.size()];

        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                Pair<Integer, Integer> res = playGame(players.get(i), players.get(j));
                results[i] += res.getKey();
                results[j] += res.getValue();
            }
        }

        for(int i = 0; i < players.size(); i++)
            tournamentResults.add(new Pair<>(players.get(i), results[i]));

        return tournamentResults;
    }

    /**
     * Make next generation by algorithm:
     * 1 - play Tournament between each other
     * 2 - sort them by their score
     * 3 - remove PERCENT_OF_EXCLUSION_CLONING worst
     * 4 - duplicate PERCENT_OF_EXCLUSION_CLONING best
     */
    private static void makeNextGeneration() {
        ArrayList<Pair<Player, Integer>> tournamentResults = playTournament();

        tournamentResults.sort(Comparator.comparingInt(Pair::getValue));

        int numberToExcludeClone = (int) (players.size() * PERCENT_OF_EXCLUSION_CLONING);

        // remove 5 worst
        for (int i = 0; i < numberToExcludeClone; i++) {
            players.remove(tournamentResults.get(i).getKey());
        }

        // clone 5 best
        for (int i = 0; i < numberToExcludeClone; i++) {
            players.add(tournamentResults.get(tournamentResults.size() - i - 1).getKey());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < NUMBER_OF_COPYCATS; i++) players.add(new Copycat());
        for (int i = 0; i < NUMBER_OF_ALWAYSSWERVE; i++) players.add(new AlwaysSwerve());
        for (int i = 0; i < NUMBER_OF_ALWAYSSTRAIGHT; i++) players.add(new AlwaysStraight());
        for (int i = 0; i < NUMBER_OF_GRUDGERS; i++) players.add(new Grudger());
        for (int i = 0; i < NUMBER_OF_DETECTIVES; i++) players.add(new Detective());
        for (int i = 0; i < NUMBER_OF_RANDOMGUYS; i++) players.add(new RandomGuy());
        for (int i = 0; i < NUMBER_OF_COPYKITTENS; i++) players.add(new Copykitten());

        System.out.println("\nGeneration 0");
        for (int j = 0; j < NUMBER_OF_PLAYERS; j++)
            System.out.println(players.get(j).getClass());

        for(int i = 0; i < NUMBER_OF_GENERATIONS; i++) {
            makeNextGeneration();

            System.out.println("\nGeneration " + (i + 1));
            for (int j = 0; j < NUMBER_OF_PLAYERS; j++)
                System.out.println(players.get(j).getClass());
        }


    }
}
