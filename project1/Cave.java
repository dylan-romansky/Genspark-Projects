import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Cave	{
	public static void Cave()	{
		Scanner userIn = new Scanner(System.in);
		String cave_app = "You approach the cave...\nIt is dark and spooky...\nA large dragon jumps out in front of you! He opens his jaws and...\n";
		String[] dragons = {"Gobbles you down in one bite", "Offers you some of his treasure..."};

		System.out.println("You are in a land full of dragons. In front of you,\nyou see two caves. In one cave, the dragon is friendly\nand will share his treasure with you. The other dragon\nis greedy and hungry and will eat you on sight.\nWhich cave will you go int? (1 or 2)");
		int input = 0;
		try	{
			//give the player the illusion of choice
			input = userIn.nextInt() & 1;
            Random rand = new Random(System.currentTimeMillis() / 1000L);
            input += rand.nextInt();
			input &= 1;
        }
		catch(InputMismatchException e)	{
			System.out.println("Error, invalid input");
			System.exit(1);
		}
		System.out.println(cave_app + dragons[input]);
		System.exit(0);
	}
}
