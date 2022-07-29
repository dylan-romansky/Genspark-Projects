import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameScreen extends JPanel {
    private BufferedImage _hanger;
    private BufferedImage _background;
    private BufferedImage[] _dude = new BufferedImage[6];
    private FunctionalHangman mang;
    private int width;
    private int height;
    private final String[] artPaths = {"resources/hed.png", "resources/bod.png", "resources/Legarm.png", "resources/legaRm.png"};

    GameScreen(int x, int y) {
        width = x;
        height = y;
        try {
            _hanger = ImageIO.read(new File("resources/hanger.png"));
            _background = ImageIO.read(new File("resources/bg.png"));
            //normally I would use a for loop but under the conditions of this challenge I can only use Stream methods to loop
            //I doubt it, but I'll check if this is possible to do with a Stream after I get a working prototype
            _dude[0] = ImageIO.read(new File(artPaths[0]));
            _dude[1] = ImageIO.read(new File(artPaths[1]));
            _dude[2] = ImageIO.read(new File(artPaths[2]));
            _dude[3] = ImageIO.read(new File(artPaths[3]));
            _dude[4] = ImageIO.read(new File(artPaths[4]));
            _dude[5] = ImageIO.read(new File(artPaths[5]));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addHangman(FunctionalHangman hang)  {
        mang = hang;
    }
    public void draw()  {
        repaint();
    }
    public void paintComponent(Graphics g)  {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(_background, 0, 0, width, height, null);
        //the following x and y values are placeholders till I've drawn up my assets
        g2.drawImage(_hanger, startX, startY, endX, endY, null);
        switch (mang.getLimbscount())   {
            case 6:
                g2.drawImage(_dude[5], startX, startY, endX, endY, null);
            case 5:
                g2.drawImage(_dude[4], startX, startY, endX, endY, null);
            case 4:
                g2.drawImage(_dude[3], startX, startY, endX, endY, null);
            case 3
                g2.drawImage(_dude[2], startX, startY, endX, endY, null);
            case 2:
                g2.drawImage(_dude[1], startX, startY, endX, endY, null);
            case 1:
                g2.drawImage(_dude[0], startX, startY, endX, endY, null);
        }
        g2.drawString(mang._getGuesses(), startX, startY);
    }
}