package view;
import manage.StateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PauseState extends GameState  {
    private Background bg;

    public PauseState() {

        try {
            bg = new Background("/pause.png");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }
}
