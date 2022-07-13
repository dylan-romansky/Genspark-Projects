import java.util.HashSet;

public class Inventory {
    private int gold;
    private HashSet<Consumable> items;
    private HashSet<Weapon> weapons;
    Inventory() {
        gold = 0;
        items = new HashSet<>();
        weapons = new HashSet<>();
    }

    public void addLoot(Loot loot)  {
        Integer check = loot.getGold();
        if (check != null)
            gold += check;
        items.addAll(loot.getItems());
        weapons.addAll(loot.getWeapon());
    }
    public void addLoot(Weapon loot)  {
        weapons.add(loot);
    }
    public void addLoot(Consumable loot)  {
        items.add(loot);
    }
    public void addLoot(int glod)   {
        gold += glod;
    }
    int getGold()   {
        return gold;
    }
    HashSet<Consumable> getItems()  {
        return items;
    }
    public HashSet<Weapon> getWeapons() {
        return weapons;
    }
    public String toString()    {
        String wepList = "";
        String itList = "";
        for (Object ent : weapons.toArray())
            wepList += "\n " + ((Weapon) ent).toString();
        for (Object ent : items.toArray())
            itList += "\n " + ((Consumable) ent).toString();
        return "Gold: " + gold + "\nWeapons:" + wepList + "\nConsumables: " + itList;
    }
    //getItem figure out a way to get the current inventory, access a specific one, etc

}
