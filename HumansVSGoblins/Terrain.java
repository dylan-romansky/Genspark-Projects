import java.util.ArrayList;
import java.util.Random;

public class Terrain {
    private final int _y;
    private final int _x;
    private String _map;
    Terrain()   {
        _y = 20;
        _x = 20;
        fillMap();
    }
    Terrain(int x, int y)   {
        _y = y;
        _x = x;
        fillMap();
    }
    private void fillMap()  {
        _map = "";
        for (int i = _y * _x; i >= 0; i--)  {
            _map += StateController._rng.nextInt(0, 20) == 0 ? "~" : "-";
        }
    }
    public String populate(ArrayList<Humanoid> populace) {
        StringBuilder populous = new StringBuilder(_map);
        for (Humanoid dude : populace)  {
            Coordinates loc = dude.getCoords();
            populous.setCharAt((loc.getY() * _y) + loc.getX(), dude instanceof Human ? 'H' : 'G');
        }
        return populous.toString();
    }
}