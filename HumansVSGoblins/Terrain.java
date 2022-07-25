import java.awt.*;
import java.util.ArrayList;
//DEPRECATED: please convert to graphical printing
public class Terrain {
    private final int _y;
    private final int _x;
    private ArrayList<tile> grid;
    private Video panel;
    Terrain()   { //make a sanity check so the map has to be larger than some amount
        _y = 10;
        _x = 10;
        panel = new Video(_x, _y);
        fillMap();
    }
    Terrain(int x, int y, Video pane)   {
        _y = y;
        _x = x;
        panel = pane;
        fillMap();
//        fightMenu();
    }
    public class tile {
        final int x;
        final int y;
        final int size = 48;
        Humanoid h;
        Video panel;
        tile(int col, int row, Video pane)  {
            x = col;
            y = row;
            panel = pane;
        }
        @Override
        public String toString() {
            return x + "," + y;
        }
        public void draw(Graphics2D g2)  {
            switch (h)  {
                case null -> g2.setColor(Color.green);
                case Human ignored -> g2.setColor(Color.blue);
                case Goblin ignored -> g2.setColor(Color.red);
                case default -> g2.setColor(Color.black);
            }
            g2.fillRect((x * 50) + 1, (y * 50) + 1, 48, 48);
        }
        public void update(Humanoid hu) {
            h = hu;
        }
    }
    private void fillMap()  { //unit test for this
        grid = new ArrayList<>();
        for (int i = 0; i < _x * _y; i++)
            grid.add(new tile((i % _y), (i / _x), panel));
        panel.setGrid(grid);
    }

    public ArrayList<tile> getGrid() {
        return grid;
    }
    public void update(Human fighter, ArrayList<Humanoid> hominids)  {
        for (tile t : grid) {
            t.update(null);
        }
        Coordinates coord = fighter.getCoords();
        grid.get((coord.getY() * _x) + coord.getX()).update(fighter);
        for (Humanoid hominid : hominids)   {
            coord = hominid.getCoords();
            grid.get((coord.getY() * _x) + coord.getX()).update(hominid);
        }
    }
    public void draw()  {
        panel.draw();
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