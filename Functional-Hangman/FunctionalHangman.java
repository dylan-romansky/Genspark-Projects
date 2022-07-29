import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Scanner;

public class FunctionalHangman	{
	public int exit = 1;
	private final String _word;
	private final String _dude = "O/|\\/\\";
	private final String[] _hanger;
	private String _guesses;
	private int _limbsCount;
	private Scanner input;

	public static void main(String[] args) {
		double refreshRate = 1000000000/60; //60 fps
		JFrame screen = new JFrame("Hangman");
		GameScreen video = new GameScreen(500, 500);
		screen.setResizable(false);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.add(video);
		screen.pack();
		screen.setLocationRelativeTo(null);
		screen.setVisible(true);
		Scanner input = new Scanner(System.in); //replace with a new method of text input
		FunctionalHangman mang = new FunctionalHangman(input);
		video.addHangman(mang);
		while (mang.exit != 0)	{
			double nextUpdate = System.nanoTime() + refreshRate;
			video.draw();
			mang._playGame();
			if (mang.exit == 0) {
				System.out.println("Play again?\n\n1: yes\n2: no");
				mang.exit = input.nextInt() - 1;
				if (mang.exit != 0)
					video.addHangman(new FunctionalHangman(input));
			}
			try {
				long wait = (long) (nextUpdate - System.nanoTime()) / 1000000;
				Thread.sleep(wait < 0 ? 0 : wait);  }
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		screen.dispose();
	}
	public FunctionalHangman(Scanner in)	{
		input = in;
		_word = _genWord();
		_hanger = new String[3];
		_hanger[0] = "+-+";
		_hanger[1] = "|";
		_hanger[2] = "===";
		_guesses = "";
		_limbsCount = 0;
		for (int i = _word.length(); i > 0; i--)
			_guesses += "_";
	}
	public FunctionalHangman()	{
		_word = _genWord();
		_hanger = new String[3];
		_hanger[0] = "+-+";
		_hanger[1] = "|";
		_hanger[2] = "===";
		_guesses = "";
		_limbsCount = 0;
		for (int i = _word.length(); i > 0; i--)
			_guesses += "_";
	}
	protected void _playGame()	{
		_printMan();
		String c = "";
		try {
			c = input.nextLine().toUpperCase();
			System.out.println(_word);
			System.out.println(c);
		}
		catch (Exception e)	{
			System.out.println("Error: invalid input");
			return;
		}
		if (c.length() != 1) {
			System.out.println("One letter at a time please");
			return;
		}
		char _c = c.toCharArray()[0];
		if (_guesses.contains(c))	{
			System.out.println("You've already guessed " + c);
			return;
		}
		System.out.println(_c);
		if (_word.contains(c)) {
			StringBuilder bob = new StringBuilder(_guesses);
			for (int i = _word.length() - 1; i >= 0; i--) {
				if (_c == _word.toUpperCase().charAt(i))
					bob.setCharAt(i, _c);
			}
			_guesses = bob.toString();
		}
		else
			addlimb();
		switch (checkVictory()) {
			case 1 -> {
				System.out.println("Oops, out of guesses.");
				System.out.println("Your word was: " + _word);
				exit = 0;
			}
			case 2 -> {
				System.out.println("Congratulations! You win!");
				exit = 0;
			}
		}
		_printMan();
	}
	protected int checkVictory() {
		if (0 >_limbsCount || _limbsCount >= 6)
			return 1;
		if (Objects.equals(_word, _guesses))
			return 2;
		return 0;
	}
	public void addlimb()	{
		_limbsCount++;
		//open file for the next limb
	}
	private void _printMan()	{
		System.out.println(_hanger[0]);
		String body = "";
		if (_limbsCount > 0) {
			body += ' ';
			body += _dude.charAt(0);
		}
		System.out.println(_hanger[1] + body);
		body = "";
		for (int i = 1; i < _limbsCount && i <= 3; i++)
			body += _dude.charAt(i);
		System.out.println(_hanger[1].concat(body));
		body = "";
		for (int i = 4; i < _limbsCount; i++)
			body += _dude.charAt(i);
		System.out.println(_hanger[1].concat(body));
		System.out.println(_hanger[2]);
		System.out.print(_guesses);
		System.out.println(": " + _word.length() + " characters");
	}
	protected String _getWord()	{
		return _word;
	}
	protected String _getGuesses()	{
		return _guesses;
	}
	public int getLimbscount()	{
		return _limbsCount;
	}
	protected void _setLimbscount(int newCount)	{
		_limbsCount = newCount;
	}
	protected void _setGuesses(String newGuess)	{
		_guesses = newGuess;
	}
// The following was never about efficiency. It was always about finding a way to
// write code that interacts with the web in a way that's braindead easy and gets
// me the desired end result;
	private String _genWord()	{
//		return "stub".toUpperCase();
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
		WebDriver driver = new ChromeDriver(opt);
		driver.get("https://randomwordgenerator.com/");
		String ret = driver.findElement(By.xpath("//*[@id='result']/li")).getText();
		driver.close();
//		System.out.println(ret);
		return ret.toUpperCase();
	}
}
