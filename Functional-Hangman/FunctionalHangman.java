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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
		while (mang.exit != 0) {
			double nextUpdate = System.nanoTime() + refreshRate;
			if (mang.exit == 2)
				video.endGame();
			video.draw();
			try {
				long wait = (long) (nextUpdate - System.nanoTime()) / 1000000;
				Thread.sleep(wait < 0 ? 0 : wait);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(2500L);
		}
		catch (Exception e)	{
			e.printStackTrace();
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
		if (_word.contains(c)) {
			System.out.println(c + " in " + _word);
			StringBuffer n = new StringBuffer(_guesses);
			Matcher match = Pattern.compile(c).matcher(_word);
			match.results().map(MatchResult::start).forEach(a -> n.setCharAt(a, _c));
			System.out.println(n);
			_guesses = n.toString();
		}
		else
			addlimb();
		switch (checkVictory()) {	// TODO: handle changes of gamestate
			case 1 -> {
				System.out.println("Oops, out of guesses.");
				System.out.println("Your word was: " + _word);
				exit = 2;
			}
			case 2 -> {
				System.out.println("Congratulations! You win!");
				exit = 2;
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
