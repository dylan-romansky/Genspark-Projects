import java.util.ArrayList;
//DEPRECATED: please convert to graphical printing
public class Terrain {
    private final int _y;
    private final int _x;
    private ArrayList<tile> grid;
    Terrain()   { //make a sanity check so the map has to be larger than some amount
        _y = 20;
        _x = 20;
        fillMap();
    }
    Terrain(int x, int y)   {
        _y = y;
        _x = x;
        fillMap();
        fightMenu();
    }
    class tile  {
        final int x;
        final int y;
        tile(int col, int row)  {
            x = col;
            y = row;
        }
    }
    private void fillMap()  {
        _map = "";
        for (int i = _y * _x; i > 0; i--)  {
            _map += StateController._rng.nextInt(0, 20) == 0 ? "~" : "-";
        }
    }
    private void fightMenu() { ////put on hold till I can get a loop together that supports movement
        int lim = _y/2;
        _fightmap = new String[lim];
        String topbot = "+";
        while (topbot.length() < lim - 1)
            topbot += "-";
        topbot += "+";
        _fightmap[0] = topbot;
        _fightmap[lim - 1] = topbot;
        for (int y = 1; y < lim - 1; y++)    {
            String line = "|";
            while (line.length() < _x - 1)
                line += " ";
            line += " ";
            _fightmap[lim - 1] = line;
        }
    }
    public String populate(Human fighter, ArrayList<Humanoid> populace) {
        StringBuilder populous = new StringBuilder(_map);
        Coordinates loc = fighter.getCoords();
        populous.setCharAt((loc.getY() * _x) + loc.getX(), 'H');
        for (Humanoid dude : populace)  {
            loc = dude.getCoords();
            //System.out.println(dude.getName() + ": x = " + loc.getX() + " y = " + loc.getY());
            populous.setCharAt((loc.getY() * _x) + loc.getX(), 'G');
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