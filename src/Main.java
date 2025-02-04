import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        AssistantJack jack = new AssistantJack();
        final int NUM_TESTS = 10;

        ArrayList<Solver> solvers = new ArrayList<>();
        solvers.add(new RandomSolver());

        for (Solver solver : solvers) {
            jack.resetTimesAsked();
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < NUM_TESTS; i++) {
                jack.setAnswerSet(AssistantJack.RANDOM_SET);
                Theory theory = solver.completeSolution(jack);
            }

            long endTime = System.currentTimeMillis();
            displayRank(solver.getName(), jack.getTimesAsked(), NUM_TESTS, endTime - startTime);
            System.out.println();
        }
    }

    public static void displayRank(String solverName, int guesses, int tests, long time) {
        double avgGuesses = (double)guesses / (double)tests;
        double avgTime = (double)time / (double)tests;

        String rank = "Jacques Clouseau";
        if (avgGuesses <= 10) {
            rank = "Sherlock Holmes";
        } else if (avgGuesses <= 15) {
            rank = "Nancy Drew";
        } else if (avgGuesses <= 20) {
            rank = "Columbo";
        } else if (avgGuesses <= 50) {
            rank = "Magnum P.I.";
        }

        System.out.println(solverName + ", with an average of " + avgGuesses + " guesses at " + avgTime + "ms per solution. Your rank is: " + rank + "!");
    }
}