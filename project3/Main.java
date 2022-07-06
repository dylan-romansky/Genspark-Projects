import java.util.Scanner;
import java.util.InputMismatchException;

public class Main    {
    public static void main(String[] args)  {
        boolean run = true;
        Scanner input = new Scanner(System.in);
		int choice = 0;
        while (run) {
            System.out.println("please choose a project to run");
            System.out.println("1: Dragon Cave\n2: Guess The Number\n\n0: Exit");
			try	{
				choice = input.nextInt();
				if (choice < 0 || choice > 2)
					throw new InputMismatchException();
			}
			catch(InputMismatchException e)	{
				System.out.println("Error: incorrect input");
			}
			System.out.println("");
			switch (choice)	{
				case 0:
					System.out.println("Thanks for playing!");
					System.exit(0);
				case 1:
					Cave.Game();
					System.out.println("");
					break;
				case 2:
					GuessTheNumber.Game();
					System.out.println("");
					break;
			}
        }
    }
}
