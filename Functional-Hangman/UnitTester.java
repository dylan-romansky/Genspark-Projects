import org.junit.*;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.Assert.*;

public class UnitTester extends FunctionalHangman {
    @Test
    public void testWordGen()  {
        System.out.println("Testing word generator");
        try {
            String word = _getWord(); //if we don't throw an exception here, we succeed
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
            _setLimbscount(i);
            assertEquals(0, checkVictory());
        }
        for (int num : List.of(7, 12, 97476, -200, -1, -134683)) {
            _setLimbscount(num);
            assertEquals(1, checkVictory());
        }
        _setLimbscount(0);
        _setGuesses(_getWord());
        assertEquals(2, checkVictory());
    }

    @Test
    public void testGameloop() { //this test needs to be reworked to account for new loopless game logic and also that exit is 1 while playing, 0 when done
        System.out.println("Testing game loop");
        try {
            System.out.println("acquiring solution");
            String in = "";
            for (char c : _getWord().toCharArray()) {
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
            while (_getWord().indexOf(c) >= 0)
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
            _setGuesses(_getGuesses().replaceAll(".", "_"));
            System.setIn(new ByteArrayInputStream(in.getBytes()));
            //assertEquals(1, _playGame(0)); //gonna need to adjust this
        }
        catch (Exception e) {
            fail(); //if we encounter an unhandled exception we fail
        }
    }

    public static void main(String[] args)  {
        UnitTester test = new UnitTester();
        test.testWordGen();
        test.testWinCon();
        test.testGameloop();
    }
}
