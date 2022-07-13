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
        if (check != null)
            wep.put(Wep, check + 1);
    }
    //for testing these are fine but they should be using entries to guarantee no loot is lost
    public void addItems(Loot itemset){
        items.putAll(itemset.getItems());
    }
    public void addItems(HashMap<Consumable, Integer> itemset)  {
        items.putAll(itemset);
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
        System.out.println(items.entrySet());
        for (Map.Entry ent : items.entrySet())
            totLoot += "\n-" + ent.getKey() + ": " + ent.getValue();
        return "looted:\n" + "-gold - " + gold + totLoot;
    }
}