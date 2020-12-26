package manage;
import java.awt.*;
import java.io.IOException;

import manage.*;
import network.ServerSocket;

public abstract class GameState {
    protected StateManager sManager;
    public abstract void init();
    public abstract void update() throws IOException;
    public abstract void draw(java.awt.Graphics2D g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);

}



