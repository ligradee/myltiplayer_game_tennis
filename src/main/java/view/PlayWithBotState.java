package view;
import manage.StateManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;

public class PlayWithBotState extends GameState  {
    private Background bg;
    private PlayerState person;
    private Bot bot;

    private int x1, y1;
    private int score = 0;


    public PlayWithBotState(StateManager sManager) {
        this.sManager = sManager;
        person = new PlayerState(1);
        bot = new Bot();
        x1 = person.getModel().getX();
        y1 = person.getModel().getY();

        try {
            bg = new Background("/court.png");
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
        person.getModel().set();
        bot.getModel().set();
    }


    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        person.draw(g);
        bot.draw(g);
    }

    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_ENTER){

        }
        if(key == KeyEvent.VK_RIGHT) {
            person.getModel().xSpeed = 2;
            bot.getModel().xSpeed = -2;
        }

        if(key == KeyEvent.VK_LEFT) {
            person.getModel().xSpeed = -2;
            bot.getModel().xSpeed = 2;
        }
    }


    @Override
    public void keyReleased(int key) {
        if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            person.getModel().xSpeed = 0;
            bot.getModel().xSpeed = 0;
        }
    }
}
