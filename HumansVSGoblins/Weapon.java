public class Weapon implements Item {
    Weapon()    {
        dmgModifier = 0;
    }
    public static Item createWeapon(){
        Random rand = new Random(System.currentTimeMillis() / 1000L);
        switch (rand.nextInt() % 3) {
            case 0:
                return new Axe();
            case 1:
                return new Sword();
            case 2:
                return new Spear();
        }
    }
    public String toString()    {
        return "swings its weapon";
    }
}