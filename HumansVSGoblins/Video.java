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
    public fightState fight;
    private int state = 0;
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
        String[] menuOpts = {"Attack", "Inventory"};
        int selOpt = 0;
        int choGob = 0;
        fightState(int x, int y)    {
            width = x;
            height = y;
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
        switch (state) {
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
        switch (direct) {
            case UP:
            case RIGHT:
                fight.selOpt += 1;
                if (fight.selOpt >= fight.menuOpts.length)
                    fight.selOpt = 0;
                break;
            case DOWN:
            case LEFT:
                fight.selOpt -= 1;
                if (fight.selOpt < 0)
                    fight.selOpt = fight.menuOpts.length - 1;
                break;
        }
    }
    public void fightToggle()    {
        fighting = !fighting;
    }
    public void death() {
        System.exit(0);
    }
}
