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
            System.out.println("meow");
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
        if(client.receivedMessage.trim().equals("newTable"))
            init();
        sManager.setState(StateManager.playGameState);
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        player.draw(g);

    }

    public void select(){
        if(currentChoice == tableOptions.size()-1) {
            client.send("@new");
            init();
        }
    }

    public void setFlag(int flag){
        this.flag = flag;
        if(flag == 1) {
            client.send("@new");
            init();
        }
    }

    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ESCAPE){
            sManager.setState(StateManager.menuState);
        }
        if(k == KeyEvent.VK_ENTER)
            select();
        if(k == KeyEvent.VK_UP) {
            currentChoice--;
            if(currentChoice == -1)
                currentChoice = tableOptions.size() - 1;
        }
        if(k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if(currentChoice == tableOptions.size())
                currentChoice = 0;
        }
    }

    public void keyReleased(int k) {

    }
}
