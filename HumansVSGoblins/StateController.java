import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class StateController {
    public static Random _rng = new Random(System.currentTimeMillis());
    Terrain terra;
    Video panel;
    ArrayList<Humanoid> hominids = new ArrayList<>(3);
    Human fighter;
    private Boolean fight = false;
    private boolean playing = true;
    StateController()   {
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
        panel = new Video();
        terra = new Terrain(20, 20, panel);
    }
    StateController(int x, int y)   {
        panel = new Video();
        terra = new Terrain(x, y, panel);
        fighter = new Human(new Coordinates(x/2, y/2), panel.setup());
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
        terra.populate(fighter, hominids);
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
        while (playing == true) {
            switch (panel.direct)  {
                case UP:
                    fighter.updateCoords('w', terra.getX(), terra.getY());
                case DOWN:
                    fighter.updateCoords('s', terra.getX(), terra.getY());
                case LEFT:
                    fighter.updateCoords('a', terra.getX(), terra.getY());
                case RIGHT:
                    fighter.updateCoords('d', terra.getX(), terra.getY());
            }
            for (Humanoid dude : hominids)
                dude.updateCoords(terra.getX(), terra.getY());
        }
        return;
    }
}