package view;
import manage.StateManager;
import model.PlayerModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.*;

public class PlayerState {

    private PlayerModel model;
    private BufferedImage person;

    public PlayerState(int playerNumber) {
        try {
            if (playerNumber == 0){
                person = ImageIO.read(getClass().getResourceAsStream("/person2.png"));
            }
            if (playerNumber == 1){
                person = ImageIO.read(getClass().getResourceAsStream("/person.png"));
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        model = new PlayerModel(playerNumber);

    }

    public void setForm(int playForm) throws IOException {
        if (playForm == 0){
            person = ImageIO.read(getClass().getResourceAsStream("/person2.png"));
        }
        if (playForm == 1){
            person = ImageIO.read(getClass().getResourceAsStream("/person.png"));
        }
        if (playForm == 2){
            person = ImageIO.read(getClass().getResourceAsStream("/person3.png"));
        }
        if (playForm == 3){
            person = ImageIO.read(getClass().getResourceAsStream("/person4.png"));
        }
        if (playForm == 4){
            person = ImageIO.read(getClass().getResourceAsStream("/personNewYear.png"));
        }
    }

    public PlayerModel getModel(){
        return model;
    }

    public void draw(Graphics2D g) {
        g.drawImage(person, model.getX(), model.getY(), 150, 100, null);
    }




}








