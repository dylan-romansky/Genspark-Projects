import javax.swing.*;
import java.awt.*;

public class Video {
    JFrame screen;
    Video()  {
        screen = new JFrame("HvG");
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
}
