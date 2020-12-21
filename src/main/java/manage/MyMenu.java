package manage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class MyMenu extends JPanel implements Runnable, KeyListener{
    public static final int widthWindow = 600;
    public static final int heightWindow = 700;

    private Graphics2D g;

    private BufferedImage image;
    private Thread thread;
    private boolean running;


    public MyMenu(){
        super();
        setSize(new Dimension(widthWindow, heightWindow));
        setFocusable(true);
        requestFocus();
    }

    private void loadImage() {
        image = new BufferedImage(widthWindow, widthWindow, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        running = true;

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void drawToScreen(){
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, null);
    }

    public void run() {
        loadImage();
        while(running){
            drawToScreen();
        }
    }
}
