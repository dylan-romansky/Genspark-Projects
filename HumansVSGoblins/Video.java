import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Video implements KeyListener {
    enum directions { NONE, UP, DOWN, LEFT, RIGHT }
    JFrame screen;
    public directions direct = directions.NONE;
    Boolean quit = false;
    Video()  {
        screen = new JFrame("HvG");
        screen.addKeyListener(this);
        screen.setLayout(new GridBagLayout());
        screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        screen.setSize(200, 200);
        screen.pack();
    }
    private class MyGrid extends JPanel {
        final int Tsize = 48;
        final int col = 20;
        final int row = 20;
        final int dimX = col * Tsize;
        final int dimY = row * Tsize;

        MyGrid()    {
            setPreferredSize(new Dimension(dimX, dimY));
            setBackground(Color.black);
            setDoubleBuffered(true);
        }
        void update() {
        }
        void paintComponent(Graphics g, Humanoid h, Coordinates c) {
            super.paintComponent(g);
            Graphics2D me = (Graphics2D) g;
            switch (h)  {
                case Human hu -> g.setColor(Color.blue);
                case Goblin hu -> g.setColor(Color.red);
                default -> g.setColor(Color.green);
            }
            me.fillRect(c.getX() * Tsize, c.getY() * Tsize, dimX, dimY);
            me.dispose();
        }
    }
    void makeVis()  {
        screen.setVisible(true);
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
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                direct = directions.DOWN;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                direct = directions.LEFT;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                direct = directions.RIGHT;
            case KeyEvent.VK_ESCAPE:
                quit = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                direct = directions.NONE;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                direct = directions.NONE;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                direct = directions.NONE;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                direct = directions.NONE;
        }
    }
}
