import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame menuWindow = new JFrame("Funny Tennis");
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuWindow.setResizable(false);
        menuWindow.setContentPane(new Menu());
        menuWindow.pack();
        menuWindow.setVisible(true);


    }
}
