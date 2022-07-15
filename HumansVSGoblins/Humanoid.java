import java.util.Random;

public abstract class Humanoid{
    protected Coordinates coords;
    protected int _health;
    protected int _attack;
    protected Weapon wep;
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
    public void updateCoords(int boundX, int boundY)  { //StateController will perform the sanity checks to keep these in bounds
        int odds = StateController._rng.nextInt(0, 5);
        if (odds == 0)  { //20% chance for a Humanoid to move. Human overrides this.
            odds = StateController._rng.nextInt(0, 4);
            int x = 0;
            int y = 0;
            switch (odds)   {
                case 0:
                    x = coords.getX() - 1;
                    coords.setX(x >= 0 ? x : 0);
                    break;
                case 1:
                    x = coords.getX() + 1;
                    coords.setX(x < boundX ? x : boundX);
                    break;
                case 2:
                    y = coords.getY() - 1;
                    coords.setY(y >= 0 ? y : 0);
                    break;
                case 3:
                    y = coords.getY() + 1;
                    coords.setY(y < boundY ? y : boundY - 1);
                    break;
            }
        }
    }
    public abstract void updateCoords(char dir, int boundX, int boundY);
    //make another one that takes a key press event
    public int attack(Humanoid target)  {
        int targHealth = target.getHealth();
        if (StateController._rng.nextInt(0, 20) != 0) { //global 5% chance to miss an attack
            int damage = StateController._rng.nextInt(0, wep.getMod()) + _attack;
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
        if (_health < 0)
           _health = 0;
        else if (_health > 100)
            _health = 100;
    }
    public void setWep(Weapon newep) {
        wep = newep;
    }
}
