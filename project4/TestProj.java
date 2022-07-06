import org.junit.*;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class TestProj	{
	Cave cave;
	GuessTheNumber num;
	@Test

	void	runGames()	{
		ByteArrayInputStream in = new ByteArrayInputStream("4\n9\n1\n1\n1\n1\n1\n1\n1\n1\n1\n2\n1\n2\n1\n2\n1\n2\n2\n10\n5\n15\n20\n-12\n60\n12\n6\n".getBytes());
		System.setIn(in);
		Scanner test = new Scanner(System.in);
		for (int i = 0; i < 19; i++)
			cave.Play(test);
		for (int i = 19; i < 27; i++)
			num.Play(test);
	}
	public static void main(String[] args)	{
		TestProj test = new TestProj();
		test.runGames();
	}
}
