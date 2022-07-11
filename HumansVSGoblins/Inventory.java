public class Inventory {
    private int gold;
    private HashMap<Item> inv;

    public void addLoot(Loot loot)  {
        gold += loot.gold;
        inv += loot.items();
    }
    int getGold()   {
        return gold;
    }
    //getItem figure out a way to get the current inventory, access a specific one, etc
}