package view;

import manage.GameState;
import manage.StateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverState extends GameState {
    private Background bg;
    private Font font;
    public  int score1, score2;
    private int currentState = 0;
    private String[] variants = {
            "Revenge",
            "Menu"
    };


    public GameOverState(StateManager sManager) {

        this.sManager = sManager;
        try {
            bg = new Background("/gameover.png");
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

    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setFont(font);

        for(int i = 0; i < variants.length; i++) {
            if(i == currentState) {
                g.setColor(Color.PINK);
            }
            else {
                g.setColor(Color.WHITE);
            }
            g.drawString(variants[i], 240, 330 + i * 45);
        }
    }


    private void select() {
        if(currentState == 0) {
            sManager.setState(StateManager.playWithBotState);
        }
        if(currentState == 1) {
            sManager.setState(StateManager.menuState);
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









