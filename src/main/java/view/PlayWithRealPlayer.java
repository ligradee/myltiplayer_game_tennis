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
    private boolean ballFlag = false;

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
        player1 = new PlayerState(0);
        player2 = new PlayerState(1);



    }

    public void init() {
        flag = sManager.game;
        if(flag == 1) {
            nameMain = "@new";
        }
        if(flag == 0) {
            nameMain ="@add";
        }
        tableOptions.clear();
        freeTables = "";

        freeTables = client.receivedMessage;
        client.send(nameMain + " X " + String.valueOf(player1.getModel().xSpeed));
        if (!freeTables.equals("") & freeTables.length() > 3){
            if ((freeTables.contains("X") == true) & (!freeTables.split(" ")[0].trim().equals(nameMain))){
                player2.getModel().xSpeed = Integer.parseInt(freeTables.split(" ")[2].trim());
            }
        }

        String[] buf = freeTables.split("&");
        tableOptions.addAll(Arrays.asList(buf));
        tableOptions.remove(tableOptions.size()-1);
        tableOptions.add("+ New table");
    }


    public void update() {
        player1.getModel().set();
        player2.getModel().set();
        flag = sManager.game;
        if(flag == 1) {
            nameMain = "@new";
        }
        if(flag == 0) {
            nameMain ="@add";
        }
        tableOptions.clear();
        freeTables = "";

        freeTables = client.receivedMessage;
        client.send(nameMain + " X " + String.valueOf(player1.getModel().xSpeed));
        if (!freeTables.equals("") & freeTables.length() > 3){
            if ((freeTables.contains("X") == true) & (!freeTables.split(" ")[0].trim().equals(nameMain))){
                player2.getModel().xSpeed = Integer.parseInt(freeTables.split(" ")[2].trim());
            }
        }

        String[] buf = freeTables.split("&");
        tableOptions.addAll(Arrays.asList(buf));
        tableOptions.remove(tableOptions.size()-1);
        tableOptions.add("+ New table");
        //sManager.setState(StateManager.playGameState);
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        player1.draw(g);
        player2.draw(g);

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
    }

    public void keyReleased(int key) {
        if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            player1.getModel().xSpeed = 0;
        }
    }
}
