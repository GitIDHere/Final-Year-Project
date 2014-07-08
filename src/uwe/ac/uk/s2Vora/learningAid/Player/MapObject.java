package uwe.ac.uk.s2Vora.learningAid.Player;

import uwe.ac.uk.s2Vora.learningAid.Entity.Entity;
import uwe.ac.uk.s2Vora.learningAid.TileMap.Tile;
import uwe.ac.uk.s2Vora.learningAid.TileMap.TileMap;

public abstract class MapObject {

    //tiles
    protected TileMap tileMap;
    protected int tileSize;

    //position and vector
    protected double x;
    protected double y;

    //dimensions
    protected int width;
    protected int height;

    //Rotation
    protected int newAngle = 0;
    protected int currentAngle = 0;

    //front Tile
    protected int frontTile;

    //tileType
    protected int tileType;

    //collision box
    protected int cwidth;
    protected int cheight;

    //column and row position
    protected int currRow;
    protected int currCol;

    //this is to hold the position of where we are going
    protected double xdest;
    protected double ydest;

    protected int topTileType;
    protected int bottomTileType;
    protected int rightTileType;
    protected int leftTileType;

    private int collDetTopTile;
    private int collDetBottomTile;
    private int collDetRightTile;
    private int collDetLeftTile;

    protected boolean isFacingRight = false;
    protected boolean isFacingLeft = false;
    protected boolean isFacingUp = false;
    protected boolean isFacingDown = false;

    //Animation
    protected int currentAnimation;
    protected boolean isScratching = false;
    protected boolean isIdle = true;
    
    //Check if colliding with objects
    protected boolean collision = false;

    //Movement attributes
    protected double moveSpeed; //How fast the player accelerates

    //test
    protected Entity entity;

    //Since this is not a constructor, it is invoked via a method from all the classes that use it.
    //This is used to check for collision and is a base class for all the entities.
    public void setTileMap(TileMap tm) {
        tileMap = tm;
        tileSize = tm.getTileSize();
        entity = Entity.getEntityInstance();
    }

    //RENAME THIS - IT SETS THE TYPES OF TYILES AROUND THE PLAYER
    protected boolean setRightTileTypes(double x, double y) {

        boolean isItemThere = false;
        
        double numx = 0;
        double numy = 0;
        
        currCol = (int) x / tileSize;
        currRow = (int) y / tileSize;
        
        int tileCount = 0;
        
        if(isFacingRight || isFacingLeft){
            //For some reason this works with + and -
            tileCount = 11 + currCol;
        }else if(isFacingUp || isFacingDown){
            tileCount = 6 + currRow;
        }
        
        for (int i = 1; i <= tileCount; i++) {

            if(isFacingRight) {
                 numx = x + (tileSize * i);
                 numy = y;
            }else if(isFacingLeft){
                 numx = x - (tileSize * i);
                 numy = y;
            }else if(isFacingUp){
                 numx = x;
                 numy = y - (tileSize * i);
            }else if(isFacingDown){
                 numx = x;
                 numy = y + (tileSize * i);
            }

            int isEntitiyOnTile = entity.getEntityType((int) numx, (int) numy);
            
            if (isEntitiyOnTile == 3) {
                isItemThere = true;
            }
        }
        
        return isItemThere;
    }

    protected void setPlayerEntity(int x, int y, Object player) {
        entity.addEntity(x, y, Entity.PLAYER, player);
    }

    private void setCollisionTiles(double x, double y) {

        currCol = (int) x / tileSize;
        currRow = (int) y / tileSize;

        int leftTile = (int) (x - cwidth / 2 - 1) / tileSize;
        int rightTile = (int) (x + cwidth / 2 - 1) / tileSize;
        int topTile = (int) (y - cheight / 2 - 1) / tileSize;
        int bottomTile = (int) (y + cheight / 2 - 1) / tileSize;

        collDetTopTile = tileMap.getTileType(topTile, currCol);
        collDetBottomTile = tileMap.getTileType(bottomTile, currCol);
        collDetRightTile = tileMap.getTileType(currRow, rightTile);
        collDetLeftTile = tileMap.getTileType(currRow, leftTile);
    }

    protected void checkTileMapCollision() {

        setCollisionTiles(xdest, ydest);

        if (collDetTopTile == Tile.OBSTACLE && collDetBottomTile == Tile.OBSTACLE) {
            System.out.println("Top Bottom");
            collision = true;
        } else if (collDetRightTile == Tile.OBSTACLE && collDetLeftTile == Tile.OBSTACLE) {
            System.out.println("Left Right");
            collision = true;
        }
    }

    protected void resetDirectionFacing() {
        isFacingRight = false;
        isFacingLeft = false;
        isFacingUp = false;
        isFacingDown = false;
    }

    public void setStartPosition(double xPos, double yPos, boolean reset) {

        if (reset == true) {
            //This wont do anything!!
            resetDirectionFacing();
            isFacingUp = true;
        }
        
        newAngle = 0;
        currentAngle = 0;
        collision = false;

        entity.setEntityMapPos((int) x, (int) y, (int) xPos, (int) yPos, Entity.PLAYER);

        x = xPos;
        y = yPos;

        xdest = xPos;
        ydest = yPos;
    }
}
