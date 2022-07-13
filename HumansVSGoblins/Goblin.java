import java.util.HashMap;

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
    private Loot _genLootSet()  {
        Loot loot = new Loot();
        if (Math.abs(_rng.nextInt() % 5) == 0)
            loot.addWeapon(Weapon.createWeapon());
        HashMap<Consumable, Integer> consoom = new HashMap<>();
        for (int i = Math.abs(_rng.nextInt() % 5); i > 0; i--)  {
            Consumable con = Consumable.makeConsumable();
            Integer check = consoom.putIfAbsent(con, 1);
            if (check != null)
                consoom.put(con, check + 1);
        }
        loot.addItems(consoom);
        return loot;
    }

    @Override
    public void updateCoords(char dir)  {}

    public Loot drops() {
        if (_health > 0)  {
            return null;
        }
        Loot loot = new Loot(Math.abs(_rng.nextInt() % 50) + 100);
        loot.addItems(_genLootSet());
        return loot;
    }
}
