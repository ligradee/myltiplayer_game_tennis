package view;

import manage.GameState;
import manage.StateManager;
import network.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

public class PlayWithRealPlayer extends GameState {
    private Background bg;
    private PlayerState player;
    private ServerSocket client;
    private String name;
    private String freeTables = "";
    private int currentChoice = 0;
    private ArrayList<String> tableOptions;
    private int flag;



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
        if (sManager.game == 1){
            setFlag(1);
        }
        player = new PlayerState(1);

    }

    public void init() {
        tableOptions.clear();
        freeTables = "";
        while(!freeTables.contains("Table")) {
            client.send("@getTables");
            freeTables = client.receivedMessage;
        }
        String[] buf = freeTables.split("&");
        tableOptions.addAll(Arrays.asList(buf));
        tableOptions.remove(tableOptions.size()-1);
        tableOptions.add("+ New table");
    }

    public void init(int mod) {

    }

    public void update() {
        player.getModel().set();
        if(client.receivedMessage.trim().equals("newTable"))
            init();
        sManager.setState(StateManager.playGameState);
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        player.draw(g);

    }

    public void select(){

    }

    public void setFlag(int flag){
        this.flag = flag;
        if(flag == 1) {
            client.send("@new");
            init();
        }
    }

    public void keyPressed(int key) {
        if(key == KeyEvent.VK_ESCAPE){
            sManager.setState(StateManager.pauseState);
        }
        if(key == KeyEvent.VK_RIGHT) {
            player.getModel().xSpeed = 3;
//            if (ball.getModel().flag == false){
//                ball.getModel().xSpeed = 3;
//            }

        }
        if(key == KeyEvent.VK_LEFT) {
            player.getModel().xSpeed = -3;
//            if (ball.getModel().flag == false){
//                ball.getModel().xSpeed = -3;
//            }

        }
        if(key == KeyEvent.VK_ENTER)
            select();

    }

    public void keyReleased(int key) {
        if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            player.getModel().xSpeed = 0;
        }
    }
}
