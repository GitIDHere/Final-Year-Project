package uwe.ac.uk.s2Vora.learningAid.GamePackage;

import uwe.ac.uk.s2Vora.learningAid.Items.PlaceItems;
import uwe.ac.uk.s2Vora.learningAid.Player.Player;
import uwe.ac.uk.s2Vora.learningAid.Items.Item;
import uwe.ac.uk.s2Vora.learningAid.TileMap.TileMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import uwe.ac.uk.s2Vora.learningAid.Bag.Bag;
import uwe.ac.uk.s2Vora.learningAid.Entity.Entity;
import uwe.ac.uk.s2Vora.learningAid.Items.ItemCollect;
import uwe.ac.uk.s2Vora.learningAid.Main.LearningConfig;
import uwe.ac.uk.s2Vora.learningAid.UserInterface.TabPanels;

public class GameLevel extends GameState {
    
    public static int currentLevel;

    private PlaceItems itemsInGame;
    private ArrayList<Item> itemsArray;

    private int playerStartX;
    private int playerStartY;
    
    private TileMap tileMap;
    private Player playerInstance;
    private static GameLevel gamelevlInstance;
    private TabPanels tabs;
    private ItemCollect itemsToCollect;
    
    private Entity entity;
    
    /*
    This class was adapted from the tutorial I used to learn to display graphics within the GamePanel GUI.
    Author: ForeignGuyMike
    Available from: https://www.youtube.com/playlist?list=PL-2t7SM0vDfcIedoMIghzzgQqZq45jYGv
    Accessed: 02 November 2013
    */
    
    
    public static GameLevel getGameLevelInstance() {
        if (gamelevlInstance == null) {
            GameStateManager gsm = GameStateManager.getgameStateManagerInstance();
            gamelevlInstance = new GameLevel(gsm);
        }
        return gamelevlInstance;
    }

    //This class needs an instance of GameStateManager because it extends GameState.
    private GameLevel(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }
    
    //This method is used to initalise the necessary variables for displayin the first level.
    @Override
    public final void init() {
        try {
            
            currentLevel = 1;
            
            //Based on the current level load the instructions and code examples.
            tabs = TabPanels.getTabsPanel();
            tabs.setTabs(currentLevel);
            
            //Instantiate the ItemCollect class.
            itemsToCollect = ItemCollect.getItemsCollectInstance();
            
            final LearningConfig config = LearningConfig.getInstance();
            
            //instantiate and load the first tile map.
            tileMap = new TileMap(30);
            tileMap.loadTiles(config.getProperty("tileMap"));
            tileMap.loadMap("/Resources/Maps/level_"+currentLevel+".map");
            
            //set the robot's position
            playerStartX = 75;
            playerStartY = 195;
            
            //create an instance of the Player class.
            playerInstance = Player.getPlayerInstance();
            
            //Instantiate the Entity class.
            entity = Entity.getEntityInstance();
            entity.addEntity(playerStartX, playerStartY, Entity.PLAYER, playerInstance);
            
            //sets the position of the robot.
            playerInstance.setTileMap(tileMap);
            playerInstance.setStartPosition(playerStartX, playerStartY, false);
            
            //Create the items that will be placed on the level.
            itemsInGame = new PlaceItems();
            itemsArray = itemsInGame.getItems();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //This method is invoked to change to the next level. This method loads the map tiles
    // of the new level, as well as reseting the necessary variables for the next level.
    public void loadMap(String mapResourcePath, int newLevel, int playerX, int playerY){
        
        //Whilst a new level is being loaded, the player is restricted to move.
        playerInstance.canPlayerMove(false);

        currentLevel = newLevel;
        Bag.instanceCount = 0;

        itemsToCollect.reset();
        
        tabs.setTabs(currentLevel);
        
        entity.deleteAllEntities();
        
        //Load the next tile map.
        tileMap.loadMap(mapResourcePath);
        
        //re-load a new set of hidden items.
        itemsInGame = new PlaceItems();
        itemsArray = itemsInGame.getItems();
        
        //Reset the robot's position.
        playerStartX = playerX;
        playerStartY = playerY;
        playerInstance.setStartPosition(playerStartX, playerStartY, true);
    }
    
    //This method is invoked when the Reset button is clicked. This resets
    // the necessary variables so that the user can have another go.
    public void resetLevel(){
        
        //Reset the player position
        playerInstance.setStartPosition(playerStartX, playerStartY, true);
        
        //Reset the current count of the items collected.
        itemsToCollect.reset();
        
        //Reset and hide all the hidden items that have been revealed.
        for (int i = 0; i < itemsArray.size(); i++) {
            itemsArray.get(i).setPickedUp(false);
            itemsArray.get(i).setCurrentAnimation(Item.BLOCK);
        }
        
        //Enable the Player class to initate the robot's movement.
        playerInstance.canPlayerMove(true);
        
        //Reset the instances of the Bag class.
        Bag.instanceCount = 0;
    }
    
    public int getCurrentLevel(){
        return currentLevel;
    }

    //The update() method is used to update animations.
    @Override
    public void update() {
        //Updates the player sprite.
        playerInstance.update();
        
        //Update every image that is currently within the level.
        for (int i = 0; i < itemsArray.size(); i++) {
            if(itemsArray.get(i).isPickedUp() == false){
                itemsArray.get(i).updateItem();
            }
        }
    }
    
    //This draw method is used to draw graphics to the GamePanel GUI.
    @Override
    public void draw(Graphics2D g) {
        //Clear the Background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WindowThread.WIDTH * WindowThread.SCALE, WindowThread.HEIGHT * WindowThread.SCALE);
        
        //Draw the map tiles.
        tileMap.drawMap(g);
        playerInstance.draw(g);
        
        //for every item within the array, draw the item within the GamePanel GUI
        for (int i = 0; i < itemsArray.size(); i++) {
            if(itemsArray.get(i).isPickedUp() == false){
                itemsArray.get(i).drawItem(g);
            }
        }
    }

    @Override
    public void keyPressed(int k) {}

    @Override
    public void keyReleased(int k) {}
}
