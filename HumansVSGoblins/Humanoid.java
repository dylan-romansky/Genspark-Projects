import java.util.Random;

public class Humanoid{
    private Coordinates coords;
    private int _health;
    private int _attack;
    private Weapon wep;
    private Random _rng = new Random(System.currentTimeMillis() / 1000L);


    public Humanoid()  {
        coords = new Coordinates;
        _health = 0;
        _attack = 0;
        Weapon wep = null;
    }
    public Humanoid(Coords pos)    {
        coords = pos;
    }
    public void updateCoords()  { //StateController will perform the sanity checks to keep these in bounds
        int odds = rand.nextInt() % 4;
        if (odds == 0)  { //20% chance for a Humanoid to move. Human overrides this.
            odds = rand.nextInt() % 3;
            switch (odds)   {
                case 0:
                    coords.setX(coords.getX() - 1);
                    break;
                case 1:
                    coords.setX(coords.getX() + 1);
                    break;
                case 2:
                    coords.setY(coords.getY() - 1);
                    break;
                case 3:
                    coords.setY(coords.getY() + 1);
                    break;
            }
        }
    }
    public void updateCoords(char dir);
    public int attack(Humanoid target)  {
        int targHealth = target.getHealth();
        if (_rng.nextInt() % 19 != 0) { //global 5% chance to miss an attack
            int damage = (_rng.nextInt() % wep.getMod()) + _attack;
            targHealth -= damage;
            target.setHealth(targHealth);
        }
        return targHealth; //so the state controller knows if it needs to remove an entity
    }
    public String toString {
        return "?";
    }
    public int getHealth()  {
        return _health;
    }
    public int getAttack()  {
        return _attack;
    }
    public Coordinates getCoords() {
        return coords;
    }

    public Weapon getWep() {
        return wep;
    }
    public void setHealth(int health) {
        _health = health;
    }
    public void setWep(newep) {
        wep = newep;
    }
}