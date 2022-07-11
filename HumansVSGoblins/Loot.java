public class Loot   {
    int gold;
    HashMap<Item> items;
    Loot(int g) {
        gold = g;
    }
    void addItems(HashMap<Item> itemset){
        items = itemset;
    }
    int getGold(){
        return gold;
    }
    HashMap<Item> getItems(){
        return items;
    }
}