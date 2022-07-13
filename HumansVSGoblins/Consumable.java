import java.util.Random;

public class Consumable {
    static Random rand = new Random(System.currentTimeMillis());
    protected int dmgModifier;
    Consumable(){
        dmgModifier = 0;
    }
    public static Consumable makeConsumable(){
        switch (Math.abs(rand.nextInt() % 3)) {
            case 0:
                return new SmallPotion();
            case 1:
                return new Potion();
            case 2:
                return new LargePotion();
        }
        return null;
    }
    public int getMod() {
        return dmgModifier;
    }
    public String toString()   {
        return "consumable is consumed healing 0 damage";
    }
}