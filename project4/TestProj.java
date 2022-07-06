import org.junit.*;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class TestProj	{
	@Test
	void	runGames()	{
		Game[] games = new Game[2];
		games[0]  = new Cave();
		games[1] = new GuessTheNumber();
		ByteArrayInputStream in = new ByteArrayInputStream("4\n9\n1\n1\n1\n1\n1\n1\n1\n1\n1\n2\n1\n2\n1\n2\n1\n2\n2\n10\n5\n15\n20\n-12\n60\n12\n6\n".getBytes());
		System.setIn(in);
		Scanner test = new Scanner(System.in);
		for (int i = 0; i < 19; i++)
			games[0].Play(test);
//		for (int i = 19; i < 27; i++)
		games[1].Play(test);
	}
	public static void main(String[] args)	{
		TestProj test = new TestProj();
		test.runGames();
	}
}
