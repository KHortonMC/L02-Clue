import java.util.Random;

public class RandomGuessing extends Solution {
    @Override
    public Theory completeSolution(AssistantJack jack) {
        int weapon, location, suspect;
        Random random = new Random();

        int solution = 0;
        do {
            weapon = random.nextInt(TheoryItem.TOTAL_WEAPONS) + 1;
            location = random.nextInt(TheoryItem.TOTAL_LOCATIONS) + 1;
            suspect = random.nextInt(TheoryItem.TOTAL_SUSPECTS) + 1;
            solution = jack.checkAnswer(weapon, location, suspect);
        } while (solution != 0);

        return new Theory(weapon, location, suspect);
    }

    @Override
    public String getName() {
        return "Random Guessing";
    }
}
