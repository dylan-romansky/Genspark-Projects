public class Human extends Humanoid {
    private Inventory inventory;
    Human() {//state controller will handle an invalid human character
    }
    Human(Coordinates pos){
        super(pos, "Greg");
        _health = 100;
        _attack = 10;
        wep = new Sword();
        inventory = new Inventory();
    }
    Human(Coordinates pos, String name) {
        super(pos, name);
        _health = 100;
        _attack = 10;
        wep = new Sword();
        inventory = new Inventory();
    }
    public void updateCoords(char dir, int boundX, int boundY)  {
        int x = 0;
        int y = 0;
        switch(dir) { //state controller keeps this in bounds
            case 'w':
            case'W':
                y = coords.getY() - 1;
                coords.setY(y >= 0 ? y : 0);
                break;
            case 's':
            case'S':
                y = coords.getY() + 1;
                coords.setY(y < boundY ? y : boundY - 1);
                break;
            case 'a':
            case 'A':
                x = coords.getX() - 1;
                coords.setX(x >= 0 ? x : 0);
                break;
            case 'd':
            case 'D':
                x = coords.getX() + 1;
                coords.setX(x < boundX ? x : boundX - 1);
                break;
        }
    }
    public String setWeapon(Weapon newwep)   {
        wep = newwep;
        String out = newwep instanceof Fist ? name + " is fighting bare-handed" : name + " equipped a" + (newwep instanceof Axe ? "n Axe" : " " + newwep.toString());
        return out;
    }
    public String useConsumable(Consumable item)  {
        _health += item.getMod();
        if (inventory.getItems().contains(item))
            inventory.getItems().remove(item);
        return name + " consumed a " + item.toString() + " healing " + item.getMod();
    }
    public void useConsumable(int index)    { //convert from hashset to arraylist
        Consumable consoom;
        consoom = (Consumable) inventory.getItems().toArray()[index];
        useConsumable(consoom);
    }
    public Inventory getInv(){
        return inventory;
    }
    public void addLoot(Loot loot)   {
        inventory.addLoot(loot);
    }
    public void addLoot(Weapon loot)   {
        inventory.addLoot(loot);
    }
    public void addLoot(Consumable loot)   {
        inventory.addLoot(loot);
    }
    public void addLoot(int gold)   {
        inventory.addLoot(gold);
    }
}