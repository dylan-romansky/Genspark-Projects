import java.util.Random;

public abstract class Humanoid{
    protected Coordinates coords;
    protected int _health;
    protected int _attack;
    protected Weapon wep;
    protected static Random _rng = new Random(System.currentTimeMillis());
    protected String name;


    public Humanoid()  {
        coords = new Coordinates();
        _health = 0;
        _attack = 0;
        Weapon wep = null;
    }
    public Humanoid(Coordinates pos)    {
        coords = pos;
    }
    public Humanoid(Coordinates pos, String na) {
        coords = pos;
        name = na;
    }
    public void updateCoords()  { //StateController will perform the sanity checks to keep these in bounds
        int odds = Math.abs(_rng.nextInt() % 5);
        if (odds == 0)  { //20% chance for a Humanoid to move. Human overrides this.
            odds = Math.abs(_rng.nextInt() % 4);
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
    public abstract void updateCoords(char dir);
    //make another one that takes a key press event
    public int attack(Humanoid target)  {
        int targHealth = target.getHealth();
        if (Math.abs(_rng.nextInt()) % 20 != 0) { //global 5% chance to miss an attack
            int damage = Math.abs(_rng.nextInt() % wep.getMod()) + _attack;
            targHealth -= damage;
            target.setHealth(targHealth);
        }
        return targHealth; //so the state controller knows if it needs to remove an entity
    }
    public String toString() {
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
    public String getName()    {
        return name;
    }
    public void setHealth(int health) {
        _health = health;
    }
    public void setWep(Weapon newep) {
        wep = newep;
    }
}