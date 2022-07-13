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
    public void updateCoords(char dir)  {
        switch(dir) { //state controller keeps this in bounds
            case 'w':
            case'W':
                coords.setY(coords.getY() - 1);
                break;
            case 's':
            case'S':
                coords.setY(coords.getY() + 1);
                break;
            case 'a':
            case 'A':
                coords.setX(coords.getX() - 1);
                break;
            case 'd':
            case 'D':
                coords.setX(coords.getX() + 1);
                break;
        }
    }
    public String setWeapon(Weapon newwep)   {
        wep = newwep;
        String out = newwep instanceof Fist ? name + " is fighting bare-handed" : name + " equipped a";
        return out + (newwep instanceof Axe ? "n " + newwep.toString() : " " + newwep.toString());
    }
    public String useConsumable(Consumable item)  {
        _health += item.getMod();
        if (inventory.getItems().contains(item))
            inventory.getItems().remove(item);
        return name + " consumed a " + item.toString() + " healing " + item.getMod();
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