import java.util.Random;

public class Weapon {
    protected int dmgModifier;
    Weapon()    {
        dmgModifier = 0;
    }
    public static Weapon createWeapon(){
        Random rand = new Random(System.currentTimeMillis() / 1000L);
        switch (rand.nextInt() % 3) {
            case 0:
                return new Axe();
            case 1:
                return new Sword();
            case 2:
                return new Spear();
        }
        return null;
    }

    int getMod()    {
        return dmgModifier;
    }
    public String toString()    {
        return "swings its weapon";
    }
}