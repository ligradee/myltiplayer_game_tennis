package manage;

import javax.swing.*;
import java.awt.*;
import java.awt.Menu;


public class Main {
    static GraphicsConfiguration gc;

    public static void main(String[] args) {
        JFrame menuWindow = new JFrame("Funny Tennis");
        menuWindow.setContentPane(new MyMenu());
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuWindow.setResizable(false);
        menuWindow.pack();
        menuWindow.setSize(600,700);
        menuWindow.setVisible(true);
    }
}
