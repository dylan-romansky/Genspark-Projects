import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StateController {
    public static Random _rng = new Random(System.currentTimeMillis());
    Terrain terra;
    ArrayList<Humanoid> hominids = new ArrayList<>(3);
    StateController()   {
        terra = new Terrain(20, 20);
        hominids.add(new Human(new Coordinates(10, 10), "greg"));
        for (int i = 1; i <= 2; i++) {
            int x = 10;
            int y = 10;
            while (x == 10 && y == 10)  {
                x = _rng.nextInt(0, 20);
                y = _rng.nextInt(0, 20);
            }
            hominids.add(new Goblin(new Coordinates(x, y)));
        }
    }
    StateController(int x, int y, String name)   {
        terra = new Terrain(x, y);
        hominids.add(new Human(new Coordinates(x/2, y/2), name));
        for (int i = 1; i <= 2; i++)    {
            int _x = x/2;
            int _y = y/2;
            while (x == x/2 && y == y/2)    {
                _x = _rng.nextInt(0, x);
                _y = _rng.nextInt(0, y);
            }
            hominids.add(new Goblin(new Coordinates(_x, _y)));
        }
    }
    void populateMap() {
        terra.populate(hominids);
    }
    ArrayList<Humanoid> fightCheck(){
        ArrayList<Humanoid> gottem = new ArrayList<>();
        Coordinates checkee = hominids.get(0).getCoords();
        for (int i = 1; i < hominids.size(); i++)   {
            Humanoid guy = hominids.get(i);
            Coordinates checker = guy.getCoords();
            if (checker.getX() == checkee.getX() && checker.getY() == checkee.getY())
                gottem.add(guy);
        }
        return gottem;
    }
    Boolean healthCheck()   {
        ArrayList<Humanoid> filtered = hominids.stream().filter(x -> x.getHealth() > 0).collect(Collectors.toCollection(ArrayList<Humanoid>::new));
        if (!(filtered.get(0) instanceof Human))
            return false;
        return true;
    }
}