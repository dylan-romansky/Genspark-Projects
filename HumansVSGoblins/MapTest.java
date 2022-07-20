import org.junit.Test;
import static org.junit.Assert.*;

//DEPRECATED: please convert over to graphical printing
public class MapTest {
    StateController status;
    @Test
    public void printTest() {
        System.out.println("Testing default configuration");
        status = new StateController();
        System.out.println(status.getTerra().toString());
        System.out.println("\nDoes that look right?\n");
        assertTrue(linesTest(status));
        status = new StateController(50, 10);
        System.out.println(status.getTerra().toString());
        System.out.println("\nDoes that one look right too?");
        assertTrue(linesTest(status));
        System.out.println("\nPopulation testing\n");
        System.out.println(status.getTerra().populate(status.fighter, status.getPopulous()));
        System.out.println("\nDid that fill in? No one overlapping?");
    }
    public Boolean linesTest(StateController status)   {
        int y = status.getTerra().getY();
        int x = status.getTerra().getX();
        int count = 0;
        String map = status.getTerra().toString();
        for (String line : map.split("\n")) {
            if (line.length() != x)
                return false;
            y--;
        }
        if (y != 0)
            return false;
        return true;
    }
}