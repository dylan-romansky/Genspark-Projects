import java.util.ArrayList;

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
        for (int i = _y * _x; i > 0; i--)  {
            _map += StateController._rng.nextInt(0, 20) == 0 ? "~" : "-";
        }
    }
    public String populate(ArrayList<Humanoid> populace) {
        StringBuilder populous = new StringBuilder(_map);
        for (Humanoid dude : populace)  {
            Coordinates loc = dude.getCoords();
            System.out.println(dude.getName() + ": x = " + loc.getX() + " y = " + loc.getY());
            populous.setCharAt((loc.getY() * _x) + loc.getX(), dude instanceof Human ? 'H' : 'G');
        }
        for (int i = populous.length(); i > 0; i--) {
            if (i % _x == 0)    {
                populous.insert(i, '\n');
            }
        }
        return populous.toString();
    }
    public String toString()    {
        String out = "";
        int start = 0;
        while (start < _map.length()) {
            out += _map.substring(start, start + _x) + "\n";
            start += _x;
        }
        return out;
    }
    public int getX()   {
        return _x;
    }
    public int getY()   {
        return _y;
    }
}