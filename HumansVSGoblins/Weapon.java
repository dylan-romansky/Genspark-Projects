import java.util.Random;

public class Weapon {
    static Random rand = new Random(System.currentTimeMillis());
    protected int dmgModifier;
    Weapon()    {
        dmgModifier = 0;
    }
    public static Weapon createWeapon(){
        int weprand = Math.abs(rand.nextInt() % 3);
        switch (weprand) {
            case 0:
                return new Axe();
            case 1:
                return new Sword();
            case 2:
                return new Spear();
            default:
                System.out.println("uh oh we got a " + weprand);
        }
        return null;
    }

    int getMod()    {
        return dmgModifier;
    }
    public String toString()    {
        return "Error: Weapon of unknown type";
    }
}