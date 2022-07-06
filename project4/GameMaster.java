import java.util.Scanner;

public class GameMaster	{

	public static void main(String args[])	{
		Game[] games = new Game[2];
		games[0]  = new Cave();
		games[1] = new GuessTheNumber();
		Scanner input = new Scanner(System.in);
		Boolean quit = false;
		while (quit == false)
		{
			try {
				System.out.println("Which game would you like to play?\n1: Cave\n2: Number\n\nanything else: quit");
				games[input.nextInt() - 1].Play(input);
			}
			catch (Exception e)	{ //this is very silly and I know it
				System.out.println("Thanks for playing!");
				quit = true;
			}
		}
	}
}
