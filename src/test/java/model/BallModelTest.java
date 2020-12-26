package model;

import org.junit.jupiter.api.Test;
import view.Ball;

import static org.junit.jupiter.api.Assertions.*;

class BallModelTest {
    private BallModel ball = new BallModel(0,0);

    @Test
    void reconstructor() {

    }

    @Test
    void getX() {
        int expected = 40;
        int actual = ball.getX();
        assertEquals(expected, actual);
    }

    @Test
    void getY() {
        int expected = -20;
        int actual = ball.getY();
        assertEquals(expected, actual);
    }

    @Test
    void setFlag() {
        ball.setFlag();
        boolean expected = true;
        boolean actual = ball.flag;
        assertEquals(expected, actual);
    }

    @Test
    void set2() {
        ball.set2(15, 15);
        int expected1 = 55;
        int actual1 = ball.getX();
        assertEquals(expected1, actual1);
        int expected2 = -5;
        int actual2 = ball.getY();
        assertEquals(expected2, actual2);
    }
}