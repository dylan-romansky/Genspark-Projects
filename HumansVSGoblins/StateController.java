import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class StateController {
    public static Random _rng = new Random(System.currentTimeMillis());
    public Terrain terra;
    public Video panel;
    private ArrayList<Humanoid> hominids = new ArrayList<>(3);
    private Human fighter;
    private Boolean fight = false;
    private boolean playing = true;
    private double refreshRate = 1000000000/60; //60 fps
    StateController()   {
        int xbound = 10;
        int ybound = 10;
        fighter = new Human(new Coordinates(xbound/2, ybound/2), "Greg");
        panel.setFighter(fighter);
        for (int i = 0; i <= 2; i++) {
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
        panel.setFighter(fighter);
        for (int i = 0; i <= 2; i++)    {
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
        for (int i = 0; i < hominids.size(); i++)   {
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
    public void fightloop(ArrayList<Humanoid> combatants) {
        if (combatants.size() == 0)
            return;
        Video.directions last = Video.directions.NONE;
        terra.update(fighter, hominids);
        terra.draw();
        panel.fightToggle();
        panel.addCombatants(fighter, combatants);
        hominids.removeAll(combatants);
        while (combatants.size() > 0) {
            double nextUpdate = System.nanoTime() + refreshRate;
            if ((last != panel.direct && panel.direct != Video.directions.NONE) || panel.click == true || panel.back == true)
                panel.updateFight();
            last = panel.direct;
            panel.draw();
            if (fighter.getHealth() <= 0) {
                panel.death();
                playing = false;
                return;
            }
            try {
                long wait = (long) (nextUpdate - System.nanoTime()) / 1000000;
                Thread.sleep(wait < 0 ? 0 : wait);  }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        fighter.addLoot(panel.lootPool());
        panel.clearLoot();
        panel.fightToggle();
    }
    public void gameLoop()  {
        Video.directions last = Video.directions.NONE;
        while (playing) {
            double nextUpdate = System.nanoTime() + refreshRate;
            if (panel.click && !panel.hold)
                panel.menuing = true;
            else if (!panel.menuing)    {
                if (panel.direct != Video.directions.NONE && panel.direct != last) { //kinda hacky but it keeps the game responsive while also staying turn based
                    switch (panel.direct) {
                        case UP -> fighter.updateCoords('w', terra.getX(), terra.getY());
                        case DOWN -> fighter.updateCoords('s', terra.getX(), terra.getY());
                        case LEFT -> fighter.updateCoords('a', terra.getX(), terra.getY());
                        case RIGHT -> fighter.updateCoords('d', terra.getX(), terra.getY());
                    }
                    for (Humanoid dude : hominids)
                        dude.updateCoords(terra.getX(), terra.getY());
                    fightloop(fightCheck());
                }
                if (_rng.nextInt() % 10 == 0 && hominids.size() < 5) {
                    Coordinates playerPos = fighter.getCoords();
                    int x = playerPos.getX() / 2;
                    int y = playerPos.getY() / 2;
                    while (x == playerPos.getX() / 2 && y == playerPos.getY() / 2) {
                        x = _rng.nextInt(0, terra.getX());
                        y = _rng.nextInt(0, terra.getY());
                    }
                    hominids.add(new Goblin(new Coordinates(x, y)));
                }
                last = panel.direct;
                terra.update(fighter, hominids);
            }
            else if (panel.direct != last || panel.click || panel.back) {
                panel.mainMenuControl();
                last = panel.direct;
            }
            terra.draw();
            try {
                long wait = (long) (nextUpdate - System.nanoTime()) / 1000000;
                Thread.sleep(wait < 0 ? 0 : wait);  }
            catch (Exception e) {
                e.printStackTrace();
            }
            if (!playing) {
                replayCheck();
                System.out.println(playing);
            }
        }
    }
    private void replayCheck()  {
        System.out.println("so we here then?");
        System.out.println(panel.click + " && " + panel.back);
        while (panel.hold)
            System.out.println("this sucks");
        while (!panel.click && !panel.back)
            System.out.println("waiting");
        playing = panel.click;
        if (!playing)
            return;
        Coordinates fightcoord = fighter.getCoords();
        int x = terra.getX();
        int y = terra.getY();;
        fightcoord.set(x/2, y/2);
        fighter.setHealth(100);
        hominids.removeAll(hominids);
        for (int i = 0; i <= 2; i++)    {
            int _x = x/2;
            int _y = y/2;
            while (_x == x/2 && _y == y/2)    {
                _x = _rng.nextInt(0, x);
                _y = _rng.nextInt(0, y);
            }
            hominids.add(new Goblin(new Coordinates(_x, _y)));
        }
        panel.reset();
    }
}