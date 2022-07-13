import org.junit.*;
import static org.junit.Assert.*;

public class HumanoidTest   {
    @Test
    public void HumanoidTests() {
        Human greg = new Human(new Coordinates(5, 5));
        Goblin grog = new Goblin(new Coordinates(5, 5));
        System.out.println(grog.getCoords().toString());
        assertEquals(0, greg.getCoords().toString().compareTo("5, 5"));
        assertEquals(0, grog.getCoords().toString().compareTo("5, 5"));
        for (int i = 0; i < 40; i++)    {
            grog.updateCoords();
            System.out.println(grog.getCoords().toString());
        }
        System.out.println("Did Grog move? Due to the nature of random numbers this is impossible to set a 100% accurate check for");
        greg.updateCoords('w');
        assertEquals(0, greg.getCoords().toString().compareTo("5, 4"));
        greg.updateCoords('W');
        assertEquals(0, greg.getCoords().toString().compareTo("5, 3"));
        greg.updateCoords('a');
        assertEquals(0, greg.getCoords().toString().compareTo("4, 3"));
        greg.updateCoords('A');
        assertEquals(0, greg.getCoords().toString().compareTo("3, 3"));
        greg.updateCoords('s');
        assertEquals(0, greg.getCoords().toString().compareTo("3, 4"));
        greg.updateCoords('S');
        assertEquals(0, greg.getCoords().toString().compareTo("3, 5"));
        greg.updateCoords('d');
        assertEquals(0, greg.getCoords().toString().compareTo("4, 5"));
        greg.updateCoords('D');
        assertEquals(0, greg.getCoords().toString().compareTo("5, 5"));
        int greghelth = greg.getHealth();
        for (int i = 0; i < 5; i++) {
            System.out.println("Grog attacks greg with his " + grog.getWep().toString());
            System.out.println("Greg is now at " + grog.attack(greg) + " health");
        }
        System.out.println("Time for greg to get some payback");
        int groghelth = grog.getHealth();
        for (int i = 0; i < 5; i++) {
            System.out.println("Greg attacks grog with his " + greg.getWep().toString());
            System.out.println("Grog is now at " + greg.attack(grog) + " health");
        }
        System.out.println(grog.getHealth() <= 0 ? "Grog is aliven't B-)" : "Grog somehow manages to survive!");
        assertTrue(greg.getHealth() != greghelth);
        assertTrue(grog.getHealth() != groghelth);
        System.out.println(grog.drops());
    }
}
