package view;
import manage.StateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PauseState extends GameState  {
    private Background bg;
    private Font font;
    private int currentState = 0;

    private String[] variants = {
            "Resume",
            "Finish Game",
            "Info",

    };
    public PauseState(StateManager sManager) {

        this.sManager = sManager;
        try {
            bg = new Background("/pause.png");
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

        for(int i = 0; i < variants.length; i++) {
            if(i == currentState) {
                g.setColor(Color.BLUE);
            }
            else {
                g.setColor(Color.WHITE);
            }
            g.drawString(variants[i], 220, 280 + i * 45);
        }
    }

    private void select() {
        if(currentState == 0) {
            sManager.setState(StateManager.playWithBotState);
        }
        if(currentState == 2) {
            sManager.setState(StateManager.menuState);
        }
        if(currentState == 3) {
            this.sManager.setState(StateManager.infoState);
        }
    }

    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_ENTER){
            select();
        }
        if(key == KeyEvent.VK_UP) {
            currentState--;
            if(currentState == -1) {
                currentState = variants.length - 1;
            }
        }
        if(key == KeyEvent.VK_DOWN) {
            currentState++;
            if(currentState == variants.length) {
                currentState = 0;
            }
        }
    }

    @Override
    public void keyReleased(int k) {

    }
}
