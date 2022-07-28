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
    boolean hold = false;
    public fightState fight;
    private boolean dead = false;
    public menuState menu;
    boolean menuing = false;
    Video(int x, int y)  {
        setPreferredSize(new Dimension(x * 50, y * 50));
        setBackground(Color.black);
        setDoubleBuffered(true);
        addKeyListener(this);
        setFocusable(true);
        fight = new fightState(x * 50, (y * 50) / 2);
        menu = new menuState(x * 50, y * 50);
    }
    void setGrid(ArrayList<Terrain.tile> g) {
        grid = g;
    }
    void setFighter(Human fig)  {
        menu.fighter = fig;
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
    public void death() {
        dead = true;
        fightToggle();
    }
    public void reset() {
        dead = false;
        click = false;
        back = false;
        hold = false;
        direct = directions.NONE;
    }
    public void death(Graphics2D g2)    {
        g2.setColor(Color.black);
        g2.fillRect(50, 50, fight.width - 100, fight.height - 50);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(50, 50, fight.width - 100, fight.height - 50);
        g2.drawString("Game Over", fight.width/2 - 5*6, fight.height/2);
        g2.drawString("Play Again?", fight.width/2 - 5*6, fight.height/2 + 15);
        g2.drawString("Enter: play      Esc: exit", fight.width/2 - 11*6, fight.height/2 + 40);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Terrain.tile t : grid)
            t.draw(g2);
        if (fighting)
            fight.draw(g2);
        if (menuing)
            menu.menstat(g2);
        if (dead)
            death(g2);
        g2.dispose();
    }
    private class fightState {
        ArrayList<Humanoid> combatants;
        Human   fighter;
        int width;
        int height;
        int state = 0;
        String[] menuOpts = {"Attack", "Inventory"};
        int selOpt = 1;
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
            g2.setStroke(new BasicStroke(1));
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
            g2.fillRect(width/3, 25, width/3, 2*height-50);
            g2.setColor(Color.white);
            g2.drawRect(width/3, 25, width/3, 2*height-50);
            int x = (width / 3) + 25;
            int y = 50;
            String[] set = fighter.getInv().conString();
            for (String s : set) {
                g2.drawString(s, x, y);
                y += 20;
            }
            g2.drawString("close", x, y);
            g2.setStroke(new BasicStroke(1));
            g2.drawLine(x, 52 + (20 * chosIt), getFontMetrics(getFont()).stringWidth(chosIt < set.length ? set[chosIt] : "close"), 52 + (20 * chosIt));
        }
        public void addCombatants(Human hu, ArrayList<Humanoid> co) {
            fighter = hu;
            combatants = co;
        }
    }
    private class menuState {
        String[] opts = { "Equipment", "Items", "Player", "close" };
        int width, height;
        int mainOpt = 0;
        int subOpt = 0;
        int state = 0;
        Human fighter;
        boolean inspect = false;
        int inspecting = 0;
        menuState(int x, int y) {
            width = x;
            height = y;
        }
        public void menstat(Graphics2D g2)  {
            switch(state)   {
                case 0 -> drawMainMenu(g2);
                case 1 -> drawEquipMenu(g2);
                case 2 -> drawItMenu(g2);
                case 3 -> drawPlayerStat(g2);
            }
        }
        public void drawMainMenu(Graphics2D g2) {
            int x = width/2;
            g2.setColor(Color.black);
            g2.fillRect(x, 0, x, height);
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(5));
            g2.drawRect(x, 0, x, height);
            int y = 15;
            x += 5;
            for (String opt : opts) {
                g2.drawString(opt, x, y);
                y += 15;
            }
            g2.setStroke(new BasicStroke(1));
            g2.drawLine(x,17 + (mainOpt * 15), getFontMetrics(getFont()).stringWidth(mainOpt < opts.length ? opts[mainOpt] : "close"), 17 + (mainOpt * 15));
        }
        public void drawEquipMenu(Graphics2D g2)   {
            int x = width/2;
            g2.setColor(Color.black);
            g2.fillRect(0, 0, width, height);
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(5));
            g2.drawRect(0, 0, x, height);
            g2.drawRect(x, 0, x, height);
            int y = 15;
            g2.drawString(fighter.toString(), 5, y);
            g2.drawString("HP: " + fighter.getHealth(), 5, y + 15);
            g2.drawString("Weapon: " + fighter.getWep() + ", +" + fighter.getWep().getMod() + " dmg", 5, y + 30);
            g2.drawString("Attack: " + fighter.getAttack(), 5, y + 45);
            g2.drawString("Dmg Total: " + (fighter.getAttack() + fighter.getWep().getMod()), 5, y + 60);
            for (Weapon wep : fighter.getInv().getWeapons())    {
                g2.drawString(wep.toString() + ", +" + wep.getMod() + " dmg", x + 5, y);
                y += 10;
            }
            g2.drawString("cancel", x + 5, y);
            g2.setStroke(new BasicStroke(1));
            g2.drawLine(x + 5,17 + (subOpt * 10), getFontMetrics(getFont()).stringWidth(mainOpt < opts.length ? opts[mainOpt] : "close"), 17 + (subOpt * 10));
            if (inspect)
                inspection(g2);
        }
        public void drawItMenu(Graphics2D g2)   {
            int x = width/2;
            g2.setColor(Color.black);
            g2.fillRect(0, 0, width, height);
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(5));
            g2.drawRect(0, 0, x, height);
            g2.drawRect(x, 0, x, height);
            int y = 15;
            g2.drawString(fighter.toString(), 5, y);
            g2.drawString("HP: " + fighter.getHealth(), 5, y + 15);
            g2.drawString("Weapon: " + fighter.getWep() + ", +" + fighter.getWep().getMod() + " dmg", 5, y + 30);
            g2.drawString("Attack: " + fighter.getAttack(), 5, y + 45);
            g2.drawString("Dmg Total: " + (fighter.getAttack() + fighter.getWep().getMod()), 5, y + 60);
            for (Consumable con : fighter.getInv().getItems())    {
                g2.drawString(con.toString() + ", +" + con.getMod() + " hp", x + 5, y);
                y += 10;
            }
            g2.drawString("cancel", x + 5, y);
            g2.setStroke(new BasicStroke(1));
            g2.drawLine(x + 5,17 + (subOpt * 10), getFontMetrics(getFont()).stringWidth(mainOpt < opts.length ? opts[mainOpt] : "close"), 17 + (subOpt * 10));
            if (inspect)
                inspection(g2);
        }
        public void drawPlayerStat(Graphics2D g2) {
            int x = width / 2;
            g2.setColor(Color.black);
            g2.fillRect(0, 0, width, height);
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(5));
            g2.drawRect(0, 0, width, height);
            int y = 15;
            g2.drawString(fighter.toString(), 5, y);
            g2.drawString("HP: " + fighter.getHealth(), 5, y + 15);
            g2.drawString("Weapon: " + fighter.getWep() + ", +" + fighter.getWep().getMod() + " dmg", 5, y + 30);
            g2.drawString("Attack: " + fighter.getAttack(), 5, y + 45);
            g2.drawString("Dmg Total: " + (fighter.getAttack() + fighter.getWep().getMod()), 5, y + 60);
        }
        public void inspection(Graphics2D g2)   {
            if (subOpt == (state == 1 ? fighter.getInv().getWeapons().size() : fighter.getInv().getItems().size()) || state == 3) {
                use();
                return;
            }
            int x = width/2 - 15;
            int y = height/2 - 15;
            g2.setColor(Color.black);
            g2.fillRect(x, y, 30, 30);
            g2.setColor(Color.white);
            g2.drawRect(x, y, 30, 30);
            g2.drawString("Use", x + 10, y + 10);
            g2.drawString("cancel", x + 5, y + 20);
            g2.setStroke(new BasicStroke(1));
            if (inspecting == 0)
                g2.drawLine(x + 10, y + 10, getFontMetrics(getFont()).stringWidth("Use"), y + 10);
            else
                g2.drawLine(x + 5, y + 20, getFontMetrics(getFont()).stringWidth("Cancel"), y + 20);
        }
        public void use()   {
            Inventory inv = fighter.getInv();
            int size = state == 1 ? inv.getWeapons().size() : inv.getItems().size();
            if (state == 3 || subOpt == size)
                state = 0;
            switch (menu.state) {
                case 1:
                    if (subOpt == inv.getWeapons().size()) {
                        menu.state = 0;
                        break;
                    }
                    inv.getWeapons().add(fighter.getWep());
                    fighter.setWeapon(inv.getWeapons().get(subOpt));
                    break;
                case 2:
                    if (subOpt == inv.getItems().size()) {
                        menu.state = 0;
                        break;
                    }
                    fighter.useConsumable(subOpt);
            }
            menu.inspect = false;
        }
    }
    public void addCombatants(Human hu, ArrayList<Humanoid> co) {
        fight.addCombatants(hu, co);
    }
    public void updateFight()   {
        switch (fight.state) {
            case 0 -> menuControl();
            case 1 -> chooseTarget();
            case 2 -> chooseItem();
        }
    }

    private void menuControl()  {
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
        if (click && !hold) {
            fight.state = 0;
            Humanoid targ = fight.combatants.get(fight.choGob);
            fight.fighter.attack(targ);
            if (targ.getHealth() <= 0) {
                fight.lootPool.addAll(((Goblin) targ).drops());
                fight.combatants.remove(targ);
            }
            for (Humanoid combatant : fight.combatants) {
                combatant.attack(fight.fighter);
            }
            hold = true;
        }
        if (back && !hold)  {
            fight.state = 0;
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
        if (click && !hold) {
            fight.state = 0;
            if (fight.chosIt < fight.fighter.getInv().getItems().size())
                fight.fighter.useConsumable(fight.chosIt);
            hold = true;
        }
        if (back && !hold)  {
            fight.state = 0;
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
    void mainMenuControl()  {
        if (click && !hold) {
            if (menu.inspect)
                menu.use();
            else if (menu.state != 0)
                menu.inspect = true;
            else if (menu.mainOpt == menu.opts.length - 1)
                menuing = false;
            else
                menu.state += 1 + menu.mainOpt;
            hold = true;
        }
        if (back && !hold)  {
            if (menu.inspect)
                menu.inspect = false;
            else if (menu.state != 0)
                menu.state = 0;
            else
                menuing = false;
            hold = true;
        }
        switch (direct) {
            case UP:
            case RIGHT:
                if (menu.state == 0)
                    menu.mainOpt -= menu.mainOpt == 0 ? 0 : 1;
                else if (menu.inspect)
                    menu.inspecting = menu.inspecting == 0 ? 1 : 0;
                else
                    menu.subOpt -= menu.subOpt == 0 ? 0 : 1;
                break;
            case DOWN:
            case LEFT:
                if (menu.state == 0)
                    menu.mainOpt += menu.mainOpt == menu.opts.length - 1 ? 0 : 1;
                else if (menu.inspect)
                    menu.inspecting = menu.inspecting == 0 ? 1 : 0;
                else {
                    int max;
                    max = switch (menu.state) {
                        case 1 -> menu.fighter.getInv().getWeapons().size();
                        case 2 -> menu.fighter.getInv().getItems().size();
                        case default -> 0;
                    };
                    menu.subOpt += menu.subOpt >= max ? 0 : 1;
                }
                break;
        }
    }
}
