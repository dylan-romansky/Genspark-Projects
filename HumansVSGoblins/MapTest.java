import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

//DEPRECATED: please convert over to graphical printing
public class MapTest {
    StateController status = new StateController(20, 20);
    @Test
    public void gridTest() {
        ArrayList<Terrain.tile> map = status.terra.getGrid();
        for (Terrain.tile t : map) {
            if (t.x == 1 && t.y != 1)
                System.out.print("\n" + t.toString() + " ");
            else
                System.out.print(t.toString() + " ");
        }
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