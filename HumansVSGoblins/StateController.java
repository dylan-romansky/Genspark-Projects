import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class StateController implements Runnable {
    public static Random _rng = new Random(System.currentTimeMillis());
    public Terrain terra;
    public Video panel;
    private ArrayList<Humanoid> hominids = new ArrayList<>(3);
    private Human fighter;
    private Boolean fight = false;
    Thread playing;
    private double refreshRate = 1000000000/60; //60 fps
    StateController()   {
        int xbound = 10;
        int ybound = 10;
        fighter = new Human(new Coordinates(xbound/2, ybound/2), "greg");
        for (int i = 1; i <= 2; i++) {
            int x = xbound/2;
            int y = ybound/2;
            while (x == xbound/2 && y == ybound/2)  {
                x = _rng.nextInt(0, xbound);
                y = _rng.nextInt(0, ybound);
            }
            hominids.add(new Goblin(new Coordinates(x, y)));
        }
        panel = new Video(xbound, ybound);
        terra = new Terrain(xbound, ybound, panel);
    }
    StateController(int x, int y)   {
        panel = new Video(x, y);
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
        terra.update(fighter, hominids);
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
    public void printMap(String map)    { //only useful in terminal
        System.out.println("\033[H\033[2J");
        System.out.flush();
        System.out.println(map);
    }
    public void fightloop() {
        return;
    }
    public void start() {
        playing = new Thread(this);
        playing.start();
    }
    @Override
    public void run()  {
        while (playing != null) {
            double nextUpdate = System.nanoTime() + refreshRate;
            switch (panel.direct)  {
                case UP:
                    fighter.updateCoords('w', terra.getX(), terra.getY());
                    break;
                case DOWN:
                    fighter.updateCoords('s', terra.getX(), terra.getY());
                    break;
                case LEFT:
                    fighter.updateCoords('a', terra.getX(), terra.getY());
                    break;
                case RIGHT:
                    fighter.updateCoords('d', terra.getX(), terra.getY());
                    break;
            }
            for (Humanoid dude : hominids)
                dude.updateCoords(terra.getX(), terra.getY());
            terra.update(fighter, hominids);
            terra.draw();
            try {
             Thread.sleep((long) (nextUpdate - System.nanoTime()) / 1000000);  }
            catch (Exception e) {}
        }
        return;
    }
}