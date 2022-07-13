import java.util.Random;

public class StateController {
    public static Random _rng = new Random(System.currentTimeMillis());
    Terrain terra;
    Humanoid[] hominids = new Humanoid[3];
    StateController()   {
        terra = new Terrain(20, 20);
        hominids[0] = new Human(new Coordinates(10, 10), "greg");
        for (int i = 1; i <= 2; i++) {
            int x = 10;
            int y = 10;
            while (x == 10 && y == 10)  {
                x = _rng.nextInt(0, 20);
                y = _rng.nextInt(0, 20);
            }
            hominids[i] = new Goblin(new Coordinates(x, y));
        }
    }
    StateController(int x, int y, String name)   {
        terra = new Terrain(x, y);
        hominids[0] = new Human(new Coordinates(x/2, y/2), name);
        for (int i = 1; i <= 2; i++)    {
            int _x = x/2;
            int _y = y/2;
            while (x == x/2 && y == y/2)    {
                _x = _rng.nextInt(0, x);
                _y = _rng.nextInt(0, y);
            }
            hominids[i] = new Goblin(new Coordinates(_x, _y));
        }
    }
}