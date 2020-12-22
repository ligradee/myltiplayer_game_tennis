package model;

import view.PlayerState;

import java.awt.*;
import java.util.Random;

public class BallModel {

    private int score1;
    private int score2;
    private int speed = 3;
    private int x, y, width = 25, height = 25;
    private int motionX, motionY;
    private Rectangle hitBox;

   // private final Random random;
    private  int amountOfHits;

    public BallModel()
    {
        this.y = 0;
        this.x = 225;

        hitBox = new Rectangle(this.x, this.y, width, height);
        //this.random = new Random();
        //spawn();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /*private void spawn() {
        this.amountOfHits = 0;
        this.x = 700 / 2 - this.width / 2;
        this.y = 700 / 2 - this.height / 2;

        this.motionY = -2 + random.nextInt(4);

        if (motionY == 0)
        {
            motionY = 1;
        }

        if (random.nextBoolean())
        {
            motionX = 1;
        }
        else
        {
            motionX = -1;
        }
    }*/

    /*public void update(PlayerState player1, PlayerState player2)
    {
        this.x += motionX * speed;
        this.y += motionY * speed;

        if (this.y + height - motionY > 700 || this.y + motionY < 0)
        {
            if (this.motionY < 0)
            {
                this.y = 0;
                this.motionY = random.nextInt(4);

                if (motionY == 0)
                {
                    motionY = 1;
                }
            }
            else {
                this.motionY = -random.nextInt(4);
                this.y = 700- height;

                if (motionY == 0)
                {
                    motionY = -1;
                }
            }
        }

//        if (checkCollision(paddle1) == 1)
//        {
//            this.motionX = 1 + (amountOfHits / 5);
//            this.motionY = -2 + random.nextInt(4);
//
//            if (motionY == 0)
//            {
//                motionY = 1;
//            }
//
//            amountOfHits++;
//        }
//        else if (checkCollision(paddle2) == 1)
//        {
//            this.motionX = -1 - (amountOfHits / 5);
//            this.motionY = -2 + random.nextInt(4);
//
//            if (motionY == 0)
//            {
//                motionY = 1;
//            }
//
//            amountOfHits++;
//        }

//        if (checkCollision(paddle1) == 2)
//        {
//            paddle1.score++;
//            spawn();
//        }
//        else if (checkCollision(paddle2) == 2)
//        {
//            paddle2.score++;
//            spawn();
//        }
    }*/



}
