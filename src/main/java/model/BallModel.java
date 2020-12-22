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
    public Rectangle hitBoxBall;

    private  int amountOfHits;

    public BallModel(int x, int y)
    {
        this.y = y-20;
        this.x = x+40;

        hitBoxBall = new Rectangle(this.x, this.y, width, height);
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
            hitBoxBall.x += xSpeed;

        }
        if ((x == 499) || (x < 25)){
            x = x - xSpeed;
            System.out.println(x);
            hitBoxBall.x = x;
        }
        if ((xSpeed < 0) & (x > 24)) {
            x += xSpeed;
            hitBoxBall.x += xSpeed;
            if (x == 24){
                x -= xSpeed;
                hitBoxBall.x -= xSpeed;
            }
        }
        if (flag == true) {

            if ((y > 555) || (y < 0)){
                flag = false;
                y = y1 - 20;
                x = x1 + 40;
                hitBoxBall.x = x;
                hitBoxBall.y = y;
                ySpeed = 0;
                xSpeed = 0;
            }
            y += ySpeed;
            hitBoxBall.y += ySpeed;
        }
    }

}
