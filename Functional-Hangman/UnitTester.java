import org.junit.*;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class UnitTester {
    FunctionalHangman hang = new FunctionalHangman();
    GameScreen video = new GameScreen(500, 500);
    Scores scores = new Scores();
    File scoreFile = new File("test.json");
    @After
    public void success()   {
        System.out.println("Success!");
        if (scoreFile.exists())
            scoreFile.delete();
    }
    @Test
    public void testWordGen()  {
        System.out.println("Testing word generator");
        try {
            String word = hang._getWord(); //if we don't throw an exception here, we succeed
            assertTrue(true);
        }
        catch (Exception e) {
            fail(); //if we caught an exception we've failed
        }
    }

    @Test
    public void testWinCon() {
        System.out.println("Testing win conditions");
        for (int i = 0; i < 6; i++) {
            hang._setLimbscount(i);
            assertEquals(0, hang.checkVictory());
        }
        for (int num : List.of(7, 12, 97476, -200, -1, -134683)) {
            hang._setLimbscount(num);
            assertEquals(1, hang.checkVictory());
        }
        hang._setLimbscount(0);
        hang._setGuesses(hang._getWord());
        assertEquals(2, hang.checkVictory());
    }
    @Test
    public void testGuessPrint()    {
        int width = 500, height = 500;
        JFrame screen = new JFrame("Hangman");
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFormattedTextField text;
        try {
            text = new JFormattedTextField(new MaskFormatter("U"));
            text.setBounds(width/2-25, height - 75, 50, 50);
            text.setEditable(true);
            text.setColumns(1);
            text.setVisible(true);
            video.setTextfield(text);
            video.add(text);
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        screen.add(video);
        screen.pack();
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);;
        video.addHangman(hang);
        video.draw();
        Character[] cs = {'u','t','s','b'};
        for (Character c : cs) {
            hang._playGame(Character.toUpperCase(c));
            try {
                Thread.sleep(2500L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            video.draw();
        }
        assert hang._getGuesses().compareTo("stub") == 0;
    }
    @Test
    public void testGameloop() { //this test needs to be reworked to account for new loopless game logic and also that exit is 1 while playing, 0 when done
        System.out.println("Testing game loop");
        try {
            System.out.println("acquiring solution");
            String in = "";
            for (char c : hang._getWord().toCharArray()) {
                if (in.indexOf(c) < 0) {
                    in += c;
                    in += '\n';
                }
            }
            in += "1\n";
            System.out.println("injecting to standard in:\n'");
            System.out.println(in);
              System.out.println("'");
            System.setIn(new ByteArrayInputStream(in.getBytes()));
            //assertEquals(0, _playGame(0)); //gonna need to adjust this
            System.out.println("acquiring an incorrect letter");
            char c = 'A';
            while (hang._getWord().indexOf(c) >= 0)
                c++;
            in = "";
            for (int i = 0; i < 6; i++) {
                in += c;
                in += '\n';
            }
            in += "2\n";
            System.out.println("injecting incorrect solution:\n'");
            System.out.println(in);
            System.out.println("'");
            hang._setGuesses(hang._getGuesses().replaceAll(".", "_"));
            System.setIn(new ByteArrayInputStream(in.getBytes()));
            //assertEquals(1, _playGame(0)); //gonna need to adjust this
        }
        catch (Exception e) {
            fail(); //if we encounter an unhandled exception we fail
        }
    }
    @Test
    public void scoreTest() {
        System.out.println("Testing score adding and sorting");
        String[] expected = {"chad: 9999", "greg: 8328", "julian: 6340", "steve: 1234", "kyle: 1232"};
        scores.add("julian", 6340);
        scores.add("greg", 8328);
        scores.add("steve", 1234);
        scores.add("kyle", 1232);
        scores.add("chad", 9999);
        String[] results = scores.getScores();
        for (String s : results)
            System.out.println(s);
        for (int i = 0; i < 5; i++) {
            System.out.println(results[i] + " == " + expected[i]);
            assert expected[i].compareTo(results[i]) == 0;
        }
        System.out.println("\nTesting score write to/read from file");
        System.out.println("writing...");
        scores.writeHighScores(scoreFile);
        System.out.println("checking for file...");
        assert scoreFile.exists();
        System.out.println("making new scoreboard...");
        scores = new Scores();
        System.out.println("reading scores from file...");
        scores.readHighScores(scoreFile);
        results = scores.getScores();
        for (String s : results)
            System.out.println(s);
        for (int i = 0; i < 5; i++) {
            System.out.println(results[i] + " == " + expected[i]);
            assert expected[i].compareTo(results[i]) == 0;
        }
        System.out.println("\nTesting addition of more scores on a full scoreboard");
        scores.add("chad", 9999);
        scores.add("chad", 9999);
        scores.add("chad", 9999);
        scores.add("chad", 9999);
        results = scores.getScores();
        assert(results.length == 5);
        for (String result : results)   {
            System.out.println(result + " == chad: 9999");
            assert result.compareTo("chad: 9999") == 0;
        }
    }
    @Test
    public void testEmptyScores()   {
        System.out.println("Testing behaviour on an empty scoreboard");
        System.out.println("Attempting read on a nonexistent file");
        System.out.println("-if we crash, we fail");
        try {
            scores.readHighScores(scoreFile); //test exception handling for when the file doesn't exist. if it doesn't crash, we're successful
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Unhandled exception");
        }
        System.out.println("\nWriting empty scoreboard");
        scores.writeHighScores(scoreFile);
        assert (scoreFile.exists());
        System.out.println("Reading empty scoreboard");
        scores = new Scores(scoreFile);
        scores.readHighScores(scoreFile);
        for (String score : scores.getScores())
            assert score.compareTo("") == 0;
        System.out.println("Adding player with a score of 0");
        scores.add("dave", 0);
        String res = scores.getScores()[0];
        System.out.println(res);
        assert scores.getScores()[0].compareTo("dave: 0") == 0;
    }
}
