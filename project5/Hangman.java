import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.sound.midi.Soundbank;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Hangman	{
	private final String _word;
	private final String[] _hanger;
	private String _guesses;
	private int _limbsCount;
	private String _dude = "O/|\\/\\";

	public static void main(String[] args) {
		int exit = 0;
		while (exit == 0)	{
			Hangman mang = new Hangman();
			exit = mang._playGame(exit);
		}
	}
	public Hangman()	{
		_word = _getWord();
		_hanger = new String[3];
		_hanger[0] = "+-+";
		_hanger[1] = "|";
		_hanger[2] = "===";
		_guesses = "";
		_limbsCount = 0;
		for (int i = _word.length(); i > 0; i--)
			_guesses += "_";
	}
	private int _playGame(int exit)	{
		Scanner input = new Scanner(System.in);
		_printMan();
		String c = "";
		while (exit == 0) {
			try {
				c = input.nextLine().toUpperCase();
				System.out.println(_word);
				System.out.println(c);
			}
			catch (Exception e)	{
				System.out.println("Error: invalid input");
				continue;
			}
			if (c.length() != 1) {
				System.out.println("One letter at a time please");
				continue;
			}
			char _c = c.toCharArray()[0];
			if (_guesses.contains(c))	{
				System.out.println("You've already guessed " + c);
				continue;
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
				_limbsCount++;
			switch (checkVictory()) {
				case 1 -> {
					System.out.println("Oops, out of guesses.");
					System.out.println("Your word was: " + _word;
					exit = 1;
				}
				case 2 -> {
					System.out.println("Congratulations! You win!");
					exit = 1;
				}
			}
			_printMan();
		}
		System.out.println("Play again?\n\n1: yes\n2: no");
		return input.nextInt() - 1;
	}

	public int checkVictory() {
		if (_limbsCount == 6)
			return 1;
		if (Objects.equals(_word, _guesses))
			return 2;
		return 3;
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

// The following was never about efficiency. It was always about finding a way to
// write code that interacts with the web in a way that's braindead easy and gets
// me the desired end result;
	private String _getWord()	{
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
