package view;

import model.BallModel;
import model.BotModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
public class Ball {

    private BufferedImage ball;
    private BallModel model;

    public Ball(int x, int y) {
        try {

            ball = ImageIO.read(getClass().getResourceAsStream("/ball.png"));

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        model = new BallModel(x, y);

    }

    public BallModel getModel(){
        return model;
    }

    public void draw(Graphics2D g) {
        g.drawImage(ball, model.getX(), model.getY(), 70, 70, null);
    }
}
