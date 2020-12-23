package model;
import view.Bot;

import java.awt.*;
import java.util.BitSet;


public class BotModel {

    public int x, y, width = 150, height = 47;
    public int xSpeed = 0;
    public Rectangle hitBox;

    public BotModel(int num){
        this.y = 0;
        this.x = 225;

        hitBox = new Rectangle(this.x, this.y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void set(){

        if ((x < 425)  & (x > 24)) {
            x += xSpeed;
            hitBox.x += xSpeed;

        }
        if ((x < 25) || (x > 424)) {
            xSpeed = xSpeed * -1;
            x += xSpeed;
            hitBox.x += xSpeed;
        }

    }

}
