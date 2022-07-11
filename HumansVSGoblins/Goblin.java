public class Goblin extends Humanoid    {
    Goblin()    { //state contoller should handle errored goblins
    }
    Goblin(Coordinates pos) {
        super(pos);
        _health = 100;
        _attack = (_rng.nextInt() % 6) + 9;
        wep = new Weapon();
    }
    public String toString()   {
        return "G";
    }
    private HashMap<Items> _genLootSet()  {
        HashMap<Items> loot = new HashMap<>();
        if (_rng.nextInt() % 4 == 0)
            loot.add(wep);
        HashMap<Items> consoom = new HashMap<>();
        for (int i = _rng.nextInt() % 3; i > 0; i--)
            consoom.add(new::Consumable);
        return loot.add(consoom);
    }
    public Loot drops() {
        loot = new Loot((_rng.nextInt() % 50) + 100);
        loot.addItems(_genLootSet());
        return loot;
    }
}
