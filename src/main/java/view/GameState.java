package view;
import java.awt.*;
import manage.*;

public abstract class GameState {
    protected StateManager sManager;
    public abstract void init(int flag);
    public abstract void init(int flag, String file);
    public abstract void draw(Graphics2D g);
    public abstract void update();
    public abstract void keyPressed(int key);
    public abstract void keyReleased(int key);
}



