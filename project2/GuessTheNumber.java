import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class GuessTheNumber	{
	public static void GuessTheNumber()	{
		Scanner input = new Scanner(System.in);
		Random rand = new Random(System.currentTimeMillis() / 1000L);
		int number = Math.abs((rand.nextInt() % 19) + 1);
		System.out.println("I have picked a number between 1 and 20.\nCan you guess what it is?");
		int in = 0;
		for (int i = 0; i < 6; i++)	{
			try	{
				in = input.nextInt();
			}
			catch (InputMismatchException e)	{
				System.out.println("Error: bad input");
				System.exit(1);
			}
			if (in == number)	{
				System.out.println("Congratulations!");
				System.exit(0);
			}
			else
				System.out.println(in > number ? "Too high" : "Too low");
		}
		System.out.println("Better luck next time!");
	}
}
