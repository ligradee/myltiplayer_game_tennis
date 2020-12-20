import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class Menu extends JPanel implements Runnable, KeyListener{
    public static final int widthWindow = 1200;
    public static final int heightWindow = 1600;

    private Graphics2D g;

    private BufferedImage image;
    private Thread thread;
    private boolean running;


    public Menu(){
        super();
        setSize(new Dimension(widthWindow, heightWindow));
        setFocusable(true);
        requestFocus();
    }


    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void run() {

    }
}
