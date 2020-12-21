package manage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel
        implements Runnable, KeyListener {

    public static final int width = 600;
    public static final int height = 700;

    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    private BufferedImage image;
    private Graphics2D g;

    private StateManager sManager;

    public GamePanel() {
        super();
        setPreferredSize(
                new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    private void init() {

        image = new BufferedImage(
                width, height,
                BufferedImage.TYPE_INT_RGB
        );
        g = (Graphics2D) image.getGraphics();

        running = true;

        sManager = new StateManager();

    }

    public void run() {
        init();

        long start;
        long elapsed;
        long wait;

        while(running) {

            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed / 1000000;
            if(wait < 0) wait = 5;

            try {
                Thread.sleep(wait);
            }
            catch(Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void update() {
        sManager.update();
    }

    private void draw() {
        sManager.draw(g);
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0,
                width, height, null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent key) {}

    public void keyPressed(KeyEvent key) {
        sManager.keyPressed(key.getKeyCode());
    }

    public void keyReleased(KeyEvent key) {
        sManager.keyReleased(key.getKeyCode());
    }

}
