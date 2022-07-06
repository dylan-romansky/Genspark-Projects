import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class GuessTheNumber extends Game	{
	private int _number;

	private int _numberGame(int input)	{
		if (input == _number)	{
			System.out.println("Congratulations!");
			return 1;
		}
		else
			System.out.println(input > _number ? "Too high" : "Too low");
		return 0;
	}

	@Override
	public void Play(Scanner input)	{
		Random rand = new Random(System.currentTimeMillis() / 1000L);
		_number = Math.abs((rand.nextInt() % 19) + 1);
		System.out.println("I have picked a number between 1 and 20.\nCan you guess what it is?");
		try	{
			int stat;
			for (int i = 0; i < 6; i++)
			{
				if (_numberGame(input.nextInt()) > 0)
					break;
			}
		}
		catch (InputMismatchException e)	{
			System.out.println("Error: bad input");
		}
	}
}
