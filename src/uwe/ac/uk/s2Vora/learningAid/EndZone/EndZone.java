package uwe.ac.uk.s2Vora.learningAid.EndZone;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import uwe.ac.uk.s2Vora.learningAid.Animation.Animation;
import uwe.ac.uk.s2Vora.learningAid.GamePackage.GameLevel;
import uwe.ac.uk.s2Vora.learningAid.GamePackage.NextLevel;
import uwe.ac.uk.s2Vora.learningAid.Main.LearningConfig;

public class EndZone {

    private ArrayList<BufferedImage[]> sprites;

    private final int width;
    private final int height;
    
    private Animation animation;
    
    private double x;
    private double y;
    
    //This is the class which is used to display the white box with a red square in the middle.
    public EndZone() {

        width = 30;
        height = 30;
        
        setPosition();
        
        animation = new Animation();
        sprites = new ArrayList<BufferedImage[]>();
        
        int[] numFrames = new int[1];
        numFrames[0] = 1;
        
        final LearningConfig config = LearningConfig.getInstance();
        animation.loadSprites(sprites, config.getProperty("endZone"), numFrames, 1);
    }
    
    //Set the position of the end zone within which it will be displayed.
    private void setPosition(){
        switch(GameLevel.currentLevel){
            case 1: 
                x = 315;
                y = 75;
                break;
            case 2:
                x = 195;
                y = 75;
                break;
        }
    }
    
    //This method is used to identify whether the player has reached to same position in the 
    // map as the end zone. If it has then move to the next level.
    public void hasPlayerReachedZone(double playerX, double playerY){
        if(playerX == x && playerY == y){
            NextLevel n = NextLevel.getNextLevelInstance();
            n.goToNextLevel(GameLevel.currentLevel+1);
            setPosition();
        }
    }
    
    //Only draws the end zone if the current level is 2 or less.
    public void draw(Graphics2D g) {
        if(GameLevel.currentLevel <= 2){
            setPosition();
        g.drawImage(sprites.get(0)[0],
                (int) (x - width / 2),
                (int) (y - height / 2),
                null);
        }
    }

}
