import java.util.ArrayList;
//DEPRECATED: please convert to graphical printing
public class Terrain {
    private final int _y;
    private final int _x;
    private ArrayList<tile> grid;
    private Video panel;
    Terrain()   { //make a sanity check so the map has to be larger than some amount
        _y = 20;
        _x = 20;
        fillMap();
        panel = new Video();
    }
    Terrain(int x, int y, Video pane)   {
        _y = y;
        _x = x;
        fillMap();
//        fightMenu();
        panel = pane;
    }
    public class tile  {
        final int x;
        final int y;
        tile(int col, int row)  {
            x = col;
            y = row;
        }
        @Override
        public String toString() {
            return x + "," + y;
        }
    }
    private void fillMap()  { //unit test for this
        grid = new ArrayList<>();
        for (int i = 0; i < _x * _y; i++)
            grid.add(new tile((i % _y) + 1, (i / _x)+ 1));
    }

    public ArrayList<tile> getGrid() {
        return grid;
    }

    //replace the following with a call to the drawing functions
    public void populate(Human fighter, ArrayList<Humanoid> populace) {
        panel.fillMap(grid);
        panel.place(fighter);
        for (Humanoid gabl : populace)
            panel.place(gabl);
    }
    public String toString()    {
        String out = "";
        for (tile t : grid) {
            if (t.x == 1 && t.y != 1)
                out += '\n';
            out += t.toString();
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