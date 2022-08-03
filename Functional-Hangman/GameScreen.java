import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameScreen extends JPanel implements ActionListener   {
    private BufferedImage _hanger;
    private BufferedImage _background;
    private BufferedImage[] _dude = new BufferedImage[4];
    private FunctionalHangman mang;
    private int width;
    private int height;
    private int state = 0;
    public JFormattedTextField text;
    public JTextField name;
    private final String[] artPaths = {"resources/hed.png", "resources/bod.png", "resources/Legarm.png", "resources/legaRm.png"};

    GameScreen(int x, int y) {
        setLayout(null);
        setPreferredSize(new Dimension(x, y));
        setBackground(Color.black);
        setDoubleBuffered(true);
        setFocusable(true);
        width = x;
        height = y;
        try {
            _hanger = ImageIO.read(new File("resources/hanger.png"));
            _background = ImageIO.read(new File("resources/bg.png"));
            //normally I would use a for loop but under the conditions of this challenge I can only use Stream methods to loop
            //I'll check if this is possible to do with a Stream after I get a working prototype
            _dude[0] = ImageIO.read(new File(artPaths[0]));
            _dude[1] = ImageIO.read(new File(artPaths[1]));
            _dude[2] = ImageIO.read(new File(artPaths[2]));
            _dude[3] = ImageIO.read(new File(artPaths[3]));
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
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Times New Roman", Font.BOLD, 60));
        g2.drawImage(_background, 0, 0, width, height, null);
        switch (state) {
            case 0 -> drawNameEntry(g2);
            case 1 -> drawGame(g2);
        }
    }
    public void drawNameEntry(Graphics2D g2)    {
        g2.drawString("Please enter your name (12 character limit)", width/2-100, 100);
    }
    public void drawGame(Graphics2D g2)  {
        g2.drawImage(_hanger, 0, 0, width, height, null);
        switch (mang.getLimbscount())   {
            case 6:
                g2.drawImage(_dude[3], width/2-7, height/4+107, 100, 100, null);
            case 5:
                g2.drawImage(_dude[2], width/2-72, height/4+105, 100, 100, null);
            case 4:
                g2.drawImage(_dude[3], width/2-7, height/4+25, 100, 100, null);
            case 3:
                g2.drawImage(_dude[2], width/2-72, height/4+25, 100, 100, null);
            case 2:
                g2.drawImage(_dude[1], width/2-42, height/4+25, 100, 100, null);
            case 1:
                g2.drawImage(_dude[0], width/2-42, height/4-50, 100, 100, null);
        }
        System.out.println(mang._getGuesses());
        g2.drawString(mang._getGuesses(), 20, height-27);
    }
    public Character getChar() {
        System.out.println(text.getValue());
        return (Character) text.getValue();
    }
    public void setTextfield(JFormattedTextField t)  {
        text = t;
    }
    public void setNameField(JTextField t)  {
        name = t;
    }
    public void setState(int state) {
        this.state = state;
        switch (state)  {
            case 0 ->   {
                name.setVisible(true);
                text.setVisible(false);
            }
            case 1 -> {
                name.setVisible(false);
                text.setVisible(true);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (state) {
            case 0 -> setState(1);
            case 1 -> mang._playGame(text.getText().toCharArray()[0]);
        }
    }
}