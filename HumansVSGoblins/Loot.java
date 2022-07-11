public class Loot   {
    private int gold;
    private HashMap<Item> items;
    Loot(int g) {
        gold = g;
        items = new HashMap<>;
    }
    void addItems(HashMap<Item> itemset){
        items.add(itemset);
    }
    int getGold(){
        return gold;
    }
    HashMap<Item> getItems(){
        return items;
    }
    String toString()   {
        return "looted";
    }
}