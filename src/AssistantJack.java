import java.util.ArrayList;
import java.util.Random;

/**
 * AssistantJack.java : will be asked various theories by Detective Jill,
 * AssistantJack will then refute the answer or will accept the answer. If
 * AssistantJack refutes the answer, you will be notified of 1 part of your
 * theory that is incorrect (there may be more than one).
 *
 * @author Nery Chapeton-Lamas (material from Kevin Lewis)
 * @version 1.0
 *
 */
public class AssistantJack {
    public static final int RANDOM_SET = 3;

    private int timesAsked;
    private Theory correctTheory;

    /**
     * The default constructor but will not be called from outside.
     */
    public AssistantJack() {
        this.timesAsked = 0;
        this.correctTheory = null;
        setAnswerSet(RANDOM_SET);
    }

    /**
     * The full constructor, specifying which assistant to use (answer set 1,
     * answer set 2, or random). Depending on assistant, a different correct
     * Theory object is created.
     *
     * @param answerSet
     *            1 is a fixed answer of 1,1,1 and 2 is a fixed answer of 6,10,6
     *            any other integer will be randomly assigned
     */
    public AssistantJack(int answerSet) {
        this();
        setAnswerSet(answerSet);
    }

    public void resetTimesAsked() {
        this.timesAsked = 0;
    }

    public void setAnswerSet(int answerSet) {
        if (answerSet == 1) {
            this.correctTheory = new Theory(1, 1, 1);
        } else if (answerSet == 2) {
            this.correctTheory = new Theory(6, 10, 6);
        } else {
            Random random = new Random();
            int weapon = random.nextInt(6) + 1;
            int location = random.nextInt(10) + 1;
            int person = random.nextInt(6) + 1;
            this.correctTheory = new Theory(weapon, location, person);
        }
    }

    /**
     * Creates an assistant Jack with a theory that was passed in.
     *
     * @param theory
     *            deep copied object stored as correct theory
     */
    public AssistantJack(Theory theory) {
        this();
        this.correctTheory = new Theory(theory);
    }


    private int checkAnswer(int weapon, int location, int person, boolean countOnly) {
        ArrayList<Integer> wrongItems = new ArrayList<>();

        this.timesAsked++;

        // check each part and add to array list appropriately
        if (this.correctTheory.getWeapon() != weapon) {
            wrongItems.add(1);
        }

        if (this.correctTheory.getLocation() != location) {
            wrongItems.add(2);
        }

        if (this.correctTheory.getPerson() != person) {
            wrongItems.add(3);
        }

        if (countOnly) return wrongItems.size();

        // return based on incorrect items, if any, in array list
        if (wrongItems.isEmpty()) {
            return 0;
        } else if (wrongItems.size() == 1) {
            return wrongItems.getFirst();
        } else {
            Random random = new Random();
            int randomSelected = random.nextInt(wrongItems.size());
            return wrongItems.get(randomSelected);
        }
    }

    /**
     * Will report to the user whether their theory is correct. It will return a
     * number that needs to be interpreted (see return). Also keeps track of
     * times asked by incrementing every time this method is called
     *
     * @param weapon
     *            the weapon used 1 - 6
     * @param location
     *            the location where it was done 1 - 10
     * @param person
     *            the person who did it 1 - 6
     * @return 0 if all three are correct, 1 if the weapon is incorrect, 2 if
     *         the location is incorrect and 3 if the person is incorrect. If
     *         multiple are incorrect it will randomly select one of the
     *         incorrect parts and return that.
     */
    public int clueCount(int weapon, int location, int person) {
        return this.checkAnswer(weapon, location, person, true);
    }

    /**
     * Will report to the user whether their theory is correct. It will return a
     * number that needs to be interpreted (see return). Also keeps track of
     * times asked by incrementing every time this method is called
     *
     * @param weapon
     *            the weapon used 1 - 6
     * @param location
     *            the location where it was done 1 - 10
     * @param person
     *            the person who did it 1 - 6
     * @return the number of incorrect elements without telling which element is incorrect.
     */
    public int checkAnswer(int weapon, int location, int person) {
        return this.checkAnswer(weapon, location, person, false);
    }

    /**
     * Pass through (convenient) method that converts the theory to numbers and
     * sends it to the overloaded method.
     *
     * @param theory
     *            a theory of the murder, weapon and location
     *
     * @return 0 if all three are correct, 1 if the weapon is incorrect, 2 if
     *         the location is incorrect and 3 if the person is incorrect. If
     *         multiple are incorrect it will randomly select one of the
     *         incorrect parts and return that.
     */
    public int checkAnswer(Theory theory) {
        return this.checkAnswer(theory.getWeapon(), theory.getLocation(), theory.getPerson());
    }

    /**
     * Pass through (convenient) method that converts the theory to numbers and
     * sends it to the overloaded method.
     *
     * @param theory
     *            a theory of the murder, weapon and location
     *
     * @return the number of incorrect elements without telling which element is incorrect.
     */
    public int clueCount(Theory theory) {
        return this.checkAnswer(theory.getWeapon(), theory.getLocation(), theory.getPerson(), true);
    }

    /**
     * Accessor for times your AssistantJack has been asked to check a theory
     *
     * @return number of times AssistantJack has checkAnswer() been called
     */
    public int getTimesAsked() {
        return this.timesAsked;
    }
}