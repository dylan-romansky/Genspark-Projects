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
    Video(int x, int y)  {
        setPreferredSize(new Dimension(x * 50, y * 50));
        setBackground(Color.black);
        setDoubleBuffered(true);
        addKeyListener(this);
        setFocusable(true);
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
        g2.dispose();
    }
}
