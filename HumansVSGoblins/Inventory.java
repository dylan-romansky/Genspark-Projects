import java.util.ArrayList;

public class Inventory {
    private int gold;
    private ArrayList<Consumable> items;
    private ArrayList<Weapon> weapons;
    Inventory() {
        gold = 0;
        items = new ArrayList<>();
        weapons = new ArrayList<>();
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
    ArrayList<Consumable> getItems()  {
        return items;
    }
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }
    public String[] conString()   {
        int len = items.size();
        String[] itList = new String[len];
        for (int i = 0; i < len; i++)
            itList[i] = items.get(i).toString();
        return itList;
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
    public int getConSize() {
        return items.size();
    }
}
