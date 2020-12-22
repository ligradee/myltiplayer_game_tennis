package model;

import view.PlayerState;

import java.awt.*;
import java.util.Random;

public class BallModel {

    private int score1;
    private int score2;
    public boolean flag = false;
    private int speed = 3;
    private int x, y, width = 25, height = 25;
    public int xSpeed, ySpeed;
    public Rectangle hitBox;
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

    public void set(int x1, int y1) {
        if ((xSpeed > 0) & (x < 500)) {
            x += xSpeed;
            hitBox.x += xSpeed;
        }
        if ((xSpeed < 0) & (x > 24)) {
            x += xSpeed;
            hitBox.x += xSpeed;
        }
        if (flag == true) {
            y += ySpeed;
            hitBox.y += ySpeed;
            if ((y > 555) || (y < 0)){
                flag = false;
                y = y1 - 20;
                x = x1 + 40;
                hitBox.x = x1 + 40;
                hitBox.y = y1 - 20;
                ySpeed = 0;
                xSpeed = 0;
            }
        }
    }

}
