package view;
import manage.GameState;
import manage.StateManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class SettingsState extends GameState {
    private Background bg;
    private Font font;
    private int currentState = 0;
    public int currentStatePerson = 0;
    private int nForm = 5;
    private BufferedImage playerForm1;
    private BufferedImage playerForm2;
    private BufferedImage playerForm3;
    private BufferedImage playerForm4;
    private BufferedImage playerForm5;


    private String[] variants = {
            "Choose",
            "Back"
    };
    public SettingsState(StateManager sManager) {

        this.sManager = sManager;
        try {
            bg = new Background("/settings.png");
            font = new Font("Uber Move", Font.BOLD , 30);

            playerForm1 = ImageIO.read(getClass().getResourceAsStream("/person2.png"));
            playerForm2 = ImageIO.read(getClass().getResourceAsStream("/person.png"));
            playerForm3 = ImageIO.read(getClass().getResourceAsStream("/person3.png"));
            playerForm4 = ImageIO.read(getClass().getResourceAsStream("/person4.png"));
            playerForm5 = ImageIO.read(getClass().getResourceAsStream("/personNewYear.png"));

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

    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setFont(font);
        for(int i = 0; i < variants.length; i++) {
            if(i == currentState) {
                g.setColor(Color.BLUE);
            }
            else {
                g.setColor(Color.WHITE);
            }
            g.drawString(variants[i], 240, 400 + i * 45);
        }
        if (currentStatePerson == 0){
            g.drawImage(playerForm1, 218, 230, 150, 100, null);
        }
        if (currentStatePerson == 1){
            g.drawImage(playerForm2, 218, 230, 150, 100, null);
        }
        if (currentStatePerson == 2){
            g.drawImage(playerForm3, 218, 230, 150, 100, null);
        }
        if (currentStatePerson == 3){
            g.drawImage(playerForm4, 218, 230, 150, 100, null);
        }
        if (currentStatePerson == 4){
            g.drawImage(playerForm5, 218, 230, 150, 100, null);
        }
    }

    private void select() {
        if(currentState == 0) {
            sManager.personFormState = currentStatePerson;
            System.out.println(sManager.personFormState);
        }
        if(currentState == 1) {
            sManager.setState(StateManager.menuState);
        }
    }

    @Override
    public void keyPressed(int key) {
        if(key == KeyEvent.VK_ENTER){
            select();
        }
        if(key == KeyEvent.VK_LEFT) {
            currentStatePerson--;
            if(currentStatePerson == -1) {
                currentStatePerson = 4;
            }
        }
        if(key == KeyEvent.VK_RIGHT) {
            currentStatePerson++;
            if(currentStatePerson == nForm) {
                currentStatePerson = 0;
            }
        }
        if(key == KeyEvent.VK_UP) {
            currentState--;
            if(currentState == -1) {
                currentState = variants.length - 1;
            }
        }
        if(key == KeyEvent.VK_DOWN) {
            currentState++;
            if(currentState == variants.length) {
                currentState = 0;
            }
        }
    }

    @Override
    public void keyReleased(int k) {

    }
}