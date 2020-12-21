package view;
import manage.StateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class InfoState extends GameState {

    private Background bg;
    private Font font;
    private int currentState = 0;

    public InfoState(StateManager sManager) {
        this.sManager = sManager;

        try {
            bg = new Background("/info.png");
            font = new Font("Uber Move", Font.BOLD , 30);

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
        bg.draw(g);
        g.setFont(font);

        g.setColor(Color.BLACK);
        g.drawString("Back", 260, 590);
    }

    @Override
    public void keyPressed(int k) {
        sManager.setState(StateManager.menuState);
    }

    @Override
    public void keyReleased(int k) {

    }
}
