import javax.swing.*;

public class Main   {
    public static void main(String[] args)  {
        JFrame screen = new JFrame("HvG");
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StateController SC = new StateController(10, 10);
        screen.add(SC.panel);
        screen.pack();
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);
        SC.gameLoop();
        screen.dispose();
    }
}