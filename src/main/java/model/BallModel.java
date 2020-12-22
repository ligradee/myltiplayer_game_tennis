package model;

import view.PlayerState;

import java.awt.*;
import java.util.Random;

public class BallModel {

    private int score1;
    private int score2;
    private boolean flag = false;
    private int speed = 3;
    private int x, y, width = 25, height = 25;
    private int speedX, speedY;
    private Rectangle hitBox;
    private final Random random;

    private  int amountOfHits;

    public BallModel(int x, int y)
    {
        this.y = y-20;
        this.x = x+40;

        hitBox = new Rectangle(this.x, this.y, width, height);
        this.random = new Random();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setFlag() {
        flag = true;
    }

    public void set() {
        if (flag = false){
            speedX = 0;
            speedY = 0;
        }
    }

}
