import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;
import java.lang.Exception;

public class Cave extends Game	{
	String cave_app = "You approach the cave...\nIt is dark and spooky...\nA large dragon jumps out in front of you! He opens his jaws and...\n";
	String[] dragons = {"Gobbles you down in one bite", "Offers you some of his treasure..."};

	private void _caveGame(int input) throws Exception	{

		//give the player the illusion of choice
		Random rand = new Random(System.currentTimeMillis() / 1000L);
		input += rand.nextInt();
		if (!(0 < input && input < 2)){
			throw(new Exception("Error: invalid input"));
		}
		input &= 1;
		System.out.println(cave_app + dragons[input]);
	}

	@Override
	public void Play(Scanner input){
		System.out.println("You are in a land full of dragons. In front of you,\nyou see two caves. In one cave, the dragon is friendly\nand will share his treasure with you. The other dragon\nis greedy and hungry and will eat you on sight.\nWhich cave will you go into? (1 or 2)");
		try	{
			_caveGame(input.nextInt());
		}
		catch(Exception e)	{
			System.out.println("Error, invalid input");
		}
	}
}
