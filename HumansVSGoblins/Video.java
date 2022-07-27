import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Video extends JPanel implements KeyListener {
    enum directions { NONE, UP, DOWN, LEFT, RIGHT }
    public directions direct = directions.NONE;
    int FPS = 60;
    ArrayList<Terrain.tile> grid;
    private boolean fighting = false;
    boolean click = false;
    boolean back = false;
    private boolean hold = false;
    public fightState fight;
    Video(int x, int y)  {
        setPreferredSize(new Dimension(x * 50, y * 50));
        setBackground(Color.black);
        setDoubleBuffered(true);
        addKeyListener(this);
        setFocusable(true);
        fight = new fightState(x * 50, (y * 50) / 2);
    }
    void setGrid(ArrayList<Terrain.tile> g) {
        grid = g;
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
                direct = directions.UP;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                direct = directions.DOWN;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                direct = directions.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                direct = directions.RIGHT;
                break;
            case KeyEvent.VK_ENTER:
                click = true;
                break;
            case KeyEvent.VK_ESCAPE:
                back = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                direct = directions.NONE;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                direct = directions.NONE;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                direct = directions.NONE;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                direct = directions.NONE;
                break;
            case KeyEvent.VK_ENTER:
                click = false;
                hold = false;
                break;
            case KeyEvent.VK_ESCAPE:
                back = false;
                hold = false;
                break;
        }
    }
    public String setup()   {
        //stubbed for now. will handle game menu before the game loop starts
        return "Greg";
    }
    public void draw()  {
        repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Terrain.tile t : grid)
            t.draw(g2);
        if (fighting)
            fight.draw(g2);
        g2.dispose();
    }
    private class fightState {
        ArrayList<Humanoid> combatants;
        Human   fighter;
        int width;
        int height;
        int state = 0;
        String[] menuOpts = {"Attack", "Inventory"};
        int selOpt = 0;
        int choGob = 0;
        int chosIt = 0;
        Loot lootPool;
        fightState(int x, int y)    {
            width = x;
            height = y;
            lootPool = new Loot(0);
        }
        public void draw(Graphics2D g2) {
            g2.setColor(Color.black);
            g2.fillRect(0, height, width, height);
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(5));
            g2.drawRect(0, height, width, height);
            g2.drawString(fighter.toString() + " " + fighter.getHealth(), 50, height + 50);
            g2.drawString("Attack", 50, height + 100);
            g2.drawString("Inventory", 50, height + 120);
            g2.drawLine(50, height + 102 + (selOpt * 20), getFontMetrics(getFont()).stringWidth(menuOpts[selOpt]), height + 102 + (selOpt * 20));
            int ypos = height + 50;
            int xpos = width - 150;
            for (Humanoid dude : combatants) {
                g2.drawString(dude.toString() + " " + dude.getHealth(), xpos, ypos);
                ypos += 50;
            }
            switch (state) {
                case 0:
                    break;
                case 1:
                    g2.drawLine(xpos, height + 52 + (50 * choGob), getFontMetrics(getFont()).stringWidth("Goblin"), height + 2 + (50 * choGob));
                    break;
                case 2:
                    drawInv(g2);
                    break;
            }
        }
        public void drawInv(Graphics2D g2){
            g2.setColor(Color.black);
            g2.fillRect(width/3, 25, width/3, height-25);
            g2.setColor(Color.white);
            g2.drawRect(width/3, 25, width/3, height-25);
            int x = (width / 3) + 25;
            int y = 50;
            String[] set = fighter.getInv().conString();
            for (int i = 0; i < set.length; i ++) {
                g2.drawString(set[i], x, y);
                y += 20;
            }
            g2.drawString("close", x, y);
            g2.drawLine(x, 52 + (20 * chosIt), getFontMetrics(getFont()).stringWidth(chosIt < set.length ? set[chosIt] : "close"), 52 + (20 * chosIt));
        }
        public void addCombatants(Human hu, ArrayList<Humanoid> co) {
            fighter = hu;
            combatants = co;
        }
    }
    public void addCombatants(Human hu, ArrayList<Humanoid> co) {
        fight.addCombatants(hu, co);
    }
    public void updateFight()   {
        switch (fight.state) {
            case 0:
                menuControl();
                break;
            case 1:
                chooseTarget();
                break;
            case 2:
                chooseItem();
                break;
        }
    }

    private void menuControl()  {
        System.out.println(click + " && " + !hold);
        if (click && !hold) {
            fight.state += 1 + fight.selOpt;
            hold = true;
        }
        switch (direct) {
            case UP:
            case RIGHT:
                fight.selOpt -= 1;
                if (fight.selOpt < 0)
                    fight.selOpt = fight.menuOpts.length - 1;
                break;
            case DOWN:
            case LEFT:
                fight.selOpt += 1;
                if (fight.selOpt >= fight.menuOpts.length)
                    fight.selOpt = 0;
                break;
        }
    }
    public void chooseTarget()  {
        System.out.println(click + " && " + !hold);
        if (click && !hold) {
            fight.state = 0;
            Humanoid targ = fight.combatants.get(fight.choGob);
            fight.fighter.attack(targ);
            if (targ.getHealth() <= 0) {
                fight.lootPool.addItems(((Goblin) targ).drops());
                fight.combatants.remove(targ);
            }
            for (Humanoid combatant : fight.combatants) {
                combatant.attack(fight.fighter);
            }
            hold = true;
        }
        switch (direct) {
            case UP:
            case RIGHT:
                fight.choGob -= 1;
                if (fight.choGob < 0)
                    fight.choGob = fight.combatants.size() - 1;
                break;
            case DOWN:
            case LEFT:
                fight.choGob += 1;
                if (fight.choGob >= fight.combatants.size())
                    fight.choGob = 0;
                break;
        }
    }
    public void chooseItem()    {
        System.out.println(click + " && " + !hold);
        if (click && !hold) {
            fight.state = 0;
            if (fight.chosIt < fight.fighter.getInv().getItems().size())
                fight.fighter.useConsumable(fight.chosIt);
            hold = true;
        }
        switch (direct) {
            case UP:
            case RIGHT:
                fight.chosIt -= 1;
                if (fight.chosIt < 0)
                    fight.chosIt = fight.fighter.getInv().getConSize();
                break;
            case DOWN:
            case LEFT:
                fight.chosIt += 1;
                if (fight.chosIt > fight.fighter.getInv().getConSize())
                    fight.chosIt = 0;
                break;
        }
    }
    public void fightToggle()    {
        fighting = !fighting;
    }
    public Loot lootPool()  {
        return fight.lootPool;
    }
    public void clearLoot() {
        fight.lootPool.emptyPool();
    }
}
