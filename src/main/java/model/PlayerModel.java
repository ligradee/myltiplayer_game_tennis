package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.BitSet;

public class PlayerModel{

    public int x, y, width = 110, height = 50;
    public int xSpeed = 0;
    public Rectangle hitBoxPlayer;

    public PlayerModel(int num){
        if (num == 1) {
            this.y = 550;
        }
        if (num == 2) {
            this.y = 0;
        }
        this.x = 225;

        hitBoxPlayer = new Rectangle(this.x, this.y, width, height);


    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void set(){
        if ((xSpeed > 0) & (x < 425)) {
            x += xSpeed;
            hitBoxPlayer.x += xSpeed;
        }
        if ((xSpeed < 0) & (x > 24)) {
            x += xSpeed;
            hitBoxPlayer.x += xSpeed;
        }
    }
}
