package view;
import manage.GameState;
import manage.StateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayWithBotState extends GameState {
    private Background bg;
    private PlayerState person;
    private Bot bot;
    private Ball ball;

    private int x1, y1;
    private int score1 = 0;
    private int score2 = 0;
    private int yCheck = 0;


    public PlayWithBotState(StateManager sManager) {
        this.sManager = sManager;
        person = new PlayerState(1);
        bot = new Bot();

        x1 = person.getModel().getX();
        y1 = person.getModel().getY();
        ball = new Ball(x1, y1);

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
        ball.getModel().set(person.getModel().getX(), person.getModel().getY());
        if (bot.getModel().hitBox.intersects(ball.getModel().hitBoxBall) == true){
            ball.getModel().ySpeed = ball.getModel().ySpeed * -1;
        }
        if (person.getModel().hitBoxPlayer.intersects(ball.getModel().hitBoxBall) == true){
            if (ball.getModel().flag == true){
                ball.getModel().ySpeed = ball.getModel().ySpeed * -1;
            }
        }
        if (ball.getModel().getY() < 7){
            if (ball.getModel().getY() == yCheck){
                ball.getModel().ySpeed *=  -1;
            }
            yCheck = ball.getModel().getY();
        }
//        if (ball.getModel().getY() > 522){
//            if (ball.getModel().getY() == yCheck){
//                ball.getModel().ySpeed *=  -1;
//            }
//            yCheck = ball.getModel().getY();
//        }
        if (ball.getModel().getY() > 555){
            score1++;
            ball.getModel().set(bot.getModel().getX(), bot.getModel().getY()+70);
            ball.getModel().flag = true;
            ball.getModel().ySpeed = -5;
            ball.getModel().xSpeed = 3;
        }
        if (ball.getModel().getY() < 0){
            score2++;
        }
        if (ball.getModel().ySpeed < 0){
            bot.getModel().xSpeed = ball.getModel().xSpeed;
        }
        if (ball.getModel().getY() < 100 & ball.getModel().getX() > 410){
            bot.getModel().xSpeed = ball.getModel().xSpeed;
            if (bot.getModel().xSpeed < 0) bot.getModel().xSpeed*=-1;
        }
        if (ball.getModel().getY() < 550 & ball.getModel().getX() < 110){
            bot.getModel().xSpeed = ball.getModel().xSpeed;
            if (bot.getModel().xSpeed > 0) bot.getModel().xSpeed*=-1;
        }
        if ((score1 == 10) || (score2 == 10)){
            sManager.setState(StateManager.gameOverState);
        }




    }


    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        person.draw(g);
        bot.draw(g);
        ball.draw(g);
        String scoreBot = String.valueOf(score1) + " : " +  String.valueOf(score2);
        String scorePlayer = String.valueOf(score2) + " : " +  String.valueOf(score1);
        g.drawString(scoreBot, 270, 30);
        g.drawString(scorePlayer, 270, 660);
    }

    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_ESCAPE){
            sManager.setState(StateManager.pauseState);
        }
        if(key == KeyEvent.VK_RIGHT) {
            person.getModel().xSpeed = 3;
            if (ball.getModel().flag == false){
                ball.getModel().xSpeed = 3;
            }

        }
        if(key == KeyEvent.VK_LEFT) {
            person.getModel().xSpeed = -3;
            if (ball.getModel().flag == false){
                ball.getModel().xSpeed = -3;
            }

        }
        if(key == KeyEvent.VK_SPACE) {
            ball.getModel().flag = true;
            ball.getModel().ySpeed = -5;
            ball.getModel().xSpeed = 3;
        }
    }


    @Override
    public void keyReleased(int key) {
        if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            person.getModel().xSpeed = 0;
            bot.getModel().xSpeed = 0;
            if (ball.getModel().flag == false){
                ball.getModel().xSpeed = 0;
            }

        }
    }
}
