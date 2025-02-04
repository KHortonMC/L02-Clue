import java.util.ArrayList;
import java.util.Random;

public class BinaryElimination extends Solution {
    ArrayList<Integer> weapons = new ArrayList<Integer>();
    ArrayList<Integer> locations = new ArrayList<Integer>();
    ArrayList<Integer> suspects = new ArrayList<Integer>();

    ArrayList<Theory> theories = new ArrayList<Theory>();

    public BinaryElimination() {}

    ArrayList<Theory> generateTheories() {
        ArrayList<Theory> newTheories = new ArrayList<>();
        for (Integer i : weapons) {
            for (Integer j : locations) {
                for (Integer k : suspects) {
                    newTheories.add(new Theory(i, j, k));
                }
            }
        }
        return newTheories;
    }

    @Override
    public Theory completeSolution(AssistantJack jack) {
        weapons.clear();
        locations.clear();
        suspects.clear();

        for (int i = 0; i < TheoryItem.TOTAL_WEAPONS; i++) {
            weapons.add(i+1);
        }
        for (int i = 0; i < TheoryItem.TOTAL_LOCATIONS; i++) {
            locations.add(i+1);
        }
        for (int i = 0; i < TheoryItem.TOTAL_SUSPECTS; i++) {
            suspects.add(i+1);
        }

        theories = generateTheories();

        int solution = 0;
        Theory theory = null;
        Random random = new Random();

        do {
            theory = theories.get(random.nextInt(theories.size()));
            solution = jack.trueClue(theory);

            if (solution == 3) {
                tryEliminateAll(theory);
            }

            if (solution == 2) {
                if (numberFound() >= 1) {
                    // we have at least one identified item
                    // but two items are wrong
                    tryEliminateAll(theory);
                }
            }

            if (solution == 1) {
                if (numberFound() >= 2) {
                    // we have at least two identified items
                    // and only one wrong option
                    tryEliminateAll(theory);
                }
            }
        } while (solution != 0);

        return theory;
    }

    boolean tryEliminate(ArrayList<Integer> list, int value) {
        if (list.size() > 1) {
            int i = list.indexOf(value);
            if (i >= 0) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }

    void tryEliminateAll(Theory theory) {
        tryEliminate(weapons, theory.getWeapon());
        tryEliminate(locations, theory.getLocation());
        tryEliminate(suspects, theory.getPerson());
        theories = generateTheories();
    }

    int numberFound() {
        int found = 0;
        if (weapons.size() == 1) found++;
        if (locations.size() == 1) found++;
        if (suspects.size() == 1) found++;
        return found;
    }

    @Override
    public String getName() {
        return "Binary Elimination";
    }
}
