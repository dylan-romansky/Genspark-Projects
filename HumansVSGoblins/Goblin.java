import java.util.HashSet;

public class Goblin extends Humanoid    {
    Goblin()    { //state contoller should handle errored goblins
    }
    Goblin(Coordinates pos) {
        super(pos, "Goblin");
        _health = 40;
        _attack = StateController._rng.nextInt(0, 6) + 9;
        wep = Weapon.createWeapon();
    }
    Goblin(Coordinates pos, String name) {
        super(pos, name);
        _health = 40;
        _attack = StateController._rng.nextInt(0, 6) + 9;
        wep = Weapon.createWeapon();
    }
    private Loot _genLootSet()  {
        Loot loot = new Loot();
        if (StateController._rng.nextInt(0, 5) == 0)
            loot.addWeapon(Weapon.createWeapon());
        HashSet<Consumable> consoom = new HashSet<>();
        for (int i = StateController._rng.nextInt() % 5; i > 0; i--)
            consoom.add(Consumable.makeConsumable());
        loot.addItems(consoom);
        return loot;
    }

    @Override
    public void updateCoords(char dir, int boundX, int boundY)  {}

    public Loot drops() {
        if (_health > 0)  {
            return null;
        }
        Loot loot = new Loot(StateController._rng.nextInt(0, 50) + 100);
        loot.addItems(_genLootSet());
        return loot;
    }
}
