# CS113-L02-Clue
## Detective Jill + Assistant Jack are on the Case!

[![Build Status](https://travis-ci.com/profe/CS113-HW01-Clue.svg?token=79PBopapWMemN2tzsJkL&branch=master)](https://travis-ci.com/profe/CS113-HW01-Clue)

***Dr. Foo has been murdered!*** 

> We are going to write an algorithm to solve the crime!

In Clue there are:

(6) Possible Murder Weapons<br><br>
(10) Possible Locations<br><br>
(6) Murder Suspects<br>

>  We have an implementation of Solver already: RandomSolver.

>  Run the program to see how many guesses it takes to solve this mystery!

>  Create a new Solver that finds the answer in < 20 guesses.

>   *Hint:* Clue is a game about *removing* false clues.

>  Assistant Jack will tell you randomly one clue you have wrong.

> *Bonus Task!* <br><br> After completing the solution using jack.checkAnswer(..), try solving with jack.clueCount(...) where instead of him telling you which one is wrong, he only tells you how many are wrong.

Long Description:

Detective Jill must determine the murderer, crime scene, and weapon. There are six possible murders (numbered 1 through 6, Professor Plum to Mrs. Peacock), 10 locations (1 through 10, ballroom to cellar), and six weapons (1 through 6, lead pipe to spanner). Detective Jill tries to guess the correct combination. Each guess is a theory. She asks her assistant, Jack, to confirm or refute each theory.

Use the files found in this repository to test your answer.  Inside you will find:

 - Model classes (represent the pure data/actions):
	 - `Theory.java` = encapsulates murdered, crime scene, and weapon data
	 - `TheoryItem.java` = convenient methods/constants for translating integers
	 - `AssistantJack.java` = instantiated with answer set, contains method to check your set of guesses (theory)
 - Driver class (one example of a solution to problem below):
	 - `RandomSolver.java` = Implements Solver: method, random guesses!

Note that Assistant Jack will randomly tell you which of your guesses are wrong, if multiple are wrong.  So if you ask Assistant Jack to check your answer with the same set of guesses, you may get a different item that is wrong which means that both the first and second items returned are wrong.  When checking guesses with Assistant Jack, your number of guesses (times you ask to check the potential answer) is kept track of.  Your goal is to write a program as if you were Detective Jill which **consistently** finds the solution in <= 20 checks for any of the three answer sets above, that means you didn't just get lucky!