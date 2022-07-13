import java.security.KeyPair;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Loot   {
    private int gold;
    private HashSet<Consumable> items;
    private HashSet<Weapon> wep;
    Loot()  {
        gold = 0;
        wep = new HashSet<>();
        items = new HashSet<>();
    }
    Loot(int g) {
        gold = g;
        wep = new HashSet<>();
        items = new HashSet<>();
    }
    public void addWeapon(Weapon Wep)   {
        wep.add(Wep);
    }
    //for testing these are fine but they should be using entries to guarantee no loot is lost
    public void addItems(Loot itemset){
        items.addAll(itemset.getItems());
    }
    public void addItems(HashSet<Consumable> itemset)  {
        items.addAll(itemset);
    }
    public int getGold(){
        return gold;
    }
    HashSet<Consumable> getItems(){
        return items;
    }
    public HashSet<Weapon> getWeapon()   {
        return wep;
    }
    public String toString()   {
        String totLoot = "";
        for (Object ent : items.toArray())
            totLoot += "\n-" + ent.toString();
        return "looted:\n" + gold + " gold" + totLoot;
    }
}