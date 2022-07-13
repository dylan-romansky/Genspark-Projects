import org.junit.*;

import java.util.HashSet;

import static org.junit.Assert.*;

public class HumanoidTest   {
    public Human greg;
    public Goblin grog;
    public HumanoidTest()  {
        greg = new Human(new Coordinates(5, 5), "Greg");
        grog = new Goblin(new Coordinates(5, 5), "Grog");
    }
    @Test
    public void MovementTest() {
        System.out.println("Verifying start positions");
        System.out.println(grog.getName() + ": " + grog.getCoords().toString());
        System.out.println(greg.getName() + ": " + greg.getCoords().toString());
        assertEquals(0, greg.getCoords().toString().compareTo("5, 5"));
        assertEquals(0, grog.getCoords().toString().compareTo("5, 5"));
        System.out.println("Testing " + grog.getName() + "'s random movement");
        for (int i = 0; i < 40; i++) {
            grog.updateCoords(10, 10);
            System.out.println(grog.getCoords().toString());
        }
        System.out.println("Did " + grog.getName() + " move? Due to the nature of random numbers this is impossible to set a 100% accurate check for\n");
        System.out.println("Testing " + greg.getName() + "'s movement. This is done internaly.");
        greg.updateCoords('w', 10, 10);
        assertEquals(0, greg.getCoords().toString().compareTo("5, 4"));
        greg.updateCoords('W', 10, 10);
        assertEquals(0, greg.getCoords().toString().compareTo("5, 3"));
        greg.updateCoords('a', 10, 10);
        assertEquals(0, greg.getCoords().toString().compareTo("4, 3"));
        greg.updateCoords('A', 10, 10);
        assertEquals(0, greg.getCoords().toString().compareTo("3, 3"));
        greg.updateCoords('s', 10, 10);
        assertEquals(0, greg.getCoords().toString().compareTo("3, 4"));
        greg.updateCoords('S', 10, 10);
        assertEquals(0, greg.getCoords().toString().compareTo("3, 5"));
        greg.updateCoords('d', 10, 10);
        assertEquals(0, greg.getCoords().toString().compareTo("4, 5"));
        greg.updateCoords('D', 10, 10);
        assertEquals(0, greg.getCoords().toString().compareTo("5, 5"));
        System.out.println(greg.getName() + "'s movement check passed");
    }
    @Test
    public void CombatTest()    {
        System.out.println("Testing combat system\n");
        int greghelth = greg.getHealth();
        for (int i = 0; i < 5; i++) {
            System.out.println(grog.getName() + " attacks " + greg.getName() + " with his " + grog.getWep().toString());
            System.out.println(greg.getName() + " is now at " + grog.attack(greg) + " health");
        }
        System.out.println("\nTime for " + greg.getName() + " to get some payback...\n");
        int groghelth = grog.getHealth();
        for (int i = 0; i < 5; i++) {
            System.out.println(greg.getName() + " attacks grog with his " + greg.getWep().toString());
            System.out.println(grog.getName() + " is now at " + greg.attack(grog) + " health");
        }
        System.out.println(grog.getName() + (grog.getHealth() <= 0 ? " is aliven't B-)" : " somehow manages to survive!"));
        assertTrue(greg.getHealth() != greghelth);
        assertTrue(grog.getHealth() != groghelth);
        System.out.println(grog.drops().toString());
    }

    @Test
    public void InventoryTest() {
        System.out.println("Testing inventory management systems\n");
        System.out.println("Generating items to fill inventory");
        String wepString = "";
        String conString = "";
        HashSet<Weapon> gennedWeps = new HashSet<>();
        HashSet<Consumable> gennedCons = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            Weapon newep = Weapon.createWeapon();
            greg.addLoot(newep);
            gennedWeps.add(newep);
        }
        for (int i = 0; i < 6; i++) {
            Consumable newcon = Consumable.makeConsumable();
            greg.addLoot(newcon);
            gennedCons.add(newcon);
        }
        System.out.println(greg.getInv().toString());

        assertEquals(0, greg.getInv().getGold());
        System.out.println("Gold is correct");

        assertEquals(greg.getInv().getWeapons(), gennedWeps);
        System.out.println("Weapons are correct");

        assertEquals(greg.getInv().getItems(), gennedCons);
        System.out.println("Items are correct");
    }
}
