import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class StateController implements KeyListener {
    public static Random _rng = new Random(System.currentTimeMillis());
    Terrain terra;
    ArrayList<Humanoid> hominids = new ArrayList<>(3);
    Human fighter;
    private Boolean fight = false;
    StateController()   {
        terra = new Terrain(20, 20);
        fighter = new Human(new Coordinates(10, 10), "greg");
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
        fighter = new Human(new Coordinates(x/2, y/2), name);
        for (int i = 1; i <= 2; i++)    {
            int _x = x/2;
            int _y = y/2;
            while (_x == x/2 && _y == y/2)    {
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
        Coordinates checkee = fighter.getCoords();
        for (int i = 1; i < hominids.size(); i++)   {
            Humanoid guy = hominids.get(i);
            Coordinates checker = guy.getCoords();
            if (checker.getX() == checkee.getX() && checker.getY() == checkee.getY())
                gottem.add(guy);
        }
        return gottem;
    }
    public Boolean healthCheck()   {
        ArrayList<Humanoid> filtered = hominids.stream().filter(x -> x.getHealth() > 0).collect(Collectors.toCollection(ArrayList<Humanoid>::new));
        if (fighter.getHealth() == 0)
            return false;
        return true;
    }
    public Terrain getTerra()   {
        return terra;
    }
    public ArrayList<Humanoid> getPopulous()  {
        return hominids;
    }
    public void printMap(String map)    {
        System.out.println("\033[H\033[2J");
        System.out.flush();
        System.out.println(map);
    }
    public void fightloop() {
        return;
    }
    public void gameloop()  {
        return;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                fighter.updateCoords('w', terra.getX(), terra.getY());
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                fighter.updateCoords('s', terra.getX(), terra.getY());
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                fighter.updateCoords('a', terra.getX(), terra.getY());
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                fighter.updateCoords('d', terra.getX(), terra.getY());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}