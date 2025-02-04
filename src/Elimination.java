import java.util.ArrayList;

public class Elimination extends Solution {
    public Theory completeSolution(AssistantJack jack) {
        ArrayList<Integer> weapons = new ArrayList<Integer>();
        ArrayList<Integer> locations = new ArrayList<Integer>();
        ArrayList<Integer> suspects = new ArrayList<Integer>();

        ArrayList<Integer> possibleWeapons = new ArrayList<>();

        for (int i = 0; i < TheoryItem.TOTAL_WEAPONS; i++) {
            weapons.add(i+1);
        }
        for (int i = 0; i < TheoryItem.TOTAL_LOCATIONS; i++) {
            locations.add(i+1);
        }
        for (int i = 0; i < TheoryItem.TOTAL_SUSPECTS; i++) {
            suspects.add(i+1);
        }

        int solution = 0;

        do {
            solution = jack.checkAnswer(weapons.get(0), locations.get(0), suspects.get(0));
            switch (solution) {
                case 1: weapons.remove(0); break;
                case 2: locations.remove(0); break;
                case 3: suspects.remove(0); break;
                default: break;
            }
        } while (solution != 0);

        return new Theory(weapons.get(0), locations.get(0), suspects.get(0));
    }

    @Override
    public String getName() {
        return "Elimination";
    }
}
