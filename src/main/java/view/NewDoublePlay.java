package view;

import manage.*;
import model.PlayerModel;
import network.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
public class NewDoublePlay extends GameState {

    private GameServer gameController;
    private Background bg;
    private int currentState = 0;

    public NewDoublePlay(){
        this.sManager = sManager;
        try {

            bg = new Background("/newgame.png");

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        sManager.setState(StateManager.playGameState);
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
    }

    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_ESCAPE){
            sManager.setState(StateManager.pauseState);
        }

    }

    @Override
    public void keyReleased(int k) {

    }
    
}
