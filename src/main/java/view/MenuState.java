package view;

import manage.StateManager;

import java.awt.*;

public class MenuState extends GameState{

    private Font font;

    private int currentState = 0;
    private final String[] options = {"play", "settings", "info", "exit"};

    public MenuState(StateManager sManager) {
        this.sManager = sManager;

        try {
            font = new Font("Arial", Font.PLAIN, 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void init(int flag) {

    }

    @Override
    public void init(int flag, String file) {

    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void update() {

    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {

    }
}
