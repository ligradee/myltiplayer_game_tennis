package view;

import manage.GameState;
import manage.StateManager;
import network.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

public class PlayWithRealPlayer extends GameState {
    private Background bg;
    private PlayerState player1;
    private PlayerState player2;
    private Ball ball;

    private ServerSocket client;
    private String name;
    private String freeTables = "";
    private int currentChoice = 0;
    private ArrayList<String> tableOptions;
    private int flag;
    private String nameMain;
    private String ballFlag = "false";
    private int start = 0;

    public PlayWithRealPlayer(StateManager s) {
        this.sManager = s;
        int pos = (new Random()).nextInt(14);
        name = "Player"+String.valueOf(pos);
        String[] options = {"localhost", "9876", name};
        try {
            bg = new Background("/court.png");
            tableOptions = new ArrayList<String>();
            client = new ServerSocket(options);
            client.load(options);
            client.send(name + " connected");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(sManager.game);

        setFlag(sManager.game);
        player1 = new PlayerState(1);
        player2 = new PlayerState(0);
        ball = new Ball(player1.getModel().getX(), player1.getModel().getY());

    }


    @Override
    public void init() {

    }

    public void update() {
        flag = sManager.game;
        player1.getModel().set();
        player2.getModel().set();

        if (start == 0){
            if (flag == 0){
                ball.getModel().reconstructor(player2.getModel().getX(), player2.getModel().getY()+70);
            }

            start++;
        }

        if(flag == 1) {
            nameMain = "@new";

        }
        if(flag == 0) {
            nameMain ="@add";
            //ball.getModel().set(player2.getModel().getX(), player2.getModel().getY()+70);
        }
        tableOptions.clear();
        freeTables = "";

        freeTables = client.receivedMessage;
        //System.out.println(freeTables);
        if (flag == 1) {
            client.send(nameMain + " X " + String.valueOf(player1.getModel().xSpeed) + " " + ball.getModel().flag + " " + String.valueOf(ball.getModel().getX()) + " " + String.valueOf(ball.getModel().getY()));
        }
        else{
            client.send(nameMain + " X " + String.valueOf(player1.getModel().xSpeed));
        }
        ball.getModel().set(player1.getModel().getX(), player1.getModel().getY());
        if (!freeTables.equals("")){
            if ((freeTables.contains("X") == true) & (!freeTables.split(" ")[0].trim().equals(nameMain))){
                //System.out.println("meow");
                player2.getModel().xSpeed = - Integer.parseInt(freeTables.split(" ")[2].trim());
                if (flag == 0){
                    ball.getModel().set2(495 - Integer.parseInt(freeTables.split(" ")[4].trim()), 600 -Integer.parseInt(freeTables.split(" ")[5].trim()));
                }


            }

        }


        String[] buf = freeTables.split("&");
        tableOptions.addAll(Arrays.asList(buf));
        tableOptions.remove(tableOptions.size()-1);
        tableOptions.add("+ New table");

        //game model

        if (player2.getModel().hitBoxPlayer.intersects(ball.getModel().hitBoxBall) == true){
            ball.getModel().ySpeed = ball.getModel().ySpeed * -1;

        }
        if (player1.getModel().hitBoxPlayer.intersects(ball.getModel().hitBoxBall) == true){
            if (ball.getModel().flag == true){
                ball.getModel().ySpeed = ball.getModel().ySpeed * -1;
            }
        }
        //client.send("ball " + String.valueOf(ball.getModel().flag) + " " + String.valueOf(ball.getModel().xSpeed)+ " " + String.valueOf(ball.getModel().ySpeed));
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        player1.draw(g);
        player2.draw(g);
        ball.draw(g);

    }

    public void select(){

    }

    public void setFlag(int flag){
        this.flag = flag;
        if(flag == 1) {
            client.send("@new");
        }
        if(flag == 0) {
            client.send("@add");
        }
    }

    public void keyPressed(int key) {
        if(key == KeyEvent.VK_ESCAPE){
            sManager.setState(StateManager.pauseState);
        }
        if(key == KeyEvent.VK_RIGHT) {
            player1.getModel().xSpeed = 3;
        }
        if(key == KeyEvent.VK_LEFT) {
            player1.getModel().xSpeed = -3;
            if (key == KeyEvent.VK_ENTER)
                select();
        }
        if(key == KeyEvent.VK_SPACE) {
            ballFlag = "true";
            ball.getModel().flag = true;
            ball.getModel().ySpeed = -5;
            ball.getModel().xSpeed = 3;
            //client.send("ball " + String.valueOf(ball.getModel().flag) + " " + String.valueOf(ball.getModel().xSpeed)+ " " + String.valueOf(ball.getModel().ySpeed));
        }
    }

    public void keyReleased(int key) {
        if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            player1.getModel().xSpeed = 0;
        }
    }
}
