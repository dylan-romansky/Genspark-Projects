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
}
