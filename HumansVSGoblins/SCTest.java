import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SCTest {
    StateController status = new StateController();
    @Test
    public void testCollision()  { //sounds way cooler than it is
        Coordinates target = status.getPopulous().get(1).getCoords();
        Humanoid greg = status.getPopulous().get(0);
        while (greg.getCoords().getX() < target.getX())
            greg.updateCoords('d', status.getTerra().getX(), status.getTerra().getY());
        while (greg.getCoords().getX() > target.getX())
            greg.updateCoords('a', status.getTerra().getX(), status.getTerra().getY());
        while (greg.getCoords().getY() < target.getY())
            greg.updateCoords('s', status.getTerra().getX(), status.getTerra().getY());
        while (greg.getCoords().getY() > target.getY())
            greg.updateCoords('w', status.getTerra().getX(), status.getTerra().getY());
        if (status.fightCheck().size() == 0)
            assertTrue(false);
    }
}