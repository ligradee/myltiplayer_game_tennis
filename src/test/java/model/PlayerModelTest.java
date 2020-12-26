package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerModelTest {
    private PlayerModel player = new PlayerModel(1);

    @Test
    void getX() {
        int expected = 225;
        int actual = player.getX();
        assertEquals(expected, actual);
    }

    @Test
    void getY() {
        int expected = 550;
        int actual = player.getY();
        assertEquals(expected, actual);
    }

    @Test
    void set() {
        player.set();
        int expected = 225;
        int actual = player.getX();
        assertEquals(expected, actual);

    }
}