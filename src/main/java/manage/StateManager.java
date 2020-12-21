package manage;


import java.util.ArrayList;

public class StateManager {
    private int currentState;

    public static final int menuState = 0;
    public static final int playState = 1;
    public static final int helpState = 2;
    public static final int chooseState = 3;

    public StateManager() {
        currentState = menuState;
    }


}
