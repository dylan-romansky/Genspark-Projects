import org.junit.*;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class UnitTester extends Hangman {
    @Test
    void testWordGen()  {
        System.out.println("Testing word generator");
        try {
            String word = _getWord(); //if we don't throw an exception here, we succeed
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false); //if we caught an exception we've failed
        }
    }

    @Test
    void testWinCon() {
        System.out.println("Testing win conditions");
        for (int i = 0; i < 6; i++) {
            _setLimbscount(i);
            assertTrue(checkVictory() == 0);
        }
        for (int num : List.of(7, 12, 97476, -200, -1, -134683)) {
            _setLimbscount(num);
            assertTrue(checkVictory() == 1);
        }
        _setLimbscount(0);
        _setGuesses(_getWord());
        assertTrue(checkVictory() == 2);
    }

    @Test
    void testGameloop() {
        System.out.println("Testing game loop");
        try {
            String in = _getWord().replaceAll(".", "$0\n");
            for (char c : _getWord().toCharArray()) {
                in += c;
                in += '\n';
            }
            in += "1\n";
            System.setIn(new ByteArrayInputStream(in.getBytes()));
            assertTrue(_playGame(0) == 0);
            char c = 'A';
            while (_getWord().indexOf(c) >= 0)
                c++;
            in = "";
            for (int i = 0; i <= 6; i++) {
                in += c;
                in += '\n';
            }
            in += "2\n";
            System.setIn(new ByteArrayInputStream(in.getBytes()));
            assertTrue(_playGame(0) == 1);
        }
        catch (Exception e) {
            assertTrue(false); //if we encounter an unhandled exception we fail
        }
    }

//    public static void main(String[] args)  {
//        UnitTester test = new UnitTester();
//        test.testWordGen();
//        test.testWinCon();
//        test.testGameloop();
    }
}
