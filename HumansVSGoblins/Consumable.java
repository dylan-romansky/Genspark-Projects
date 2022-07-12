import java.util.Random;

public class Consumable {
    private int dmgModifier;
    Consumable(){
        dmgModifier = 0;
    }
    public static Consumable makeConsumable(){
        Random rand = new Random(System.currentTimeMillis() / 1000L);
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
    public String toString()   {
        return "consumable is consumed healing 0 damage";
    }
}