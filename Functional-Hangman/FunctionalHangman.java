import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.json.*;

public class FunctionalHangman {
	public int exit = 1;
	private final String _word;
	private final String _dude = "O/|\\/\\";
	private final String[] _hanger;
	private String _guesses;
	private int _limbsCount;

	public static void main(String[] args) {
		int width = 500, height = 500;
		double refreshRate = 1000000000/60; //60 fps
		JFrame screen = new JFrame("Hangman");
		screen.setLayout(null);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameScreen video = new GameScreen(width, height);
		try	{
			JTextField name;
			JFormattedTextField text;
			text = new JFormattedTextField(new MaskFormatter("U"));
			text.setFont(new Font("Times New Roman", Font.PLAIN, 50));
			text.setBounds((width/2) + 80, height - 180, 40, 52);
			text.setColumns(1);
			text.setEditable(true);
			text.setVisible(false);
			name = new JTextField(12);
			name.setFont(new Font("Times New Roman", Font.PLAIN, 60));
			name.setBounds(width/2-175, 310, 330, 70);
			name.setEditable(true);
			name.setVisible(true);
			screen.add(name);
			screen.add(text);
			video.setNameField(name);
			video.setTextfield(text);
		}
		catch (Exception e)	{
			e.printStackTrace();
			System.exit(1);
		}
		screen.add(video);
		screen.pack();
		screen.setSize(width, height);
		screen.setResizable(false);
		screen.setVisible(true);
		screen.setLocationRelativeTo(null);
		FunctionalHangman mang = new FunctionalHangman();
		video.addHangman(mang);
		while (mang.exit != 0)	{
			double nextUpdate = System.nanoTime() + refreshRate;
			video.draw();
			if (mang.exit == 0) {
				System.out.println("Play again?\n\n1: yes\n2: no");	//TODO: method of restarting the game
				mang.exit = video.getChar() - '1';
				if (mang.exit != 0)
					video.addHangman(new FunctionalHangman());
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
	public FunctionalHangman()	{
		_word = _genWord();
		_hanger = new String[3];
		_hanger[0] = "+-+";
		_hanger[1] = "|";
		_hanger[2] = "===";
		_guesses = guesslineGen(_word.length());
		_limbsCount = 0;
	}
	public String guesslineGen(int len)	{ // I'm not a loop! You're a loop!
		System.out.println("ye");
		return len > 1 ? "_" + guesslineGen(len - 1) : "_";
	}
	protected void _playGame(Character _c)	{
		String c = _c.toString();
		if (_c == '\0')
			return;
		if (_guesses.contains(c))	{
			System.out.println("You've already guessed " + c);
			return;
		}
		System.out.println(c);
		System.out.println(_word);
		if (_word.contains(c)) {
			System.out.println("weh");
			StringBuffer n = new StringBuffer(_guesses);
			System.out.println(Pattern.compile(c).matcher(_word).groupCount());
			Pattern.compile(c).matcher(_word).results().map(MatchResult::start).map(a -> {
				n.setCharAt(a, _c);//would replace the second map with .forEach(a -> n.setCharAt(a, _c)); if I didn't have to strictly use map, reduce, or filter
				return a;
			});
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
		return "stub".toUpperCase();
//		ChromeOptions opt = new ChromeOptions();
//		opt.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
//		WebDriver driver = new ChromeDriver(opt);
//		driver.get("https://randomwordgenerator.com/");
//		String ret = driver.findElement(By.xpath("//*[@id='result']/li")).getText();
//		driver.close();
//		System.out.println(ret);
//		return ret.toUpperCase();
	}
}
