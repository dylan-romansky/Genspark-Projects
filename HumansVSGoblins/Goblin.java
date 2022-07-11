public class Goblin extends Humanoid    {
    Goblin()    { //state contoller should handle errored goblins
    }
    Goblin(Coordinates pos) {
        super(pos);
        _health = 40;
        _attack = Math.abs(_rng.nextInt() % 6) + 9;
        wep = Weapon.createWeapon();
    }
    public String toString()   {
        return "G";
    }
    private HashMap<Items> _genLootSet()  {
        HashMap<Items> loot = new HashMap<>();
        if (Math.abs(_rng.nextInt() % 5) == 0)
            loot.add(Weapon.CreateWeapon());
        HashMap<Items> consoom = new HashMap<>();
        for (int i = Math.abs(_rng.nextInt() % 4); i > 0; i--)
            consoom.add(Consumable.MakeConsumable());
        return loot.add(consoom);
    }
    public Loot drops() {
        loot = new Loot(Math.abs(_rng.nextInt() % 50)) + 100);
        loot.addItems(_genLootSet());
        return loot;
    }
}
