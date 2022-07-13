import java.util.HashMap;

public class Inventory {
    private int gold;
    private HashMap<Consumable, Integer> items;
    private HashMap<Weapon, Integer> weapons;

    public void addLoot(Loot loot)  {
        gold += loot.getGold();
        //do a for each that puts when absent, adds when present
    }
    int getGold()   {
        return gold;
    }
    //getItem figure out a way to get the current inventory, access a specific one, etc

}