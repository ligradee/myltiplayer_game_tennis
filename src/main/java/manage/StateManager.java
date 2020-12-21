package manage;
import  view.*;

import java.util.ArrayList;

public class StateManager {
    private final ArrayList<GameState> gameStates;
    private int currentState;

    public static final int menuState = 0;
    public static final int playState = 1;
    public static final int infoState = 2;
    public static final int chooseState = 3;

    public StateManager() {
        gameStates = new ArrayList<GameState>();
        currentState = menuState;
        java.awt.Graphics2D  g = null;
        gameStates.add(new MyMenu(this));
        gameStates.add(new PlayState(this));
        gameStates.add(new InfoState(this));

    }
    public void setState(int s) {
        currentState = s;
        gameStates.get(currentState).init();
    }

    public void update() {
        gameStates.get(currentState).update();
    }

    public void draw(java.awt.Graphics2D g) {
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int key) { gameStates.get(currentState).keyPressed(key); }

    public void keyReleased(int key) {
        gameStates.get(currentState).keyReleased(key);
    }

}
