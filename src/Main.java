import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        AssistantJack jack = new AssistantJack();
        final int NUM_TESTS = 100;
        Runtime runtime = Runtime.getRuntime();

        ArrayList<Solution> solutions = new ArrayList<>();
        solutions.add(new Elimination());
        solutions.add(new RandomGuessing());
        solutions.add(new BinaryElimination());

        for (Solution solution : solutions) {
            jack.resetTimesAsked();
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < NUM_TESTS; i++) {
                jack.setAnswwerSet(AssistantJack.RANDOM_SET);
                Theory answer = solution.completeSolution(jack);
            }

            long endTime = System.currentTimeMillis();
            displayRank(solution.getName(), jack.getTimesAsked(), NUM_TESTS, endTime - startTime);
            System.out.println("Total Time: " + (endTime - startTime));
        }
    }

    public static void displayRank(String solverName, int guesses, int tests, double time) {
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

        System.out.println(solverName + ", with an average of " + avgGuesses + " guesses averaging " + avgTime + "ms per solution. Your rank is: " + rank + "!");
    }
}