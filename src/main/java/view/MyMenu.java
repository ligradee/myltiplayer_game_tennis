package view;
import manage.GameState;
import manage.StateManager;

import java.awt.*;
import java.awt.event.*;


public class MyMenu extends GameState {

    private Background bg;
    private Font font;
    private int currentState = 0;

    private String[] variants = {
            "Play",
            "Settings",
            "Info",
            "Exit"
    };

    public MyMenu(StateManager sManager) {
        this.sManager = sManager;

        try {

            bg = new Background("/menu.png");
            font = new Font("Uber Move", Font.BOLD , 30);

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }


    public void init() {}

    public void update() {
    }



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
            g.drawString(variants[i], 260, 280 + i * 45);
        }

    }

    private void select() {
        if(currentState == 0) {
            this.sManager.setState(StateManager.playState);
        }
        if(currentState == 1) {

        }
        if(currentState == 2) {
            this.sManager.setState(StateManager.infoState);
        }
        if(currentState == 3) {
            System.exit(0);
        }
    }

    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ENTER){
            select();
        }
        if(k == KeyEvent.VK_UP) {
            currentState--;
            if(currentState == -1) {
                currentState = variants.length - 1;
            }
        }
        if(k == KeyEvent.VK_DOWN) {
            currentState++;
            if(currentState == variants.length) {
                currentState = 0;
            }
        }
    }
    public void keyReleased(int k) {}

}