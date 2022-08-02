import org.junit.*;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.Assert.*;

public class UnitTester {
    FunctionalHangman hang = new FunctionalHangman();
    GameScreen video = new GameScreen(500, 500);
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
        assert(hang._getGuesses().compareTo("stub") == 0);
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
}
