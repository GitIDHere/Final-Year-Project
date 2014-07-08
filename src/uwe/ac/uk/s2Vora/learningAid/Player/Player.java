package uwe.ac.uk.s2Vora.learningAid.Player;

import uwe.ac.uk.s2Vora.learningAid.Items.Item;
import uwe.ac.uk.s2Vora.learningAid.Animation.Animation;
import uwe.ac.uk.s2Vora.learningAid.TileMap.TileMap.*;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import uwe.ac.uk.s2Vora.learningAid.EndZone.EndZone;
import uwe.ac.uk.s2Vora.learningAid.Entity.Entity;
import uwe.ac.uk.s2Vora.learningAid.Main.LearningConfig;
import uwe.ac.uk.s2Vora.learningAid.TileMap.Tile;
import uwe.ac.uk.s2Vora.learningAid.UserInterface.ErrorPanel;

public class Player extends MapObject implements MoveCommands {

    //movements
    private boolean isMoving = false;
    private boolean canPlayerMove = false;
    private final double moveAmt;

    //Stop execution
    private  boolean shouldPlayerMove = true;
    
    //Animation
    private final int[] numFrames = {1};
    private ArrayList<BufferedImage[]> sprites;
    private Animation animation;
    public final int IDLE = 0;
    public final int SCRATCH = 6;
    
   private Item itemInfront = null;
   private double entitiyTileX = 45;
   private double entitiyTileY = 100; 

    //Is this creating a memory Leak?
    private double[] playerMapPosision = null;

    private EndZone endZone;
    
    private static Player playerInstance;

    public static Player getPlayerInstance() {

        if (playerInstance == null) {
            playerInstance = new Player();
        }
        return playerInstance;
    }
    
    /*
    This class was also adapted from the tutorial I used to learn to implement graphics.
    Author: ForeignGuyMike
    Available from: https://www.youtube.com/watch?v=zUOkojY_Ylo
    Accessed 20 November 2013
    */
    
    private Player(){
        width = 30;
        height = 30;
        cwidth = 20;
        cheight = 20;       

        moveSpeed = 2;
        moveAmt = 30;
        
        //Create an instance of the EndZone class, which will be used to display the end zome
        // the user has to reach to complete level 1 and 2.
        endZone = new EndZone();
        
        sprites = new ArrayList<BufferedImage[]>();
        playerMapPosision = new double[2];
        animation = new Animation();
        
        //Load the robot sprite
        final LearningConfig config = LearningConfig.getInstance();
        animation.loadSprites(sprites, config.getProperty("playerSprites"), numFrames, 1);
        
        //Set which tile the robot is currently facing towards.
        setFrontTileType();
        
        //Set its animation
        currentAnimation = IDLE;
        animation.setFrames(sprites.get(IDLE));
        animation.setDelay(400);
    }
    
    //A method within which a boolean variable is set to indicate whether the robot sprite
    // is allowed to move or not.
    public void canPlayerMove(boolean move){
        shouldPlayerMove = move;
    }
    
    //A method used to set the values of the X and Y cooridnates of the robot sprite.
    private void moveCharacter(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    //This method is invoked within the moveForwards and moveBackwards method.
    // This method is used to execute the movement of the robot sprite.
    private synchronized void setMovementCommands(String moveDirection, int tilesToMove) {
        
        //Only move the sprite when the collision variable is false, and shouldPlayerMove variables is true.
        if(collision == false && shouldPlayerMove == true){
            
            //A For loop is used to iterate over how many tiles the robot should move.
            for (int i = 0; i < tilesToMove; i++) {
                
                //Retreive an array contaning the destination coordinate for the robot sprite for one tile.
                playerMapPosision = setMovement(moveDirection);
                
                xdest = playerMapPosision[0];
                ydest = playerMapPosision[1];
                
                //Change the Player class's map position within the Entitiy class, since it is about to move.
                entity.setEntityMapPos((int)x, (int)y, (int)xdest, (int)ydest, Entity.PLAYER);
                
                canPlayerMove = true;
                isMoving = true;
                
                //hold the for loop at its current iteration until the robot sprite has reaches its destination tile.
                threadWait();
                //Check if player has reached the end zone.
                endZone.hasPlayerReachedZone(x, y);
                setFrontTileType();
            }
        }
    }

    //Based on the currentAngle value, set the frontTile of the robot sprite.
    protected void setFrontTileType(){
        switch (currentAngle) {
            case 90:
            case -270:
                frontTile = rightTileType;
                isFacingRight = true;
                break;
            case 180:
            case -180:
                frontTile = bottomTileType;
                isFacingDown = true;
                break;
            case 270:
            case -90:
                frontTile = leftTileType;
                isFacingLeft = true;
                break;
            default:
                frontTile = topTileType;
                isFacingUp = true;
        }

    }
    
    //This method is used to calucate the destination coordinates which the robot sprite must move towards.
    private double[] setMovement(String moveDirection) {

        double[] tempArray = new double[2];

        switch (currentAngle) {
            case 90:
            case -270:
                tempArray[0] = (moveDirection.equals("forwards"))? x + moveAmt: x - moveAmt;
                tempArray[1] = y;
                break;
            case 180:
            case -180:
                tempArray[0] = x;
                tempArray[1] = (moveDirection.equals("forwards"))? y + moveAmt: y - moveAmt;
                break;
            case 270:
            case -90:
                tempArray[0] = (moveDirection.equals("forwards"))? x - moveAmt: x + moveAmt;
                tempArray[1] = y;
                break;
            default:
                tempArray[0] = x;
                tempArray[1] = (moveDirection.equals("forwards"))? y - moveAmt: y + moveAmt;
        }
        
        return tempArray;
    }

    //This is a helper method which is used to incrementally move the robot in a specific direction.
    private synchronized void updatePosition() {

        if (canPlayerMove == true && collision == false) {
            
            if (y > ydest) {
                moveCharacter(x, y - moveSpeed);
                
            } else if (y < ydest) {
                moveCharacter(x, y + moveSpeed);
                
            } else if (x > xdest) {
                moveCharacter(x - moveSpeed, y);
                
            } else if (x < xdest) {
                moveCharacter(x + moveSpeed, y);
                
            } else if (currentAngle < newAngle) {
                currentAngle += 1;
                
            } else if (currentAngle > newAngle) {
                currentAngle -= 1;
                
            } else if (y <= ydest && y >= ydest && x >= xdest && x <= xdest && currentAngle <= newAngle && currentAngle >= newAngle) {
                isMoving = false;
                canPlayerMove = false;
                
                //When the robot sprite has reached its destination, the For loop is initaited once again.
                notifyAll();
            }
        }

        if (collision == true) {
            notifyAll();
            isMoving = false;
            canPlayerMove = false;
        }

    }
    
    //The update method of the Player class.
    public synchronized void update() {
        
        //Check the collision of the between the robot and the walls.
        checkTileMapCollision();
        updatePosition();

        //Animation
        if (currentAnimation == SCRATCH) {
            if (animation.hasPlayedOnce()) {
                isScratching = false;
                notifyAll();
            }
        }

        if (isScratching == true) {
            if (currentAnimation != SCRATCH) {
                currentAnimation = SCRATCH;
                animation.setFrames(sprites.get(SCRATCH));
                animation.setDelay(50);
                width = 60;
            }
        } else if (currentAnimation != IDLE) {
            currentAnimation = IDLE;
            animation.setFrames(sprites.get(IDLE));
            animation.setDelay(400);
            width = 30;
        }

        animation.update();
    }
    
    //Draw the robot sprite as well as the end zone.
    public void draw(Graphics2D g) {
        endZone.draw(g);
        
        //draw Player
        BufferedImage image = animation.getImage();
        AffineTransform at = new AffineTransform();
        at.translate((int) (x - width / 2), (int) (y - height / 2));
        at.rotate(Math.toRadians(currentAngle), image.getWidth() / 2, image.getHeight() / 2);
        g.drawImage(image, at, null);
    }
    
    //Check if the robot has not exceeded +360 and -360 rotation.
    private void checkRotationOutOfBounds() {
        if (newAngle >= 361) {
            currentAngle = 0;
            newAngle = 90;
        } else if (newAngle <= -361) {
            currentAngle = 0;
            newAngle = -90;
        }
    }

    //These next four methods are the methods which the user uses to move the robot.
    
    @Override
    public void moveForwards(int tilesToMove) {
            setMovementCommands("forwards", tilesToMove);
    }

    @Override
    public void moveBackwards(int tilesToMove) {
            setMovementCommands("backwards", tilesToMove);
    }

    @Override
    public synchronized void turnRight() {
        canPlayerMove = true;
        newAngle += 90;
        resetDirectionFacing();
        checkRotationOutOfBounds();
        threadWait();
        setFrontTileType();
    }

    @Override
    public synchronized void turnLeft() {
        canPlayerMove = true;
        resetDirectionFacing();
        newAngle -= 90;
        checkRotationOutOfBounds();
        threadWait();
        setFrontTileType();
    }

    private void threadWait() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    //This method was implmented to be used within the While loop of the learning aid system.
    // This method checks if any one of the tiles in front of the robot contains a Hidden item object.
    public boolean areItemsInfront() {
        boolean areItemsInFront = setRightTileTypes(x, y);
        return (frontTile == Tile.EMPTY && areItemsInFront == true);
    }
    
   //Based on which way the robot is facing, set the coordinates that will be passed into the Entity class.
   private void setEntityTiles(){
        if(isFacingRight == true){
            entitiyTileX = x + tileSize;
            entitiyTileY = y;
        }else if(isFacingLeft == true){
            entitiyTileX = x - tileSize;
            entitiyTileY = y;
        }else if(isFacingDown == true){
            entitiyTileX = x;
            entitiyTileY = y + tileSize;
        }else if(isFacingUp == true){
            entitiyTileX = x;
            entitiyTileY = y - tileSize;
        }
   }
   
   //This method is used to reveal the hidden item in front of the robot.
    public void revealItemInfront() {

        //set the coordinates which will be used within the Entitiy class.
        setEntityTiles();
        
        boolean isEntitiyOnTile = entity.isTileEntity((int) entitiyTileX, (int) entitiyTileY);
        
        //If there is a Hidden Item in front of the robot, then invoke its reveal method.
        // else display an error to the user.
        if (isEntitiyOnTile == true) {
            itemInfront = entity.getEntity((int) entitiyTileX, (int) entitiyTileY);
            itemInfront.reveal();
        }else{
            //Send error to say that there is no item in front of robot.
            ErrorPanel errorPanel = ErrorPanel.getErrorPanelInstance();
            errorPanel.setErrorMessage("ERROR: No item in front of the robot");
        }
    }

    //The method that is used to get the item in front of the robot.
    public Item getItemInfront() {
        
        boolean isEntitiyOnTile = entity.isTileEntity((int) entitiyTileX, (int) entitiyTileY);
        
        if (isEntitiyOnTile == true) {
            return itemInfront;
        }else{
            //Send error to say that there is no item in front of robot.
            ErrorPanel errorPanel = ErrorPanel.getErrorPanelInstance();
            errorPanel.setErrorMessage("ERROR: No item in front of the robot");
            return null;
        }
    }

    public synchronized void scratch() {
        isScratching = true;
        threadWait();
    }

}
