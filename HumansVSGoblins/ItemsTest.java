import org.junit.*;
import static org.junit.Assert.*;

public class ItemsTest  {
    @Test
    public void weaponGen() {
        Sword sord = new Sword();
        Axe ax = new Axe();
        Spear sper = new Spear();
        assertEquals(0, "Sword".compareTo(sord.toString()));
        assertEquals(0, "Axe".compareTo(ax.toString()));
        assertEquals(0, "Spear".compareTo(sper.toString()));
        Weapon wep;
        try {
            for (int i = 0; i < 7; i++) {
                wep = Weapon.createWeapon();
                System.out.println(wep.toString());
                assertTrue(wep.getMod() > 0);
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
            fail();
        }
    }
    @Test
    public void consumableGen() {
        SmallPotion smPot = new SmallPotion();
        Potion pot = new Potion();
        LargePotion lgPot = new LargePotion();
        assertEquals(0, "Small Potion".compareTo(smPot.toString()));
        assertEquals(0, "Potion".compareTo(pot.toString()));
        assertEquals(0, "Large Potion".compareTo(lgPot.toString()));
        Consumable yum;
        try {
            for (int i = 0; i < 7; i++) {
                yum = Consumable.makeConsumable();
                System.out.println(yum.toString());
                assertTrue(yum.getMod() > 0);
            }
        }
        catch (Exception e) {
            fail();
        }
    }
    @Test
    public void consumableUse() {
        Human greg = new Human(new Coordinates(5, 5), "Greg");
        greg.setHealth(10);
        assertEquals(10, greg.getHealth());
        Consumable test = new LargePotion();
        System.out.println("Testing consumable items\n");
        System.out.println("Adding Large Potion to inventory\n");
        greg.addLoot(test);
        assertTrue(greg.getInv().getItems().contains(test));
        System.out.println("Successfully added");
        System.out.println(greg.useConsumable(test));
        assertEquals(60, greg.getHealth());
        assertFalse(greg.getInv().getItems().contains(test));
        System.out.println("Successfully consumed");
    }

    @Test
    public void weaponEquip()   {
        Human greg = new Human(new Coordinates(5, 5), "Greg");
        String success = greg.setWeapon(new Fist());
        assertTrue(greg.getWep() instanceof Fist);
        System.out.println(success);
        Weapon wep = Weapon.createWeapon();
        System.out.println(greg.setWeapon(wep));
        System.out.println("Success");
    }
}
