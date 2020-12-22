package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerModel {

    public int x, y, width = 150, height = 100;
    public int xSpeed = 0;
    private Rectangle hitBox;

    public PlayerModel(int num){
        this.width = width;
        this.height = height;

        if (num == 1) {
            this.y = 550;
        }
        if (num == 2) {
            this.y = 0;
        }
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
        if ((xSpeed > 0) & (x < 425)) {
            x += xSpeed;
            hitBox.x += xSpeed;
        }
        if ((xSpeed < 0) & (x > 24)) {
            x += xSpeed;
            hitBox.x += xSpeed;
        }
    }
}
