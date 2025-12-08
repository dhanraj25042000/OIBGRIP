import java.util.Scanner;
#this is the game for number guessing using jenkins.
public class Numbergame11{

	public static void
	guessingNumberGame()
	{
		Scanner sc = new Scanner(System.in);

		int number = 1 + (int)(100* Math.random());
		int K = 5;
		int i, guess;

		System.out.println("A number is choosen"+ " between 1 to 100."+ "\nGuess the number"+ " within 5 trials.");

		for (i = 0; i < K; i++) {
			System.out.println("Guess the number:");
			guess = sc.nextInt();
			if (number == guess) {
				System.out.println("ohhh Wow !!! , You Guessed the Correct Number.");
				break;
			}
			else if (number > guess
					&& i != K - 1) {
				System.out.println("The number is "+ "greater than " + guess);
			}
			else if (number < guess
					&& i != K - 1) {
				System.out.println("The number is"+ " less than " + guess);
			}
		}
		if (i == K) {
			System.out.println("You have exhausted your "+K+" trials.");
			System.out.println("The number was " + number);
		}
	}

	public static void main(String args[])
	{
		guessingNumberGame();
	}
}
