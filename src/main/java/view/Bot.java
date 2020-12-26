package view;

import model.BotModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Bot {

    private BotModel model;
    private BufferedImage person;

    public Bot() {
        try {

            person = ImageIO.read(getClass().getResourceAsStream("/bot.png"));

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        model = new BotModel();

    }

    public BotModel getModel(){
        return model;
    }

    public void draw(Graphics2D g) {
        g.drawImage(person, model.getX(), model.getY(), 150, 100, null);
    }


}
