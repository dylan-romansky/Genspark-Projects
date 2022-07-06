public class Hangman	{
	private String _word;
	private String[] _hanger;
	private String _guesses;
	private Integer _limbsCount;
	private String _dude = "O/|\\/\\";

	public Hangman()	{
		_word = _getWord();
		_hanger = new String[3];
		_hanger[0] = "+-+";
		_hanger[1] = "|";
		_hanger[2] = "===";
		_guesses = "";
		_limbsCount = 0;
		for (int i = _word.length(); i > 0; i--)
			_guesses += '_';
	}

	private void _printMan()	{
		System.out.println(_hanger[0]);
		String body = "";
		if (_limbsCount > 0)
			body += _dude.charAt(0);
		System.out.println(_hanger[1] + _dude.charAt(0));
		body = "";
		for (int i = 1; i < _limbsCount || i < 3; i++)
			body += _dude.charAt(i);
		System.out.println(_hanger[1] + body);
		body = "";
		for (int i = 4; i < _limbsCount; i++)
			body += _dude.charAt(i);
		System.out.println(_hanger[1] + body);
		System.out.println(_hanger[2]);
	}

	private String _getWord()	{
		driver = new ChromeDriver();
		driver.get("https://randomwordgenerator.com/");
		// //*[@id="result"]/li/text()
		return "Stub";
		//find a way to query a random word generator
	}
}
