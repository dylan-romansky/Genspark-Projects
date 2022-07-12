import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Loot   {
    private int gold;
    private HashMap<Consumable, Integer> items;
    private HashMap<Weapon, Integer> wep;
    Loot()  {
        gold = 0;
        wep = new HashMap<>();
        items = new HashMap<>();
    }
    Loot(int g) {
        gold = g;
        wep = new HashMap<>();
        items = new HashMap<>();
    }
    public void addWeapon(Weapon Wep)   {
        Integer check = wep.putIfAbsent(Wep, 1);
        if (check != 0)
            wep.put(Wep, check + 1);
    }
    public void addItems(Loot itemset){
        items.putAll(itemset.getItems());
    }
    public int getGold(){
        return gold;
    }
    HashMap<Consumable, Integer> getItems(){
        return items;
    }
    public HashMap<Weapon, Integer> getWeapon()   {
        return wep;
    }
    public String toString()   {
        String totLoot = "";
        for (Map.Entry ent : items.entrySet())
            totLoot += "\n-" + ent.getKey() + ": " + ent.getValue();
        return "looted:\n" + "-gold - " + gold + totLoot;
    }
}