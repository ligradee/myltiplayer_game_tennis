package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotModelTest {
    private BotModel bot = new BotModel();

    @Test
    void getX() {
        int expected = 225;
        int actual = bot.getX();
        assertEquals(expected, actual);
    }

    @Test
    void getY() {
        int expected = 0;
        int actual = bot.getY();
        assertEquals(expected, actual);
    }

    @Test
    void set() {
        bot.set();
        int expected = 225;
        int actual = bot.getX();
        assertEquals(expected, actual);
    }
}