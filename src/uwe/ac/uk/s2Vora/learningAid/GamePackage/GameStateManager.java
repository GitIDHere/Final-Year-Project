package uwe.ac.uk.s2Vora.learningAid.GamePackage;

public class GameStateManager {

    private int currentState;

    public static final int numOfGameStates = 2;
    public static final int MENUSTATE = 0;
    public static final int PLAYSTATE = 1;

    private final GameState[] gameStates;

    private static GameStateManager gameStateManagerInstance;
    
    /*
        This class was also part of the tutorial which I learnt.
        Author: ForeignGuyMike
        Available from: https://www.youtube.com/watch?v=9dzhgsVaiSo
    */
    
    
    public static GameStateManager getgameStateManagerInstance() {

        if (gameStateManagerInstance == null) {
            gameStateManagerInstance = new GameStateManager();
        }
        return gameStateManagerInstance;
    }
    
    
    private GameStateManager() {
        gameStates = new GameState[numOfGameStates];
        currentState = MENUSTATE;
        loadState(currentState);
    }
    
    //Based on the integer value passed within its parameter, load either the menu or GameLevel.
    private void loadState(int state) {
        if (state == MENUSTATE) {
            gameStates[state] = new MainMenu(this);
        } else if (state == PLAYSTATE) {
            gameStates[state] = GameLevel.getGameLevelInstance();
        }
    }
    
    //Remove the current state that is being displayed.
    private void unloadState(int state) {
        gameStates[state] = null;
    }
    
    //Load a state to be displayed within the GamePanel GUI.
    public void setState(int state) {
        unloadState(currentState);
        currentState = state;
        loadState(currentState);
    }
    
    //Update the GameStates
    public void update() {
        try {
            gameStates[currentState].update();
        } catch (Exception e) {
        }
    }
    
    //Draw the GameStates
    public void draw(java.awt.Graphics2D g) {
        try {
            gameStates[currentState].draw(g);
        } catch (Exception e) {
        }
    }

    public void keyPressed(int k) {
        gameStates[currentState].keyPressed(k);
    }

    public void keyReleased(int k) {
        gameStates[currentState].keyReleased(k);
    }
}
