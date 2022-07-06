import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class GuessTheNumber	{
	public static void Game()	{
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
			}
			if (in == number)	{
				System.out.println("Congratulations!");
				return ;
			}
			else
				System.out.println(in > number ? "Too high" : "Too low");
		}
		System.out.println("Better luck next time!");
	}
}
