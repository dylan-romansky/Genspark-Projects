import javax.swing.*;

public class Main   {
    public static void main(String[] args)  {
        StateController SC = new StateController(20, 20);
        JFrame screen = new JFrame();
        screen = new JFrame("HvG");
        screen.add(SC.panel);
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.pack();
        screen.setVisible(true);
    }
}